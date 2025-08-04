package com.stardew.Network.Common.Packet;

public class WelcomePacket extends Packet {
    private final String message;
    private final String clientId;

    public WelcomePacket(String senderId, String message, String clientId) {
        super(senderId);
        this.message = message;
        this.clientId = clientId;
    }
    @Override
    public PacketType getType() {
        return PacketType.WELCOME;
    }

    public String getClientId() {
        return clientId;
    }

    public String getMessage() {
        return message;
    }
}
