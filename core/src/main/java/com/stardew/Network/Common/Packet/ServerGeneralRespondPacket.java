package com.stardew.Network.Common.Packet;

public class ServerGeneralRespondPacket extends Packet {
    public final boolean success;
    public ServerGeneralRespondPacket(boolean success) {
        super("SERVER", "SERVER");
        this.success = success;
    }

    @Override
    public PacketType getType() { return PacketType.ServerGeneralRespondPacket; }

    @Override
    public PacketSender getSender() { return PacketSender.SERVER; }
}
