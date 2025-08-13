package com.stardew.Network.Common.Packet.ClientPacket.AudioPackets;

import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class UploadAudioPacket extends Packet {
    public final String fileName;
    public final String base64Data;
    public final String targetUsername;

    public UploadAudioPacket(String senderId, String senderUsername,
                             String fileName, String base64Data, String targetUsername) {
        super(senderId, senderUsername);
        this.fileName = fileName;
        this.base64Data = base64Data;
        this.targetUsername = targetUsername;
    }

    @Override public PacketType getTypeEnum() { return PacketType.UPLOAD_AUDIO_PACKET; }
    @Override public PacketSender getSender() { return PacketSender.CLIENT; }
}

