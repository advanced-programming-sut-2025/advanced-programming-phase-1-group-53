package Models;

import java.util.ArrayList;

public class Game {
    private static Player currentPlayer = null;
    private static Player creatorPlayer;
    private static ArrayList<Tile> currentMapTiles;
    private static final DateAndTime dateAndTime = new DateAndTime();
    private static GameMap gameMap;
    private static final ArrayList<Player> inGamePlayers = new ArrayList<>();
    private static final Weather weather = new Weather();

    public Game(Player creatorPlayer, Player currentPlayer, ArrayList<Tile> currentMapTiles, GameMap gameMap) {
        Game.currentPlayer = creatorPlayer;
        Game.creatorPlayer = creatorPlayer;
        Game.currentMapTiles = currentMapTiles;
        Game.gameMap = gameMap;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        Game.currentPlayer = currentPlayer;
    }

    public static Player getCreatorPlayer() {
        return creatorPlayer;
    }

    public static void setCreatorPlayer(Player creatorPlayer) {
        Game.creatorPlayer = creatorPlayer;
    }

    public static ArrayList<Tile> getCurrentMapTiles() {
        return currentMapTiles;
    }

    public static void setCurrentMapTiles(ArrayList<Tile> currentMapTiles) {
        Game.currentMapTiles = currentMapTiles;
    }

    public static GameMap getGameMap() {
        return gameMap;
    }

    public static void setGameMap(GameMap gameMap) {
        Game.gameMap = gameMap;
    }
}
