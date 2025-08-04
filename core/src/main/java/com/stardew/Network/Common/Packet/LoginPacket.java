package com.stardew.Network.Common.Packet;

public class LoginPacket extends Packet {
    public LoginPacket(String senderId) {
        super(senderId);
    }
    public String username;
    @Override
    public PacketType getType() { return PacketType.LOGIN; }
}

