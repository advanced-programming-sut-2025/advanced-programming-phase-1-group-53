package com.stardew.Network.Common.Packet.ClientPacket.ElectionPackets;

import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class FinalizeElectionPacket extends Packet {
    public ElectionType electionType;
    public FinalizeElectionPacket(Player sender, ElectionType electionType) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.electionType = electionType;
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.FINALIZE_ELECTION_PACKET;
    }
}
