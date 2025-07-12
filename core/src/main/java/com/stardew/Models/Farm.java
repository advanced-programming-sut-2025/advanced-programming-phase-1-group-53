package com.stardew.Models;

import com.stardew.Models.Items.Buildings.GreenHouse;
import com.stardew.Models.Items.Buildings.House;
import com.stardew.Models.Items.Buildings.Mine;
import com.stardew.Models.Items.Buildings.Lake;
import com.stardew.Models.Game.Player;

import java.util.ArrayList;

public class Farm {
    private final int FARM_SIZE = 20;
    private final int STRUCTURE_SIZE = 5;
    private final Player owner;
    private final Position position;
    private final House house;
    private final Mine mine;
    private final GreenHouse greenHouse;
    private final Lake lake;
    private final ArrayList<Position> doorPositions;

    public Farm(Position position, Player owner) {
        this.position = position;
        this.owner = owner;
        this.doorPositions = new ArrayList<>();
        this.house = createHouse();
        this.mine = createMine();
        this.greenHouse = createGreenHouse();
        this.lake = createLake();
    }

    private House createHouse() {
        // Initialize the house with its position
        return new House(new Position(position.getX(), position.getY(), STRUCTURE_SIZE, STRUCTURE_SIZE));
    }

    private Mine createMine() {
        // Initialize the mine with its position
        return new Mine(new Position(position.getX() + 15, position.getY(), STRUCTURE_SIZE, STRUCTURE_SIZE));
    }

    private GreenHouse createGreenHouse() {
        // Initialize the greenhouse with its position
        return new GreenHouse(new Position(position.getX(), position.getY() + 15, STRUCTURE_SIZE, STRUCTURE_SIZE));
    }

    private Lake createLake() {
        // Initialize the lake with its position
        return new Lake(new Position(position.getX() + 15, position.getY() + 15, STRUCTURE_SIZE, STRUCTURE_SIZE));
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

    public Lake getLake() {
        return lake;
    }

    public ArrayList<Position> getDoorPositions() {
        return doorPositions;
    }
}
