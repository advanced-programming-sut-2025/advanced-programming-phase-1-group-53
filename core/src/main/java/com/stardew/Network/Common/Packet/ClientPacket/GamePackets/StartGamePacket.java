package com.stardew.Network.Common.Packet.ClientPacket.GamePackets;

import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class StartGamePacket extends Packet {
    public String lobbyId;
    public String username1;
    public String username2;
    public String username3;
    public String username4;
    public StartGamePacket(Player sender, String lobbyId, String username1, String username2, String username3, String username4) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.lobbyId = lobbyId;
        this.username1 = username1;
        this.username2 = username2;
        this.username3 = username3;
        this.username4 = username4;
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.START_GAME_PACKET;
    }
}
