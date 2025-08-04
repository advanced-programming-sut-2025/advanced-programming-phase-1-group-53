package com.stardew.Server;

import com.stardew.Network.Server.ServerApp;

public class ServerLauncher {
    public static void main(String[] args) {
        ServerApp app = ServerApp.getInstance();
        app.initializeServer();
    }
}
