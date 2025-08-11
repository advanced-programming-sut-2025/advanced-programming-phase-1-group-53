package com.stardew.Network.Common.Packet.ServerPacket;

import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class UpdateMapPacket extends Packet {

    public UpdateMapPacket(String senderId, String senderUsername) {
        super(senderId, senderUsername);
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.SERVER;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.UPDATE_MAP_PACKET;
    }
}

