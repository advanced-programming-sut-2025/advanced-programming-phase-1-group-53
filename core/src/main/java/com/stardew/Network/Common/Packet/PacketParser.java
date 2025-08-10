package com.stardew.Network.Common.Packet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stardew.Network.Common.Packet.ClientPacket.LoginPacket;
import com.stardew.Network.Common.Packet.ClientPacket.PressKeyPacket;
import com.stardew.Network.Common.Packet.ServerPacket.ServerGeneralRespondPacket;
import com.stardew.Network.Common.Packet.ServerPacket.WelcomePacket;
import com.stardew.Network.Common.RuntimeTypeAdapterFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class PacketParser {

    private static final Gson gson;

    static {
        // ایجاد Adapter برای پشتیبانی از همه انواع Packet
        RuntimeTypeAdapterFactory<Packet> packetAdapter =
            RuntimeTypeAdapterFactory.of(Packet.class, "type")
                .registerSubtype(LoginPacket.class, PacketType.LOGIN.name())
                .registerSubtype(WelcomePacket.class, PacketType.WELCOME.name())
                .registerSubtype(PressKeyPacket.class, PacketType.PRESSKEYPACKET.name())
                .registerSubtype(ServerGeneralRespondPacket.class, PacketType.SERVER_GENERAL_RESPOND_PACKET.name());

        gson = new GsonBuilder()
            .registerTypeAdapterFactory(packetAdapter)
            .create();
    }

    public static Packet parse(String json) {
        try {
            return gson.fromJson(json, Packet.class);
        } catch (Exception e) {
            System.err.println("Failed to parse packet: " + e.getMessage());
            return null;
        }
    }

    public static String toJson(Packet packet) {
        try {
            return gson.toJson(packet);
        } catch (Exception e) {
            System.err.println("Failed to serialize packet: " + e.getMessage());
            return null;
        }
    }

    public static Packet readPacket(BufferedInputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = inputStream.read()) != -1) {
            if (ch == '\n') break;
            sb.append((char) ch);
        }
        if (sb.length() == 0) return null;
        return parse(sb.toString());
    }

    public static void writePacket(BufferedOutputStream outputStream, Packet packet) throws IOException {
        String json = toJson(packet) + "\n";
        outputStream.write(json.getBytes());
        outputStream.flush();
    }
}
