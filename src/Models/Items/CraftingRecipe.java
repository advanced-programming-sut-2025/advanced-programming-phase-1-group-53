package Models.Items;

import Enums.ItemType;
import Models.Game.App;
import Models.Items.CraftAbles.*;
import Models.Items.CraftAbles.Sprinkler;
import Models.Items.Foragings.ForagingMineral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Models.Items.CraftAbles.Sprinkler.Sprinkler;

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
        return ;
    }

    @Override
    public CraftingRecipe clone(){
        return new CraftingRecipe(itemType, ingredients, requiredAbility, requiredLevel);
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
            case CharcoalKlinCR -> ArtisanMachine.CharcoalKlin;
            case FurnaceCR -> ArtisanMachine.Furnace;
            case BeeHouseCR -> ArtisanMachine.BeeHouse;
            case CheesePressCR -> ArtisanMachine.CheesePress;
            case KegCR -> ArtisanMachine.Keg;
            case LoomCR -> ArtisanMachine.Loom;
            case MayonnaiseMachineCR -> ArtisanMachine.MayonnaiseMachine;
            case OilMakerCR -> ArtisanMachine.OilMaker;
            case PreservesJarCR -> ArtisanMachine.PreservesJar;
            case DehydratorCR -> ArtisanMachine.Dehydrator;
            case FishSmokerCR -> ArtisanMachine.FishSmoker;
            case ScareCrowCR -> ScareCrow.NormalScareCrow;
            case DeluxeScareCrowCR -> ScareCrow.DeluxeScareCrow;
            case GrassStarterCR -> CraftAble.GrassStarter;
            case MysticTreeSeedCR -> CraftAble.MysticTreeSeed;
            default -> null;
        }
    }

    public static final CraftingRecipe CherryBombCR = new CraftingRecipe(ItemType.CherryBombCR, Map.of(ForagingMineral.copperOre, 4, ForagingMineral.coal, 1),
            0, 1);
    public static final CraftingRecipe BombCR = new CraftingRecipe(ItemType.BombCR, Map.of(ForagingMineral.ironOre, 4, ForagingMineral.coal, 1),
            0, 2);
    public static final CraftingRecipe MegaBombCR = new CraftingRecipe(ItemType.MegaBombCR, Map.of(ForagingMineral.goldOre, 4, ForagingMineral.coal, 1),
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
    public static final CraftingRecipe ScareCrowCR = new CraftingRecipe(ItemType.ScareCrowCR, Map.of(ForagingMineral.Wood, 50, ForagingMineral.coal, 1, ForagingMineral.Fiber, 20),
            2, 0);
    public static final CraftingRecipe DeluxeScareCrowCR = new CraftingRecipe(ItemType.DeluxeScareCrowCR, Map.of(ForagingMineral.Wood, 50, ForagingMineral.coal, 1, ForagingMineral.Fiber, 20, ForagingMineral.iridiumOre, 1),
            2, 2);
    public static final CraftingRecipe BeeHouseCR = new CraftingRecipe(ItemType.BeeHouseCR, Map.of(ForagingMineral.Wood, 40, ForagingMineral.coal, 8, ArtisanGood.IronBar, 1),
            2, 1);
    public static final CraftingRecipe CheesePressCR = new CraftingRecipe(ItemType.BeeHouseCR, Map.of(ForagingMineral.Wood, 40, ForagingMineral.coal, 8, ArtisanGood.IronBar, 1),
            2, 1);
    public static final CraftingRecipe KegCR = new CraftingRecipe(ItemType.BeeHouseCR, Map.of(ForagingMineral.Wood, 40, ForagingMineral.coal, 8, ArtisanGood.IronBar, 1),
            2, 1);
    public static final CraftingRecipe LoomCR = new CraftingRecipe(ItemType.BeeHouseCR, Map.of(ForagingMineral.Wood, 40, ForagingMineral.coal, 8, ArtisanGood.IronBar, 1),
            2, 1);
    public static final CraftingRecipe MayonnaiseMachineCR = new CraftingRecipe(ItemType.BeeHouseCR, Map.of(ForagingMineral.Wood, 40, ForagingMineral.coal, 8, ArtisanGood.IronBar, 1),
            2, 1);
    public static final CraftingRecipe OilMakerCR = new CraftingRecipe(ItemType.BeeHouseCR, Map.of(ForagingMineral.Wood, 40, ForagingMineral.coal, 8, ArtisanGood.IronBar, 1),
            2, 1);
    public static final CraftingRecipe PreservesJarCR = new CraftingRecipe(ItemType.BeeHouseCR, Map.of(ForagingMineral.Wood, 40, ForagingMineral.coal, 8, ArtisanGood.IronBar, 1),
            2, 1);
    public static final CraftingRecipe DehydratorCR = new CraftingRecipe(ItemType.BeeHouseCR, Map.of(ForagingMineral.Wood, 40, ForagingMineral.coal, 8, ArtisanGood.IronBar, 1),
            2, 1);
    public static final CraftingRecipe GrassStarterCR = new CraftingRecipe(ItemType.BeeHouseCR, Map.of(ForagingMineral.Wood, 40, ForagingMineral.coal, 8, ArtisanGood.IronBar, 1),
            2, 1);
    public static final CraftingRecipe FishSmokerCR = new CraftingRecipe(ItemType.BeeHouseCR, Map.of(ForagingMineral.Wood, 40, ForagingMineral.coal, 8, ArtisanGood.IronBar, 1),
            2, 1);
    public static final CraftingRecipe MysticTreeSeedCR = new CraftingRecipe(ItemType.BeeHouseCR, Map.of(ForagingMineral.Wood, 40, ForagingMineral.coal, 8, ArtisanGood.IronBar, 1),
            2, 1);


    //TODO: fix fields from cheesePress

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
