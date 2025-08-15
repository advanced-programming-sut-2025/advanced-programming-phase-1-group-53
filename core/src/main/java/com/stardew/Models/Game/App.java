package com.stardew.Models.Game;

import com.stardew.Controllers.InGameControllers.*;
import com.stardew.Enums.Menu;
import com.stardew.Main;
import com.stardew.Models.Lobby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class App {
    public static final float TAKING_STEP_TIME_GAP = 0.18f;
    public static final int ADVANCE_OF_EACH_STEP = 5;

    //TODO: fix into multiplayer
    private static Player currentPlayer = null;
    private static final Thread MAIN_THREAD = Thread.currentThread();
    private static Player myPlayer = null;
    private static App app = null;
    private static Menu currentMenu = null;
        //TODO unnull
    private static Game game;
    private final Map<String, Controller> controllerRegistry = new HashMap<>();
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Game> games = new ArrayList<>();
    private final ArrayList<Lobby> lobbies = new ArrayList<>();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Map<Lobby, ScheduledFuture<?>> lobbyRemovalTasks = new ConcurrentHashMap<>();
    public static Main main = Main.getInstance();

    private App(){
//        currentPlayer = new Player("ilia", "ii", "ii", "oo", Gender.MALE, "a");
//        players.add(currentPlayer);
//        players.add(new Player("ilias", "ii", "ii", "ooo", Gender.MALE, "a"));
//        players.add(new Player("iliass", "ii", "ii", "oooo", Gender.MALE, "a"));
//        players.add(new Player("iliasss", "ii", "ii", "oooooo", Gender.MALE, "a"));
    }

    public static App getInstance(){
        if(app == null){
            app = new App();
            setControllers();
//            new GameMenuController().newGame("ilia", "ilias", "iliass", "iliasss");
        }
        return app;
    }

    private static void setControllers() {
        app.controllerRegistry.put(AbilityMenuController.MENU_NAME, new AbilityMenuController());
        app.controllerRegistry.put(RankingsController.MENU_NAME, new RankingsController());
        app.controllerRegistry.put(CheatMenuController.MENU_NAME, new CheatMenuController());
        app.controllerRegistry.put(CookingMenuController.MENU_NAME, new CookingMenuController());
        app.controllerRegistry.put(CoopMenuController.MENU_NAME, new CoopMenuController());
        app.controllerRegistry.put(CraftingMenuController.MENU_NAME, new CraftingMenuController());
        app.controllerRegistry.put(InventoryMenuController.MENU_NAME, new InventoryMenuController());
        app.controllerRegistry.put(MapMenuController.MENU_NAME, new MapMenuController());
        app.controllerRegistry.put(MiniGameMenuController.MENU_NAME, new MiniGameMenuController());
        app.controllerRegistry.put(RefrigeratorMenuController.MENU_NAME, new RefrigeratorMenuController());
        app.controllerRegistry.put(SellingMenuController.MENU_NAME, new SellingMenuController());
        app.controllerRegistry.put(ShopMenuController.MENU_NAME, new ShopMenuController());
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
        if (Thread.currentThread().getName().equals("main")) {
            return App.getMyPlayer();
        }
        else {
            return currentPlayer;
        }
    }
    public synchronized static void setCurrentPlayer(Player currentPlayer) {
        App.currentPlayer = currentPlayer;
//        App appInstance = getInstance();
//        if (!appInstance.players.contains(currentPlayer)) {
//            appInstance.players.add(currentPlayer);
//        }
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

    public static Thread getMainThread() {
        return MAIN_THREAD;
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

    public static Player getMyPlayer() {
        return myPlayer;
    }

    public static void setMyPlayer(Player myPlayer) {
        App.myPlayer = myPlayer;
    }

    public Controller getController(String className) {
        return controllerRegistry.get(className);
    }
}
