package com.stardew.Network.Common.Packet.ClientPacket.KeyboardPackets;

import com.badlogic.gdx.Screen;
import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class MouseMovePacket extends Packet {
    public int screenX, screenY;
    public String className;
    public MouseMovePacket(Player sender, int screenX, int screenY, Class<? extends Screen> clazz) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.screenX = screenX;
        this.screenY = screenY;
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
