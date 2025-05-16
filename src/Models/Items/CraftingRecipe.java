package Models.Items;

import Enums.ItemType;
import Models.Game.App;
import Models.Items.CraftAbleAndArtisan.ArtisanGood;
import Models.Items.CraftAbleAndArtisan.*;
import Models.Items.CraftAbleAndArtisan.Sprinkler;
import Models.Items.Foragings.ForagingCrop;
import Models.Items.Foragings.ForagingMineral;
import Models.Items.Foragings.ForagingSeed;
import Models.Items.Foragings.PlantAbleCrop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CraftingRecipe extends Item{
    private final Map<Item, Integer> ingredients = new HashMap<>();
    private final int requiredAbility;
    private final int requiredLevel;

    private CraftingRecipe(ItemType itemType, Map<Item, Integer> ingredients, int requiredAbility, int requiredLevel){
        super(itemType);
        this.ingredients.putAll(ingredients);
        this.requiredAbility = requiredAbility;
        this.requiredLevel = requiredLevel;
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

    public String details(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name : " + itemType.name());
        stringBuilder.append("\nIngredients : " + "\n--------------\n");
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
    public CraftingRecipe clone(){
        return new CraftingRecipe(itemType, ingredients, requiredAbility, requiredLevel).makeSellPrice(baseSellPrice);
    }

    @Override
    public CraftingRecipe makeSellPrice(double price) {
        baseSellPrice = price;
        return this;
    }

    public boolean isAvailable(){
        if(App.getGame().getCurrentPlayer().abilities.getAbilities()[requiredAbility] >= requiredLevel)
            return true;
        return false;
    }

    public CraftingRecipe getRecipeByProdName(String prodName){
        String name = prodName + "CR";
        for(CraftingRecipe craftingRecipe : craftingRecipes){
            if(craftingRecipe.itemType.name().equalsIgnoreCase(name))
                return craftingRecipe;
        }
        return null;
    }

    public CraftAble getCrafting(){
        CraftAble craftAble = switch (itemType){
            case CherryBombCR -> Bomb.CherryBomb;
            case BombCR -> Bomb.Bomb;
            case MegaBombCR -> Bomb.MegaBomb;
            case SprinklerCR -> Sprinkler.NormalSprinkler;
            case IridiumSprinklerCR -> Sprinkler.IridiumSprinkler;
            case QualitySprinklerCR -> Sprinkler.QualitySprinkler;
            case CharcoalKlinCR -> Artisan.CharCoalKlin;
            case FurnaceCR -> Artisan.Furnace;
            case BeeHouseCR -> Artisan.BeeHouse;
            case CheesePressCR -> Artisan.CheesePress;
            case KegCR -> Artisan.Keg;
            case LoomCR -> Artisan.Loom;
            case MayonnaiseMachineCR -> Artisan.MayonnaiseMachine;
            case OilMakerCR -> Artisan.OilMaker;
            case PreservesJarCR -> Artisan.PreservesJar;
            case DehydratorCR -> Artisan.Dehydrator;
            case FishSmokerCR -> Artisan.FishSmoker;
            case ScareCrowCR -> ScareCrow.NormalScareCrow;
            case DeluxeScareCrowCR -> ScareCrow.DeluxeScareCrow;
            case GrassStarterCR -> CraftAble.GrassStarter;
            case MysticTreeSeedCR -> CraftAble.MysticTreeSeed;
            default -> null;
        };
        return craftAble;
    }

    public static final CraftingRecipe CherryBombCR = new CraftingRecipe(ItemType.CherryBombCR, Map.of(ForagingMineral.copperOre, 4, ArtisanGood.Coal, 1),
            0, 1);
    public static final CraftingRecipe BombCR = new CraftingRecipe(ItemType.BombCR, Map.of(ForagingMineral.ironOre, 4, ArtisanGood.Coal, 1),
            0, 2);
    public static final CraftingRecipe MegaBombCR = new CraftingRecipe(ItemType.MegaBombCR, Map.of(ForagingMineral.goldOre, 4, ArtisanGood.Coal, 1),
            0, 1);
    public static final CraftingRecipe SprinklerCR = new CraftingRecipe(ItemType.SprinklerCR, Map.of(ForagingMineral.copperOre, 1, ForagingMineral.ironOre, 1),
            2, 1);
    public static final CraftingRecipe QualitySprinklerCR = new CraftingRecipe(ItemType.QualitySprinklerCR, Map.of(ForagingMineral.ironOre, 1, ForagingMineral.goldOre, 1),
            2, 2);
    public static final CraftingRecipe IridiumSprinklerCR = new CraftingRecipe(ItemType.IridiumSprinklerCR, Map.of(ForagingMineral.iridiumOre, 1, ForagingMineral.goldOre, 1),
            2, 3);
    public static final CraftingRecipe CharCoalKlinCR = new CraftingRecipe(ItemType.CharcoalKlinCR, Map.of(ForagingMineral.Wood, 20, ForagingMineral.copperOre, 2),
            3, 1);
    public static final CraftingRecipe FurnaceCR = new CraftingRecipe(ItemType.FurnaceCR, Map.of(ForagingMineral.copperOre, 20, ForagingMineral.Stone, 25),
            2, 0);
    public static final CraftingRecipe ScareCrowCR = new CraftingRecipe(ItemType.ScareCrowCR, Map.of(ForagingMineral.Wood, 50, ArtisanGood.Coal, 1, ForagingMineral.Fiber, 20),
            2, 0);
    public static final CraftingRecipe DeluxeScareCrowCR = new CraftingRecipe(ItemType.DeluxeScareCrowCR, Map.of(ForagingMineral.Wood, 50, ArtisanGood.Coal, 1, ForagingMineral.Fiber, 20, ForagingMineral.iridiumOre, 1),
            2, 2);
    public static final CraftingRecipe BeeHouseCR = new CraftingRecipe(ItemType.BeeHouseCR, Map.of(ForagingMineral.Wood, 40, ArtisanGood.Coal, 8, ArtisanGood.IronBar, 1),
            2, 1);
    public static final CraftingRecipe CheesePressCR = new CraftingRecipe(ItemType.CheesePressCR, Map.of(ForagingMineral.Wood, 45, ForagingMineral.Stone, 45, ArtisanGood.CopperBar, 1),
            2, 2);
    public static final CraftingRecipe KegCR = new CraftingRecipe(ItemType.KegCR, Map.of(ForagingMineral.Wood, 30, ArtisanGood.IronBar, 1, ArtisanGood.CopperBar, 1),
            2, 3);
    public static final CraftingRecipe LoomCR = new CraftingRecipe(ItemType.LoomCR, Map.of(ForagingMineral.Wood, 60, ForagingMineral.Fiber, 30),
            2, 3);
    public static final CraftingRecipe MayonnaiseMachineCR = new CraftingRecipe(ItemType.MayonnaiseMachineCR, Map.of(ForagingMineral.Wood, 15, ForagingMineral.Stone, 15, ArtisanGood.CopperBar, 1),
            2, 0);
    public static final CraftingRecipe OilMakerCR = new CraftingRecipe(ItemType.OilMakerCR, Map.of(ForagingMineral.Wood, 100, ArtisanGood.GoldBar, 1, ArtisanGood.IronBar, 1),
            2, 3);
    public static final CraftingRecipe PreservesJarCR = new CraftingRecipe(ItemType.PreservesJarCR, Map.of(ForagingMineral.Wood, 50, ForagingMineral.Stone, 40, ArtisanGood.Coal, 8),
            2, 2);
    public static final CraftingRecipe DehydratorCR = new CraftingRecipe(ItemType.DehydratorCR, Map.of(ForagingMineral.Wood, 30, ForagingMineral.Stone, 20, ForagingMineral.Fiber, 30),
            2, 0).makeSellPrice(10000);
    public static final CraftingRecipe GrassStarterCR = new CraftingRecipe(ItemType.GrassStarterCR, Map.of(ForagingMineral.Wood, 1, ForagingMineral.Fiber, 1),
            2, 0).makeSellPrice(1000);
    public static final CraftingRecipe FishSmokerCR = new CraftingRecipe(ItemType.FishSmokerCR, Map.of(ForagingMineral.Wood, 50,ArtisanGood.Coal, 10, ArtisanGood.IronBar, 3),
            2, 0).makeSellPrice(10000);
    public static final CraftingRecipe MysticTreeSeedCR = new CraftingRecipe(ItemType.MysticTreeSeedCR, Map.of(ForagingSeed.Acorns, 5, ForagingSeed.MapleSeed, 5, ForagingSeed.PineCone, 5, ForagingSeed.MahoganySeed , 5),
            3, 4);


    public static final ArrayList<CraftingRecipe> craftingRecipes = new ArrayList<>(){{
        add(CherryBombCR);
        add(BombCR);
        add(MegaBombCR);
        add(SprinklerCR);
        add(QualitySprinklerCR);
        add(IridiumSprinklerCR);
        add(CharCoalKlinCR);
        add(FurnaceCR);
        add(ScareCrowCR);
        add(DeluxeScareCrowCR);
        add(BeeHouseCR);
        add(CheesePressCR);
        add(KegCR);
        add(LoomCR);
        add(MayonnaiseMachineCR);
        add(OilMakerCR);
        add(PreservesJarCR);
        add(DehydratorCR);
        add(GrassStarterCR);
        add(FishSmokerCR);
        add(MysticTreeSeedCR);
    }};
}
