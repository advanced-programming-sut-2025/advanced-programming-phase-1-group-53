package Models;

import Enums.MapsNames;
import Enums.TileKind;
import Enums.Season;
import Models.Game.App;
import Models.Game.Game;
import Models.Game.Player;
import Models.Items.Foragings.ForagingMineral;
import Models.Items.Foragings.ForagingSeed;
import Models.Items.Foragings.ForagingTree;
import Models.Items.Foragings.ForagingCrop;
import Models.Items.Buildings.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameMap {
    private static final int MAP_SIZE = 60;
    private static final int FARM_SIZE = 20;
    private static final int STRUCTURE_WIDTH = 5;
    private static final int STRUCTURE_HEIGHT = 5;
    private static final int VILLAGE_SIZE = 20;
    public static final Position SEBASTIAN_POSITION = new Position(31, 31, 1, 1);
    public static final Position ABIGAIL_POSITION   = new Position(35, 32, 1, 1);
    public static final Position HARVEY_POSITION    = new Position(25, 34, 1, 1);
    public static final Position LIA_POSITION       = new Position(28, 36, 1, 1);
    public static final Position ROBIN_POSITION     = new Position(30, 37, 1, 1);

    private final Tile[][] tiles;
    private final ArrayList<Position> villageDoors = new ArrayList<>();

    public GameMap(List<Player> players) {
        this.tiles = new Tile[MAP_SIZE][MAP_SIZE];
        initializeMap(players);
    }

    private void initializeMap(List<Player> players) {
        // Fill the entire map with "wall" tiles
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                tiles[y][x] = new Tile(new Position(x, y, 1, 1), TileKind.wall);
            }
        }

        // Initialize farms in the four corners
        initializeFarm(0, 0, players.get(0)); // Top-left
        initializeFarm(0, MAP_SIZE - FARM_SIZE, players.get(1)); // Top-right
        initializeFarm(MAP_SIZE - FARM_SIZE, 0, players.get(2)); // Bottom-left
        initializeFarm(MAP_SIZE - FARM_SIZE, MAP_SIZE - FARM_SIZE, players.get(3)); // Bottom-right

        // Initialize the village at the center
        initializeVillage();
    }

    private void initializeFarm(int startY, int startX, Player owner) {
        // Fill the farm area with "empty" tiles
        for (int y = startY; y < startY + FARM_SIZE; y++) {
            for (int x = startX; x < startX + FARM_SIZE; x++) {
                tiles[y][x] = new Tile(new Position(x, y, 1, 1), TileKind.empty);
            }
        }

        // Place structures in the four corners of the farm
        placeStructure(startY, startX); // Top-left
        placeStructure(startY, startX + FARM_SIZE - STRUCTURE_WIDTH); // Top-right
        placeStructure(startY + FARM_SIZE - STRUCTURE_HEIGHT, startX); // Bottom-left
        placeStructure(startY + FARM_SIZE - STRUCTURE_HEIGHT, startX + FARM_SIZE - STRUCTURE_WIDTH); // Bottom-right
        owner.position.setX((startX + FARM_SIZE) / 2);
        owner.position.setY((startY + FARM_SIZE) / 2);
        MapsNames location = findLocationInGameMap(owner.position.getX(), owner.position.getY());
        owner.setCurrentMap(location);
        owner.setMyFarm(location);
        Farm farm = new Farm((new Position(startX, startY, FARM_SIZE, FARM_SIZE)), owner);
        owner.setFarm(farm);
        addFarmDoors(startY, startX, owner);
    }

    private void placeStructure(int startY, int startX) {
        for (int y = startY; y < startY + STRUCTURE_HEIGHT; y++) {
            for (int x = startX; x < startX + STRUCTURE_WIDTH; x++) {
                tiles[y][x] = new Tile(new Position(x, y, 1, 1), TileKind.structure);
            }
        }
    }

    private void initializeVillage() {
        int startY = (MAP_SIZE - VILLAGE_SIZE - FARM_SIZE);
        int startX = (MAP_SIZE - VILLAGE_SIZE - FARM_SIZE);

        // Fill the village area with "empty" tiles
        for (int y = startY; y < startY + VILLAGE_SIZE; y++) {
            for (int x = startX; x < startX + VILLAGE_SIZE; x++) {
                tiles[y][x] = new Tile(new Position(x, y, 1, 1), TileKind.asphalt);
            }
        }

        // Set shop tiles to STRUCTURE
        setShopTiles(Shop.TheStardropSaloon.getPosition());
        setShopTiles(Shop.JojaMart.getPosition());
        setShopTiles(Shop.PierreGeneralStore.getPosition());
        setShopTiles(Shop.Blacksmith.getPosition());
        setShopTiles(Shop.CarpenterShop.getPosition());
        setShopTiles(Shop.FishShop.getPosition());
        setShopTiles(Shop.MarineRanch.getPosition());

        // Set NPC tiles to NPC
        tiles[SEBASTIAN_POSITION.getY()][SEBASTIAN_POSITION.getX()] = new Tile(SEBASTIAN_POSITION, TileKind.NPC);
        tiles[ABIGAIL_POSITION.getY()][ABIGAIL_POSITION.getX()] = new Tile(ABIGAIL_POSITION, TileKind.NPC);
        tiles[HARVEY_POSITION.getY()][HARVEY_POSITION.getX()] = new Tile(HARVEY_POSITION, TileKind.NPC);
        tiles[LIA_POSITION.getY()][LIA_POSITION.getX()] = new Tile(LIA_POSITION, TileKind.NPC);
        tiles[ROBIN_POSITION.getY()][ROBIN_POSITION.getX()] = new Tile(ROBIN_POSITION, TileKind.NPC);

        addVillageDoors(startY, startX);
    }

    private void addVillageDoors(int startY, int startX) {
        int middleY = startY + VILLAGE_SIZE / 2;
        int middleX = startX + VILLAGE_SIZE / 2;

        // Top side door
        tiles[startY][middleX] = new Tile(new Position(middleX, startY, 1, 1), TileKind.door);
        villageDoors.add(new Position(middleX, startY, 1, 1));

        // Bottom side door
        tiles[startY + VILLAGE_SIZE - 1][middleX] = new Tile(new Position(middleX, startY + VILLAGE_SIZE - 1, 1, 1), TileKind.door);
        villageDoors.add(new Position(middleX, startY + VILLAGE_SIZE - 1, 1, 1));

        // Left side door
        tiles[middleY][startX] = new Tile(new Position(startX, middleY, 1, 1), TileKind.door);
        villageDoors.add(new Position(startX, middleY, 1, 1));

        // Right side door
        tiles[middleY][startX + VILLAGE_SIZE - 1] = new Tile(new Position(startX + VILLAGE_SIZE - 1, middleY, 1, 1), TileKind.door);
        villageDoors.add(new Position(startX + VILLAGE_SIZE - 1, middleY, 1, 1));
    }

    private void setShopTiles(Position position) {
        for (int y = position.getY(); y < position.getY() + position.getHeight(); y++) {
            for (int x = position.getX(); x < position.getX() + position.getWidth(); x++) {
                tiles[y][x] = new Tile(new Position(x, y, 1, 1), TileKind.structure);
            }
        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || x >= MAP_SIZE || y < 0 || y >= MAP_SIZE) {
            throw new IndexOutOfBoundsException("Invalid tile position");
        }
        return tiles[y][x];
    }

    public ArrayList<Position> getVillageDoors() {
        return villageDoors;
    }

    private void addFarmDoors(int startY, int startX, Player owner) {
        int middleY = startY + FARM_SIZE / 2;
        int middleX = startX + FARM_SIZE / 2;

        // Top side door
        tiles[startY][middleX] = new Tile(new Position(middleX, startY, 1, 1), TileKind.door);
        owner.getFarm().getDoorPositions().add(new Position(middleX, startY, 1, 1));

        // Bottom side door
        tiles[startY + FARM_SIZE - 1][middleX] = new Tile(new Position(middleX, startY + FARM_SIZE - 1, 1, 1), TileKind.door);
        owner.getFarm().getDoorPositions().add(new Position(middleX, startY + FARM_SIZE - 1, 1, 1));

        // Left side door
        tiles[middleY][startX] = new Tile(new Position(startX, middleY, 1, 1), TileKind.door);
        owner.getFarm().getDoorPositions().add(new Position(startX, middleY, 1, 1));

        // Right side door
        tiles[middleY][startX + FARM_SIZE - 1] = new Tile(new Position(startX + FARM_SIZE - 1, middleY, 1, 1), TileKind.door);
        owner.getFarm().getDoorPositions().add(new Position(startX + FARM_SIZE - 1, middleY, 1, 1));
    }

    public void generateRandomThings() {
        generateWoodAndStone();
        generateMineralsInMine(App.getGame().getCurrentPlayer().getFarm().getMine().getBuildingMap());
        generateForagingSeeds();
        generateForagingTrees();
        generateForagingCrops();
    }

    private void generateWoodAndStone() {
        Random random = new Random();
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                Tile tile = tiles[y][x];

                // Check if the tile is EMPTY or GRASS and has no item
                if ((tile.getTileKind() == TileKind.empty || tile.getTileKind() == TileKind.grass) && tile.getItem() == null) {
                    // 1% chance to place a stone or wood
                    if (random.nextInt(100) < 1) {
                        if (random.nextBoolean()) {
                            tile.setItem(ForagingMineral.Stone.clone()); // Place a stone
                        } else {
                            tile.setItem(ForagingMineral.Wood.clone()); // Place wood
                        }
                    }
                }
            }
        }
    }

    public void generateMineralsInMine(Tile[][] mineTiles) {
        Random random = new Random();
        for (int y = 0; y < mineTiles.length; y++) {
            for (int x = 0; x < mineTiles[y].length; x++) {
                Tile tile = mineTiles[y][x];

                // Check if the tile is EMPTY and has no item
                if (tile.getTileKind() == TileKind.empty && tile.getItem() == null) {
                    // 0.1% chance to place a random ForagingMineral
                    if (random.nextInt(1000) < 1) {
                        // Select a random mineral from the list of remaining minerals
                        ForagingMineral randomMineral = ForagingMineral.minerals.get(random.nextInt(ForagingMineral.minerals.size()));
                        if (randomMineral != ForagingMineral.Wood && randomMineral != ForagingMineral.Stone) {
                            // Place the random mineral in the tile
                            tile.setItem(randomMineral.clone());
                        }
                    }
                }
            }
        }
    }

    public void generateForagingSeeds() {
        // Temporary season variable (replace with actual game season later)
        Season currentSeason = App.getGame().dateAndTime.getSeason();

        Random random = new Random();
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                Tile tile = tiles[y][x];

                // Check if the tile is PLOWED and has no item
                if (tile.getTileKind() == TileKind.plowed && tile.getItem() == null) {
                    // 1% chance to place a ForagingSeed
                    if (random.nextInt(100) < 1) {
                        // Select a random ForagingSeed
                        ForagingSeed randomSeed = ForagingSeed.foragingSeeds.get(random.nextInt(ForagingSeed.foragingSeeds.size()));

                        // Check if the current season is valid for the seed
                        if (randomSeed.getSeasons().contains(currentSeason)) {
                            tile.setItem(randomSeed.clone());
                        }
                    }
                }
            }
        }
    }

    public void generateForagingTrees() {
        // Temporary season variable (replace with actual game season later)
        Season currentSeason = App.getGame().dateAndTime.getSeason();

        Random random = new Random();
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                Tile tile = tiles[y][x];

                // Check if the tile is EMPTY or GRASS and has no item
                if ((tile.getTileKind() == TileKind.empty || tile.getTileKind() == TileKind.grass) && tile.getItem() == null) {
                    // 1% chance to place a ForagingTree
                    if (random.nextInt(100) < 1) {
                        // Select a random ForagingTree
                        ForagingTree randomTree = ForagingTree.trees.get(random.nextInt(ForagingTree.trees.size()));

                        // Check if the current season is valid for the tree
                        if (randomTree.getSeasons().contains(currentSeason)) {
                            tile.setItem(randomTree.clone());
                        }
                    }
                }
            }
        }
    }

    public void generateForagingCrops() {
        // Temporary season variable (replace with actual game season later)
        Season currentSeason = App.getGame().dateAndTime.getSeason();

        Random random = new Random();
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                Tile tile = tiles[y][x];

                // Check if the tile is EMPTY or GRASS and has no item
                if ((tile.getTileKind() == TileKind.empty || tile.getTileKind() == TileKind.grass) && tile.getItem() == null) {
                    // 1% chance to place a ForagingCrop
                    if (random.nextInt(100) < 1) {
                        // Select a random ForagingCrop
                        ForagingCrop randomCrop = ForagingCrop.foragingCrops.get(random.nextInt(ForagingCrop.foragingCrops.size()));

                        // Check if the current season is valid for the crop
                        if (randomCrop.getSeasons().contains(currentSeason)) {
                            tile.setItem(randomCrop.clone());
                        }
                    }
                }
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public MapsNames findLocationInGameMap(int x, int y) {
        if (x < 0 || x >= MAP_SIZE || y < 0 || y >= MAP_SIZE) {
            return null; // Invalid position
        }
        else if (x < FARM_SIZE && y < FARM_SIZE) {
            return MapsNames.Farm1;
        } else if (y < FARM_SIZE && x >= MAP_SIZE - FARM_SIZE) {
            return MapsNames.Farm2;
        } else if (y >= MAP_SIZE - FARM_SIZE && x < FARM_SIZE) {
            return MapsNames.Farm3;
        } else if (x >= MAP_SIZE - FARM_SIZE && y >= MAP_SIZE - FARM_SIZE) {
            return MapsNames.Farm4;
        } else if (x >= FARM_SIZE && x < MAP_SIZE - FARM_SIZE && y >= FARM_SIZE && y < MAP_SIZE - FARM_SIZE) {
            return MapsNames.Village;
        } else {
            return null; // Not in any defined location
        }
    }

    public void changeMapIfEnterBuilding(int x, int y) {
        Player player = App.getGame().getCurrentPlayer();
        MapsNames mapsName = player.getCurrentMap();
        if (mapsName == player.getMyFarm()) {
            Tile tile = App.getGame().getGameMap().getTile(x, y);
            if (tile.getTileKind() == TileKind.structure) {
                Building building = findBuilding(x, y);
                if (building == null || building.getBuildingMap() == null) return;
                App.getGame().setCurrentMap(building.getBuildingMap());
                player.position.setX(building.getPosition().getX() + building.getBuildingMap()[0].length / 2);
                player.position.setY(building.getPosition().getY() + building.getBuildingMap().length / 2);
                player.setCurrentMap(building.getMapsName());
            }
        }
    }
    
    private Building findBuilding(int x, int y) {
        // Check if in the village area
        if (x >= FARM_SIZE && x < MAP_SIZE - FARM_SIZE && y >= FARM_SIZE && y < MAP_SIZE - FARM_SIZE) {
            // Check each shop's area
            if (isInside(x, y, Shop.CarpenterShop.getPosition())) {
                return Shop.CarpenterShop;
            } else if (isInside(x, y, Shop.FishShop.getPosition())) {
                return Shop.FishShop;
            } else if (isInside(x, y, Shop.Blacksmith.getPosition())) {
                return Shop.Blacksmith;
            } else if (isInside(x, y, Shop.JojaMart.getPosition())) {
                return Shop.JojaMart;
            } else if (isInside(x, y, Shop.PierreGeneralStore.getPosition())) {
                return Shop.PierreGeneralStore;
            } else if (isInside(x, y, Shop.MarineRanch.getPosition())) {
                return Shop.MarineRanch;
            } else if (isInside(x, y, Shop.TheStardropSaloon.getPosition())) {
                return Shop.TheStardropSaloon;
            }
        } else {
            // Check for buildings in the current player's farm
            Farm farm = App.getGame().getCurrentPlayer().getFarm();
            if (farm != null) {
                if (isInside(x, y, farm.getHouse().getPosition())) {
                    return farm.getHouse();
                } else if (isInside(x, y, farm.getGreenHouse().getPosition())) {
                    return farm.getGreenHouse();
                } else if (isInside(x, y, farm.getMine().getPosition())) {
                    return farm.getMine();
                } else if (isInside(x, y, farm.getLake().getPosition())) {
                    return farm.getLake();
                }
            }
        }
        // Not in any known building
        return null;
    }

    private boolean isInside(int x, int y, Position pos) {
        return x >= pos.getX() && x < pos.getX() + pos.getWidth()
            && y >= pos.getY() && y < pos.getY() + pos.getHeight();
    }
}
