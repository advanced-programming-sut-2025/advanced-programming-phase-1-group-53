package com.stardew.Network.Common.Packet;

import com.google.gson.annotations.SerializedName;

public abstract class Packet {
    protected final String senderId;
    protected final String senderUsername;

    protected Packet(String senderId, String senderUsername) {
        this.senderId = senderId;
        this.senderUsername = senderUsername;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getType() {
        return getTypeEnum().name();
    }
    public abstract PacketType getTypeEnum();


    public abstract PacketSender getSender();

}
