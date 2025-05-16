package Models.Items.Buildings;

import Models.Position;
import Models.Tile;
import Enums.TileKind;

public abstract class Building {
    protected final Position position;
    protected final int SIZE = 10;
    protected final Tile[][] buildingMap;

    public Building(Position position) {
        this.position = position;
        this.buildingMap = new Tile[SIZE][SIZE];
    }

    public Position getPosition() {
        return position;
    }

    public Tile[][] getBuildingMap() {
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
        int doorWall = 0; // 0: top, 1: bottom, 2: left, 3: right
        int doorPos = SIZE / 2; // center door

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

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                TileKind kind;

                if (y == 0) {
                    kind = (x == doorPos) ? TileKind.door : TileKind.wall;
                } else if (y == SIZE - 1 || x == 0 || x == SIZE - 1) {
                    kind = TileKind.wall;
                } else {
                    kind = interiorKind;
                }

                buildingMap[y][x] = new Tile(new Position(x, y, 1, 1), kind);
            }
        }
    }
}
