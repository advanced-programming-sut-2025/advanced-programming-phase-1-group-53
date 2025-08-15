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
    public int amount;
    public GiftingPacket(Player sender, String doerUsername, String receiverUsername, ItemType itemType, int amount) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.doerUsername = doerUsername;
        this.receiverUsername = receiverUsername;
        this.itemType = itemType;
        this.amount = amount;
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
