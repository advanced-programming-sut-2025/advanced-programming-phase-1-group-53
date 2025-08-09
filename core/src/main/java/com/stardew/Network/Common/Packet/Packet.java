package com.stardew.Network.Common.Packet;

public abstract class Packet {
    protected final String senderId;
    protected final String senderUsername;
    public Packet(String senderId, String senderUsername) {
        this.senderId = senderId;
        this.senderUsername = senderUsername;
    }
    public String getSenderId() {
        return senderId;
    }
    public String getSenderUsername() {
        return senderUsername;
    }
    public abstract PacketType getType();
    public abstract PacketSender getSender();
}

