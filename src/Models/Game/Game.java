package Models.Game;

import Enums.ItemType;
import Models.*;
import Models.Items.*;
import Models.Items.CraftAbleAndArtisan.Artisan;
import Models.Items.CraftAbleAndArtisan.*;
import Models.Items.Foragings.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private int numOfPlayers;
    private int numOfTurn;
    public final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Item> allItemsInTheGame = new ArrayList<>();
    public final DateAndTime dateAndTime = new DateAndTime();
    public final Weather weather = new Weather();
    public final ArrayList<Product> sellAbleProducts = new ArrayList<>();//sellable By Players
    private final GameMap gameMap;
    private Tile[][] currentMap;

    Game(List<Player> players){
        this.players.addAll(players);
        numOfTurn = 0;
        numOfPlayers = players.size();
        allItemsInTheGame.addAll(Artisan.allArtisan);
        allItemsInTheGame.addAll(ArtisanGood.allArtisanGoods);
        allItemsInTheGame.addAll(Bomb.allBombs);
        allItemsInTheGame.addAll(CraftAble.allCraftables);
        allItemsInTheGame.addAll(ScareCrow.allScareCrows);
        allItemsInTheGame.addAll(Sprinkler.allSprinklers);
        allItemsInTheGame.addAll(ForagingCrop.foragingCrops);
        allItemsInTheGame.addAll(ForagingMineral.minerals);
        allItemsInTheGame.addAll(ForagingSeed.foragingSeeds);
        allItemsInTheGame.addAll(ForagingTree.allItems);
        allItemsInTheGame.addAll(Fruit.allFruits);
        allItemsInTheGame.addAll(PlantAbleCrop.allPlantAbleCrops);
        allItemsInTheGame.addAll(Tree.allTrees);
        allItemsInTheGame.addAll(Animal.allAnimals);
        allItemsInTheGame.addAll(AnimalProduct.allAnimalProducts);
        allItemsInTheGame.addAll(CoopAndBarn.COOP_AND_BARN);
        allItemsInTheGame.addAll(CraftingRecipe.craftingRecipes);
        allItemsInTheGame.addAll(Fish.fishes);
        allItemsInTheGame.addAll(Recipe.allRecipes);
        allItemsInTheGame.addAll(Food.allFoods);
        allItemsInTheGame.addAll(Item.allItems);
        allItemsInTheGame.addAll(Tool.allTools);
        allItemsInTheGame.add(TrashCan.normalTrashCan);
        allItemsInTheGame.add(WateringCan.normalWateringCan);
        this.gameMap = new GameMap(players);
        this.currentMap = gameMap.getTiles();
    }

    public Player getCurrentPlayer(){
        return players.get(numOfTurn);
    }

    public Item getItemByItemType(ItemType itemType){
        for(Item item : allItemsInTheGame){
            if(item.getItemType().equals(itemType))
                return item;
        }
        return null;
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
                    Item item = tile.getItem();
                    if (item instanceof Tool || item instanceof WateringCan) {
                        symbol = 'T';
                    } else if (item instanceof Animal) {
                        symbol = 'A';
                    } else if (item instanceof PlantAbleCrop) {
                        symbol = 'C';
                    } else if (item instanceof ForagingCrop) {
                        symbol = 'F';
                    } else if (item instanceof ForagingMineral) {
                        symbol = 'M';
                    } else if (item instanceof ForagingSeed) {
                        symbol = 'S';
                    } else if (item instanceof ForagingTree) {
                        symbol = 'T';
                    } else if (item instanceof Fruit) {
                        symbol = 'R';
                    } else if (item instanceof Tree) {
                        symbol = 'T';
                    } else if (item instanceof TrashCan) {
                        symbol = 'J';
                    } else if (item instanceof Recipe) {
                        symbol = 'R';
                    } else if (item instanceof Food) {
                        symbol = 'E';
                    } else if (item instanceof Fish) {
                        symbol = 'F';
                    } else if (item instanceof CraftAble) {
                        symbol = 'C';
                    } else if (item instanceof ArtisanGood) {
                        symbol = 'G';
                    } else if (item instanceof AnimalProduct) {
                        symbol = 'P';
                    } else if (item instanceof CoopAndBarn) {
                        symbol = 'B';
                    } else if (item instanceof ScareCrow) {
                        symbol = 'S';
                    } else if (item instanceof Sprinkler) {
                        symbol = 'S';
                    } else {
                        symbol = '?'; // Unknown item
                    }
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

    public void mapHelper() {
        StringBuilder message = new StringBuilder();
        message.append("Map Legend:\n");
        message.append("Symbols:\n");
        message.append("  P : Player (your current position)\n");
        message.append("  T : Tool, ForagingTree, or Tree\n");
        message.append("  A : Animal\n");
        message.append("  C : Plantable Crop or Craftable\n");
        message.append("  F : Foraging Crop or Fish\n");
        message.append("  M : Foraging Mineral\n");
        message.append("  S : Foraging Seed, ScareCrow, or Sprinkler\n");
        message.append("  R : Fruit or Recipe\n");
        message.append("  J : Trash Can\n");
        message.append("  G : Artisan Good\n");
        message.append("  P : Animal Product (when not player)\n");
        message.append("  B : Coop or Barn\n");
        message.append("  E : Food\n");
        message.append("  X : Empty tile (no player, no item)\n");
        message.append("  ? : Unknown item\n\n");
        message.append("Colors (tile background/type):\n");
        message.append("  \u001B[30mBLACK\u001B[0m   : Wall\n");
        message.append("  \u001B[37mWHITE\u001B[0m   : Empty\n");
        message.append("  \u001B[32mGREEN\u001B[0m   : Grass\n");
        message.append("  \u001B[36mCYAN\u001B[0m    : Plantation\n");
        message.append("  \u001B[34mBLUE\u001B[0m    : Structure (buildings, shops, etc.)\n");
        message.append("  \u001B[47m\u001B[30mGRAY\u001B[0m    : Asphalt\n");
        message.append("  \u001B[31mRED\u001B[0m     : Plowed\n");
        message.append("  \u001B[35mPURPLE\u001B[0m  : Door\n");
        message.append("\n");
        message.append("The map is displayed as a grid. Each cell shows a symbol (see above) colored according to the tile type.\n");
        message.append("Priority: Player > Item > Empty. If the player is on a tile, 'P' is shown regardless of items.\n");
        message.append("If you see a symbol other than 'P' or 'X', it represents the type of item on that tile.\n");
        message.append("Use the colors to understand the terrain or structure type of each tile.\n");
        System.out.println(message);
    }
}
