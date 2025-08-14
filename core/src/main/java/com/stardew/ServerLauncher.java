package com.stardew;

import com.stardew.Network.Server.ServerApp;
import com.stardew.Network.Server.ServerConnectionThread;

import java.util.Scanner;

public class ServerLauncher {
    public static void main(String[] args) {
        ServerApp app = ServerApp.getInstance();
        app.initializeServer();


    }
}

