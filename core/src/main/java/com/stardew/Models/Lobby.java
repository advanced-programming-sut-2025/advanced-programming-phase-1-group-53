package com.stardew.Models;

import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Lobby {
    private final String name;
    private final String id;
    private final String password;
    private final boolean isPublic;
    private final boolean isVisible;
    private String ownerName;
    private final ArrayList<Player> players;

    private Lobby(String name, String id, String password, boolean isPublic, boolean isVisible, String ownerName) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.isPublic = isPublic;
        this.isVisible = isVisible;
        this.ownerName = ownerName;
        this.players = new ArrayList<>();
    }

    public static Result createLobby(String name, String password, boolean isPublic, boolean isVisible, String ownerName) {
        Player player = App.getInstance().findPlayerByUsername(ownerName);
        if (player == null) {
            return new Result(false, "owner not found");
        }
        ArrayList<Lobby> lobbies = App.getInstance().getLobbies();
        ArrayList<String> ids = new ArrayList<>();
        for (Lobby lobby : lobbies) {
            ids.add(lobby.id);
        }
        String id = UUID.randomUUID().toString();
        while (ids.contains(id)) {
            id = UUID.randomUUID().toString();
        }
        Lobby lobby = new Lobby(name, id, password, isPublic, isVisible, ownerName);
        lobbies.add(lobby);
        player.getLobbies().add(lobby);
        App.getInstance().scheduleLobbyRemoval(lobby);
        return new Result(true, id);
    }

    public static Result createLobby(String name, String id, String password, boolean isPublic, boolean isVisible, String ownerName) {
        Player player = App.getInstance().findPlayerByUsername(ownerName);
        if (player == null) {
            return new Result(false, "owner not found");
        }
        ArrayList<Lobby> lobbies = App.getInstance().getLobbies();
        Lobby lobby = new Lobby(name, id, password, isPublic, isVisible, ownerName);
        lobbies.add(lobby);
        player.getLobbies().add(lobby);
        App.getInstance().scheduleLobbyRemoval(lobby);
        return new Result(true, "lobby created");
    }

    public static Result addPlayer(String name, String lobbyId, String password) {
        Lobby lobby = Lobby.findLobbyById(lobbyId);
        if (lobby == null) {
            return new Result(false, "lobby not found");
        }
        if (!lobby.isPublic) {
            if (!password.equals(lobby.password)) {
                return new Result(false, "password does not match");
            }
        }
        Player player = App.getInstance().findPlayerByUsername(name);
        if (player == null) {
            return new Result(false, "Player not found");
        }
        if (lobby.players.contains(player)) {
            new Result(false, "Player already exists");
        }
        if (player.getCurrentLobby() != null) {
            return new Result(false, "Player already in a lobby");
        }
        lobby.players.add(player);
        player.setCurrentLobby(lobby);
        App.getInstance().cancelLobbyRemoval(lobby);
        return new Result(true, "Player added");
    }

    public static Result removePlayer(String name, String lobbyId) {
        Lobby lobby = Lobby.findLobbyById(lobbyId);
        if (lobby == null) {
            return new Result(false, "lobby not found");
        }
        Player player = App.getInstance().findPlayerByUsername(name);
        if (player == null) {
            return new Result(false, "player not found");
        }
        Lobby playerCurrentLobby = player.getCurrentLobby();
        if (playerCurrentLobby == null || !playerCurrentLobby.equals(lobby)) {
            return new Result(false, "player not in lobby");
        }
        Player admin = App.getInstance().findPlayerByUsername(lobby.ownerName);
        if (admin == null) {
            return new Result(false, "admin not found");
        }
        lobby.players.remove(player);
        player.setCurrentLobby(null);
        if (lobby.players.isEmpty()) {
            App.getInstance().getLobbies().remove(lobby);
            return new Result(true, "lobby removed");
        }
        lobby.setOwnerName(lobby.getPlayers().get(0).personalInfo.getName());
        return new Result(true, "player removed");
    }

    public static Lobby findLobbyById(String lobbyId) {
        for (Lobby lobby : App.getInstance().getLobbies()) {
            if (lobby.getId().equals(lobbyId)) {
                return lobby;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}

