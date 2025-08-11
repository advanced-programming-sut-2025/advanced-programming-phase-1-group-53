package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class CreateLobbyPacket extends Packet {
    public final String name;
    public final String password;
    public final boolean isPublic;
    public final boolean isVisible;
    public final String ownerName;
    public CreateLobbyPacket(Player sender, String name, String password, boolean isPublic,
                             boolean isVisible, String ownerName) {
        super(sender.personalInfo.getConnectionId(), sender.personalInfo.getName());
        this.name = name;
        this.password = password;
        this.isPublic = isPublic;
        this.isVisible = isVisible;
        this.ownerName = ownerName;
    }

    @Override
    public PacketType getTypeEnum() { return PacketType.CREATE_LOBBY; }

    @Override
    public PacketSender getSender() { return PacketSender.CLIENT; }
}
