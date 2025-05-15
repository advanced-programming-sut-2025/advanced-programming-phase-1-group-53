package Models.Game;

import Enums.ItemType;
import Models.*;
import Models.Items.Item;

import java.util.ArrayList;

public class Game {
    private int numOfPlayers;
    private int numOfTurn;
    public final ArrayList<Player> players = new ArrayList<>();
    public final DateAndTime dateAndTime = new DateAndTime();
    public final Weather weather = new Weather();
    public final ArrayList<Product> sellAbleProducts = new ArrayList<>();//sellable By Players
    private final GameMap gameMap = new GameMap();
    private Tile[][] currentMap = gameMap.getTiles();

    public Player getCurrentPlayer(){
        return players.get(numOfTurn);
    }

    public Item getItemByItemType(ItemType itemType){

    }

    public Product getProductByItemType(ItemType itemType){
        for(Product product : sellAbleProducts){
            if(product.)
        }
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        numOfPlayers = numOfPlayers;
    }

    public int getNumOfTurn() {
        return numOfTurn;
    }

    public void setNumOfTurn(int numOfTurn) {
        numOfTurn = numOfTurn;
    }

    Game(){

    }
    public Player getPlayer(int playerIndex){
        if(playerIndex > players.size() || playerIndex < 0){
            return null;
        }
        return players.get(playerIndex);
    }

    public static Player whichPlayersFarm(int x, int y){
        return null;
    }

    public static boolean isInFarm(int x, int y){
        return false;
    }
    public boolean isInCave(int x, int y){
        return false;
    }
    public boolean isInField(int x, int y){
        return false;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Tile[][] getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(Tile[][] currentMap) {
        this.currentMap = currentMap;
    }

    public void printMap() {
        Tile[][] map = getCurrentMap();
        Player player = getCurrentPlayer();
        int playerX = player.position.getX();
        int playerY = player.position.getY();

        // ANSI color codes
        final String RESET = "\u001B[0m";
        final String BLACK = "\u001B[30m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String BLUE = "\u001B[34m";
        final String PURPLE = "\u001B[35m";
        final String CYAN = "\u001B[36m";
        final String WHITE = "\u001B[37m";
        final String BG_GRAY = "\u001B[47m";

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                Tile tile = map[y][x];
                String color = "";
                char symbol;

                // Priority: Player > Item > Empty
                if (playerX == x && playerY == y) {
                    symbol = 'P';
                } else if (tile.getItem() != null) {
                    symbol = 'T';
                } else {
                    symbol = 'X';
                }

                // Color depends on tile type
                switch (tile.getTileKind()) {
                    case wall:
                        color = BLACK;
                        break;
                    case empty:
                        color = WHITE;
                        break;
                    case grass:
                        color = GREEN;
                        break;
                    case plantation:
                        color = CYAN;
                        break;
                    case structure:
                        color = BLUE;
                        break;
                    case asphalt:
                        color = BG_GRAY + BLACK;
                        break;
                    case plowed:
                        color = RED;
                        break;
                    case door:
                        color = PURPLE;
                        break;
                    default:
                        color = WHITE;
                }
                System.out.print(color + symbol + RESET);
            }
            System.out.println();
        }
    }
}
