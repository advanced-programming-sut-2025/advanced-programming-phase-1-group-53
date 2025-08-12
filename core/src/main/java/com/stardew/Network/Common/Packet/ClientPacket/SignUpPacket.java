package com.stardew.Network.Common.Packet.ClientPacket;

import com.stardew.Enums.Gender;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketSender;
import com.stardew.Network.Common.Packet.PacketType;

public class SignUpPacket extends Packet {
    public String username;
    public String nickname;
    public String password;
    public String email;
    public Gender gender;
    public SignUpPacket(
        String senderId, String senderUsername, String username,
        String nickname, String password, String email, Gender gender
    ) {
        super(senderId, senderUsername);
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.gender = gender;
    }

    @Override
    public PacketSender getSender() {
        return PacketSender.CLIENT;
    }

    @Override
    public PacketType getTypeEnum() {
        return PacketType.SIGN_UP_PACKET;
    }
}
