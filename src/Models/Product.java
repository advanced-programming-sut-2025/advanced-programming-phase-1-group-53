package Models;

import Enums.ItemType;
import Enums.Season;
import Models.Game.App;
import Models.Items.Item;

import java.util.ArrayList;

public class Product{
    private final int price;
    private final ItemType itemType;
    private int availableToday;
    private final int dailyLimit;
    private int secondaryPrice;
    private Season availableSeason;
    private Season season = null;

    private Product(ItemType itemType,int price, int dailyLimit) {
        this.price = price;
        this.itemType = itemType;
        this.availableToday = dailyLimit;
        this.dailyLimit = dailyLimit;
    }

    private Product makeNaturalProduct(ItemType itemType){
        return new Product(itemType,(int) App.getGame().getItemByItemType(itemType).getBaseSellPrice(), 20000);
    }

    private Product makeLevelLimit(int requiredLevel, int requiredAbility){
        return this;
    }

    private Product makeSeasonLimit(Season season, int secondaryPrice){
        this.season = season;
        this.secondaryPrice = secondaryPrice;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public int getAvailableToday() {
        return availableToday;
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
    public final static ArrayList<Product> BlackSmithProducts = new ArrayList<>(){{
        add(new Product(ItemType.CopperOre, 75, 200000));
        add(new Product(ItemType.IronOre, 150, 200000));
        add(new Product(ItemType.Coal, 150, 200000));
        add(new Product(ItemType.GoldOre, 400, 200000));
    }};

    public static final ArrayList<Product> MarniesRanchProducts = new ArrayList<>(){{
        add(new Product(ItemType.Hen, 800, 2));
        add(new Product(ItemType.Cow, 1500, 2));
        add(new Product(ItemType.Goat, 4000, 2));
        add(new Product(ItemType.Duck, 1200, 2));
        add(new Product(ItemType.Sheep, 8000, 2));
        add(new Product(ItemType.Rabbit, 8000, 2));
        add(new Product(ItemType.Dino, 14000, 2));
        add(new Product(ItemType.Pig, 16000, 2));
        add(new Product(ItemType.Hay, 50, 200000));
        add(new Product(ItemType.MilkPail, 1000, 1));
        add(new Product(ItemType.Shear, 1000, 1));
    }};
    public static final ArrayList<Product> StarDropSaloonProducts = new ArrayList<>(){{
        add(new Product(ItemType.Beer, 400, 20000));
        add(new Product(ItemType.Salad, 220, 20000));
        add(new Product(ItemType.Bread, 120, 20000));
        add(new Product(ItemType.Spaghetti, 240, 20000));
        add(new Product(ItemType.Pizza, 600, 20000));
        add(new Product(ItemType.Coffee, 300, 20000));
        add(new Product(ItemType.HashBrownsRecipe, 50, 1));
        add(new Product(ItemType.OmeletRecipe, 100, 1));
        add(new Product(ItemType.PancakesRecipe, 100, 1));
        add(new Product(ItemType.BreadRecipe, 100, 1));
        add(new Product(ItemType.TortillaRecipe, 100, 1));
        add(new Product(ItemType.PizzaRecipe, 150, 1));
        add(new Product(ItemType.MakiRollRecipe, 300, 1));
        add(new Product(ItemType.TripleShotEspressoRecipe, 5000, 1));
        add(new Product(ItemType.CookieRecipe, 300, 1));
    }};
    public static final ArrayList<Product> CarpetnersShopProducts = new ArrayList<>(){{
        add(new Product(ItemType.Wood, 10, 1));
        add(new Product(ItemType.Stone, 20, 1));
        add(new Product(ItemType.NormalBarn, 20, 1));
        add(new Product(ItemType.NormalCoop, 20, 1));
        add(new Product(ItemType.BigCoop, 20, 1));
        add(new Product(ItemType.BigBarn, 20, 1));
        add(new Product(ItemType.DeluxeBarn, 20, 1));
        add(new Product(ItemType.DeluxeCoop, 20, 1));
        add(new Product(ItemType.Well, 20, 1));
        add(new Product(ItemType.ShippingBin, 20, 200000));
    }};

    public static final ArrayList<Product> FishShopProducts = new ArrayList<>(){{
        add(new Product(ItemType.FishSmokerCR, 10000, 1));
        add(new Product(ItemType.TroutSoup, 250, 1));
    }};
}
