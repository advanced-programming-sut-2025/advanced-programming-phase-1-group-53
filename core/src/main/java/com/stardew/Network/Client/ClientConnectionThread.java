package com.stardew.Network.Client;

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
import com.stardew.Network.Common.Packet.ClientPacket.IntractionPackets.GiftingPacket;
import com.stardew.Network.Common.Packet.ClientPacket.IntractionPackets.GiveFlowerPacket;
import com.stardew.Network.Common.Packet.ClientPacket.IntractionPackets.HuggingPacket;
import com.stardew.Network.Common.Packet.ClientPacket.IntractionPackets.MarrigePacket;
import com.stardew.Network.Common.Packet.ClientPacket.KeyboardPackets.*;
import com.stardew.Network.Common.Packet.ClientPacket.LobbyPackets.CreateLobbyPacket;
import com.stardew.Network.Common.Packet.ClientPacket.LobbyPackets.JoinLobbyPacket;
import com.stardew.Network.Common.Packet.ClientPacket.LobbyPackets.LeaveLobbyPacket;
import com.stardew.Network.Common.Packet.ClientPacket.RegisterPackets.LoginPacket;
import com.stardew.Network.Common.Packet.ClientPacket.RegisterPackets.SignUpPacket;
import com.stardew.Network.Common.Packet.ServerPacket.NPCDialoguePacket;
import com.stardew.Network.Common.Packet.ServerPacket.ServerGeneralRespondPacket;
import com.stardew.Network.Common.Packet.ServerPacket.UpdateMapPacket;
import com.stardew.Network.Common.Packet.ServerPacket.WelcomePacket;
import com.stardew.Network.Server.ChangeDurationPacket;

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
//        for (Player player : App.getGame().getPlayers()) {
//            System.out.println(player.personalInfo.getName());
//        }
        System.out.println("Received from server: " + packet.getClass().getSimpleName());

        if (packet instanceof ServerGeneralRespondPacket serverGeneralRespondPacket) {
            Packet innerPacket = serverGeneralRespondPacket.getReceivedPacket();
            Result result = serverGeneralRespondPacket.result;
            Player player = App.getInstance().findPlayerByUsername(innerPacket.getSenderId());
            System.out.println(innerPacket.getSenderId());
            if (player != null) {
                App.setCurrentPlayer(player);
            }

            if (innerPacket instanceof LoginPacket) {

            } else if (innerPacket instanceof CreateLobbyPacket createLobbyPacket) {
                if (!result.success()) {
                    System.out.println(result.message());
                    return true;
                }
                Lobby.createLobby(createLobbyPacket.name, result.message(), createLobbyPacket.password,
                    createLobbyPacket.isPublic, createLobbyPacket.isVisible, createLobbyPacket.ownerName);
                return true;
            } else if (innerPacket instanceof GiveFlowerPacket giveFlowerPacket) {
                //TODO
            } else if (innerPacket instanceof HuggingPacket huggingPacket) {
                //TODO
            } else if (innerPacket instanceof JoinLobbyPacket joinLobbyPacket) {
                if (!result.success()) {
                    System.out.println(result.message());
                    return true;
                }
                Lobby.addPlayer(joinLobbyPacket.playerUsername, joinLobbyPacket.lobbyId, joinLobbyPacket.password);
                return true;
            } else if (innerPacket instanceof LeaveLobbyPacket leaveLobbyPacket) {
                if (!result.success()) {
                    System.out.println(result.message());
                    return true;
                }
                Lobby.removePlayer(leaveLobbyPacket.playerUsername, leaveLobbyPacket.lobbyId);
                return true;
            } else if (innerPacket instanceof MarrigePacket marrigePacket) {
                //TODO
            } else if (innerPacket instanceof GiftingPacket giftingPacket) {
                //TODO
            } else if (innerPacket instanceof ReactionPacket) {

            } else if (innerPacket instanceof RestartGamePacket) {

            } else if (innerPacket instanceof SaveGamePacket) {

            } else if (innerPacket instanceof SendPublicMessagePacket) {

            }  else if (innerPacket instanceof SignUpPacket signUpPacket) {
                if (!result.success()) {
                    System.out.println(result.message());
                    return true;
                }
                Player.createPlayer(signUpPacket.username, signUpPacket.nickname, signUpPacket.password,
                    signUpPacket.email, signUpPacket.gender, signUpPacket.getSenderId());
//                System.out.println(result.message());
//                System.out.println(App.getInstance().getPlayers().get(0).personalInfo.getName());
//                System.out.println(signUpPacket.getSenderId());
//                System.out.println(signUpPacket.username);
//                System.out.println(signUpPacket.getSenderUsername());
                Player player1 = App.getInstance().findPlayerByUsername(signUpPacket.getSenderUsername());
                if (player1 == null) {
                    System.out.println("what the fuck");
                }
                if (ClientApp.getInstance().getConnectionThread().getClientId().equals(signUpPacket.getSenderId())) {
                    App.setMyPlayer(player1);
                }
                return true;
            } else if (innerPacket instanceof StartGamePacket) {

            } else if (innerPacket instanceof StartVotingPacket) {

            } else if (innerPacket instanceof VotePacket) {

            } else if (innerPacket instanceof NPCDialoguePacket npcDialoguePacket) {
                if (!result.success()) {
                    System.out.println(result.message());
                    return true;
                }
                String dialogue = npcDialoguePacket.dialogue;
                // کلاینت به دیالوگ هیستوری دسترسی نداره
                // TODO show dialogue
                return true;
            } else if (innerPacket instanceof KeyUpPacket keyUpPacket) {
                if (!result.success()) {
                    System.out.println(result.message());
                    return true;
                }
                Controller controller = App.getInstance().getController(keyUpPacket.className);
                if (controller == null) {
                    System.out.println("controller not found");
                    return true;
                }
                controller.keyUp(keyUpPacket);
                System.out.println(result.message());
                return true;
            } else if (innerPacket instanceof KeyDownPacket keyDownPacket) {
                if (!result.success()) {
                    System.out.println(result.message());
                    return true;
                }
                Controller controller = App.getInstance().getController(keyDownPacket.className);
                if (controller == null) {
                    System.out.println("controller not found");
                    return true;
                }
                controller.keyDown(keyDownPacket);
                System.out.println(result.message());
                return true;
            } else if (innerPacket instanceof TouchDownPacket touchDownPacket) {
                if (!result.success()) {
                    System.out.println(result.message());
                    return true;
                }
                Controller controller = App.getInstance().getController(touchDownPacket.className);
                if (controller == null) {
                    System.out.println("controller not found");
                    return true;
                }
                controller.touchDown(touchDownPacket);
                System.out.println(result.message());
                return true;
            } else if (innerPacket instanceof MouseMovePacket mouseMovePacket) {
                if (!result.success()) {
                    System.out.println(result.message());
                    return true;
                }
                Controller controller = App.getInstance().getController(mouseMovePacket.className);
                if (controller == null) {
                    System.out.println("controller not found");
                    return true;
                }
                controller.mouseMove(mouseMovePacket);
                System.out.println(result.message());
                return true;
            } else if (innerPacket instanceof ClickPacket clickPacket) {
                if (!result.success()) {
                    System.out.println(result.message());
                    return true;
                }
                Controller controller = App.getInstance().getController(clickPacket.className);
                if (controller == null) {
                    System.out.println("controller not found");
                    return true;
                }
                controller.click(clickPacket);
                System.out.println(result.message());
                return true;
            }
            return false;
        } else if (packet instanceof UpdateMapPacket) {

        } else if (packet instanceof WelcomePacket) {

        } else if (packet instanceof RequestAudioPacket requestAudioPacket) {
            UploadAudioPacket audioPacket = null;//TODO jabar
            if (audioPacket == null) {
                System.out.println("did not send audio packet");
                return true;
            }
            System.out.println("sending audio packet");
            sendPacket(audioPacket);
            return true;
        } else if (packet instanceof UploadAudioPacket uploadAudioPacket) {
            Result result = PacketParser.saveAudio(uploadAudioPacket);
            System.out.println(result.message());
            return true;
        } else if (packet instanceof ChangeDurationPacket changeDurationPacket) {
            NPC.changeDuration(changeDurationPacket);
        }

        return false;
    }
}
