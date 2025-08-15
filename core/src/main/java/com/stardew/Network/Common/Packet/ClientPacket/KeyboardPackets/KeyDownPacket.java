package com.stardew.Network.Common.Packet.ClientPacket.KeyboardPackets;

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
        this.className = clazz.getSimpleName();
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.KEY_DOWN_PACKET;
    }
}
