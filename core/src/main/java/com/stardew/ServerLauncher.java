package com.stardew;

import com.stardew.Network.Server.ServerApp;
import com.stardew.Network.Server.ServerConnectionThread;

import java.util.Scanner;

public class ServerLauncher {
    public static void main(String[] args) {
        ServerApp app = ServerApp.getInstance();
        app.initializeServer();
        Scanner scanner = new Scanner(System.in);
        String input = "a";
        while ((input = scanner.nextLine()) != "q") {
            ServerConnectionThread connectionThread = app.getConnection("Player1");
            if (input.equalsIgnoreCase("server")) {
//                connectionThread.sendPacket(new ServerGeneralRespondPacket(true));
            }
        }
    }
}

