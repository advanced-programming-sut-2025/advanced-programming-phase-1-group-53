package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class LeaveLobbyPacket extends Packet {
    public LeaveLobbyPacket(String senderId, String senderUsername) {
        super(senderId, senderUsername);
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.LEAVE_LOBBY_PACKET;
    }
}
