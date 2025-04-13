package Enums;

import java.util.HashMap;
import java.util.Map;

public enum Food {
    FriedEgg(50, 35,null);



    private final int energy;
    private final int price;
    private final Map<Product, Integer> ingredients;

    Food(int energy, int price, HashMap<Product, Integer> ingredients) {
        this.energy = energy;
        this.price = price;
        this.ingredients = ingredients;
    }
}
