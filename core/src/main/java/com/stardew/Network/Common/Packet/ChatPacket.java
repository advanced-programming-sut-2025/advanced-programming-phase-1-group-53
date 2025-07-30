package com.stardew.Network.Common.Packet;

public class ChatPacket extends Packet {

    @Override
    public PacketType getType() {
        return PacketType.CHAT;
    }
}
