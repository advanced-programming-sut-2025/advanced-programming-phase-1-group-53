package com.stardew.Network.Common.Packet;

public class LoginPacket extends Packet {
    public LoginPacket(String senderId) {
        super(senderId, "LOGIN");
    }
    @Override
    public PacketType getTypeEnum() { return PacketType.LOGIN; }

    @Override
    public PacketSender getSender() { return PacketSender.CLIENT; }
}

