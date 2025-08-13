package com.stardew.Network.Server;

import com.stardew.Controllers.InGameControllers.Controller;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Models.Lobby;
import com.stardew.Models.NPC.NPC;
import com.stardew.Models.Result;
import com.stardew.Network.Common.ConnectionThread;
import com.stardew.Network.Common.Packet.*;
import com.stardew.Network.Common.Packet.ClientPacket.AudioPackets.RequestAudioPacket;
import com.stardew.Network.Common.Packet.ClientPacket.AudioPackets.UploadAudioPacket;
import com.stardew.Network.Common.Packet.ClientPacket.ContactPackets.ReactionPacket;
import com.stardew.Network.Common.Packet.ClientPacket.ContactPackets.SendPublicMessagePacket;
import com.stardew.Network.Common.Packet.ClientPacket.ElectionPackets.StartVotingPacket;
import com.stardew.Network.Common.Packet.ClientPacket.ElectionPackets.VotePacket;
import com.stardew.Network.Common.Packet.ClientPacket.GamePackets.RestartGamePacket;
import com.stardew.Network.Common.Packet.ClientPacket.GamePackets.SaveGamePacket;
import com.stardew.Network.Common.Packet.ClientPacket.GamePackets.StartGamePacket;
import com.stardew.Network.Common.Packet.ClientPacket.IntractionPackets.*;
import com.stardew.Network.Common.Packet.ClientPacket.KeyboardPackets.*;
import com.stardew.Network.Common.Packet.ClientPacket.LobbyPackets.CreateLobbyPacket;
import com.stardew.Network.Common.Packet.ClientPacket.LobbyPackets.JoinLobbyPacket;
import com.stardew.Network.Common.Packet.ClientPacket.LobbyPackets.LeaveLobbyPacket;
import com.stardew.Network.Common.Packet.ClientPacket.RegisterPackets.LoginPacket;
import com.stardew.Network.Common.Packet.ClientPacket.RegisterPackets.SignUpPacket;
import com.stardew.Network.Common.Packet.ServerPacket.NPCDialoguePacket;
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
            ServerConnectionThread conn = ServerApp.getInstance().getConnection(clientId);
            if (conn != null) {
                System.out.println("Duplicate client id " + clientId);
                return false;
            }
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
        Player player = App.getInstance().findPlayerByUsername(packet.getSenderId());
        System.out.println(packet.getSenderId());
        if (player != null) {
            App.setCurrentPlayer(player);
        }
        Result result;
        System.out.println("Received packet from " + getClientId() + ": " + packet.getClass().getSimpleName());

        if (packet instanceof GiveFlowerPacket giveFlowerPacket) {
            //TODO
        } else if (packet instanceof HuggingPacket huggingPacket) {
            //TODO
        } else if (packet instanceof JoinLobbyPacket joinLobbyPacket) {
            result = Lobby.addPlayer(joinLobbyPacket.playerUsername, joinLobbyPacket.lobbyId, joinLobbyPacket.password);
            System.out.println(result.message());
            ServerApp.getInstance().broadcast(new ServerGeneralRespondPacket(result, joinLobbyPacket));
            return true;
        } else if (packet instanceof LeaveLobbyPacket leaveLobbyPacket) {
            result = Lobby.removePlayer(leaveLobbyPacket.playerUsername, leaveLobbyPacket.lobbyId);
            System.out.println(result.message());
            ServerApp.getInstance().broadcast(new ServerGeneralRespondPacket(result, leaveLobbyPacket));
            return true;
        } else if (packet instanceof MarrigePacket marrigePacket) {
            //TODO
        } else if (packet instanceof GiftingPacket giftingPacket) {
            //TODO
        } else if (packet instanceof ReactionPacket) {

        } else if (packet instanceof RestartGamePacket) {

        } else if (packet instanceof SaveGamePacket) {

        } else if (packet instanceof SendPublicMessagePacket) {

        }  else if (packet instanceof SignUpPacket signUpPacket) {
            result = Player.createPlayer(signUpPacket.username, signUpPacket.nickname, signUpPacket.password,
                signUpPacket.email, signUpPacket.gender, signUpPacket.getSenderId());
            System.out.println(result.message());
            ServerApp.getInstance().broadcast(new ServerGeneralRespondPacket(result, signUpPacket));
            return true;
        } else if (packet instanceof StartGamePacket) {

        } else if (packet instanceof StartVotingPacket) {

        } else if (packet instanceof VotePacket) {

        } else if (packet instanceof CreateLobbyPacket createLobbyPacket) {
            result = Lobby.createLobby(createLobbyPacket.name, createLobbyPacket.password,
                createLobbyPacket.isPublic, createLobbyPacket.isVisible, createLobbyPacket.ownerName);
            System.out.println(result.message());
            ServerApp.getInstance().broadcast(new ServerGeneralRespondPacket(result, createLobbyPacket));
            return true;
        } else if (packet instanceof TalkToNPCPacket talkToNPCPacket) {
            result = NPC.generateDialogue(talkToNPCPacket.NPCName, talkToNPCPacket.getSenderUsername());
            System.out.println(result.message());
            this.sendPacket(new ServerGeneralRespondPacket(result, new NPCDialoguePacket(talkToNPCPacket.getSenderId(),
                talkToNPCPacket.getSenderUsername(), result.message())));
            return true;
        } else if (packet instanceof KeyUpPacket keyUpPacket) {
            Controller controller = App.getInstance().getController(keyUpPacket.className);
            if (controller == null) {
                ServerApp.getInstance().broadcastInGame(new ServerGeneralRespondPacket(new Result(false, "controller not found"), keyUpPacket));
                System.out.println("controller not found");
                return true;
            }
            result = controller.keyUp(keyUpPacket);
            System.out.println(result.message());
            ServerApp.getInstance().broadcastInGame(new ServerGeneralRespondPacket(result, keyUpPacket));
            return true;
        } else if (packet instanceof KeyDownPacket keyDownPacket) {
            Controller controller = App.getInstance().getController(keyDownPacket.className);
            if (controller == null) {
                ServerApp.getInstance().broadcastInGame(new ServerGeneralRespondPacket(new Result(false, "controller not found"), keyDownPacket));
                System.out.println("controller not found");
                return true;
            }
            result = controller.keyDown(keyDownPacket);
            System.out.println(result.message());
            ServerApp.getInstance().broadcastInGame(new ServerGeneralRespondPacket(result, keyDownPacket));
            return true;
        } else if (packet instanceof TouchDownPacket touchDownPacket) {
            Controller controller = App.getInstance().getController(touchDownPacket.className);
            if (controller == null) {
                ServerApp.getInstance().broadcastInGame(new ServerGeneralRespondPacket(new Result(false, "controller not found"), touchDownPacket));
                System.out.println("controller not found");
                return true;
            }
            result = controller.touchDown(touchDownPacket);
            System.out.println(result.message());
            ServerApp.getInstance().broadcastInGame(new ServerGeneralRespondPacket(result, touchDownPacket));
            return true;
        } else if (packet instanceof MouseMovePacket mouseMovePacket) {
            Controller controller = App.getInstance().getController(mouseMovePacket.className);
            if (controller == null) {
                ServerApp.getInstance().broadcastInGame(new ServerGeneralRespondPacket(new Result(false, "controller not found"), mouseMovePacket));
                System.out.println("controller not found");
                return true;
            }
            result = controller.mouseMove(mouseMovePacket);
            System.out.println(result.message());
            ServerApp.getInstance().broadcastInGame(new ServerGeneralRespondPacket(result, mouseMovePacket));
            return true;
        } else if (packet instanceof ClickPacket clickPacket) {
            Controller controller = App.getInstance().getController(clickPacket.className);
            if (controller == null) {
                ServerApp.getInstance().broadcastInGame(new ServerGeneralRespondPacket(new Result(false, "controller not found"), clickPacket));
                System.out.println("controller not found");
                return true;
            }
            result = controller.click(clickPacket);
            System.out.println(result.message());
            ServerApp.getInstance().broadcastInGame(new ServerGeneralRespondPacket(result, clickPacket));
            return true;
        } else if (packet instanceof RequestAudioPacket requestAudioPacket) {
            Player player1 = App.getInstance().findPlayerByUsername(requestAudioPacket.targetPlayerUsername);
            if (player1 == null) {
                sendPacket(new ServerGeneralRespondPacket(new Result(false, "player not found"), requestAudioPacket));
                System.out.println("player not found");
                return true;
            }
            ServerConnectionThread connectionThread = ServerApp.getInstance().getConnection(player1.personalInfo.getConnectionId());
            if (connectionThread == null) {
                sendPacket(new ServerGeneralRespondPacket(new Result(false, "connection not found"), requestAudioPacket));
                System.out.println("connection not found");
                return true;
            }
            connectionThread.sendPacket(requestAudioPacket);
            System.out.println("request has been transferred");
            return true;
        } else if (packet instanceof UploadAudioPacket uploadAudioPacket) {
            Player player1 = App.getInstance().findPlayerByUsername(uploadAudioPacket.targetUsername);
            if (player1 == null) {
                sendPacket(new ServerGeneralRespondPacket(new Result(false, "player not found"), uploadAudioPacket));
                System.out.println("player not found");
                return true;
            }
            ServerConnectionThread connectionThread = ServerApp.getInstance().getConnection(player1.personalInfo.getConnectionId());
            if (connectionThread == null) {
                sendPacket(new ServerGeneralRespondPacket(new Result(false, "connection not found"), uploadAudioPacket));
                System.out.println("connection not found");
                return true;
            }
            connectionThread.sendPacket(uploadAudioPacket);
            System.out.println("upload audio has been transferred");
            return true;
        }
        return false;
    }

}
