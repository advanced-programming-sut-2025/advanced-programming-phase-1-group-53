package Models.Items;

import Enums.ItemType;

import java.util.ArrayList;
import java.util.HashMap;

public class Food extends Item{
    private final Recipe recipe;

    private Food(ItemType itemType, double energy, Recipe recipe){
        super(itemType);
        this.energy = energy;
        this.recipe = recipe;
        isEdible = true;
    }


    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public Food clone(){
        return new Food(getItemType(), energy, recipe);
    }

    @Override
    public Food makeSellPrice(double price) {
        baseSellPrice = price;
        return this;
    }

    public static final Food FriedEgg = new Food(ItemType.FriedEgg, 50, Recipe.FriedEggRecipe).makeSellPrice(35);
    public static final Food BakedFish = new Food(ItemType.BakedFish, 75, Recipe.BakedFishRecipe).makeSellPrice(100);
    public static final Food Salad = new Food(ItemType.Salad, 113, Recipe.SaladRecipe).makeSellPrice(110);
    public static final Food Omelet = new Food(ItemType.Omelet, 100, Recipe.OmeletRecipe).makeSellPrice(125);
    public static final Food PumpkinPie = new Food(ItemType.PumpkinPie, 225, Recipe.PumpkinPieRecipe).makeSellPrice(385);
    public static final Food Spaghetti = new Food(ItemType.Spaghetti, 75, Recipe.SpaghettiRecipe).makeSellPrice(120);
    public static final Food Pizza = new Food(ItemType.Pizza, 150, Recipe.PizzaRecipe).makeSellPrice(300);
    public static final Food Tortilla = new Food(ItemType.Tortilla, 50, Recipe.TortillaRecipe).makeSellPrice(50);
    public static final Food MakiRoll = new Food(ItemType.MakiRoll, 100, Recipe.MakiRollRecipe).makeSellPrice(220);
    public static final Food TripleShotEspresso = new Food(ItemType.TripleShotEspresso, 200,
            Recipe.TrippleShotEspressoRecipe).makeSellPrice(450);
    public static final Food Cookie = new Food(ItemType.Cookie, 90, Recipe.CookieRecipe).makeSellPrice(140);
    public static final Food HashBrowns = new Food(ItemType.HashBrowns, 90, Recipe.HashbrownsRecipe).makeSellPrice(120);
    public static final Food Pancakes = new Food(ItemType.Pancakes, 90, Recipe.PancakeRecipe).makeSellPrice(80);
    public static final Food FruitSalad = new Food(ItemType.FruitSalad, 263, Recipe.FruitSaladRecipe).makeSellPrice(450);
    public static final Food RedPlate = new Food(ItemType.RedPlate, 240, Recipe.RedPlateRecipe).makeSellPrice(400);
    public static final Food Bread = new Food(ItemType.Bread, 50, Recipe.BreadRecipe).makeSellPrice(60);
    public static final Food SalmonDinner = new Food(ItemType.SalmonDinner, 125, Recipe.SalmonDinnerRecipe).makeSellPrice(300);
    public static final Food VegetableMedley = new Food(ItemType.VegetableMedley, 165, Recipe.VegetableMedelyRecipe).makeSellPrice(120);
    public static final Food FarmersLunch = new Food(ItemType.FarmersLunch, 200, Recipe.FarmerLunchRecipe).makeSellPrice(150);
    public static final Food SurvivalBurger = new Food(ItemType.SurvivalBurger, 125, Recipe.SurvivalBurgerRecipe).makeSellPrice(180);
    public static final Food DishOTheSea = new Food(ItemType.DishOTheSea, 150, Recipe.DishOtheSeaRecipe).makeSellPrice(220);
    public static final Food SeaFormPudding = new Food(ItemType.SeaFormPudding, 175, Recipe.SeaFormPuddingRecipe).makeSellPrice(300);
    public static final Food MinersTreat = new Food(ItemType.MinersTreat, 125, Recipe.MinersTreatRecipe).makeSellPrice(200);

    public static final ArrayList<Food> allFoods= new ArrayList<>(){{
        add(FriedEgg);
        add(BakedFish);
        add(Salad);
        add(Omelet);
        add(PumpkinPie);
        add(Spaghetti);
        add(Pizza);
        add(Tortilla);
        add(MakiRoll);
        add(TripleShotEspresso);
        add(Cookie);
        add(HashBrowns);
        add(Pancakes);
        add(FruitSalad);
        add(RedPlate);
        add(Bread);
        add(SalmonDinner);
        add(VegetableMedley);
        add(FarmersLunch);
        add(SurvivalBurger);
        add(DishOTheSea);
        add(SeaFormPudding);
        add(MinersTreat);
    }};
}
