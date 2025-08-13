package com.stardew.Network.Common.Packet.ServerPacket;

import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class WelcomePacket extends Packet {
    private final String message;
    private final String clientId;

    public WelcomePacket(String message, String clientId) {
        super("SERVER", "SERVER");
        this.message = message;
        this.clientId = clientId;
    }
    @Override
    public PacketType getTypeEnum() {
        return PacketType.WELCOME_PACKET;
    }

    @Override
    public PacketSender getSender() { return PacketSender.SERVER; }

    public String getClientId() {
        return clientId;
    }

    public String getMessage() {
        return message;
    }
}
