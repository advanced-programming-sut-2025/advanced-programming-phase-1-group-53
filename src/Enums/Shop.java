package Enums;

import Models.Position;
import Models.Tile;

import java.util.ArrayList;
import java.util.HashMap;

public enum Shop {
    BlackSmith("BlackSmith", null, null, null, 9, 16, NPC.Clint, null, null),
    JojaMart("JojaMart", null, null, null, 9, 23, NPC.Morris, null, null),
    PierreGeneralStore("Pierre's GeneralStore", null, null, null, 9, 17, NPC.Pierre, null, null),
    CarpetnerShop("Carpenter's Shop", null, null, null, 9, 20, NPC.Robin, null, null),
    FishShop("Fish Shop", null, null, null, 9, 17, NPC.Willie, null, null),
    MarniesRanch("Marnie's Ranch", null, null, null, 9, 16, NPC.Marnie, null, null),
    TheStarDoopSaloon("The Star Doop Saloon", null, null, null, 12, 0, NPC.Gus, null, null),;

    private HashMap<Product, Integer> productsPrice = new HashMap<>();
    private HashMap<Product, Integer> productQuantity = new HashMap<>();
    private String storeName;
    private NPC owner;
    private int openingHour;
    private int closingHour;
    private final Position position;
    private final ArrayList<ArrayList<Tile>> buildingMap;
    private final Position doorPosition;

    Shop(String storeName, Position doorPosition, ArrayList<ArrayList<Tile>> buildingMap, Position position, int closingHour, int openingHour, NPC owner, HashMap<Product, Integer> productQuantity, HashMap<Product, Integer> productsPrice) {
        this.doorPosition = doorPosition;
        this.storeName = storeName;
        this.buildingMap = buildingMap;
        this.position = position;
        this.closingHour = closingHour;
        this.openingHour = openingHour;
        this.owner = owner;
        this.productQuantity = productQuantity;
        this.productsPrice = productsPrice;
    }

    public void showAllProducts(boolean onlyAvailable) {}

}
