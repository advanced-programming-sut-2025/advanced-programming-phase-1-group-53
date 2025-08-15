package com.stardew.Network.Common.Packet.ClientPacket.ContactPackets;

import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class ReactionPacket extends Packet {
    public final Reaction reaction;
    public final String receiverUsername;
    public final boolean isEmoji;

    public ReactionPacket(Player sender, Reaction reaction, String receiverUsername, boolean isEmoji) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.reaction = reaction;
        this.receiverUsername = receiverUsername;
        this.isEmoji = isEmoji;
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.REACTION_PACKET;
    }
}
