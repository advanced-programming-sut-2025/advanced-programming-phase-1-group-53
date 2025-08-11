package com.stardew.Network.Server;

import com.stardew.Models.Game.Player;
import com.stardew.Models.Lobby;
import com.stardew.Models.Result;
import com.stardew.Network.Common.ConnectionThread;
import com.stardew.Network.Common.Packet.*;
import com.stardew.Network.Common.Packet.ClientPacket.*;
import com.stardew.Network.Common.Packet.ServerPacket.ServerGeneralRespondPacket;
import com.stardew.Network.Common.Packet.ServerPacket.WelcomePacket;

import java.io.IOException;
import java.net.Socket;

public class ServerConnectionThread extends ConnectionThread {
    private final ServerApp serverApp = ServerApp.getInstance();

    // حذف clientId از کانستراکتور، چون بعد از لاگین تنظیم می‌شود
    public ServerConnectionThread(Socket socket) throws IOException {
        super(socket, "");
    }

    @Override
    public boolean initialHandshake() {
        try {
            Packet pkt = PacketParser.readPacket(inputStream);
            if (!(pkt instanceof LoginPacket login)) {
                System.err.println("Expected LoginPacket but received " + (pkt == null ? "null" : pkt.getClass().getSimpleName()));
                return false;
            }
            this.clientId = login.getSenderId();
            System.out.println("Login packet received for clientId: " + clientId);

            ServerApp.getInstance().registerConnection(clientId, this);

            Packet welcome = new WelcomePacket("Welcome, your ID is ", clientId);
            sendPacket(welcome);
            System.out.println("Welcome packet sent for clientId: " + clientId);
            return true;
        } catch (IOException e) {
            System.err.println("Handshake failed: " + e.getMessage());
            return false;
        }
    }

    @Override
    protected boolean handlePacket(Packet packet) {
        Result result;
        System.out.println("Received packet from " + getClientId() + ": " + packet.getClass().getSimpleName());

        if (packet instanceof PressKeyPacket) {
            String pk = PacketParser.toJson(packet);
            System.out.println(pk);
            ServerGeneralRespondPacket respondPacket = new ServerGeneralRespondPacket(new Result(true, "hi"), packet);
            this.sendPacket(respondPacket);
            return true;

        } else if (packet instanceof GiveFlowerPacket) {

        } else if (packet instanceof HuggingPacket) {

        } else if (packet instanceof JoinLobbyPacket joinLobbyPacket) {
            result = Lobby.addPlayer(joinLobbyPacket.playerUsername, joinLobbyPacket.lobbyId, joinLobbyPacket.password);
            ServerApp.getInstance().broadcast(new ServerGeneralRespondPacket(result, joinLobbyPacket));
            return true;
        } else if (packet instanceof LeaveLobbyPacket leaveLobbyPacket) {
            result = Lobby.removePlayer(leaveLobbyPacket.playerUsername, leaveLobbyPacket.lobbyId);
            ServerApp.getInstance().broadcast(new ServerGeneralRespondPacket(result, leaveLobbyPacket));
            return true;
        } else if (packet instanceof MarrigePacket) {

        } else if (packet instanceof ReactionPacket) {

        } else if (packet instanceof RestartGamePacket) {

        } else if (packet instanceof SaveGamePacket) {

        } else if (packet instanceof SendMessagePacket) {

        }  else if (packet instanceof SignUpPacket signUpPacket) {
            result = Player.createPlayer(signUpPacket.username, signUpPacket.nickname, signUpPacket.password,
                signUpPacket.email, signUpPacket.gender);
            ServerApp.getInstance().broadcast(new ServerGeneralRespondPacket(result, signUpPacket));
            return true;
        } else if (packet instanceof StartGamePacket) {

        } else if (packet instanceof StartVotingPacket) {

        } else if (packet instanceof VotePacket) {

        } else if (packet instanceof CreateLobbyPacket createLobbyPacket) {
            result = Lobby.createLobby(createLobbyPacket.name, createLobbyPacket.password,
                createLobbyPacket.isPublic, createLobbyPacket.isVisible, createLobbyPacket.ownerName);
            ServerApp.getInstance().broadcast(new ServerGeneralRespondPacket(result, createLobbyPacket));
            return true;
        }
        return false;
    }

}
