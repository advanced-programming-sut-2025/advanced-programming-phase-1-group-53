package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Enums.ItemType;
import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class BuyItemPacket extends Packet {
    public final ItemType itemType;
    public final String field;
    public BuyItemPacket(Player sender, ItemType itemType, String field) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.itemType = itemType;
        this.field = field;
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.PICK_ITEM_PACKET;
    }
}
