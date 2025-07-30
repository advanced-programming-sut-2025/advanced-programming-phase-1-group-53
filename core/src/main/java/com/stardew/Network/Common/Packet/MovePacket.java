package com.stardew.Network.Common.Packet;

public class MovePacket extends Packet {

    @Override
    public PacketType getType() {
        return PacketType.MOVE;
    }
}
