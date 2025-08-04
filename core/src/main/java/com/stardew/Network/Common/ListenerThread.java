package com.stardew.Network.Common;

import com.stardew.Network.Server.ServerApp;
import com.stardew.Network.Server.ServerConnectionThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class ListenerThread extends Thread {
    protected final ServerSocket serverSocket;

    public ListenerThread(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (!ServerApp.getInstance().isEnded()) {
            try {
                Socket socket = serverSocket.accept();
                handleConnection(socket);
            } catch (IOException e) {
                System.err.println("Listener error: " + e.getMessage());
                break;
            }
        }
    }

    /**
     * Delegate new connection socket for processing.
     */
    abstract protected void handleConnection(Socket socket);

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}
