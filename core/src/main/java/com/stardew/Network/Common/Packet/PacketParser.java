package com.stardew.Network.Common.Packet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stardew.Network.Common.Packet.ClientPacket.*;
import com.stardew.Network.Common.Packet.ServerPacket.NPCDialoguePacket;
import com.stardew.Network.Common.Packet.ServerPacket.ServerGeneralRespondPacket;
import com.stardew.Network.Common.Packet.ServerPacket.UpdateMapPacket;
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
                .registerSubtype(LoginPacket.class, PacketType.LOGIN_PACKET.name())
                .registerSubtype(WelcomePacket.class, PacketType.WELCOME_PACKET.name())
                .registerSubtype(GiveFlowerPacket.class, PacketType.GIVE_FLOWER_PACKET.name())
                .registerSubtype(HuggingPacket.class, PacketType.HUGGING_PACKET.name())
                .registerSubtype(JoinLobbyPacket.class, PacketType.JOIN_LOBBY_PACKET.name())
                .registerSubtype(LeaveLobbyPacket.class, PacketType.LEAVE_LOBBY_PACKET.name())
                .registerSubtype(MarrigePacket.class, PacketType.MARRIAGE_PACKET.name())
                .registerSubtype(ReactionPacket.class, PacketType.REACTION_PACKET.name())
                .registerSubtype(RestartGamePacket.class, PacketType.RESTART_GAME_PACKET.name())
                .registerSubtype(SaveGamePacket.class, PacketType.SAVE_GAME_PACKET.name())
                .registerSubtype(SendPublicMessagePacket.class, PacketType.SEND_PUBLIC_MESSAGE_PACKET.name())
                .registerSubtype(SendPrivateMessagePacket.class, PacketType.SEND_PRIVATE_MESSAGE_PACKET.name())
                .registerSubtype(SignUpPacket.class, PacketType.SIGN_UP_PACKET.name())
                .registerSubtype(StartGamePacket.class, PacketType.START_GAME_PACKET.name())
                .registerSubtype(StartVotingPacket.class, PacketType.START_VOTING_PACKET.name())
                .registerSubtype(CreateLobbyPacket.class, PacketType.CREATE_LOBBY_PACKET.name())
                .registerSubtype(ServerGeneralRespondPacket.class, PacketType.SERVER_GENERAL_RESPOND_PACKET.name())
                .registerSubtype(UpdateMapPacket.class, PacketType.UPDATE_MAP_PACKET.name())
                .registerSubtype(ClickPacket.class, PacketType.CLICK_PACKET.name())
                .registerSubtype(KeyDownPacket.class, PacketType.KEY_DOWN_PACKET.name())
                .registerSubtype(KeyUpPacket.class, PacketType.KEY_UP_PACKET.name())
                .registerSubtype(MouseMovePacket.class, PacketType.MOUSE_MOVE_PACKET.name())
                .registerSubtype(TalkToNPCPacket.class, PacketType.TALK_TO_NPC_PACKET.name())
                .registerSubtype(TouchDownPacket.class, PacketType.TOUCH_DOWN_PACKET.name())
                .registerSubtype(GiftingPacket.class, PacketType.GIFTING_PACKET.name())
                .registerSubtype(NPCDialoguePacket.class, PacketType.NPC_DIALOGUE_PACKET.name());

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
