package Models.Items;

import Enums.ItemType;
import Models.Items.Item;

import java.util.ArrayList;

public class AnimalProduct extends Item {

    private AnimalProduct(ItemType itemType, double price){
        super(itemType);
        this.baseSellPrice = price;
    }

    @Override
    public AnimalProduct makeSellPrice(double price){
        this.baseSellPrice = price;
        return this;
    }

    @Override
    public AnimalProduct clone() {
        return new AnimalProduct(itemType, baseSellPrice).makeSellPrice(baseSellPrice);
    }

    public static final AnimalProduct Egg = new AnimalProduct(ItemType.Egg, 50);
    public static final AnimalProduct BigEgg = new AnimalProduct(ItemType.BigEgg, 95);
    public static final AnimalProduct DuckEgg = new AnimalProduct(ItemType.DuckEgg, 95);
    public static final AnimalProduct DinoEgg = new AnimalProduct(ItemType.DinoEgg, 350);
    public static final AnimalProduct DuckFeather = new AnimalProduct(ItemType.DuckFeather, 250);
    public static final AnimalProduct RabbitLeg = new AnimalProduct(ItemType.RabbitLeg, 565);
    public static final AnimalProduct RabbitWool = new AnimalProduct(ItemType.RabbitWool, 340);
    public static final AnimalProduct Milk = new AnimalProduct(ItemType.CowMilk, 125);
    public static final AnimalProduct BigMilk = new AnimalProduct(ItemType.BigCowMilk, 190);
    public static final AnimalProduct GoatMilk = new AnimalProduct(ItemType.GoatMilk, 225);
    public static final AnimalProduct BigGoatMilk = new AnimalProduct(ItemType.BigGoatMilk, 345);
    public static final AnimalProduct SheepWool = new AnimalProduct(ItemType.SheepWool, 340);
    public static final AnimalProduct Truffle = new AnimalProduct(ItemType.Truffle, 625);

    public static ArrayList<AnimalProduct> allAnimalProducts = new ArrayList<>(){{
        add(Egg);
        add(BigEgg);
        add(DuckEgg);
        add(DuckFeather);
        add(DinoEgg);
        add(RabbitLeg);
        add(RabbitWool);
        add(Milk);
        add(BigMilk);
        add(GoatMilk);
        add(BigGoatMilk);
        add(SheepWool);
        add(Truffle);
    }};
}
