package com.stardew.Views.NetworkMenus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Select;
import com.stardew.Controllers.NetworkControllers.LobbyController;
import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Models.Lobby;
import com.stardew.Models.Result;
import com.stardew.Network.Client.ClientApp;
import com.stardew.Network.Common.Packet.ClientPacket.CreateLobbyPacket;
import com.stardew.Views.AppMenu;
import com.stardew.Views.ExitMenu;
import com.stardew.Views.MainMenu;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

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
        table.add(lobbySelectBox).pad(20).row();

        TextButton openLobbyWindowButton = new TextButton("Show Lobby Info", skin);
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
                if (selectedLobby != null) {
                    showLobbyWindow(selectedLobby);
                }
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

        TextButton BackBtn = new TextButton("Back", skin);
        BackBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.main.setScreen(new MainMenu(App.main));
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
        createWindow.setSize(600, 400);
        float worldWidth = stage.getViewport().getWorldWidth();
        float worldHeight = stage.getViewport().getWorldHeight();
        createWindow.setPosition((worldWidth - 600) / 2f, (worldHeight - 400) / 2f);

        com.badlogic.gdx.scenes.scene2d.ui.Table contentTable = new com.badlogic.gdx.scenes.scene2d.ui.Table();
        contentTable.add(new Label("Lobby Name:", skin)).pad(10).row();
        com.badlogic.gdx.scenes.scene2d.ui.TextField nameField = new com.badlogic.gdx.scenes.scene2d.ui.TextField("", skin);
        contentTable.add(nameField).pad(10).row();
        contentTable.add(new Label("Lobby Password:", skin)).pad(10).row();
        com.badlogic.gdx.scenes.scene2d.ui.TextField passwordField = new com.badlogic.gdx.scenes.scene2d.ui.TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');
        contentTable.add(passwordField).pad(10).row();
        contentTable.add(new Label("Lobby Visibility:", skin)).pad(10).row();
        SelectBox<String> visibilitySelectBox = new SelectBox<>(skin);
        visibilitySelectBox.setItems("visible", "hidden");
        contentTable.add(visibilitySelectBox).pad(10).row();
        TextButton createButton = new TextButton("Create", skin);
        createButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result creationResult;
                CreateLobbyPacket packet;
                String name = nameField.getText();
                String password = passwordField.getText();
                boolean isVisible = visibilitySelectBox.getSelected().equals("visible");
                if (name.isEmpty()) {
                    com.badlogic.gdx.scenes.scene2d.ui.Dialog dialog = new com.badlogic.gdx.scenes.scene2d.ui.Dialog("Error", skin) {
                        protected void result(Object object) {
                            this.hide();
                        }
                    };
                    dialog.text("All fields must be filled!");
                    dialog.button("OK");
                    dialog.show(stage);
                    return;
                }
                if (password.isEmpty()) {
                    creationResult  = Lobby.createLobby(name, password, true, isVisible, App.getCurrentPlayer().getUsername());
                    packet = new CreateLobbyPacket(
                        ClientApp.getInstance().getConnectionThread().getClientId(),
                        ,
                        name, null, password,
                        true, isVisible,

                        );
                }
                else {
                    creationResult  = Lobby.createLobby(name, password, false, isVisible, App.getCurrentPlayer().getUsername());
                    packet = new CreateLobbyPacket();
                }
                ClientApp.getInstance().getConnectionThread().sendPacket(packet);
                com.badlogic.gdx.scenes.scene2d.ui.Dialog dialog = new com.badlogic.gdx.scenes.scene2d.ui.Dialog("Pop-up", skin) {
                    protected void result(Object object) {
                        this.hide();
                    }
                };
                dialog.text(creationResult.message());
                dialog.button("OK");
                dialog.show(stage);
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

    private void showLobbyWindow(Lobby lobby) {
        this.id = lobby.getId();
        this.admin = lobby.getAdmin();
        this.players = lobby.getPlayers();
        this.isPrivate = !lobby.isPublic();
        this.isVisible = lobby.isVisible();
        this.password = lobby.getPassword();

        lobbyWindow = new com.badlogic.gdx.scenes.scene2d.ui.Window("Lobby Info", skin);
        lobbyWindow.setSize(600, 400);
        float worldWidth = stage.getViewport().getWorldWidth();
        float worldHeight = stage.getViewport().getWorldHeight();
        lobbyWindow.setPosition((worldWidth - 600) / 2f, (worldHeight - 400) / 2f);

        com.badlogic.gdx.scenes.scene2d.ui.Table contentTable = new com.badlogic.gdx.scenes.scene2d.ui.Table();
        contentTable.add(new Label("Lobby ID: " + id, skin)).pad(10).row();
        contentTable.add(new Label("Admin: " + admin.getPersonalInfo().getName(), skin)).pad(10).row();
        playersCountLabel = new Label("Players (" + players.size() + ")", skin);
        contentTable.add(playersCountLabel).pad(10).row();
        playerSelectBox = new SelectBox<>(skin);
        refreshPlayers();
        contentTable.add(playerSelectBox).pad(10).row();
        TextButton startButton = new TextButton("Start Game", skin);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.startGame(LobbyMenu.this);
            }
        });
        contentTable.add(startButton).pad(10).row();
        TextButton leaveButton = new TextButton("Leave Lobby", skin);
        leaveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.leaveLobby(LobbyMenu.this);
            }
        });
        contentTable.add(leaveButton).pad(10).row();
        if (isAdmin()) {
            TextButton deleteButton = new TextButton("Delete Lobby", skin);
            deleteButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    controller.deleteLobby(LobbyMenu.this);
                }
            });
            contentTable.add(deleteButton).pad(10).row();
        }
        TextButton closeButton = new TextButton("Close Lobby", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                closeLobbyWindow();
            }
        });
        contentTable.add(closeButton).pad(10).row();

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
}
