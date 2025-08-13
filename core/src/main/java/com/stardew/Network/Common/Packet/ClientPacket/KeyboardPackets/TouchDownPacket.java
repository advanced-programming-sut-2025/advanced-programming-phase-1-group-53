package com.stardew.Network.Common.Packet.ClientPacket.KeyboardPackets;

import com.badlogic.gdx.Screen;
import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class TouchDownPacket extends Packet {
    public int screenX, screenY, pointer, button;
    public String className;
    public TouchDownPacket(Player sender, int screenX, int screenY, int pointer, int button, Class<? extends Screen> clazz) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.screenX = screenX;
        this.screenY = screenY;
        this.pointer = pointer;
        this.button = button;
        className = clazz.getName();
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.TOUCH_DOWN_PACKET;
    }
}
