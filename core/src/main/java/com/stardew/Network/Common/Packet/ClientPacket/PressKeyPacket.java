package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

// for in game press key like w a s d
public class PressKeyPacket extends Packet {
    public final int keyCode;
    public PressKeyPacket(String senderId, String senderUsername, int keyCode) {
        super(senderId, senderUsername);
        this.keyCode = keyCode;
    }

    @Override
    public PacketSender getSender() { return PacketSender.CLIENT; }

    @Override
    public PacketType getTypeEnum() { return PacketType.PRESSKEYPACKET; }
}
