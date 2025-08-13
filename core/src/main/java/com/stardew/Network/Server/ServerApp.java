package com.stardew.Network.Server;

import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.*;

import static com.stardew.Models.Game.App.getGame;

public class ServerApp {
    private String serverIP;
    private int serverPort;
    private ServerListenerThread listenerThread;
    private volatile boolean exitFlag = false;
    // store active connections by client ID
    private final Map<String, ServerConnectionThread> connections = new ConcurrentHashMap<>();
    // thread pool for both initial packet handlers and persistent connection threads
    private final ExecutorService connectionThreadPool = Executors.newCachedThreadPool();
    /**
     * Add a new persistent connection thread to the pool.
     */
    public void addConnection(ServerConnectionThread conn) {
        connections.put(conn.getClientId(), conn);
        System.out.println("adding connection");
        connectionThreadPool.submit(conn);
        System.out.println("submitted connection");
    }

    /**
     * Remove and terminate an existing connection thread.
     */
    public void removeConnection(ServerConnectionThread conn) {
        if (connections.remove(conn.getClientId(), conn)) {
            conn.end();
        }
    }

    /**
     * Lookup an active connection thread by client ID.
     */
    public ServerConnectionThread getConnection(String clientId) {
        return connections.get(clientId);
    }

    /**
     * Broadcast a packet to all connected clients.
     */
    public void broadcast(Packet packet) {
        for (ServerConnectionThread conn : connections.values()) {
            conn.sendPacket(packet);
        }
    }

    public void broadcastInGame(Packet packet) {
        ArrayList<String> inGameClientIds = new ArrayList<>();
        for (Player player : getGame().getPlayers()) {
            inGameClientIds.add(player.personalInfo.getConnectionId());
        }
        for (ServerConnectionThread conn : connections.values()) {
            if (inGameClientIds.contains(conn.getClientId())) {
                conn.sendPacket(packet);
            }
        }
    }

    /**
     * Broadcast a packet to all except the sender.
     */
    public void broadcastExcept(ServerConnectionThread sender, Packet packet) {
        for (ServerConnectionThread conn : connections.values()) {
            if (conn != sender) {
                conn.sendPacket(packet);
            }
        }
    }

    public synchronized void initializeServer() {
        try {
            listenerThread = new ServerListenerThread(12345);
            this.serverIP = InetAddress.getLocalHost().getHostAddress();
            this.serverPort = listenerThread.getServerSocket().getLocalPort();
            System.out.println("Listening on " + this.serverIP + ":" + this.serverPort);
            listenerThread.start();
        } catch (IOException e) {
            initializeServer();
        }
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getServerIP() {
        return serverIP;
    }

    public ServerListenerThread getListenerThread() {
        return listenerThread;
    }

    private static ServerApp instance = null;
    private ServerApp() {}

    public static synchronized ServerApp getInstance() {
        if (instance == null) {
            instance = new ServerApp();
        }
        return instance;
    }

    public void exit() {
        exitFlag = true;
        connectionThreadPool.shutdown();
    }

    public boolean isEnded() {
        return exitFlag;
    }

    public Map<String, ServerConnectionThread> getConnections() {
        return connections;
    }

    public ExecutorService getConnectionThreadPool() {
        return connectionThreadPool;
    }

    public void registerConnection(String clientId, ServerConnectionThread conn) {
        ServerApp.getInstance().getConnections().put(clientId, conn);
        System.out.println("Registered connection for clientId=" + clientId);
    }

}
