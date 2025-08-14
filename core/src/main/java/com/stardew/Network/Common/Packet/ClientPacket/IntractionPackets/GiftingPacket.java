package com.stardew.Network.Common.Packet.ClientPacket.IntractionPackets;

import com.stardew.Enums.ItemType;
import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class GiftingPacket extends Packet {
    public String doerUsername;
    public String receiverUsername;
    public ItemType itemType;
    public GiftingPacket(Player sender, String doerUsername, String receiverUsername, ItemType itemType) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.doerUsername = doerUsername;
        this.receiverUsername = receiverUsername;
        this.itemType = itemType;
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.GIFTING_PACKET;
    }
}
