package com.stardew.Views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
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
    private final String name;
    private final String id;
    private final ArrayList<Player> players;
    private final Player admin;
    private final boolean isPrivate;
    private final boolean isVisible;
    private String password;
    private SelectBox<String> playerSelectBox;
    private Label playersCountLabel;
    private final LobbyController controller = new LobbyController();

    public LobbyMenu(Player admin, String name, boolean isVisible, Game main) {
        super(main);
        this.name = name;
        this.admin = admin;
        this.id = UUID.randomUUID().toString().substring(0, 6);
        this.players = new ArrayList<>();
        this.players.add(admin);
        this.isPrivate = false;
        this.isVisible = isVisible;
    }

    public LobbyMenu(Player admin, String name, boolean isVisible, Game main, String password) {
        super(main);
        this.name = name;
        this.admin = admin;
        this.id = UUID.randomUUID().toString().substring(0, 6);
        this.players = new ArrayList<>();
        this.players.add(admin);
        this.password = hashPassword(password);
        this.isPrivate = true;
        this.isVisible = isVisible;
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
        // Remove window, add items directly to table
        table.clear();
        table.add(new Label("Lobby ID: " + id, skin)).pad(10).row();
        table.add(new Label("Admin: " + admin.getPersonalInfo().getName(), skin)).pad(10).row();
        playersCountLabel = new Label("Players (" + players.size() + ")", skin);
        table.add(playersCountLabel).pad(10).row();
        playerSelectBox = new SelectBox<>(skin);
        refreshPlayers();
        table.add(playerSelectBox).pad(10).row();
        TextButton startButton = new TextButton("Start Game", skin);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.startGame(LobbyMenu.this);
            }
        });
        table.add(startButton).pad(10).row();
        TextButton leaveButton = new TextButton("Leave Lobby", skin);
        leaveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.leaveLobby(LobbyMenu.this);
            }
        });
        table.add(leaveButton).pad(10).row();
        if (isAdmin()) {
            TextButton deleteButton = new TextButton("Delete Lobby", skin);
            deleteButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    controller.deleteLobby(LobbyMenu.this);
                }
            });
            table.add(deleteButton).pad(10).row();
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
    public void check(Scanner scanner) {
        // No command-based logic for graphical UI
    }
}
