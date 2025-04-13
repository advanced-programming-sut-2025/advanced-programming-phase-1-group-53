package Enums;

import java.net.Inet4Address;
import java.util.HashMap;

public enum Recipes {
    CherryBomb(),
    Bomb();

    private final String name;
    private final int price;
    private final HashMap<Product, Integer> ingredients;

    Recipes(String name, int price, HashMap<Product, Integer> ingredients) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }
}
