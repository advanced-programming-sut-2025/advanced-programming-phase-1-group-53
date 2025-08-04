package com.stardew.Network.Server;

import com.stardew.Network.Common.Packet.LoginPacket;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketParser;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.*;

public class ServerApp {
    private String serverIP;
    private int serverPort;
    private ServerListenerThread listenerThread;

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

    private volatile boolean exitFlag = false;
    public void exit() {
        exitFlag = true;
        connectionThreadPool.shutdown();
    }
    public boolean isEnded() {
        return exitFlag;
    }

    // store active connections by client ID
    private final Map<String, ServerConnectionThread> connections = new ConcurrentHashMap<>();

    // thread pool for both initial packet handlers and persistent connection threads
    private final ExecutorService connectionThreadPool = Executors.newCachedThreadPool();

    /**
     * Add a new persistent connection thread to the pool.
     */
    public void addConnection(ServerConnectionThread conn) {
        connections.put(conn.getClientId(), conn);
        connectionThreadPool.submit(conn);
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
     * Handle first-packet logic: login or delegate to existing connection.
     */
    public void handleInitialPacket(Socket socket) {
        connectionThreadPool.submit(() -> {
            try (BufferedInputStream in = new BufferedInputStream(socket.getInputStream())) {
                Packet packet = PacketParser.readPacket(in);
                if (packet == null) return;

                if (packet instanceof LoginPacket login) {
                    String userId = login.username;
                    // Replace old connection if exists
                    ServerConnectionThread old = connections.put(userId, null);
                    if (old != null) old.end();

                    // Create and start new connection thread
                    ServerConnectionThread conn = new ServerConnectionThread(socket);
                    conn.setClientId(userId);
                    addConnection(conn);
                } else {
                    // Delegate non-login packet to existing connection
                    String senderId = packet.getSenderId();
                    ServerConnectionThread conn = getConnection(senderId);
                    if (conn != null) {
                        conn.enqueuePacket(packet);
                    } else {
                        // no existing connection: ignore or log
                        System.err.println("Received packet for unknown client: " + senderId);
                    }
                    socket.close();
                }
            } catch (IOException e) {
                System.err.println("Error handling initial packet: " + e.getMessage());
            }
        });
    }

    /**
     * Broadcast a packet to all connected clients.
     */
    public void broadcast(Packet packet) {
        for (ServerConnectionThread conn : connections.values()) {
            conn.sendPacket(packet);
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

    public void initializeServer() {
        try {
            listenerThread = new ServerListenerThread(0);
            this.serverIP = InetAddress.getLocalHost().getHostAddress();
            this.serverPort = listenerThread.getServerSocket().getLocalPort();
            System.out.println("Listening on " + this.serverIP + ":" + this.serverPort);
            listenerThread.start();
        } catch (IOException e) {
            initializeServer();
        }
    }
}
