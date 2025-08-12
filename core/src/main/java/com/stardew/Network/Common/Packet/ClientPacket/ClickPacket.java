package com.stardew.Network.Common.Packet.ClientPacket;

import com.badlogic.gdx.Screen;
import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class ClickPacket extends Packet {
    public TextButtonType textButtonType;
    public String className;
    public ClickPacket(Player sender, TextButtonType textButtonType, Class<? extends Screen> clazz) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.textButtonType = textButtonType;
        this.className = clazz.getName();
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.CLICK_PACKET;
    }
}
