package com.stardew.Network.Client;

import com.stardew.Network.Common.ConnectionThread;
import com.stardew.Network.Common.Packet.*;
import com.stardew.Network.Common.Packet.ClientPacket.*;
import com.stardew.Network.Common.Packet.ServerPacket.ServerGeneralRespondPacket;
import com.stardew.Network.Common.Packet.ServerPacket.UpdateMapPacket;
import com.stardew.Network.Common.Packet.ServerPacket.WelcomePacket;

import java.io.IOException;
import java.net.Socket;

/**
 * نخ دائمی کلاینت برای ارسال/دریافت پکت به/از سرور.
 * initialHandshake() اینجا پکت لاگین را می‌فرستد و منتظر WelcomePacket می‌ماند.
 */
public class ClientConnectionThread extends ConnectionThread {

    public ClientConnectionThread(Socket socket, String clientId) throws IOException {
        super(socket, clientId);
    }

    @Override
    public boolean initialHandshake() {
        try {
            // ۱) ارسال LoginPacket
            sendPacket(new LoginPacket(clientId));
            System.out.println("Client sent login packet");
            // ۲) منتظر WelcomePacket بمان
            Packet pkt;
            while ((pkt = PacketParser.readPacket(inputStream)) != null) {
                if (pkt instanceof WelcomePacket welcome) {
                    System.out.println("Server says: " + welcome.getMessage());
                    return true;
                }
                // اگر پکت دیگری آمد می‌توانید enqueuePacket کنید یا نادیده بگیرید
            }
        } catch (IOException e) {
            System.err.println("Handshake error: " + e.getMessage());
        }
        return false;
    }

    @Override
    protected boolean handlePacket(Packet packet) {
        System.out.println("Received from server: " + packet.getClass().getSimpleName());

        if (packet instanceof ServerGeneralRespondPacket) {
            Packet innerPacket = ((ServerGeneralRespondPacket) packet).getReceivedPacket();

            if (innerPacket instanceof LoginPacket) {

            } else if (innerPacket instanceof CreateLobbyPacket) {

            } else if (innerPacket instanceof GiveFlowerPacket) {

            } else if (innerPacket instanceof HuggingPacket) {

            } else if (innerPacket instanceof JoinLobbyPacket) {

            } else if (innerPacket instanceof LeaveLobbyPacket) {

            } else if (innerPacket instanceof MarrigePacket) {

            } else if (innerPacket instanceof ReactionPacket) {

            } else if (innerPacket instanceof RefreshLobbyPacket) {

            } else if (innerPacket instanceof RestartGamePacket) {

            } else if (innerPacket instanceof SaveGamePacket) {

            } else if (innerPacket instanceof SendMessagePacket) {

            } else if (innerPacket instanceof SignInPacket) {

            } else if (innerPacket instanceof SignUpPacket) {

            } else if (innerPacket instanceof StartGamePacket) {

            } else if (innerPacket instanceof StartVotingPacket) {

            } else if (innerPacket instanceof VotePacket) {

            }
        } else if (packet instanceof UpdateMapPacket) {

        } else if (packet instanceof WelcomePacket) {

        }

        return false;
    }
}
