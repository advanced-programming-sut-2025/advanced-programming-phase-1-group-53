package com.stardew.Views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.Controllers.LobbyController;
import com.stardew.Models.Game.Player;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class LobbyMenu extends AppMenu {
    private String name;
    private String id;
    private ArrayList<Player> players;
    private final Player admin;
    private String password;
    private boolean isPrivate;
    private boolean isVisible;
    private final LobbyController controller = new LobbyController();
    private Game main;
    private Table table;
    private Window window;
    private SelectBox<String> playerSelectBox;
    private Label playersCountLabel;

    public LobbyMenu(Player admin, String name, boolean isVisible) {
        this.main = main;
        this.skin = getSkin();
        this.name = name;
        this.admin = admin;
        this.id = UUID.randomUUID().toString().substring(0, 6);
        players = new ArrayList<>();
        players.add(admin);
        isPrivate = false;
        this.isVisible = isVisible;
        setupUI();
    }

    public LobbyMenu(Player admin, String name, boolean isVisible, String password) {
        this.main = main;
        this.name = name;
        this.admin = admin;
        this.id = UUID.randomUUID().toString().substring(0, 6);
        players = new ArrayList<>();
        players.add(admin);
        this.password = hashPassword(password);
        isPrivate = true;
        this.isVisible = isVisible;
        setupUI();
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private void setupUI() {
        table = new Table();
        table.setFillParent(true);
        skin = getSkin();
        window = new Window("Lobby: " + name, skin);
        window.setMovable(false);
        window.setResizable(false);
        window.pad(20);
        window.row();
        window.add(new Label("Lobby ID: " + id, skin)).pad(10).row();
        window.add(new Label("Admin: " + admin.getPersonalInfo().getName(), skin)).pad(10).row();
        playersCountLabel = new Label("Players (" + players.size() + "/4):", skin);
        window.add(playersCountLabel).pad(10).row();
        playerSelectBox = new SelectBox<>(skin);
        refreshPlayers();
        window.add(playerSelectBox).pad(10).row();
        TextButton startButton = new TextButton("Start Game", skin);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.startGame(LobbyMenu.this);
            }
        });
        window.add(startButton).pad(10).row();
        TextButton leaveButton = new TextButton("Leave Lobby", skin);
        leaveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.leaveLobby(LobbyMenu.this);
            }
        });
        window.add(leaveButton).pad(10).row();
        if (isAdmin()) {
            TextButton deleteButton = new TextButton("Delete Lobby", skin);
            deleteButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    controller.deleteLobby(LobbyMenu.this);
                }
            });
            window.add(deleteButton).pad(10).row();
        }
        table.add(window).expand().center();
        stage.clear();
        stage.addActor(table);
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
        return admin != null && players.size() > 0 && players.get(0).equals(admin);
    }

    @Override
    public void check(Scanner scanner) {
        // No command-based logic
    }


}
