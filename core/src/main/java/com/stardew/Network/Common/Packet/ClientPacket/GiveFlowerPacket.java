package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Enums.ItemType;
import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class GiveFlowerPacket extends Packet {
    public String doerUsername;
    public String receiverUsername;
    public GiveFlowerPacket(Player sender, String doerUsername, String receiverUsername) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.doerUsername = doerUsername;
        this.receiverUsername = receiverUsername;
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
