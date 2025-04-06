package Models;

import java.util.ArrayList;

public class Farm {
    private final Player owner;
    private final Position position;
    private final House house;
    private final Mine mine;
    private final GreenHouse greenHouse;
    private final Position lakePosition;
    private final ArrayList<Position> doorPositions;

    public Farm(House house, Mine mine, GreenHouse greenHouse, Position lakePosition, ArrayList<Position> doorPositions, Position position, Player owner) {
        this.house = house;
        this.mine = mine;
        this.greenHouse = greenHouse;
        this.lakePosition = lakePosition;
        this.doorPositions = doorPositions;
        this.position = position;
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public Position getPosition() {
        return position;
    }

    public House getHouse() {
        return house;
    }

    public Mine getMine() {
        return mine;
    }

    public GreenHouse getGreenHouse() {
        return greenHouse;
    }

    public Position getLakePosition() {
        return lakePosition;
    }

    public ArrayList<Position> getDoorPositions() {
        return doorPositions;
    }
}
