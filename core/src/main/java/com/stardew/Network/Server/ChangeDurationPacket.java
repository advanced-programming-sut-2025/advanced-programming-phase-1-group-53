package com.stardew.Network.Server;

import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class ChangeDurationPacket extends Packet {
    public final int SebastianDuration, AbigailDuration, HarveyDuration, LiaDuration, RobinDuration;
    public ChangeDurationPacket(String connectionId, String username, int SebastianDuration, int AbigailDuration,
                                int HarveyDuration, int LiaDuration, int RobinDuration) {
        super(connectionId, username);
        this.SebastianDuration = SebastianDuration;
        this.AbigailDuration = AbigailDuration;
        this.HarveyDuration = HarveyDuration;
        this.LiaDuration = LiaDuration;
        this.RobinDuration = RobinDuration;
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.SERVER;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.CHANGE_DURATION_PACKET;
    }
}
