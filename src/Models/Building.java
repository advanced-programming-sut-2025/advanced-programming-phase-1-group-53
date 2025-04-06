package Models;

import java.util.ArrayList;

public abstract class Building {
    private final Position position;
    private final ArrayList<ArrayList<Tile>> buildingMap;
    private final Position doorPosition;

    public Building() {
        this.doorPosition = null;
        this.buildingMap = null;
        this.position = null;
    }

    public Position getPosition() {
        return position;
    }

    public ArrayList<ArrayList<Tile>> getBuildingMap() {
        return buildingMap;
    }

    public Position getDoorPosition() {
        return doorPosition;
    }
}
