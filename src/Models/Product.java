package Models;

import Enums.Season;
import Models.Items.Item;

import java.util.ArrayList;

public class Product {
    private final int price;
    private final Item item;
    private final int requiredAbility;  //0 for mining 1 for fishing 2 for farming 3 for nature
    private final int requiredLevel;
    private int dailyLimit;
    private int secondaryPrice;
    private Season season = null;

    private Product(int price, Item item, int requiredAbility, int requiredLevel, int dailyLimit) {
        this.price = price;
        this.item = item;
        this.requiredAbility = requiredAbility;
        this.requiredLevel = requiredLevel;
        this.dailyLimit = dailyLimit;
    }

    private Product addSeason(Season season, int secondaryPrice){
        this.season = season;
        this.secondaryPrice = secondaryPrice;
        return this;
    }

    private Product addAbility(int requiredAbility, int requiredLevel){
        this.requiredAbility = requiredAbility;
        this.requiredLevel = requiredLevel;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Item getItem() {
        return item;
    }

    public int getRequiredAbility() {
        return requiredAbility;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public int getSecondaryPrice() {
        return secondaryPrice;
    }

    public Season getSeason() {
        return season;
    }

    public static final Product FishSmoker = new Product(10000, )
}
