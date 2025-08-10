package com.stardew.Network.Common.Packet;

public class CreatLobby extends Packet {
    public final String name;
    public final String Id;
    public final String password;
    public final boolean isPrivate;
    public final boolean isVisible;
    public CreatLobby(String senderId, String senderUsername, String name, String Id, String password, boolean isPrivate,
                      boolean isVisible) {
        super(senderId, senderUsername);
        this.name = name;
        this.Id = Id;
        this.password = password;
        this.isPrivate = isPrivate;
        this.isVisible = isVisible;
    }

    @Override
    public PacketType getTypeEnum() { return PacketType.createLobby; }

    @Override
    public PacketSender getSender() { return PacketSender.CLIENT; }
}
