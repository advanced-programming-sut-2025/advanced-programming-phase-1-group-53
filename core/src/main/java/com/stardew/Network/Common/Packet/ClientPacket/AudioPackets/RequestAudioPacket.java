package com.stardew.Network.Common.Packet.ClientPacket.AudioPackets;

import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class RequestAudioPacket extends Packet {
    public final String targetPlayerUsername; // کلاینتی که میخوای وصل بشی

    public RequestAudioPacket(String senderId, String senderUsername, String targetPlayerUsername) {
        super(senderId, senderUsername);
        this.targetPlayerUsername = targetPlayerUsername;
    }

    @Override public PacketType getTypeEnum() { return PacketType.REQUEST_AUDIO_PACKET; }
    @Override public PacketSender getSender() { return PacketSender.CLIENT; }
}

