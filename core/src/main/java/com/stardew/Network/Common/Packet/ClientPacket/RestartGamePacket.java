package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class RestartGamePacket extends Packet {
    public RestartGamePacket(String senderId, String senderUsername) {
        super(senderId, senderUsername);
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.RESTART_GAME_PACKET;
    }
}
