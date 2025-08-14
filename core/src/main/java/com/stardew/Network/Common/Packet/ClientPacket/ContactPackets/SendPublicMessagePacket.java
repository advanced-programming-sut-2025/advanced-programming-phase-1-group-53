package com.stardew.Network.Common.Packet.ClientPacket.ContactPackets;

import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class SendPublicMessagePacket extends Packet {
    public String message;
    public String tagUsername;
    public boolean isTagged;
    public SendPublicMessagePacket(Player sender, String message, String tagUsername, boolean isTagged) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.message = message;
        this.tagUsername = tagUsername;
        this.isTagged = isTagged;
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.SEND_PUBLIC_MESSAGE_PACKET;
    }
}
