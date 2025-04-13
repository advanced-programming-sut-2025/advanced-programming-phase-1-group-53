package Models;

import Enums.Menu;

import java.util.ArrayList;

public class App {
    private static Menu currentMenu = Menu.loginRegisterMenu;
    private static Game currentGame = null;
    private static final ArrayList<Game> games = new ArrayList<>();
    private static final ArrayList<GameMap> gameMaps = new ArrayList<>();
    private static final ArrayList<Player> players = new ArrayList<>();

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu menu) {
        currentMenu = menu;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        App.currentGame = currentGame;
    }

    public static ArrayList<Game> getGames() {
        return games;
    }

    public static ArrayList<GameMap> getGameMaps() {
        return gameMaps;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }
}
