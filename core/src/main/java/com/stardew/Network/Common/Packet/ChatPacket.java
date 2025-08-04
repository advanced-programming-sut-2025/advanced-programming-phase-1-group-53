package com.stardew.Network.Common.Packet;

public class ChatPacket extends Packet {
    public ChatPacket(String senderId) {
        super(senderId);
    }

    @Override
    public PacketType getType() {
        return PacketType.CHAT;
    }
}
