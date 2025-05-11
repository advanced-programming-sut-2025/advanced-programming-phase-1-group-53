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

    // Helper method to initialize a 10x10 building map
    private void initializeBuildingMap() {
        buildingMap.clear();
        for (int y = 0; y < 10; y++) {
            ArrayList<Tile> row = new ArrayList<>();
            for (int x = 0; x < 10; x++) {
                row.add(new Tile(new Position(x, y, 1, 1), TileKind.empty, null));
            }
            buildingMap.add(row);
        }
    }
}