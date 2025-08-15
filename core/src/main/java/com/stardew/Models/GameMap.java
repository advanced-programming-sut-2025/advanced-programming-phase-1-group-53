package com.stardew.Models;

import com.badlogic.gdx.math.Vector2;
import com.stardew.Enums.ItemType;
import com.stardew.Enums.MapsNames;
import com.stardew.Enums.TileKind;
import com.stardew.Enums.Season;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Models.Items.Foragings.*;
import com.stardew.Models.Items.Buildings.*;
import com.stardew.Models.Items.ShippingBin;
import com.stardew.Models.NPC.NPC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GameMap {
    private static final int MAP_SIZE = 60;
    private static final int FARM_SIZE = MAP_SIZE/3;
    private static final int TILE_PRINT_SIZE = 80;
    private static final int STRUCTURE_WIDTH = 5;
    private static final int STRUCTURE_HEIGHT = 5;
    private static final int VILLAGE_SIZE = 20;
    private HashMap<Vector2, ItemType> reGenerateQue = new HashMap<>();
    public static final Position SEBASTIAN_POSITION = new Position(31, 31, 1, 1);
    public static final Position ABIGAIL_POSITION   = new Position(35, 32, 1, 1);
    public static final Position HARVEY_POSITION    = new Position(25, 34, 1, 1);
    public static final Position LIA_POSITION       = new Position(28, 36, 1, 1);
    public static final Position ROBIN_POSITION     = new Position(30, 37, 1, 1);

    public static int getMapSize(){
        return MAP_SIZE;
    }

    public static int getFarmSize(){
        return FARM_SIZE;
    }

    public static Vector2 getPositionByCoordinates(float x, float y){
        return new Vector2(x/TILE_PRINT_SIZE, y/TILE_PRINT_SIZE);
    }

    public static int getTilePrintSize(){
        return TILE_PRINT_SIZE;
    }
    private final Tile[][] tiles;
    private final ArrayList<Position> villageDoors = new ArrayList<>();

    public GameMap(List<Player> players) {
        this.tiles = new Tile[MAP_SIZE][MAP_SIZE];
        initializeMap(players);
    }

    public HashMap<Vector2, ItemType> getReGenerateQue() {
        return reGenerateQue;
    }

    private void initializeMap(List<Player> players) {
        // Fill the entire map with "wall" tiles
        System.out.println("aa");

        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                tiles[y][x] = new Tile(new Position(x, y, 1, 1), TileKind.grass);
            }
        }

        System.out.println("aa");

        int[] x= new int[]{0, MAP_SIZE-FARM_SIZE, 0, MAP_SIZE-FARM_SIZE};
        int[] y = new int[]{0, 0, MAP_SIZE-FARM_SIZE, MAP_SIZE-FARM_SIZE};
        // Initialize farms in the four corners
        for(int i = 0; i< players.size(); i++){
            initializeFarm(y[i], x[i], players.get(i));
        }
        System.out.println("aa");


        // Initialize the village at the center
        initializeVillage();
        System.out.println("aa");

    }

    private void initializeFarm(int startY, int startX, Player owner) {
        // Fill the farm area with "empty" tiles
        for (int y = startY; y < startY + FARM_SIZE; y++) {
            for (int x = startX; x < startX + FARM_SIZE; x++) {
                tiles[y][x] = new Tile(new Position(x, y, 1, 1), TileKind.grass);
            }
        }

        // Place structures in the four corners of the farm
        placeStructure(startY, startX, 2); // Bottom-left
        placeStructure(startY, startX + FARM_SIZE - STRUCTURE_WIDTH, 3); // Bottom-right
        placeStructure(startY + FARM_SIZE - STRUCTURE_HEIGHT, startX, 0); // Top-left
        placeStructure(startY + FARM_SIZE - STRUCTURE_HEIGHT, startX + FARM_SIZE - STRUCTURE_WIDTH, 1); // Top-right
        owner.position.setX((startX + FARM_SIZE / 2)*GameMap.getTilePrintSize());
        owner.position.setY((startY + FARM_SIZE / 2)*GameMap.getTilePrintSize());
        MapsNames location = findLocationInGameMap(owner.position.getX(), owner.position.getY());
        owner.setCurrentMap(location);
        owner.setMyFarm(location);
        Farm farm = new Farm((new Position(startX, startY, FARM_SIZE, FARM_SIZE)), owner);
        owner.setFarm(farm);
        addFarmDoors(startY, startX, owner);
    }

    private void placeStructure(int startY, int startX, int structureNumber) {
        TileKind tileKind = null;
        if(structureNumber == 0)
            tileKind = TileKind.greenhouse;
        if(structureNumber == 1)
            tileKind = TileKind.lake;
        if(structureNumber == 2)
            tileKind = TileKind.house;
        if(structureNumber == 3)
            tileKind = TileKind.mine;
        for (int y = startY; y < startY + STRUCTURE_HEIGHT; y++) {
            for (int x = startX; x < startX + STRUCTURE_WIDTH; x++) {
                tiles[y][x] = new Tile(new Position(x, y, 1, 1),tileKind);
            }
        }
    }

    private void initializeVillage() {
        int startY = (MAP_SIZE - VILLAGE_SIZE - FARM_SIZE);
        int startX = (MAP_SIZE - VILLAGE_SIZE - FARM_SIZE);

        // Fill the village area with "empty" tiles
        for (int y = startY; y < startY + VILLAGE_SIZE; y++) {
            for (int x = startX; x < startX + VILLAGE_SIZE; x++) {
                tiles[y][x] = new Tile(new Position(x, y, 1, 1), TileKind.asphalt);
            }
        }

        tiles[startY+VILLAGE_SIZE - 4][startX+VILLAGE_SIZE - 4].setItem(ShippingBin.ShippingBin);
        ShippingBin.ShippingBin.getPosition().setX(startX+VILLAGE_SIZE - 4);
        ShippingBin.ShippingBin.getPosition().setY(startY+VILLAGE_SIZE - 4);


        for(int i = 0; i< 3; i++){
            for(int j = 0; j<3; j++){
                tiles[startY+VILLAGE_SIZE - 4+j][startX+VILLAGE_SIZE - 4+i].setTileKind(TileKind.shippingBin);
            }
        }

        // Set shop tiles to STRUCTURE
        setShopTiles(Shop.TheStardropSaloon.getPosition());
        setShopTiles(Shop.JojaMart.getPosition());
        setShopTiles(Shop.PierreGeneralStore.getPosition());
        setShopTiles(Shop.Blacksmith.getPosition());
        setShopTiles(Shop.CarpenterShop.getPosition());
        setShopTiles(Shop.FishShop.getPosition());
        setShopTiles(Shop.MarineRanch.getPosition());

        // Set NPC tiles to NPC
        tiles[SEBASTIAN_POSITION.getY()][SEBASTIAN_POSITION.getX()] = new Tile(SEBASTIAN_POSITION, TileKind.NPC);
        tiles[ABIGAIL_POSITION.getY()][ABIGAIL_POSITION.getX()] = new Tile(ABIGAIL_POSITION, TileKind.NPC);
        tiles[HARVEY_POSITION.getY()][HARVEY_POSITION.getX()] = new Tile(HARVEY_POSITION, TileKind.NPC);
        tiles[LIA_POSITION.getY()][LIA_POSITION.getX()] = new Tile(LIA_POSITION, TileKind.NPC);
        tiles[ROBIN_POSITION.getY()][ROBIN_POSITION.getX()] = new Tile(ROBIN_POSITION, TileKind.NPC);

        //addVillageDoors(startY, startX);
    }

    private void addVillageDoors(int startY, int startX) {
        int middleY = startY + VILLAGE_SIZE / 2;
        int middleX = startX + VILLAGE_SIZE / 2;

        // Top side door
        tiles[startY][middleX] = new Tile(new Position(middleX, startY, 1, 1), TileKind.door);
        villageDoors.add(new Position(middleX, startY, 1, 1));

        // Bottom side door
        tiles[startY + VILLAGE_SIZE - 1][middleX] = new Tile(new Position(middleX, startY + VILLAGE_SIZE - 1, 1, 1), TileKind.door);
        villageDoors.add(new Position(middleX, startY + VILLAGE_SIZE - 1, 1, 1));

        // Left side door
        tiles[middleY][startX] = new Tile(new Position(startX, middleY, 1, 1), TileKind.door);
        villageDoors.add(new Position(startX, middleY, 1, 1));

        // Right side door
        tiles[middleY][startX + VILLAGE_SIZE - 1] = new Tile(new Position(startX + VILLAGE_SIZE - 1, middleY, 1, 1), TileKind.door);
        villageDoors.add(new Position(startX + VILLAGE_SIZE - 1, middleY, 1, 1));
    }

    private void setShopTiles(Position position) {
        for (int y = position.getY(); y < position.getY() + position.getHeight(); y++) {
            for (int x = position.getX(); x < position.getX() + position.getWidth(); x++) {
                tiles[y][x] = new Tile(new Position(x, y, 1, 1), TileKind.shop);
            }
        }
    }

    public Shop getShopByPosition(int x, int y){
        for(Shop shop:Shop.shops){
            if(shop.getPosition().isHere(x, y)){
                return shop;
            }
        }
        return null;
    }

    public Tile getTileByPixelCoordinate(float x, float y) {
        if (x < 0 || x >= GameMap.getTilePrintSize() *MAP_SIZE || y < 0 || y >= GameMap.getTilePrintSize() *MAP_SIZE) {
            throw new IndexOutOfBoundsException("Invalid tile position");
        }
        int yy =(int) y/GameMap.getTilePrintSize() ;
        int xx =(int) x/GameMap.getTilePrintSize() ;
        return tiles[yy][xx];
    }

    public boolean areInBound(float x, float y){
        if (x <= 0 || x >= GameMap.getTilePrintSize() *MAP_SIZE || y <= 0 || y >= GameMap.getTilePrintSize() *MAP_SIZE) {
            return false;
        }
        return true;
    }

    public Tile getTileByPosition(int x, int y) {
        if (x < 0 || x >= MAP_SIZE || y < 0 || y >= MAP_SIZE) {
            throw new IndexOutOfBoundsException("Invalid tile position");
        }
        return tiles[y][x];
    }

    public ArrayList<Position> getVillageDoors() {
        return villageDoors;
    }

    private void addFarmDoors(int startY, int startX, Player owner) {
        int middleY = startY + FARM_SIZE / 2;
        int middleX = startX + FARM_SIZE / 2;

        // Top side door
        tiles[startY][middleX] = new Tile(new Position(middleX, startY, 1, 1), TileKind.door);
        owner.getFarm().getDoorPositions().add(new Position(middleX, startY, 1, 1));

        // Bottom side door
        tiles[startY + FARM_SIZE - 1][middleX] = new Tile(new Position(middleX, startY + FARM_SIZE - 1, 1, 1), TileKind.door);
        owner.getFarm().getDoorPositions().add(new Position(middleX, startY + FARM_SIZE - 1, 1, 1));

        // Left side door
        tiles[middleY][startX] = new Tile(new Position(startX, middleY, 1, 1), TileKind.door);
        owner.getFarm().getDoorPositions().add(new Position(startX, middleY, 1, 1));

        // Right side door
        tiles[middleY][startX + FARM_SIZE - 1] = new Tile(new Position(startX + FARM_SIZE - 1, middleY, 1, 1), TileKind.door);
        owner.getFarm().getDoorPositions().add(new Position(startX + FARM_SIZE - 1, middleY, 1, 1));
    }

    public void generateRandomThings(int randomPercent) {
//        generateWoodAndStone(randomPercent);
//        generateMineralsInMine(App.getGame().getCurrentPlayer().getFarm().getMine(), 10*randomPercent);
//        generateForagingSeeds(randomPercent);
//        generateForagingTrees(randomPercent);
//        generateForagingCrops(randomPercent);
    }

    public void generateRandomThings(List<Player> players, int randomPercent) {
//        generateWoodAndStone(10*randomPercent);
//        generateForagingSeeds(10*randomPercent);
//        generateForagingTrees(6*randomPercent);
//        generateForagingCrops(10*randomPercent);
//        for(Player p : players){
//            generateMineralsInMine(p.getFarm().getMine(), randomPercent);
//        }
    }

    private void generateWoodAndStone(int randomPercent) {
        Random random = new Random();
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                // Check if the tile is EMPTY or GRASS and has no item
                if ((tiles[y][x].getTileKind() == TileKind.empty || tiles[y][x].getTileKind() == TileKind.grass) && tiles[y][x].getItem() == null) {
                    // 1% chance to place a stone or wood
                    if (random.nextInt(randomPercent) < 1) {
                        ForagingMineral randomForagingMineral;
                        int h = random.nextInt(3);
                        if (h == 0) {
                            randomForagingMineral = ForagingMineral.Stone.clone();
                            randomForagingMineral.getPosition().setX(x);
                            randomForagingMineral.getPosition().setY(y);
                            tiles[y][x].setItem(randomForagingMineral); // Place a stone
                        }
                        else if (h == 1) {
                            randomForagingMineral = ForagingMineral.Fiber.clone();
                            randomForagingMineral.getPosition().setX(x);
                            randomForagingMineral.getPosition().setY(y);
                            tiles[y][x].setItem(randomForagingMineral);
                        }
                        else {
                            randomForagingMineral = ForagingMineral.Wood.clone();
                            randomForagingMineral.getPosition().setX(x);
                            randomForagingMineral.getPosition().setY(y);
                            tiles[y][x].setItem(randomForagingMineral); // Place wood
                        }
                        tiles[y][x].setTileKind(TileKind.foraging);
                    }
                }
            }
        }
    }

    public void generateMineralsInMine(Tile[][] mineTiles, int randomPercent) {
        Random random = new Random();
        for (int y = 0; y < mineTiles.length; y++) {
            for (int x = 0; x < mineTiles[y].length; x++) {

                // Check if the tile is EMPTY and has no item
                if (mineTiles[y][x].getTileKind() == TileKind.mine && mineTiles[y][x].getItem() == null) {
                    // 0.1% chance to place a random ForagingMineral
                    if (random.nextInt(randomPercent) < 1) {
                        System.out.println(mineTiles[y][x].getPosition().getX()+ "ajncabdcbw"+mineTiles[y][x].getPosition().getY());

                        // Select a random mineral from the list of remaining minerals
                        ForagingMineral randomMineral = ForagingMineral.minerals.get(random.nextInt(ForagingMineral.minerals.size())).clone();
                        if(randomMineral.getItemType() != ItemType.Wood && randomMineral.getItemType() != ItemType.Stone &&
                            randomMineral.getItemType() != ItemType.Fiber) {
                            tiles[mineTiles[y][x].getPosition().getY()][mineTiles[y][x].getPosition().getX()].setTileKind(TileKind.foragingMineral);
                            randomMineral.getPosition().setX(mineTiles[y][x].getPosition().getX());
                            randomMineral.getPosition().setY(mineTiles[y][x].getPosition().getY());
                            mineTiles[y][x].setItem(randomMineral);
                        }
                    }
                }
            }
        }
    }

    public void generateForagingSeeds(int randomPercent) {
        // Temporary season variable (replace with actual game season later)
        Season currentSeason = App.getGame().dateAndTime.getSeason();

        Random random = new Random();
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {

                // Check if the tile is PLOWED and has no item
                if (tiles[y][x].getTileKind() == TileKind.plowed && tiles[y][x].getItem() == null) {
                    // 1% chance to place a ForagingSeed
                    if (random.nextInt(randomPercent) < 1) {
                        // Select a random ForagingSeed
                        ForagingSeed randomSeed = ForagingSeed.foragingSeeds.get(random.nextInt(ForagingSeed.foragingSeeds.size()));
                        PlantAbleCrop plantAbleCrop = PlantAbleCrop.getCropBySeed(randomSeed.getItemType());
                        tiles[y][x].setItem(plantAbleCrop.clone());
                        // Check if the current season is valid for the seed
                        if (randomSeed.getSeasons().contains(currentSeason)) {
                            tiles[y][x].setItem(randomSeed.clone());
                        }
                    }
                }
            }
        }
    }

    public void generateForagingTrees(int randomPercent) {
        // Temporary season variable (replace with actual game season later)
        Season currentSeason = App.getGame().dateAndTime.getSeason();

        Random random = new Random();
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {

                // Check if the tile is EMPTY or GRASS and has no item
                if ((tiles[y][x].getTileKind() == TileKind.empty || tiles[y][x].getTileKind() == TileKind.grass) && tiles[y][x].getItem() == null) {
                    // 1% chance to place a ForagingTree
                    if (random.nextInt(randomPercent) < 1) {
                        // Select a random ForagingTree
                        ForagingTree randomTree = ForagingTree.trees.get(random.nextInt(ForagingTree.trees.size())).clone();

                        // Check if the current season is valid for the tree
                        if (randomTree.getSeasons().contains(currentSeason)) {
                            tiles[y][x].setTileKind(TileKind.foraging);
                            randomTree.getPosition().setX(x);
                            randomTree.getPosition().setY(y);
                            tiles[y][x].setItem(randomTree);
                        }
                    }
                }
            }
        }
    }

    public void generateForagingCrops(int randomPercent) {
        // Temporary season variable (replace with actual game season later)
        Season currentSeason = App.getGame().dateAndTime.getSeason();

        Random random = new Random();
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                Tile tile = tiles[y][x];

                // Check if the tile is EMPTY or GRASS and has no item
                if ((tile.getTileKind() == TileKind.empty || tile.getTileKind() == TileKind.grass) && tile.getItem() == null) {
                    // 1% chance to place a ForagingCrop
                    if (random.nextInt(randomPercent) < 1) {
                        // Select a random ForagingCrop
                        ForagingCrop randomCrop = ForagingCrop.foragingCrops.get(random.nextInt(ForagingCrop.foragingCrops.size()));

                        // Check if the current season is valid for the crop
                        if (randomCrop.getSeasons().contains(currentSeason)) {
                            tile.setItem(randomCrop.clone());
                        }
                    }
                }
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public MapsNames findLocationInGameMap(int x, int y) {
        if (x < 0 || x >= MAP_SIZE || y < 0 || y >= MAP_SIZE) {
            return null; // Invalid position
        }
        else if (x < FARM_SIZE && y < FARM_SIZE) {
            return MapsNames.Farm1;
        } else if (y < FARM_SIZE && x >= MAP_SIZE - FARM_SIZE) {
            return MapsNames.Farm2;
        } else if (y >= MAP_SIZE - FARM_SIZE && x < FARM_SIZE) {
            return MapsNames.Farm3;
        } else if (x >= MAP_SIZE - FARM_SIZE && y >= MAP_SIZE - FARM_SIZE) {
            return MapsNames.Farm4;
        } else if (x >= FARM_SIZE && x < MAP_SIZE - FARM_SIZE && y >= FARM_SIZE && y < MAP_SIZE - FARM_SIZE) {
            return MapsNames.Village;
        } else {
            return null; // Not in any defined location
        }
    }

    /*public void changeMapIfEnterBuilding(int x, int y) {
        Player player = App.getGame().getCurrentPlayer();
        Building building = findBuilding(x, y);
        if (building == null || building.getBuildingMap() == null) return;
        App.getGame().setCurrentMap(building.getBuildingMap());
        player.position.setX(building.getPosition().getX() + building.getBuildingMap()[0].length / 2);
        player.position.setY(building.getPosition().getY() + building.getBuildingMap().length / 2);
        player.setCurrentMap(building.getMapsName());
    }*/


    public Building findBuilding(int x, int y) {
        // Check if in the village area
        if (x >= FARM_SIZE && x < MAP_SIZE - FARM_SIZE && y >= FARM_SIZE && y < MAP_SIZE - FARM_SIZE) {
            // Check each shop's area
            if (isInside(x, y, Shop.CarpenterShop.getPosition())) {
                return Shop.CarpenterShop;
            } else if (isInside(x, y, Shop.FishShop.getPosition())) {
                return Shop.FishShop;
            } else if (isInside(x, y, Shop.Blacksmith.getPosition())) {
                return Shop.Blacksmith;
            } else if (isInside(x, y, Shop.JojaMart.getPosition())) {
                return Shop.JojaMart;
            } else if (isInside(x, y, Shop.PierreGeneralStore.getPosition())) {
                return Shop.PierreGeneralStore;
            } else if (isInside(x, y, Shop.MarineRanch.getPosition())) {
                return Shop.MarineRanch;
            } else if (isInside(x, y, Shop.TheStardropSaloon.getPosition())) {
                return Shop.TheStardropSaloon;
            }
        } else {
            // Check for buildings in the current player's farm
            Farm farm = App.getGame().getCurrentPlayer().getFarm();
            if (farm != null) {
                if (isInside(x, y, farm.getHouse().getPosition())) {
                    return farm.getHouse();
                } else if (isInside(x, y, farm.getGreenHouse().getPosition())) {
                    return farm.getGreenHouse();}
//                } else if (isInside(x, y, farm.getMine())) {
//                    return farm.getMine();
//                }
                else if (isInside(x, y, farm.getLake().getPosition())) {
                    return farm.getLake();
                }
            }
        }
        // Not in any known building
        return null;
    }

    public static boolean isInside(int x, int y, Position pos) {
        return x >= pos.getX() && x < pos.getX() + pos.getWidth()
                && y >= pos.getY() && y < pos.getY() + pos.getHeight();
    }

    public boolean amINearPlayer(ItemType itemType) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        Player player = App.getGame().getCurrentPlayer();
        Position myPosition = player.position;
        for (int i = 0; i < dx.length; i++) {
            int newX = myPosition.getX() + dx[i];
            int newY = myPosition.getY() + dy[i];
            if (newX >= 0 && newX < MAP_SIZE && newY >= 0 && newY < MAP_SIZE) {
                Tile tile = tiles[newY][newX];
                if (tile.getItem() != null && tile.getItem().getItemType() == itemType) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean amINearPlayer(NPC npc) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        Player player = App.getGame().getCurrentPlayer();
        Position myPosition = player.position;
        for (int i = 0; i < dx.length; i++) {
            int newX = myPosition.getX() + dx[i];
            int newY = myPosition.getY() + dy[i];
            int NPCx = npc.getPosition().getX();
            int NPCy = npc.getPosition().getY();
            if (newX >= 0 && newX < MAP_SIZE && newY >= 0 && newY < MAP_SIZE) {
                if (newX == NPCx && newY == NPCy) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean amINearPlayer(Shop shop) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        Player player = App.getGame().getCurrentPlayer();
        Position myPosition = player.position;
        for (int i = 0; i < dx.length; i++) {
            int newX = myPosition.getX() + dx[i];
            int newY = myPosition.getY() + dy[i];
            Position shopPosition = shop.getPosition();
            if (newX >= 0 && newX < MAP_SIZE && newY >= 0 && newY < MAP_SIZE) {
                if (newX >= shopPosition.getX() && newX < shopPosition.getX() + shop.getPosition().getX() &&
                        newY >= shopPosition.getY() && newY < shopPosition.getY() + shop.getPosition().getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean amINearPlayer(Lake lake) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        Player player = App.getGame().getCurrentPlayer();
        Position myPosition = player.position;
        for (int i = 0; i < dx.length; i++) {
            int newX = myPosition.getX() + dx[i];
            int newY = myPosition.getY() + dy[i];
            Position lakePosition = lake.getPosition();
            if (newX >= 0 && newX < MAP_SIZE && newY >= 0 && newY < MAP_SIZE) {
                if (newX >= lakePosition.getX() && newX < lakePosition.getX() + lake.getPosition().getX() &&
                        newY >= lakePosition.getY() && newY < lakePosition.getY() + lake.getPosition().getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean amINearPlayer(Player player) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        Position myPosition = App.getGame().getCurrentPlayer().position;
        Position otherPosition = player.position;
        for (int i = 0; i < dx.length; i++) {
            int newX = myPosition.getX() + dx[i];
            int newY = myPosition.getY() + dy[i];
            if (newX == otherPosition.getX() && newY == otherPosition.getY()) {
                return true;
            }
        }
        return false;
    }
}
