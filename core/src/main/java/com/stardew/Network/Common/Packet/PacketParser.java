package com.stardew.Network.Common.Packet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stardew.Models.Game.App;
import com.stardew.Models.Result;
import com.stardew.Network.Common.Packet.ClientPacket.AudioPackets.RequestAudioPacket;
import com.stardew.Network.Common.Packet.ClientPacket.AudioPackets.UploadAudioPacket;
import com.stardew.Network.Common.Packet.ClientPacket.ContactPackets.ReactionPacket;
import com.stardew.Network.Common.Packet.ClientPacket.ContactPackets.SendPrivateMessagePacket;
import com.stardew.Network.Common.Packet.ClientPacket.ContactPackets.SendPublicMessagePacket;
import com.stardew.Network.Common.Packet.ClientPacket.ElectionPackets.FinalizeElectionPacket;
import com.stardew.Network.Common.Packet.ClientPacket.ElectionPackets.StartVotingPacket;
import com.stardew.Network.Common.Packet.ClientPacket.ElectionPackets.VotePacket;
import com.stardew.Network.Common.Packet.ClientPacket.GamePackets.RestartGamePacket;
import com.stardew.Network.Common.Packet.ClientPacket.GamePackets.SaveGamePacket;
import com.stardew.Network.Common.Packet.ClientPacket.GamePackets.StartGamePacket;
import com.stardew.Network.Common.Packet.ClientPacket.IntractionPackets.*;
import com.stardew.Network.Common.Packet.ClientPacket.KeyboardPackets.*;
import com.stardew.Network.Common.Packet.ClientPacket.LobbyPackets.CreateLobbyPacket;
import com.stardew.Network.Common.Packet.ClientPacket.LobbyPackets.JoinLobbyPacket;
import com.stardew.Network.Common.Packet.ClientPacket.LobbyPackets.LeaveLobbyPacket;
import com.stardew.Network.Server.ChangeDurationPacket;
import com.stardew.Network.Common.Packet.ClientPacket.NPCPackets.TalkToNPCPacket;
import com.stardew.Network.Common.Packet.ClientPacket.RegisterPackets.LoginPacket;
import com.stardew.Network.Common.Packet.ClientPacket.RegisterPackets.SignUpPacket;
import com.stardew.Network.Common.Packet.ServerPacket.NPCDialoguePacket;
import com.stardew.Network.Common.Packet.ServerPacket.ServerGeneralRespondPacket;
import com.stardew.Network.Common.Packet.ServerPacket.UpdateMapPacket;
import com.stardew.Network.Common.Packet.ServerPacket.WelcomePacket;
import com.stardew.Network.Common.RuntimeTypeAdapterFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

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
                .registerSubtype(NPCDialoguePacket.class, PacketType.NPC_DIALOGUE_PACKET.name())
                .registerSubtype(UploadAudioPacket.class, PacketType.UPLOAD_AUDIO_PACKET.name())
                .registerSubtype(RequestAudioPacket.class, PacketType.REQUEST_AUDIO_PACKET.name())
                .registerSubtype(StartVotingPacket.class, PacketType.START_VOTING_PACKET.name())
                .registerSubtype(VotePacket.class, PacketType.VOTE_PACKET.name())
                .registerSubtype(FinalizeElectionPacket.class, PacketType.FINALIZE_ELECTION_PACKET.name())
                .registerSubtype(ChangeDurationPacket.class, PacketType.CHANGE_DURATION_PACKET.name());

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

    public static Result saveAudio(UploadAudioPacket packet) {
        String folderPath = packet.targetUsername + "Audio";
        String savePath = folderPath + "/" + packet.fileName;

        try {
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            byte[] fileData = java.util.Base64.getDecoder().decode(packet.base64Data);
            java.nio.file.Files.write(java.nio.file.Paths.get(savePath), fileData);
        } catch (Exception e ) {
            e.printStackTrace();
            return new Result(false, "Failed to write audio: " + e.getMessage());
        }
        return new Result(true, "Successfully saved audio");
    }

    public static UploadAudioPacket uploadAudio(String targetPlayerUsername, String fileName, String thisPlayerUsername) {
        String folderPath = "assets/" +targetPlayerUsername + "Audio";
        String savePath = folderPath + "/" + fileName;
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(savePath));
            String base64Data = Base64.getEncoder().encodeToString(fileBytes);
            return new UploadAudioPacket(App.getMyPlayer().personalInfo.getConnectionId(), App.getMyPlayer().personalInfo.getName(),
                fileName, base64Data, thisPlayerUsername);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
