package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class SignInPacket extends Packet {
    public SignInPacket(String senderId, String senderUsername) {
        super(senderId, senderUsername);
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.SIGN_IN_PACKET;
    }
}
