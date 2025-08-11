package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class CreateLobbyPacket extends Packet {
    public final String name;
    public final String Id;
    public final String password;
    public final boolean isPublic;
    public final boolean isVisible;
    public final String ownerName;
    public CreateLobbyPacket(String senderId, String senderUsername, String name, String Id, String password, boolean isPublic,
                             boolean isVisible, String ownerName) {
        super(senderId, senderUsername);
        this.name = name;
        this.Id = Id;
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
