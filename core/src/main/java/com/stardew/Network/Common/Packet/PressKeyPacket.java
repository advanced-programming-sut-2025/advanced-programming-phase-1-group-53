package com.stardew.Network.Common.Packet;

public class PressKeyPacket extends Packet {
    protected PressKeyPacket(String senderId, String senderUsername) {
        super(senderId, senderUsername);
    }

    @Override
    public PacketType getTypeEnum() {
        return null;
    }

    @Override
    public PacketSender getSender() {
        return null;
    }
}
