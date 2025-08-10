package com.stardew.Network.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread {
    private final ServerSocket serverSocket;

    public ServerListenerThread(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (!ServerApp.getInstance().isEnded()) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Server received connection from " + socket.getRemoteSocketAddress());
                ServerConnectionThread conn = new ServerConnectionThread(socket);
                // submit the connection runnable directly
                ServerApp.getInstance().getConnectionThreadPool().submit(conn);
                System.out.println("submitted connection runnable for " + socket.getRemoteSocketAddress());
            } catch (IOException e) {
                System.err.println("Listener error: " + e.getMessage());
                break;
            }
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}
