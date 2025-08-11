package com.stardew.Network.Common.Packet.ClientPacket;

import com.badlogic.gdx.Screen;
import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class KeyDownPacket extends Packet {
    public int keycode;
    public String className;
    public KeyDownPacket(Player sender, int keycode, Class<? extends Screen> clazz) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.keycode = keycode;
        this.className = clazz.getName();
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
