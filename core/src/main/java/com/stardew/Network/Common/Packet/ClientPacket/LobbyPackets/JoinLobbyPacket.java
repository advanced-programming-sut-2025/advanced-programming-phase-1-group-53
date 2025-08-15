package com.stardew.Network.Common.Packet.ClientPacket.LobbyPackets;

import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class JoinLobbyPacket extends Packet {
    public final String playerUsername;
    public final String lobbyId;
    public final String password;
    public JoinLobbyPacket(Player sender, String playerUsername, String lobbyId, String password) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.playerUsername = playerUsername;
        this.lobbyId = lobbyId;
        this.password = password;
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.JOIN_LOBBY_PACKET;
    }
}
