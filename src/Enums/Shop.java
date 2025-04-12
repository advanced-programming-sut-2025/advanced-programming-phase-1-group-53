package Enums;

import Models.Position;
import Models.Tile;

import java.util.ArrayList;
import java.util.HashMap;

public enum Shop {

    private HashMap<Product, Integer> productsPrice = new HashMap<>();
    private HashMap<Product, Integer> productQuantity = new HashMap<>();
    private NPC owner;
    private int openingHour;
    private int closingHour;
    private final Position position;
    private final ArrayList<ArrayList<Tile>> buildingMap;
    private final Position doorPosition;

    public void showAllProducts(boolean onlyAvailable) {}

}
