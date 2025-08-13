package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class SendPrivateMessagePacket extends Packet {
    public String message;
    public String receiverUsername;
    public SendPrivateMessagePacket(Player sender, String message, String receiverUsername) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.message = message;
        this.receiverUsername = receiverUsername;
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.SEND_PUBLIC_MESSAGE_PACKET;
    }
}
