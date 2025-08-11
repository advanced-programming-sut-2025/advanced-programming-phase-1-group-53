package com.stardew.Models.Game;

import com.stardew.Controllers.GameMenuController;
import com.stardew.Enums.Gender;
import com.stardew.Enums.Menu;
import com.stardew.Main;
import com.stardew.Models.Lobby;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class App {
    public static final float TAKING_STEP_TIME_GAP = 0.18f;
    public static final int ADVANCE_OF_EACH_STEP = 5;

    //TODO: fix into multiplayer
    private static Player currentPlayer = null;
    private static App app = null;
    private static Menu currentMenu = Menu.gameMenu;
    private static Game game;
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Game> games = new ArrayList<>();
    private final ArrayList<Lobby> lobbies = new ArrayList<>();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Map<Lobby, ScheduledFuture<?>> lobbyRemovalTasks = new ConcurrentHashMap<>();
    public static Main main = Main.getInstance();

    private App(){
        currentPlayer = new Player("ilia", "ii", "ii", "oo", Gender.MALE);
        players.add(currentPlayer);
        players.add(new Player("ilias", "ii", "ii", "ooo", Gender.MALE));
        players.add(new Player("iliass", "ii", "ii", "oooo", Gender.MALE));
        players.add(new Player("iliasss", "ii", "ii", "oooooo", Gender.MALE));
    }

    public static App getInstance(){
        if(app == null){
            app = new App();
            new GameMenuController().newGame("ilias", "iliass", "iliasss");
        }
        return app;
    }

    public static Game getGame(){
        return App.game;
    }

    public void setGame(Game game){
        App.game = game;
    }

    public static Menu getCurrentMenu() {
        return App.currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
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

    public ArrayList<Lobby> getLobbies() {
        return lobbies;
    }

    // زمان‌بندی حذف لابی
    public void scheduleLobbyRemoval(Lobby lobby) {
        ScheduledFuture<?> task = scheduler.schedule(() -> {
            synchronized (this) {
                if (lobby.getPlayers().size() <= 1) {
                    getLobbies().remove(lobby);
                    System.out.println("Lobby " + lobby.getName() + " removed after 5 minutes of inactivity.");
                }
            }
        }, 5, TimeUnit.MINUTES);

        lobbyRemovalTasks.put(lobby, task);
    }

    // لغو تایمر حذف لابی
    public void cancelLobbyRemoval(Lobby lobby) {
        ScheduledFuture<?> task = lobbyRemovalTasks.remove(lobby);
        if (task != null) {
            task.cancel(false);
        }
    }
}
