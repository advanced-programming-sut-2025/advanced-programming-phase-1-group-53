package Models.Items;

import Enums.ItemType;
import Models.Game.App;
import Models.Items.CraftAbleAndArtisan.ArtisanGood;
import Models.Items.Foragings.ForagingCrop;
import Models.Items.Foragings.ForagingMineral;
import Models.Items.Foragings.Fruit;
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

    public Map<Item, Integer> getIngredients() {
        return ingredients;
    }

    public int getRequiredAbility() {
        return requiredAbility;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public boolean isAvailable(){
        if(App.getGame().getCurrentPlayer().abilities.getAbilities()[requiredAbility] >= requiredLevel)
            return true;
        return false;
    }

    public String details(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name : " + itemType.name() + "\n");
        stringBuilder.append("Ingredients : \n------------\n");
        for(Item item : ingredients.keySet()){
            stringBuilder.append("Item : " + item.getItemType().name() + ", Quantity : " + ingredients.get(item) + "\n");
        }
        if(requiredLevel != 0) {
            String string = switch (requiredAbility){
                case 0 -> "mining";
                case 1 -> "fishing";
                case 2 -> "farming";
                case 3 -> "foraging";
                default -> "kyvbys";
            };
            stringBuilder.append("Required ability : " + string + " level : " + requiredLevel);
        }
        return stringBuilder.toString();
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
    public static final Recipe OmeletRecipe = new Recipe(ItemType.OmeletRecipe, Map.of(AnimalProduct.Egg,1, AnimalProduct.Milk, 1)
            , 0, 0);
    public static final Recipe PumpkinPieRecipe = new Recipe(ItemType.PumpkinPieRecipe,
            Map.of(Item.WheatFlour, 1, Item.Sugar, 1,
                    PlantAbleCrop.Pumpkin, 1,AnimalProduct.Milk, 1),0, 0);

    public static final Recipe SpaghettiRecipe = new Recipe(ItemType.SpaghettiRecipe, Map.of(Item.WheatFlour, 1,
            PlantAbleCrop.Tomato, 1)
            , 0, 0);
    public static final Recipe PizzaRecipe = new Recipe(ItemType.PizzaRecipe, Map.of(Item.WheatFlour, 1,
            PlantAbleCrop.Tomato, 1, ArtisanGood.Cheese, 1)
            , 0, 0);
    public static final Recipe TortillaRecipe = new Recipe(ItemType.TortillaRecipe, Map.of(PlantAbleCrop.Corn, 1)
            , 0, 0);
    public static final Recipe MakiRollRecipe = new Recipe(ItemType.MakiRollRecipe, Map.of(PlantAbleCrop.Rice, 1,
            ForagingMineral.Fiber, 1,Fish.Salmon, 1)
            , 0, 0);
    public static final Recipe TrippleShotEspressoRecipe = new Recipe(ItemType.TripleShotEspressoRecipe,
            Map.of(PlantAbleCrop.CoffeeBean, 3), 0, 0);
    public static final Recipe CookieRecipe = new Recipe(ItemType.CookieRecipe, Map.of(AnimalProduct.Egg, 1
            ,Item.WheatFlour, 1,Item.Sugar, 1)
            , 0, 0);
    public static final Recipe HashbrownsRecipe = new Recipe(ItemType.HashBrownsRecipe, Map.of(ArtisanGood.SunFlowerOil, 1,
            PlantAbleCrop.Potato, 1)
            , 0, 0);
    public static final Recipe PancakeRecipe = new Recipe(ItemType.PancakesRecipe, Map.of(Item.WheatFlour, 1,
            AnimalProduct.Egg, 1), 0, 0);
    public static final Recipe FruitSaladRecipe = new Recipe(ItemType.FruitSaladRecipe, Map.of(PlantAbleCrop.Blueberry, 1,
            Fruit.Apricot, 1,PlantAbleCrop.Melon, 1)
            , 0, 0);
    public static final Recipe RedPlateRecipe = new Recipe(ItemType.RedPlateRecipe, Map.of(PlantAbleCrop.Radish, 1,
            PlantAbleCrop.RedCabbage, 1)
            , 0, 0);
    public static final Recipe BreadRecipe = new Recipe(ItemType.BreadRecipe, Map.of(Item.WheatFlour, 1)
            , 0, 0);
    public static final Recipe SalmonDinnerRecipe = new Recipe(ItemType.SalmonDinnerRecipe, Map.of(Fish.Salmon, 1,
            PlantAbleCrop.Amaranth, 1,PlantAbleCrop.Kale, 1)
            , 0, 0);
    public static final Recipe VegetableMedelyRecipe = new Recipe(ItemType.VegetableMedleyRecipe, Map.of(PlantAbleCrop.Tomato, 1,
            PlantAbleCrop.Beet, 1)
            , 2, 3);
    public static final Recipe FarmerLunchRecipe = new Recipe(ItemType.FarmersLunchRecipe, Map.of(PlantAbleCrop.Parsnip, 1,
            Food.Omelet, 1)
            , 1, 2);
    public static final Recipe SurvivalBurgerRecipe = new Recipe(ItemType.SurvivalBurgerRecipe, Map.of(PlantAbleCrop.Eggplant, 1,
            PlantAbleCrop.Carrot, 1,Food.Bread, 1)
            , 3, 3);
    public static final Recipe DishOtheSeaRecipe = new Recipe(ItemType.DishOTheSeaRecipe, Map.of(Fish.Sardine, 2,
            Food.HashBrowns, 1)
            , 2, 1);
    public static final Recipe SeaFormPuddingRecipe = new Recipe(ItemType.SeaFormPuddingRecipe, Map.of(Fish.MidnightCarp, 1,
            Fish.Flounder, 1)
            , 3, 1);
    public static final Recipe MinersTreatRecipe = new Recipe(ItemType.MinersTreatRecipe, Map.of(PlantAbleCrop.Carrot, 2,
            AnimalProduct.Milk, 1,Item.Sugar, 1)
            , 1, 0);

    public static final ArrayList<Recipe> allRecipes = new ArrayList<>(){{
        add(FriedEggRecipe);
        add(BakedFishRecipe);
        add(SaladRecipe);
        add(OmeletRecipe);
        add(PumpkinPieRecipe);
        add(SpaghettiRecipe);
        add(PizzaRecipe);
        add(TortillaRecipe);
        add(MakiRollRecipe);
        add(TrippleShotEspressoRecipe);
        add(CookieRecipe);
        add(HashbrownsRecipe);
        add(PancakeRecipe);
        add(FruitSaladRecipe);
        add(RedPlateRecipe);
        add(BreadRecipe);
        add(SalmonDinnerRecipe);
        add(VegetableMedelyRecipe);
        add(FarmerLunchRecipe);
        add(SurvivalBurgerRecipe);
        add(DishOtheSeaRecipe);
        add(SeaFormPuddingRecipe);
        add(MinersTreatRecipe);
    }};
}