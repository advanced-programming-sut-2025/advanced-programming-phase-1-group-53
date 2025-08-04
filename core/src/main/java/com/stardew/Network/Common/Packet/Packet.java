package com.stardew.Network.Common.Packet;

public abstract class Packet {
    protected String senderId;
    public Packet(String senderId) {
        this.senderId = senderId;
    }
    public String getSenderId() {
        return senderId;
    }
    public abstract PacketType getType();
}

