package com.stardew.Network.Common.Packet.ServerPacket;

import com.google.gson.annotations.SerializedName;
import com.stardew.Models.Result;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class ServerGeneralRespondPacket extends Packet {
    public final Result result;

    @SerializedName("receivedPacket")
    private final Packet receivedPacket;

    public ServerGeneralRespondPacket(Result result, Packet receivedPacket) {
        super("SERVER", "SERVER");
        this.result = result;
        this.receivedPacket = receivedPacket;
    }

    public Packet getReceivedPacket() {
        return receivedPacket;
    }

    @Override
    public PacketType getTypeEnum() { return PacketType.SERVER_GENERAL_RESPOND_PACKET; }

    @Override
    public PacketSender getSender() { return PacketSender.SERVER; }
}

