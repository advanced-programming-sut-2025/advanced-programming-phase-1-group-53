package com.stardew.Models.Game;

import java.util.ArrayList;
import java.util.List;

public class App {
    private static Player currentPlayer = null;
    private static App app = null;
    private static Game game;
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Game> games = new ArrayList<>();

    public static App getInstance(){
        if(app == null){
            app = new App();
        }
        return app;
    }

    public static Game getGame(){
        return App.game;
    }

    public void setGame(Game game){
        App.game = game;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }
    public static void setCurrentPlayer(Player currentPlayer) {
        App.currentPlayer = currentPlayer;
    }

    public void setPlayers(List<Player> players) {
        this.players.clear();
        this.players.addAll(players);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public Player findPlayerByUsername(String username) {
        for (Player player : players) {
            if (player.personalInfo.getName().equals(username)) {
                return player;
            }
        }
        return null;
    }
}
