package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class LoginPacket extends Packet {
    public LoginPacket(String senderId) {
        super(senderId, "LOGIN");
    }
    @Override
    public PacketType getTypeEnum() { return PacketType.LOGIN; }

    @Override
    public PacketSender getSender() { return PacketSender.CLIENT; }
}

