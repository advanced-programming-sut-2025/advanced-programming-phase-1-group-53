package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class KeyUpPacket extends Packet {
    public int keycode;
    public KeyUpPacket(Player sender, int keycode) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.keycode = keycode;
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
