package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class ClickPacket extends Packet {
    public TextButtonType textButtonType;
    public ClickPacket(Player sender, TextButtonType textButtonType) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.textButtonType = textButtonType;
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.GIVE_FLOWER_PACKET;
    }
}
