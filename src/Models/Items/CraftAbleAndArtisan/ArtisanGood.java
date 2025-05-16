package Models.Items.CraftAbleAndArtisan;

import Enums.ItemType;
import Models.Game.App;
import Models.Items.AnimalProduct;
import Models.Items.Fish;
import Models.Items.Foragings.*;
import Models.Items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArtisanGood extends Item {
    private int processingTimeLeft = 0;
    private boolean isPicked = false;
    private final Map<Item, Integer> ingredients = new HashMap<>();

    ArtisanGood(ItemType itemType, int processingTimeLeft, Map<Item, Integer> ingredients){
        super(itemType);
        this.ingredients.putAll(ingredients);
        this.processingTimeLeft = processingTimeLeft;
    }

    public boolean isPicked() {
        return isPicked;
    }

    public void setPicked(boolean picked) {
        isPicked = picked;
    }

    public int getProcessingTimeLeft() {
        return processingTimeLeft;
    }

    public Map<Item, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public ArtisanGood clone(){
        return new ArtisanGood(itemType, processingTimeLeft, ingredients);
    }

    @Override
    public ArtisanGood makeEdible(double energy){
        isEdible = true;
        this.energy = energy;
        return this;
    }

    @Override
    public void update(){
        processingTimeLeft =(int) Math.max(0, processingTimeLeft - App.getGame().dateAndTime.getPassedHours());
    }

    @Override
    public ArtisanGood makeSellPrice(double price) {
        this.baseSellPrice = price;
        return this;
    }

    private static void addJuice(ArrayList<ArtisanGood> allArtisanGoods){
        for(PlantAbleCrop plantAbleCrop : PlantAbleCrop.Vegetables){
            if(getJuiceByCrop(plantAbleCrop.getItemType()) == null)
                continue;
            allArtisanGoods.add( new ArtisanGood(getJuiceByCrop(plantAbleCrop.getItemType()), 4*24, Map.of(plantAbleCrop, 1)).makeEdible(plantAbleCrop.getEnergy()*2).makeSellPrice(2.25 * plantAbleCrop.getBaseSellPrice()));
        }
    }

    private static void addSmokedFish(ArrayList<ArtisanGood> allArtisanGoods){
        for(Fish fish : Fish.fishes){
            if(getSmokedByFish(fish.getItemType()) == null)
                continue;
            allArtisanGoods.add(new ArtisanGood(getSmokedByFish(fish.getItemType()), 1, Map.of(fish, 1, ArtisanGood.Coal, 1)).makeEdible(1.5*fish.getEnergy()).makeSellPrice(2*fish.getBaseSellPrice()));
        }
    }

    private static void addDriedFruit(ArrayList<ArtisanGood> allArtisanGoods){
        for(Plant plant : Fruit.Fruits){
            if(getDriedFruitByFruit(plant.getItemType()) == null)
                continue;
            if(plant.getItemType().equals(ItemType.Grape))
                continue;
            if(plant instanceof Fruit fruit){
                allArtisanGoods.add( new ArtisanGood(getDriedFruitByFruit(fruit.getItemType()), 16, Map.of(fruit, 5)).makeEdible(75).makeSellPrice(7.5* fruit.getBaseSellPrice() + 25));
            }
            if(plant instanceof PlantAbleCrop plantAbleCrop){
                allArtisanGoods.add( new ArtisanGood(getDriedFruitByFruit(plantAbleCrop.getItemType()), 16, Map.of(plantAbleCrop, 5)).makeEdible(75).makeSellPrice(7.5* plantAbleCrop.getBaseSellPrice() + 25));
            }
        }
    }

    private static void addDriedMush(ArrayList<ArtisanGood> allArtisanGoods){
        for(ForagingCrop foragingCrop : ForagingCrop.Mushrooms){
            if(getDriedMushroomByMushroom(foragingCrop.getItemType()) == null)
                continue;
            allArtisanGoods.add( new ArtisanGood(getDriedMushroomByMushroom(foragingCrop.getItemType()), 16, Map.of(foragingCrop, 5)).makeEdible(50).makeSellPrice(foragingCrop.getBaseSellPrice()*7.5 + 25));
        }
    }

    private static void addJelly(ArrayList<ArtisanGood> allArtisanGoods){
        for(Plant plant : Fruit.Fruits){
            if(getJellyByFruit(plant.getItemType()) == null)
                continue;
            if(plant instanceof Fruit fruit) {
                allArtisanGoods.add(new ArtisanGood(getJellyByFruit(fruit.getItemType()), 3 * 24, Map.of(fruit, 1)).makeEdible(fruit.getEnergy() * 2).makeSellPrice(2 * fruit.getBaseSellPrice() + 50));
            }
            if(plant instanceof PlantAbleCrop plantAbleCrop) {
                allArtisanGoods.add(new ArtisanGood(getJellyByFruit(plantAbleCrop.getItemType()), 3 * 24, Map.of(plantAbleCrop, 1)).makeEdible(plantAbleCrop.getEnergy() * 2).makeSellPrice(2 * plantAbleCrop.getBaseSellPrice() + 50));
            }
        }
    }

    private static void addPickles(ArrayList<ArtisanGood> allArtisanGoods){
        for(PlantAbleCrop plantAbleCrop : PlantAbleCrop.Vegetables){
            if(getPicklesByCrop(plantAbleCrop.getItemType()) == null)
                continue;
            allArtisanGoods.add( new ArtisanGood(getPicklesByCrop(plantAbleCrop.getItemType()), 6, Map.of(plantAbleCrop, 1)).makeEdible(plantAbleCrop.getEnergy()*1.75).makeSellPrice(2*plantAbleCrop.getBaseSellPrice()+50));
        }
    }

    private static void addWine(ArrayList<ArtisanGood> allArtisanGoods){
        for(Plant plant : Fruit.Fruits){
            if(getWineByFruit(plant.getItemType()) == null)
                continue;
            if(plant instanceof Fruit fruit)
                allArtisanGoods.add( new ArtisanGood(getWineByFruit(fruit.getItemType()), 7*24, Map.of(fruit, 1)).makeEdible(fruit.getEnergy()*1.75).makeSellPrice(3*fruit.getBaseSellPrice()));
            if(plant instanceof PlantAbleCrop plantAbleCrop)
                allArtisanGoods.add( new ArtisanGood(getWineByFruit(plantAbleCrop.getItemType()), 7*24, Map.of(plantAbleCrop, 1)).makeEdible(plantAbleCrop.getEnergy()*1.75).makeSellPrice(3*plantAbleCrop.getBaseSellPrice()));
        }
    }

    private static ItemType getSmokedByFish(ItemType fishType){
        for(ItemType itemType1 : ItemType.values()){
            if(itemType1.name().contains("Smoked")){
                if(itemType1.name().toLowerCase().contains(fishType.name().toLowerCase()))
                    return itemType1;
            }
        }
        return null;
    }

    private static ItemType getJuiceByCrop(ItemType cropType){
        for(ItemType itemType1 : ItemType.values()){
            if(itemType1.name().contains("Juice")){
                if(itemType1.name().toLowerCase().contains(cropType.name().toLowerCase()))
                    return itemType1;
            }
        }
        return null;
    }

    private static ItemType getPicklesByCrop(ItemType cropType){
        for(ItemType itemType1 : ItemType.values()){
            if(itemType1.name().contains("Pickles")){
                if(itemType1.name().toLowerCase().contains(cropType.name().toLowerCase()))
                    return itemType1;
            }
        }
        return null;
    }

    private static ItemType getDriedFruitByFruit(ItemType fruitType){
        for(ItemType itemType1 : ItemType.values()){
            if(itemType1.name().contains("Dried")){
                if(itemType1.name().toLowerCase().contains(fruitType.name().toLowerCase()))
                    return itemType1;
            }
        }
        return null;
    }

    private static ItemType getDriedMushroomByMushroom(ItemType mushType){
        for(ItemType itemType1 : ItemType.values()){
            if(itemType1.name().contains("Dried")){
                if(itemType1.name().toLowerCase().contains(mushType.name().toLowerCase()))
                    return itemType1;
            }
        }
        return null;
    }

    private static ItemType getJellyByFruit(ItemType fruitType){
        for(ItemType itemType1 : ItemType.values()){
            if(itemType1.name().contains("Jelly")){
                if(itemType1.name().toLowerCase().contains(fruitType.name().toLowerCase()))
                    return itemType1;
            }
        }
        return null;
    }

    private static ItemType getWineByFruit(ItemType fruitType){
        for(ItemType itemType1 : ItemType.values()){
            if(itemType1.name().contains("Wine")){
                if(itemType1.name().toLowerCase().contains(fruitType.name().toLowerCase()))
                    return itemType1;
            }
        }
        return null;
    }

    public static final ArtisanGood Honey = new ArtisanGood(ItemType.Honey, 4*24, new HashMap<>()).makeEdible(75).makeSellPrice(350);
    public static final ArtisanGood Cheese = new ArtisanGood(ItemType.Cheese, 3, Map.of(AnimalProduct.Milk, 1)).makeEdible(100).makeSellPrice(230);
    public static final ArtisanGood BigCheese = new ArtisanGood(ItemType.BigCheese, 3, Map.of(AnimalProduct.BigMilk, 1)).makeEdible(100).makeSellPrice(345);
    public static final ArtisanGood GoatCheese = new ArtisanGood(ItemType.GoatCheese, 3, Map.of(AnimalProduct.GoatMilk, 1)).makeEdible(100).makeSellPrice(400);
    public static final ArtisanGood BigGoatCheese = new ArtisanGood(ItemType.BigGoatCheese, 3, Map.of(AnimalProduct.BigGoatMilk, 1)).makeEdible(100).makeSellPrice(600);
    public static final ArtisanGood Beer = new ArtisanGood(ItemType.Beer, 24, Map.of(PlantAbleCrop.Wheat, 1)).makeEdible(50).makeSellPrice(200);
    public static final ArtisanGood Vinegar = new ArtisanGood(ItemType.Vinegar, 10, Map.of(PlantAbleCrop.Rice, 1)).makeEdible(13).makeSellPrice(100);
    public static final ArtisanGood Coffee = new ArtisanGood(ItemType.Coffee, 2, Map.of(PlantAbleCrop.CoffeeBean, 5)).makeEdible(75).makeSellPrice(150);
    public static final ArtisanGood Mead = new ArtisanGood(ItemType.Mead, 10, Map.of(ArtisanGood.Honey, 1)).makeEdible(100).makeSellPrice(300);
    public static final ArtisanGood PaleAle = new ArtisanGood(ItemType.PaleAle, 24*3, Map.of(PlantAbleCrop.Hops, 1)).makeEdible(50).makeSellPrice(300);
    public static final ArtisanGood Raisins = new ArtisanGood(ItemType.Raisins, 24, Map.of(PlantAbleCrop.Grape, 5)).makeEdible(125).makeSellPrice(600);
    public static final ArtisanGood Coal = new ArtisanGood(ItemType.Coal, 1, Map.of(ForagingMineral.Wood, 10)).makeSellPrice(50);
    public static final ArtisanGood SheepCloth = new ArtisanGood(ItemType.Cloth, 4, Map.of(AnimalProduct.SheepWool, 1)).makeSellPrice(470);
    public static final ArtisanGood RabbitCloth = new ArtisanGood(ItemType.Cloth, 4, Map.of(AnimalProduct.RabbitWool, 1)).makeSellPrice(470);
    public static final ArtisanGood BigMayonnaise = new ArtisanGood(ItemType.BigMayonnaise, 3, Map.of(AnimalProduct.BigEgg, 1)).makeEdible(50).makeSellPrice(237);
    public static final ArtisanGood Mayonnaise = new ArtisanGood(ItemType.Mayonnaise, 3, Map.of(AnimalProduct.Egg, 1)).makeEdible(50).makeSellPrice(190);
    public static final ArtisanGood DuckMayonnaise = new ArtisanGood(ItemType.DuckMayonnaise, 3, Map.of(AnimalProduct.DuckEgg, 1)).makeEdible(75).makeSellPrice(37);
    public static final ArtisanGood DinoMayonnaise = new ArtisanGood(ItemType.DinoMayonnaise, 3, Map.of(AnimalProduct.DinoEgg, 1)).makeEdible(125).makeSellPrice(800);
    public static final ArtisanGood TruffleOil = new ArtisanGood(ItemType.TruffleOil, 6, Map.of(AnimalProduct.Truffle, 1)).makeEdible(38).makeSellPrice(1065);
    public static final ArtisanGood CornOil = new ArtisanGood(ItemType.CornOil, 6, Map.of(PlantAbleCrop.Corn, 1)).makeEdible(13).makeSellPrice(100);
    public static final ArtisanGood SunFlowerOil = new ArtisanGood(ItemType.SunFlowerOil, 2*24, Map.of(PlantAbleCrop.Sunflower, 1)).makeEdible(13).makeSellPrice(100);
    public static final ArtisanGood SunflowerSeedOil = new ArtisanGood(ItemType.SunFlowerSeedOil, 1, Map.of(ForagingSeed.SunflowerSeeds, 1)).makeEdible(13).makeSellPrice(100);
    public static final ArtisanGood CopperBar = new ArtisanGood(ItemType.CopperBar, 4, Map.of(ForagingMineral.copperOre, 5, ArtisanGood.Coal, 1)).makeSellPrice(50);
    public static final ArtisanGood IronBar = new ArtisanGood(ItemType.IronBar, 4, Map.of(ForagingMineral.ironOre, 5, ArtisanGood.Coal, 1)).makeSellPrice(100);
    public static final ArtisanGood GoldBar = new ArtisanGood(ItemType.GoldBar, 4, Map.of(ForagingMineral.goldOre, 5, ArtisanGood.Coal, 1)).makeSellPrice(250);
    public static final ArtisanGood IridiumBar = new ArtisanGood(ItemType.IridiumBar, 4, Map.of(ForagingMineral.iridiumOre, 5, ArtisanGood.Coal, 1)).makeSellPrice(1000);






    public static final ArrayList<ArtisanGood> allArtisanGoods = new ArrayList<>();
    public static final ArrayList<ArtisanGood> KegGoods = new ArrayList<>(){{
        add(Beer);
        add(Vinegar);
        add(Coffee);
        add(Mead);
        add(PaleAle);
    }};
    public static final ArrayList<ArtisanGood>  CheesePressGoods= new ArrayList<>(){{
        add(BigGoatCheese);
        add(GoatCheese);
        add(BigCheese);
        add(Cheese);
    }};
    public static final ArrayList<ArtisanGood> BeeHouseGoods = new ArrayList<>(){{
        add(Honey);
    }};
    public static final ArrayList<ArtisanGood> DehydratorGoods = new ArrayList<>(){{
        add(Raisins);
    }};
    public static final ArrayList<ArtisanGood> CharCoalKlinGoods = new ArrayList<>(){{
        add(Coal);
    }};
    public static final ArrayList<ArtisanGood> LoomGoods = new ArrayList<>(){{
        add(RabbitCloth);
        add(SheepCloth);
    }};
    public static final ArrayList<ArtisanGood> MayonnaiseMachineGoods = new ArrayList<>(){{
        add(Mayonnaise);
        add(BigMayonnaise);
        add(DinoMayonnaise);
        add(DuckMayonnaise);
    }};
    public static final ArrayList<ArtisanGood> OilMakerGoods = new ArrayList<>(){{
        add(CornOil);
        add(SunFlowerOil);
        add(TruffleOil);
        add(SunflowerSeedOil);
    }};
    public static final ArrayList<ArtisanGood> PreservesJarGoods = new ArrayList<>();
    public static final ArrayList<ArtisanGood> FishSmokerGoods = new ArrayList<>();
    public static final ArrayList<ArtisanGood> FurnaceGoods = new ArrayList<>(){{
        add(CopperBar);
        add(IronBar);
        add(GoldBar);
        add(IridiumBar);
    }};

    static {
        addPickles(PreservesJarGoods);
        addJelly(PreservesJarGoods);
        addDriedFruit(DehydratorGoods);
        addDriedMush(DehydratorGoods);
        addJuice(KegGoods);
        addWine(KegGoods);
        addSmokedFish(FishSmokerGoods);
        allArtisanGoods.addAll(PreservesJarGoods);
        allArtisanGoods.addAll(KegGoods);
        allArtisanGoods.addAll(BeeHouseGoods);
        allArtisanGoods.addAll(DehydratorGoods);
        allArtisanGoods.addAll(LoomGoods);
        allArtisanGoods.addAll(FishSmokerGoods);
        allArtisanGoods.addAll(FurnaceGoods);
        allArtisanGoods.addAll(OilMakerGoods);
        allArtisanGoods.addAll(MayonnaiseMachineGoods);
        allArtisanGoods.addAll(CharCoalKlinGoods);
        allArtisanGoods.addAll(CheesePressGoods);
    }


}
