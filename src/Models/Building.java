package Models;

import java.util.ArrayList;

public abstract class Building {
    protected final Position position;
    protected final ArrayList<ArrayList<Tile>> buildingMap;
    protected final Position doorPosition;

    public Building(Position position, Position doorPosition) {
        this.doorPosition = doorPosition;
        this.buildingMap = new ArrayList<>();
        this.position = position;
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
    //
}