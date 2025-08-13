package com.stardew.Views.NetworkMenus;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.Controllers.NetworkControllers.LobbyController;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Models.Lobby;
import com.stardew.Network.Client.ClientApp;
import com.stardew.Network.Common.Packet.ClientPacket.LobbyPackets.JoinLobbyPacket;
import com.stardew.Network.Common.Packet.ClientPacket.LobbyPackets.LeaveLobbyPacket;
import com.stardew.Network.Common.Packet.ClientPacket.GamePackets.StartGamePacket;
import com.stardew.Views.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class LobbyMenu extends AppMenu {
    private String name;
    private String id;
    private ArrayList<Player> players;
    private Player admin;
    private boolean isPrivate;
    private boolean isVisible;
    private String password;
    private SelectBox<String> playerSelectBox;
    private SelectBox<String> lobbySelectBox;
    private Label playersCountLabel;
    private final LobbyController controller = new LobbyController();
    private com.badlogic.gdx.scenes.scene2d.ui.Window lobbyWindow;
    private com.badlogic.gdx.scenes.scene2d.ui.Window createWindow;

    public LobbyMenu() {
        super(App.main);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    @Override
    public void show() {
        table.clear();

        lobbySelectBox = new SelectBox<>(skin);
        ArrayList<String> lobbyNames = new ArrayList<>();
        for (Lobby lobby : App.getInstance().getLobbies()) {
            lobbyNames.add(lobby.getName());
        }
        lobbySelectBox.setItems(lobbyNames.toArray(new String[0]));
        table.add(lobbySelectBox).width(300).height(60).pad(20).row();

        final TextField joinPasswordField = new TextField("", skin);
        joinPasswordField.setMessageText("password");
        joinPasswordField.setPasswordMode(true);
        joinPasswordField.setPasswordCharacter('*');
        joinPasswordField.setWidth(300);
        joinPasswordField.setHeight(60);
        table.add(joinPasswordField).width(300).height(60).pad(20).row();

        TextButton openLobbyWindowButton = new TextButton("Join lobby", skin);
        openLobbyWindowButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String selectedLobbyName = lobbySelectBox.getSelected();
                Lobby selectedLobby = null;
                for (Lobby lobby : App.getInstance().getLobbies()) {
                    if (lobby.getName().equals(selectedLobbyName)) {
                        selectedLobby = lobby;
                        break;
                    }
                }
                if (selectedLobby != null && selectedLobby.getPlayers().size() < 4) {
                    showLobbyWindow(selectedLobby, joinPasswordField.getText());
                    return;
                }
                Dialog dialog = STab.createDialog("couldn't open the lobby!\nit may be full or closed.", "OK");
                dialog.show(stage);
            }
        });
        table.add(openLobbyWindowButton).pad(20).row();

        TextButton createLobbyBtn = new TextButton("Create New Lobby", skin);
        createLobbyBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                openCreateWindow();
            }
        });
        table.add(createLobbyBtn).pad(20).row();

        TextButton searchLobbyBtn = new TextButton("Search Lobby by ID", skin);
        searchLobbyBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                openSearchLobbyWindow();
            }
        });
        table.add(searchLobbyBtn).pad(20).row();

        TextButton BackBtn = new TextButton("Back", skin);
        BackBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.main.setScreen(new NetMainMenu(App.main));
            }
        });
        table.add(BackBtn).pad(20).row();

        TextButton ExitBtn = new TextButton("Exit", skin);
        ExitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.main.setScreen(new ExitMenu(App.main));
            }
        });
        table.add(ExitBtn).pad(20).row();

        stage.addActor(table);
    }

    private void openCreateWindow() {
        createWindow = new com.badlogic.gdx.scenes.scene2d.ui.Window("Create Lobby", skin);
        createWindow.setSize(600, 800);
        float worldWidth = stage.getViewport().getWorldWidth();
        float worldHeight = stage.getViewport().getWorldHeight();
        createWindow.setPosition((worldWidth - 600) / 2f, (worldHeight - 800) / 2f);

        com.badlogic.gdx.scenes.scene2d.ui.Table contentTable = new com.badlogic.gdx.scenes.scene2d.ui.Table();
        contentTable.add(new Label("Lobby Name:", skin)).pad(10).row();
        com.badlogic.gdx.scenes.scene2d.ui.TextField nameField = new com.badlogic.gdx.scenes.scene2d.ui.TextField("", skin);
        nameField.setWidth(300);
        nameField.setHeight(60);
        contentTable.add(nameField).width(300).height(60).pad(10).row();
        contentTable.add(new Label("Lobby Password:", skin)).pad(10).row();
        com.badlogic.gdx.scenes.scene2d.ui.TextField passwordField = new com.badlogic.gdx.scenes.scene2d.ui.TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');
        passwordField.setWidth(300);
        passwordField.setHeight(60);
        contentTable.add(passwordField).width(300).height(60).pad(10).row();
        contentTable.add(new Label("Lobby Visibility:", skin)).pad(10).row();
        SelectBox<String> visibilitySelectBox = new SelectBox<>(skin);
        visibilitySelectBox.setItems("visible", "hidden");
        contentTable.add(visibilitySelectBox).width(300).height(60).pad(10).row();

        TextButton createButton = new TextButton("Create", skin);
        createButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.createLobby(stage, skin, nameField, passwordField, visibilitySelectBox);
            }
        });
        contentTable.add(createButton).pad(10).row();

        TextButton refreshBtn = new TextButton("Refresh", skin);
        refreshBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                createWindow.remove();
                App.main.setScreen(new LobbyMenu());
            }
        });
        contentTable.add(refreshBtn).pad(10).row();

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                createWindow.remove();
            }
        });
        contentTable.add(backButton).pad(10).row();

        com.badlogic.gdx.scenes.scene2d.ui.ScrollPane scrollPane = new com.badlogic.gdx.scenes.scene2d.ui.ScrollPane(contentTable, skin);
        createWindow.add(scrollPane).expand().fill();
        stage.addActor(createWindow);
    }

    private void showLobbyWindow(Lobby lobby, String password) {
        this.id = lobby.getId();
        this.admin = lobby.getAdmin();
        this.players = lobby.getPlayers();
        this.isPrivate = !lobby.isPublic();
        this.isVisible = lobby.isVisible();
        this.password = lobby.getPassword();

        if (isPrivate && password.isEmpty()) {
            Dialog dialog = STab.createDialog("The lobby is private!\n" +
                "You need the password to login.", "OK");
            dialog.show(stage);
            return;
        }

        JoinLobbyPacket packet = controller.joinLobby(App.getMyPlayer(), lobby, password);
        ClientApp.getInstance().getConnectionThread().sendPacket(packet);

        lobbyWindow = new com.badlogic.gdx.scenes.scene2d.ui.Window("Lobby Info", skin);
        lobbyWindow.setSize(600, 800);
        float worldWidth = stage.getViewport().getWorldWidth();
        float worldHeight = stage.getViewport().getWorldHeight();
        lobbyWindow.setPosition((worldWidth - 600) / 2f, (worldHeight - 800) / 2f);

        com.badlogic.gdx.scenes.scene2d.ui.Table contentTable = new com.badlogic.gdx.scenes.scene2d.ui.Table();
        contentTable.add(new Label("Lobby ID: " + id, skin)).pad(10).row();
        contentTable.add(new Label("Admin: " + admin.getPersonalInfo().getName(), skin)).pad(10).row();
        playersCountLabel = new Label("Players (" + players.size() + ")", skin);
        contentTable.add(playersCountLabel).pad(10).row();

        playerSelectBox = new SelectBox<>(skin);
        playerSelectBox.setWidth(300);
        playerSelectBox.setHeight(60);
        refreshPlayers();
        contentTable.add(playerSelectBox).pad(10).row();
        TextButton startButton = new TextButton("Start Game", skin);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!lobby.getAdmin().equals(App.getMyPlayer()) || lobby.getPlayers().size() < 2) {
                    Dialog dialog = STab.createDialog("couldn't start the game", "OK");
                    dialog.show(stage);
                    return;
                }
                String username1 = lobby.getPlayers().get(0).getUsername();
                String username2 = lobby.getPlayers().get(1).getUsername();
                String username3 = lobby.getPlayers().get(2).getUsername();
//                String username4 = lobby.getPlayers().get(3).getUsername();
                StartGamePacket packet1 = new StartGamePacket(App.getMyPlayer(), lobby.getId(), username1, username2, username3, null);
//                StartGamePacket packet = controller.startGame(lobby);
                ClientApp.getInstance().getConnectionThread().sendPacket(packet1);
            }
        });
        contentTable.add(startButton).pad(10).row();

        TextButton leaveButton = new TextButton("Leave Lobby", skin);
        leaveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                closeLobbyWindow();
                LeaveLobbyPacket packet = controller.leaveLobby(App.getMyPlayer() ,lobby);
                ClientApp.getInstance().getConnectionThread().sendPacket(packet);
            }
        });
        contentTable.add(leaveButton).pad(10).row();

        if (isAdmin()) {
            TextButton deleteButton = new TextButton("Delete Lobby", skin);
            deleteButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    controller.deleteLobby(lobby);
                }
            });
            contentTable.add(deleteButton).pad(10).row();
        }

        com.badlogic.gdx.scenes.scene2d.ui.ScrollPane scrollPane = new com.badlogic.gdx.scenes.scene2d.ui.ScrollPane(contentTable, skin);
        lobbyWindow.add(scrollPane).expand().fill();
        stage.addActor(lobbyWindow);
    }

    public void closeLobbyWindow() {
        if (lobbyWindow != null) {
            lobbyWindow.remove();
            lobbyWindow = null;
        }
    }

    private void refreshPlayers() {
        String[] playerNames = players.stream().map(Player::getUsername).toArray(String[]::new);
        playerSelectBox.setItems(playerNames);
    }

    public void onPlayerListChanged() {
        refreshPlayers();
        playersCountLabel.setText("Players (" + players.size() + "/4):");
    }

    private boolean isAdmin() {
        return admin != null && !players.isEmpty() && players.get(0).equals(admin);
    }

    @Override
    public void check(String s) {
        // No command-based logic for graphical UI
    }

    private void openSearchLobbyWindow() {
        Window searchWindow = new Window("Search Lobby by ID", skin);
        searchWindow.setSize(600, 600);
        float worldWidth = stage.getViewport().getWorldWidth();
        float worldHeight = stage.getViewport().getWorldHeight();
        searchWindow.setPosition((worldWidth - 600) / 2f, (worldHeight - 600) / 2f);

        Table contentTable = new Table();
        contentTable.add(new Label("Lobby ID:", skin)).pad(10);
        final TextField idField = new TextField("", skin);
        idField.setWidth(300);
        idField.setHeight(60);
        contentTable.add(idField).width(300).height(60).pad(10).row();

        final TextField searchPasswordField = new TextField("", skin);
        searchPasswordField.setPasswordMode(true);
        searchPasswordField.setPasswordCharacter('*');
        searchPasswordField.setWidth(300);
        searchPasswordField.setHeight(60);
        contentTable.add(new Label("Password:", skin)).pad(10);
        contentTable.add(searchPasswordField).width(300).height(60).pad(10).row();

        final SelectBox<String> foundLobbyBox = new SelectBox<>(skin);
        foundLobbyBox.setWidth(300);
        foundLobbyBox.setHeight(60);
        contentTable.add(foundLobbyBox).width(300).height(60).colspan(2).pad(10).row();

        TextButton searchBtn = new TextButton("Search", skin);
        searchBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String id = idField.getText();
                Lobby found = controller.searchLobbyById(id);
                if (found != null) {
                    foundLobbyBox.setItems(found.getName());
                } else {
                    foundLobbyBox.setItems();
                }
            }
        });
        contentTable.add(searchBtn).pad(10);

        TextButton joinBtn = new TextButton("Join", skin);
        joinBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String selected = foundLobbyBox.getSelected();
                if (selected != null && !selected.isEmpty()) {
                    for (Lobby lobby : App.getInstance().getLobbies()) {
                        if (lobby.getName().equals(selected)) {
                            showLobbyWindow(lobby, searchPasswordField.getText());
                            break;
                        }
                    }
                }
            }
        });
        contentTable.add(joinBtn).pad(10).row();

        TextButton backBtn = new TextButton("Back", skin);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                searchWindow.remove();
            }
        });
        contentTable.add(backBtn).colspan(2).pad(10).row();

        ScrollPane scrollPane = new ScrollPane(contentTable, skin);
        searchWindow.add(scrollPane).expand().fill();
        stage.addActor(searchWindow);
    }
}
