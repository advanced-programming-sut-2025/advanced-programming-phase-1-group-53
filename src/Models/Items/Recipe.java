package Models.Items;

import Enums.ItemType;
import Models.Items.Foragings.ForagingCrop;
import Models.Items.Foragings.PlantAbleCrop;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recipe extends Item{
    private final Map<Item, Integer> ingredients = new HashMap<>();
    private int requiredAbility = 0;
    private int requiredLevel = 0;

    private Recipe(ItemType itemType, Map<Item, Integer> ingredients, int requiredLevel, int requiredAbility){
        super(itemType);
        this.ingredients.putAll(ingredients);
        this.requiredLevel = requiredLevel;
        this.requiredAbility = requiredAbility;
    }

    @Override
    public Recipe clone(){
        return new Recipe(getItemType(), ingredients, requiredLevel, requiredAbility);
    }

    public static final Recipe FriedEggRecipe = new Recipe(ItemType.FriedEggRecipe, Map.of(AnimalProduct.Egg, 1)
    , 0, 0);
    public static final Recipe BakedFishRecipe = new Recipe(ItemType.BakedFishRecipe, Map.of(Fish.Sardine, 1, Fish.Salmon, 1, PlantAbleCrop.Wheat, 1)
            , 0, 0);
    public static final Recipe SaladRecipe = new Recipe(ItemType.SaladRecipe, Map.of(ForagingCrop.leek, 1, ForagingCrop.dandelion, 1)
            , 0, 0);
    public static final Recipe OmeletRecipe = new Recipe(ItemType.OmeletRecipe, Map.of(AnimalProduct.Egg,1, AnimalProduct.CowMilk, 1)
            , 0, 0);
    public static final Recipe PumpkinPieRecipe = new Recipe(ItemType.PumpkinPieRecipe, Map.of(AnimalProduct.Egg)
            , 0, 0);
    public static final Recipe SpaghettiRecipe = new Recipe(ItemType.SpaghettiRecipe, Map.of()
            , 0, 0);
    public static final Recipe PizzaRecipe = new Recipe(ItemType.PizzaRecipe, Map.of(AnimalProduct.Egg)
            , 0, 0);
    public static final Recipe TortillaRecipe = new Recipe(ItemType.TortillaRecipe, Map.of(AnimalProduct.Egg)
            , 0, 0);
    public static final Recipe MakiRollRecipe = new Recipe(ItemType.MakiRollRecipe, Map.of(AnimalProduct.Egg)
            , 0, 0);
    public static final Recipe TrippleShotEspressoRecipe = new Recipe(ItemType.TripleShotEspressoRecipe, Map.of(AnimalProduct.Egg)
            , 0, 0);
    public static final Recipe CookieRecipe = new Recipe(ItemType.CookieRecipe, Map.of(AnimalProduct.Egg)
            , 0, 0);
    public static final Recipe HashbrownsRecipe = new Recipe(ItemType.HashBrownsRecipe, Map.of(AnimalProduct.Egg)
            , 0, 0);
    public static final Recipe PancakeRecipe = new Recipe(ItemType.PancakesRecipe, Map.of(AnimalProduct.Egg)
            , 0, 0);
    public static final Recipe FruitSaladEspressoRecipe = new Recipe(ItemType.FruitSaladRecipe, Map.of(AnimalProduct.Egg)
            , 0, 0);
    public static final Recipe RedPlateEspressoRecipe = new Recipe(ItemType.RedPlateRecipe, Map.of(AnimalProduct.Egg)
            , 0, 0);
    public static final Recipe BreadRecipe = new Recipe(ItemType.BreadRecipe, Map.of(AnimalProduct.Egg)
            , 0, 0);
    public static final Recipe SalmonDinnerRecipe = new Recipe(ItemType.SalmonDinnerRecipe, Map.of(AnimalProduct.Egg)
            , 0, 0);
    public static final Recipe VegetableMedelyRecipe = new Recipe(ItemType.VegetableMedleyRecipe, Map.of(AnimalProduct.Egg)
            , 2, 3);
    public static final Recipe FarmerLunchRecipe = new Recipe(ItemType.FarmersLunchRecipe, Map.of(AnimalProduct.Egg)
            , 1, 2);
    public static final Recipe SurvivalBurgerRecipe = new Recipe(ItemType.SurvivalBurgerRecipe, Map.of(AnimalProduct.Egg)
            , 3, 3);
    public static final Recipe DishOtheSeaRecipe = new Recipe(ItemType.DishOTheSeaRecipe, Map.of(AnimalProduct.Egg)
            , 2, 1);
    public static final Recipe SeaFormPuddingRecipe = new Recipe(ItemType.SeaFormPuddingRecipe, Map.of(AnimalProduct.Egg)
            , 3, 1);
    public static final Recipe MinersTreatRecipe = new Recipe(ItemType.MinersTreatRecipe, Map.of(AnimalProduct.Egg)
            , 1, 0);
}
