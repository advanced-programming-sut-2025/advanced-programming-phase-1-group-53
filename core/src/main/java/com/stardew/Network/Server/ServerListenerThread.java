package com.stardew.Network.Server;

import com.stardew.Network.Common.ListenerThread;

import java.io.IOException;
import java.net.Socket;

/**
 * Server-side listener: reads first packet then delegates to ServerApp.
 */
public class ServerListenerThread extends ListenerThread {
    public ServerListenerThread(int port) throws IOException {
        super(port);
    }

    @Override
    protected void handleConnection(Socket socket) {
        // Delegate logic (login vs packet delegation) to ServerApp
        ServerApp.getInstance().handleInitialPacket(socket);
    }
}

