package com.stardew.Network.Common.Packet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PacketParser {
    private static final Gson gson = new Gson();

    private static final Map<PacketType, Class<? extends Packet>> packetClassMap = new HashMap<>();

    static {
        packetClassMap.put(PacketType.MOVE, MovePacket.class);
        packetClassMap.put(PacketType.CHAT, ChatPacket.class);
        packetClassMap.put(PacketType.LOGIN, LoginPacket.class);
        packetClassMap.put(PacketType.WELCOME, WelcomePacket.class);
    }

    private static Packet parse(String json) {
        try {
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            String typeStr = jsonObject.get("type").getAsString();
            PacketType type = PacketType.valueOf(typeStr);

            Class<? extends Packet> clazz = packetClassMap.get(type);
            if (clazz == null) {
                System.err.println("Unknown packet type: " + type);
                return null;
            }

            return gson.fromJson(jsonObject, clazz);
        } catch (Exception e) {
            System.err.println("Failed to parse packet: " + e.getMessage());
            return null;
        }
    }

    private static String toJson(Packet packet) {
        try {
            JsonObject jsonObject = gson.toJsonTree(packet).getAsJsonObject();
            jsonObject.addProperty("type", packet.getType().name());
            return gson.toJson(jsonObject);
        } catch (Exception e) {
            System.err.println("Failed to serialize packet: " + e.getMessage());
            return null;
        }
    }

    public static Packet readPacket(BufferedInputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = inputStream.read()) != -1) {
            if (ch == '\n') break; // پایان پکت
            sb.append((char) ch);
        }
        System.out.println("Read packet: " + sb.toString());
        if (sb.length() == 0) return null; // اتصال بسته یا داده‌ای نیست
        System.out.println("packet after parsing: " + PacketParser.parse(sb.toString()));
        return PacketParser.parse(sb.toString());
    }

    public static void writePacket(BufferedOutputStream outputStream, Packet packet) throws IOException {
        System.out.println("packet before json: " + packet.toString());
        String json = PacketParser.toJson(packet) + "\n";
        System.out.println("packet after json: " + json);
        outputStream.write(json.getBytes());
        outputStream.flush(); // حتما flush بشه تا داده ارسال بشه
        System.out.println("packet after flush: " + json);
    }

}
