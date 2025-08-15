package com.stardew;

import com.stardew.Network.Server.ServerApp;
import com.stardew.Network.Server.ServerConnectionThread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.Scanner;

public class ServerLauncher {
    public static void main(String[] args) {
        ServerApp app = ServerApp.getInstance();
        app.initializeServer();
        try {
            URL url = new URL("https://api.ipify.org"); // یا https://checkip.amazonaws.com
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String publicIP = in.readLine();
            System.out.println("Public IP: " + publicIP);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String localIP = localHost.getHostAddress();
            System.out.println("Local IP: " + localIP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

