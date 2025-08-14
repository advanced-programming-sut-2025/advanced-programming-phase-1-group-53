package com.stardew.Models;

import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.ClientPacket.ContactPackets.SendPrivateMessagePacket;
import com.stardew.Network.Common.Packet.ClientPacket.ContactPackets.SendPublicMessagePacket;

import java.util.ArrayList;
import java.util.HashMap;

public class GameMessages {
    private final ArrayList<ChatMessage> publicChatMessages = new ArrayList<>();
    private final HashMap<String, ArrayList<ChatMessage>> privateChatMessages = new HashMap<>();
    public GameMessages() {
        Player player = App.getGame().getPlayers().get(0);
        for (Player player1 : App.getGame().getPlayers()) {
            if (player.equals(player1)) {
                continue;
            }
            ArrayList<ChatMessage> chatMessages = new ArrayList<>();
            this.getPrivateChatMessages().put(player1.personalInfo.getName() + ":" + player.personalInfo.getName(), chatMessages);
            this.getPrivateChatMessages().put(player.personalInfo.getName() + ":" + player1.personalInfo.getName(), chatMessages);
        }
    }

    public ArrayList<ChatMessage> getPublicChatMessages() {
        return publicChatMessages;
    }

    public HashMap<String, ArrayList<ChatMessage>> getPrivateChatMessages() {
        return privateChatMessages;
    }

    public static Result newPublicChatMessage(SendPublicMessagePacket packet) {
        try {
            return sendPublicChatMessage(packet);
        } catch (NullPointerException e) {
            return new Result(false, "could not send public chat message");
        }
    }

    public static Result sendPublicChatMessage(SendPublicMessagePacket packet) {
        App.getGame().getMessages().getPublicChatMessages().add(new ChatMessage(packet.getSenderUsername(), null, packet.message));
        return new Result(true, "message sent");
    }

    public static Result newPrivateChatMessage(SendPrivateMessagePacket packet) {
        try {
            return sendPrivateChatMessage(packet);
        } catch (NullPointerException e) {
            return new Result(false, "could not send public chat message");
        }
    }

    public static Result sendPrivateChatMessage(SendPrivateMessagePacket packet) {
        try {
            App.getGame().getMessages().getPrivateChatMessages().get(packet.getSenderUsername() + ":" +
                packet.receiverUsername).add(new ChatMessage(packet.getSenderUsername(), packet.receiverUsername, packet.message));
            return new Result(true, "message sent");
        } catch (NullPointerException e) {
            return new Result(false, "could not send private chat message");
        }
    }
}
