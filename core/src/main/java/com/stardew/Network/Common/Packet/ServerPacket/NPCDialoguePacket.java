package com.stardew.Network.Common.Packet.ServerPacket;

import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class NPCDialoguePacket extends Packet {
    public String dialogue;
    public NPCDialoguePacket(String senderId, String senderUsername, String dialogue) {
        super(senderId, senderUsername);
        this.dialogue = dialogue;
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.NPC_DIALOGUE_PACKET;
    }
}
