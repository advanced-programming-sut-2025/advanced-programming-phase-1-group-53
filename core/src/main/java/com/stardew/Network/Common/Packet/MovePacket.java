package com.stardew.Network.Common.Packet;

public class MovePacket extends Packet {
    public MovePacket(String senderId) {
        super(senderId);
    }

    @Override
    public PacketType getType() {
        return PacketType.MOVE;
    }
}
