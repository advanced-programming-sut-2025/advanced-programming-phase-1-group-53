package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class SendMessagePacket extends Packet {
    public SendMessagePacket(Player sender) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.SEND_MESSAGE_PACKET;
    }
}
