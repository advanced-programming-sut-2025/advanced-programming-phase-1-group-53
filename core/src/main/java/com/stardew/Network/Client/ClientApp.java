package com.stardew.Network.Client;

import com.stardew.Network.Common.Packet.Packet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientApp {
    private static ClientApp instance;
    private ClientConnectionThread connectionThread;

    private ClientApp() {}

    public static synchronized ClientApp getInstance() {
        if (instance == null) instance = new ClientApp();
        return instance;
    }

    /**
     * serverIP و serverPort و id را از آرگومان main یا config گرفته‌اید.
     */
    public synchronized void initializeClient(String serverIP, int serverPort, String id) {
        try {
            // ۱) اتصال به سرور
            Socket socket = new Socket(serverIP, serverPort);
            System.out.println("Connected to " + serverIP + ":" + serverPort);

            // ۲) ساخت و استارت ترد ارتباطی
            connectionThread = new ClientConnectionThread(socket, id);
            new Thread(connectionThread, "ClientConn-" + id).start();

            // از این پس می‌توانید connectionThread.sendPacket(...) بزنید
        } catch (IOException e) {
            System.err.println("Failed to connect/login: " + e.getMessage());
            // می‌توانید retry کنید یا خطا بدهید
        }
    }

    /** برای ارسال پکت از جاهای دیگر برنامه: */
    public void sendPacketToServer(Packet packet) {
        if (connectionThread != null) {
            connectionThread.sendPacket(packet);
        }
    }

    public ClientConnectionThread getConnectionThread() {
        return connectionThread;
    }
}
