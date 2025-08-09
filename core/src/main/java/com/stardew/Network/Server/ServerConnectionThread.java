package com.stardew.Network.Server;

import com.stardew.Network.Common.ConnectionThread;
import com.stardew.Network.Common.Packet.*;

import java.io.IOException;
import java.net.Socket;

public class ServerConnectionThread extends ConnectionThread {
    private final ServerApp serverApp = ServerApp.getInstance();

    // حذف clientId از کانستراکتور، چون بعد از لاگین تنظیم می‌شود
    public ServerConnectionThread(Socket socket) throws IOException {
        super(socket, "");
    }

    @Override
    public boolean initialHandshake() {
        try {
            Packet pkt = PacketParser.readPacket(inputStream);
            if (!(pkt instanceof LoginPacket login)) {
                System.err.println("Expected LoginPacket but received " + (pkt == null ? "null" : pkt.getClass().getSimpleName()));
                return false;
            }
            this.clientId = login.getSenderId();
            System.out.println("Login packet received for clientId: " + clientId);

            ServerApp.getInstance().registerConnection(clientId, this);

            Packet welcome = new WelcomePacket("SERVER", "Welcome, your ID is ", clientId);
            sendPacket(welcome);
            System.out.println("Welcome packet sent for clientId: " + clientId);
            return true;
        } catch (IOException e) {
            System.err.println("Handshake failed: " + e.getMessage());
            return false;
        }
    }

    @Override
    protected boolean handlePacket(Packet packet) {
        System.out.println("Received packet from " + getClientId() + ": " + packet.getClass().getSimpleName());

        if (packet instanceof LoginPacket) {
            // نباید دوباره لاگین دریافت کنیم
            return true;
        } else if (packet instanceof MovePacket move) {
            serverApp.broadcastExcept(this, move);
            return true;
        } else if (packet instanceof ChatPacket chat) {
            serverApp.broadcast(chat);
            return true;
        } else {
            return false;
        }
    }
}
