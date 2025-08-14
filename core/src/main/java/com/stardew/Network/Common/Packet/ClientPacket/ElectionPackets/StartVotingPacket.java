package com.stardew.Network.Common.Packet.ClientPacket.ElectionPackets;

import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class StartVotingPacket extends Packet {
    public ElectionType type;
    public String username;
    public StartVotingPacket(Player sender, ElectionType type, String username) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.type = type;
        this.username = username;
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.START_VOTING_PACKET;
    }
}
