package Models.Items.Buildings;

import Models.Position;
import Models.Tile;
import Enums.TileKind;

import java.util.ArrayList;

public abstract class Building {
    protected final Position position;
    protected final ArrayList<ArrayList<Tile>> buildingMap;

    public Building(Position position) {
        this.buildingMap = new ArrayList<>();
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public ArrayList<ArrayList<Tile>> getBuildingMap() {
        return buildingMap;
    }

    // Build function for a House
    public void buildHouse() {
        initializeBuildingMap();
    }

    // Build function for a Greenhouse
    public void buildGreenhouse() {
        initializeBuildingMap();
    }

    // Build function for a Mine
    public void buildMine() {
        initializeBuildingMap();
    }

    // Helper method to initialize a 10x10 mining map with walls and a door
    private void initializeBuildingMap() {
        buildingMap.clear();
        int size = 10;
        int doorWall = 0; // 0: top, 1: bottom, 2: left, 3: right (choose any, here top)
        int doorPos = size / 2; // center position

        // Determine tile kind for interior based on building type
        boolean isGreenhouse = this instanceof GreenHouse;
        boolean isHouse = this instanceof House;
        TileKind interiorKind;
        if (isGreenhouse) {
            interiorKind = TileKind.grass;
        } else if (isHouse) {
            interiorKind = TileKind.asphalt;
        } else {
            interiorKind = TileKind.empty;
        }

        for (int y = 0; y < size; y++) {
            ArrayList<Tile> row = new ArrayList<>();
            for (int x = 0; x < size; x++) {
                TileKind kind;
                // Top wall with door
                if (y == 0) {
                    if (x == doorPos) {
                        kind = TileKind.door;
                    } else {
                        kind = TileKind.wall;
                    }
                }
                // Bottom wall
                else if (y == size - 1) {
                    kind = TileKind.wall;
                }
                // Left and right walls
                else if (x == 0 || x == size - 1) {
                    kind = TileKind.wall;
                }
                // Interior
                else {
                    kind = interiorKind;
                }
                row.add(new Tile(new Position(x, y, 1, 1), kind));
            }
            buildingMap.add(row);
        }
    }
}
