package com.stardew.Models;

import com.stardew.Models.Game.App;
import com.stardew.Models.Items.Buildings.*;
import com.stardew.Models.Game.Player;

import java.util.ArrayList;

public class Farm {
    private final int FARM_SIZE =GameMap.getFarmSize();
    private final int STRUCTURE_SIZE = GameMap.getFarmSize()/4;
    private final Player owner;
    private final Position position;
    private final House house;
    private final GreenHouse greenHouse;
    private final Lake lake;
    private final ArrayList<Position> doorPositions;

    public Farm(Position position, Player owner) {
        this.position = position;
        this.owner = owner;
        this.doorPositions = new ArrayList<>();
        this.house = createHouse();
        this.greenHouse = createGreenHouse();
        this.lake = createLake();
    }

    private House createHouse() {
        // Initialize the house with its position
        return new House(new Position(position.getX(), position.getY(), STRUCTURE_SIZE, STRUCTURE_SIZE));
    }

    private GreenHouse createGreenHouse() {
        // Initialize the greenhouse with its position
        return new GreenHouse(new Position(position.getX(), position.getY() + 3*STRUCTURE_SIZE, STRUCTURE_SIZE, STRUCTURE_SIZE));
    }

    private Lake createLake() {
        // Initialize the lake with its position
        return new Lake(new Position(position.getX() + 3*STRUCTURE_SIZE, position.getY() + 3*STRUCTURE_SIZE, STRUCTURE_SIZE, STRUCTURE_SIZE));
    }

    public Building[] getBuildings(){
        return new Building[]{house, greenHouse, lake};
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

    public Tile[][] getMine() {
        Tile[][] tiles = new Tile[STRUCTURE_SIZE][STRUCTURE_SIZE];
        for(int i =0 ; i< STRUCTURE_SIZE; i++){
            for(int j = 0; j< STRUCTURE_SIZE; j++){
                tiles[j][i] = App.getGame().getGameMap().getTiles()[position.getY()+j][position.getX() + 3*STRUCTURE_SIZE+i];
            }
        }
        return tiles;
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
