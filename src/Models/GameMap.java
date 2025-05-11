package Models;

import Enums.TileKind;

public class GameMap {
    private static final int MAP_SIZE = 50;
    private static final int FARM_SIZE = 20;
    private static final int STRUCTURE_WIDTH = 5;
    private static final int STRUCTURE_HEIGHT = 5;
    private static final int VILLAGE_SIZE = 10;

    private final Tile[][] tiles;

    public GameMap() {
        this.tiles = new Tile[MAP_SIZE][MAP_SIZE];
        initializeMap();
    }

    private void initializeMap() {
        // Fill the entire map with "wall" tiles
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                tiles[y][x] = new Tile(new Position(x, y, 1, 1), TileKind.wall, null);
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
                tiles[y][x] = new Tile(new Position(x, y, 1, 1), TileKind.empty, null);
            }
        }

        // Place structures in the four corners of the farm
        placeStructure(startY, startX); // Top-left
        placeStructure(startY, startX + FARM_SIZE - STRUCTURE_WIDTH); // Top-right
        placeStructure(startY + FARM_SIZE - STRUCTURE_HEIGHT, startX); // Bottom-left
        placeStructure(startY + FARM_SIZE - STRUCTURE_HEIGHT, startX + FARM_SIZE - STRUCTURE_WIDTH); // Bottom-right
    }

    private void placeStructure(int startY, int startX) {
        for (int y = startY; y < startY + STRUCTURE_HEIGHT; y++) {
            for (int x = startX; x < startX + STRUCTURE_WIDTH; x++) {
                tiles[y][x] = new Tile(new Position(x, y, 1, 1), TileKind.structure, null);
            }
        }
    }

    private void initializeVillage() {
        int startY = (MAP_SIZE - VILLAGE_SIZE - FARM_SIZE);
        int startX = (MAP_SIZE - VILLAGE_SIZE - FARM_SIZE);

        // Fill the village area with "empty" tiles
        for (int y = startY; y < startY + VILLAGE_SIZE; y++) {
            for (int x = startX; x < startX + VILLAGE_SIZE; x++) {
                tiles[y][x] = new Tile(new Position(x, y, 1, 1), TileKind.empty, null);
            }
        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || x >= MAP_SIZE || y < 0 || y >= MAP_SIZE) {
            throw new IndexOutOfBoundsException("Invalid tile position");
        }
        return tiles[y][x];
    }
}