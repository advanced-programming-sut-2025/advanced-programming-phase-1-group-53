package Models.Items.Foragings;

import Enums.ItemType;
import Models.Game.App;
import Models.Items.CraftAbleAndArtisan.CraftAble;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Fruit extends Plant{

    private Fruit(ItemType itemType){
        super(itemType);
    }

    @Override
    public Fruit makeEdible(double energy){
        this.setEdible();
        this.energy = energy;
        return this;
    }

    @Override
    public Fruit clone(){
        Fruit fruit = new Fruit(getItemType());
        if(isEdible())
            fruit.makeEdible(energy);
        return fruit;
    }

    public static final Fruit Apricot = new Fruit(ItemType.Apricot).makeEdible(38);
    public static final Fruit Cherry = new Fruit(ItemType.Cherry).makeEdible(38);
    public static final Fruit Peach = new Fruit(ItemType.Peach).makeEdible(38);
    public static final Fruit Orange = new Fruit(ItemType.Orange).makeEdible(38);
    public static final Fruit Apple = new Fruit(ItemType.Apple).makeEdible(38);
    public static final Fruit Banana = new Fruit(ItemType.Banana).makeEdible(75);
    public static final Fruit Pomegranate = new Fruit(ItemType.Pomegranate).makeEdible(38);
    public static final Fruit Mango = new Fruit(ItemType.Mango).makeEdible(100);
    public static final Fruit OakResin = new Fruit(ItemType.OakResin);
    public static final Fruit MapleSyrup = new Fruit(ItemType.MapleSyrup);
    public static final Fruit PineTar = new Fruit(ItemType.PineTar);
    public static final Fruit Sap = new Fruit(ItemType.Sap).makeEdible(-2);
    public static final Fruit CommonMushroom = new Fruit(ItemType.CommonMushroom).makeEdible(38);
    public static final Fruit MysticSyrup = new Fruit(ItemType.MysticSyrup).makeEdible(500);


    public static final ArrayList<Fruit> allFruits = new ArrayList<>(){{
        add(Apricot);
        add(Cherry);
        add(Peach);
        add(Orange);
        add(Apple);
        add(Banana);
        add(Pomegranate);
        add(Mango);
        add(OakResin);
        add(MapleSyrup);
        add(PineTar);
        add(Sap);
        add(CommonMushroom);
        add(MysticSyrup);
    }};

    public static final ArrayList<Plant> Fruits = new ArrayList<>(){{
        add(Apricot);
        add(Cherry);
        add(Peach);
        add(Orange);
        add(Apple);
        add(Banana);
        add(Pomegranate);
        add(Mango);
        add(Peach);
        add(MysticSyrup);
        add(MapleSyrup);
        add(PlantAbleCrop.Grape);
        add(PlantAbleCrop.Strawberry);
        add(PlantAbleCrop.AncientFruit);
        add(PlantAbleCrop.Cranberry);
        add(PlantAbleCrop.Blueberry);
        add(PlantAbleCrop.Melon);
        add(PlantAbleCrop.Starfruit);
        add(PlantAbleCrop.PowderMelon);
        add(PlantAbleCrop.SweetGemBerry);
    }};

}
