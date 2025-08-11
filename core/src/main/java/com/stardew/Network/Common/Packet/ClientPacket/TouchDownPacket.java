package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class TouchDownPacket extends Packet {
    int screenX, screenY, pointer, button;
    public TouchDownPacket(Player sender, int screenX, int screenY, int pointer, int button) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.screenX = screenX;
        this.screenY = screenY;
        this.pointer = pointer;
        this.button = button;
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
