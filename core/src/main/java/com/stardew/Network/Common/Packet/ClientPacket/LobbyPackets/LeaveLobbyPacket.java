package com.stardew.Network.Common.Packet.ClientPacket.LobbyPackets;

import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class LeaveLobbyPacket extends Packet {
    public String playerUsername;
    public String lobbyId;
    public LeaveLobbyPacket(Player sender, String playerUsername, String lobbyId) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.playerUsername = playerUsername;
        this.lobbyId = lobbyId;
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.LEAVE_LOBBY_PACKET;
    }
}
