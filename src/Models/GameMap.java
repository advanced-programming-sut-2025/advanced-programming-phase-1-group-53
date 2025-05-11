package Models;

import Enums.TileKind;
import Enums.Season;
import Models.Game.App;
import Models.Game.Game;
import Models.Items.Foragings.ForagingMineral;
import Models.Items.Foragings.ForagingSeed;
import Models.Items.Foragings.ForagingTree;
import Models.Items.Foragings.ForagingCrop;
import Models.Items.Buildings.*;

import java.util.Random;

public class GameMap {
    private static final int MAP_SIZE = 60;
    private static final int FARM_SIZE = 20;
    private static final int STRUCTURE_WIDTH = 5;
    private static final int STRUCTURE_HEIGHT = 5;
    private static final int VILLAGE_SIZE = 20;

    private final Tile[][] tiles;

    public GameMap() {
        this.tiles = new Tile[MAP_SIZE][MAP_SIZE];
        initializeMap();
    }

    private void initializeMap() {
        // Fill the entire map with "wall" tiles
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                tiles[y][x] = new Tile(new Position(x, y, 1, 1), TileKind.wall);
            }
        }

        // Initialize farms in the four corners
        initializeFarm(0, 0); // Top-left
        initializeFarm(0, MAP_SIZE - FARM_SIZE); // Top-right
        initializeFarm(MAP_SIZE - FARM_SIZE, 0); // Bottom-left
        initializeFarm(MAP_SIZE - FARM_SIZE, MAP_SIZE - FARM_SIZE); // Bottom-right

        // Initialize the village at the center
        initializeVillage();
    }

    private void initializeFarm(int startY, int startX) {
        // Fill the farm area with "empty" tiles
        for (int y = startY; y < startY + FARM_SIZE; y++) {
            for (int x = startX; x < startX + FARM_SIZE; x++) {
                tiles[y][x] = new Tile(new Position(x, y, 1, 1), TileKind.empty);
            }
        }

        addFarmDoors(startY, startX);

        // Place structures in the four corners of the farm
        placeStructure(startY, startX); // Top-left
        placeStructure(startY, startX + FARM_SIZE - STRUCTURE_WIDTH); // Top-right
        placeStructure(startY + FARM_SIZE - STRUCTURE_HEIGHT, startX); // Bottom-left
        placeStructure(startY + FARM_SIZE - STRUCTURE_HEIGHT, startX + FARM_SIZE - STRUCTURE_WIDTH); // Bottom-right
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

    private void addFarmDoors(int startY, int startX) {
        int middleY = startY + FARM_SIZE / 2;
        int middleX = startX + FARM_SIZE / 2;

        // Top side door
        tiles[startY][middleX] = new Tile(new Position(middleX, startY, 1, 1), TileKind.door);

        // Bottom side door
        tiles[startY + FARM_SIZE - 1][middleX] = new Tile(new Position(middleX, startY + FARM_SIZE - 1, 1, 1), TileKind.door);

        // Left side door
        tiles[middleY][startX] = new Tile(new Position(startX, middleY, 1, 1), TileKind.door);

        // Right side door
        tiles[middleY][startX + FARM_SIZE - 1] = new Tile(new Position(startX + FARM_SIZE - 1, middleY, 1, 1), TileKind.door);
    }

    public void generateRandomThings() {
        generateWoodAndStone();
        generateMineralsInMine();
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
}
