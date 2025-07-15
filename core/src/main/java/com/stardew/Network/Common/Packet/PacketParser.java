package com.stardew.Network.Common.Packet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PacketParser {
    private static final Gson gson = new Gson();

    private static final Map<PacketType, Class<? extends Packet>> packetClassMap = new HashMap<>();

    static {
        packetClassMap.put(PacketType.MOVE, MovePacket.class);
        packetClassMap.put(PacketType.CHAT, ChatPacket.class);
        // Add more mappings here as needed
    }

    public static Packet parse(String json) {
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
}
