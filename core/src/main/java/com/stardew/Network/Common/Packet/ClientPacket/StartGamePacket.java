package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class StartGamePacket extends Packet {
    public String lobbyId;
    public StartGamePacket(String senderId, String senderUsername, String lobbyId) {
        super(senderId, senderUsername);
        this.lobbyId = lobbyId;
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.START_GAME_PACKET;
    }
}
