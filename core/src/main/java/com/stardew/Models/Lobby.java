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
    private final String ownerName;
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
        return new Result(true, "lobby created");
    }

    public Result addPlayer(String name) {
        Player player = App.getInstance().findPlayerByUsername(name);
        if (player == null) {
            return new Result(false, "Player not found");
        }
        if (this.players.contains(player)) {
            new Result(false, "Player already exists");
        }
        if (player.getCurrentLobby() != null) {
            return new Result(false, "Player already in a lobby");
        }
        this.players.add(player);
        player.setCurrentLobby(this);
        return new Result(true, "Player added");
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
}
