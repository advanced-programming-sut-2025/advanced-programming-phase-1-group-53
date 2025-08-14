package com.stardew.Network.Common.Packet.ClientPacket.ElectionPackets;

import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class VotePacket extends Packet {
    public boolean vote;
    public VotePacket(Player sender, boolean vote) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.vote = vote;
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.VOTE_PACKET;
    }
}
