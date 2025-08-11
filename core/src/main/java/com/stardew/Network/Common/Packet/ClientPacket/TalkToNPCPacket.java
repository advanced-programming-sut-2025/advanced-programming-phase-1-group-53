package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class TalkToNPCPacket extends Packet {
    public String NPCName;
    public TalkToNPCPacket(String senderId, String senderUsername, String NPCName) {
        super(senderId, senderUsername);
        this.NPCName = NPCName;
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.START_VOTING_PACKET;
    }
}
