package com.stardew.Controllers.NetworkControllers;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Models.Lobby;
import com.stardew.Network.Client.ClientApp;
import com.stardew.Network.Common.Packet.ClientPacket.LobbyPackets.CreateLobbyPacket;
import com.stardew.Network.Common.Packet.ClientPacket.LobbyPackets.JoinLobbyPacket;
import com.stardew.Network.Common.Packet.ClientPacket.LobbyPackets.LeaveLobbyPacket;
import com.stardew.Network.Common.Packet.ClientPacket.GamePackets.StartGamePacket;
import com.stardew.Views.NetworkMenus.LobbyMenu;

public class LobbyController {
    public StartGamePacket startGame(Lobby lobby) {
        StartGamePacket packet = new StartGamePacket(App.getMyPlayer(), lobby.getId());
        return packet;
    }

    public LeaveLobbyPacket leaveLobby(Player player, Lobby lobby) {
        LeaveLobbyPacket packet = new LeaveLobbyPacket(App.getMyPlayer(), player.getUsername(), lobby.getId());
        App.main.setScreen(new LobbyMenu());
        return packet;
    }

    public void deleteLobby(Lobby lobby) {
        for (Player player : lobby.getPlayers()) {
            leaveLobby(player, lobby);
        }
    }

    public void createLobby(Stage stage, Skin skin, TextField nameField, TextField passwordField, SelectBox<String> visibilitySelectBox) {
        String name = nameField.getText();
        String password = passwordField.getText();
        boolean isVisible = visibilitySelectBox.getSelected().equals("visible");
        if (name.isEmpty()) {
            Dialog dialog = new Dialog("Error", skin) {
                protected void result(Object object) {
                    this.hide();
                }
            };
            dialog.text("All fields must be filled!");
            dialog.button("OK");
            dialog.show(stage);
            return;
        }
        CreateLobbyPacket packet;
        if (password.isEmpty()) {
            packet = new CreateLobbyPacket(
                App.getMyPlayer(), name, password,
                true, isVisible, App.getMyPlayer().getUsername()
            );
        } else {
            packet = new CreateLobbyPacket(
                App.getMyPlayer(), name, password,
                false, isVisible, App.getMyPlayer().getUsername()
            );
        }
        ClientApp.getInstance().getConnectionThread().sendPacket(packet);
        Dialog dialog = new Dialog("Pop-up", skin) {
            protected void result(Object object) {
                this.hide();
            }
        };
        dialog.text("the message pop-up is not yet set");
        dialog.button("OK");
        dialog.show(stage);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Lobby searchLobbyById(String id) {
        for (Lobby lobby : App.getInstance().getLobbies()) {
            if (lobby.getId().equals(id)) {
                return lobby;
            }
        }
        return null;
    }

    public JoinLobbyPacket joinLobby(Player currentPlayer, Lobby lobby, String password) {
        JoinLobbyPacket packet = new JoinLobbyPacket(currentPlayer, currentPlayer.getUsername(), lobby.getId(), password);
        return packet;
    }
}
