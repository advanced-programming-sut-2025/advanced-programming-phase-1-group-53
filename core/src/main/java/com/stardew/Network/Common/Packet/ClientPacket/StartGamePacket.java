package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class StartGamePacket extends Packet {
    public String lobbyId;
    public StartGamePacket(Player sender, String lobbyId) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
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
