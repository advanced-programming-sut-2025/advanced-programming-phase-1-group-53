package com.stardew.Models.Items.Buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Controllers.GameMenuController;
import com.stardew.Enums.MapsNames;
import com.stardew.Models.GameMap;
import com.stardew.Models.Position;
import com.stardew.Models.Tile;
import com.stardew.Enums.TileKind;

public abstract class Building {
    protected final Position position;
    protected Sprite sprite = new Sprite();
    protected final int SIZE = 10;
    protected final Tile[][] buildingMap;
    protected MapsNames mapsName; // Changed from final to non-final for flexibility

    public Building(Position position) {
        this.position = position;
        this.buildingMap = new Tile[SIZE][SIZE];
    }

    public Building setSprite(Texture texture){
        this.sprite = new Sprite(texture);
        return this;
    }

    public Sprite fixSpriteCoordinatesForPrint(){
        sprite.setX(GameMap.getTilePrintSize()*position.getX() );
        sprite.setY(GameMap.getTilePrintSize()*position.getY());
        sprite.setSize(GameMap.getTilePrintSize()*position.getWidth(),
            GameMap.getTilePrintSize()*position.getHeight());
        return sprite;
    }

    public Sprite getSprite(){
        sprite.setX(GameMap.getTilePrintSize()*position.getX() - GameMenuController.getPrintStartX());
        sprite.setY(GameMap.getTilePrintSize()*position.getY() - GameMenuController.getPrintStartY());
        sprite.setSize(GameMap.getTilePrintSize()*position.getWidth(),
            GameMap.getTilePrintSize()*position.getHeight());
        return sprite;
    }

    public Position getPosition() {
        return position;
    }

    public Tile[][] getBuildingMap() {
        return buildingMap;
    }

    public MapsNames getMapsName() {
        return mapsName;
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
