package com.stardew.Network.Common.Packet;

import com.google.gson.annotations.SerializedName;

public class ServerGeneralRespondPacket extends Packet {
    public final boolean success;

    @SerializedName("receivedPacket")
    private final Packet receivedPacket;

    public ServerGeneralRespondPacket(boolean success, Packet receivedPacket) {
        super("SERVER", "SERVER");
        this.success = success;
        this.receivedPacket = receivedPacket;
    }

    public Packet getReceivedPacket() {
        return receivedPacket;
    }

    @Override
    public PacketType getTypeEnum() { return PacketType.ServerGeneralRespondPacket; }

    @Override
    public PacketSender getSender() { return PacketSender.SERVER; }
}

