package Models.Items.CraftAbles;

import Enums.ItemType;
import Models.Items.Artisan;
import Models.Items.ArtisanGood;
import Models.Items.CraftingRecipe;
import Models.Items.Foragings.ForagingMineral;

import java.util.Map;

public class ArtisanMachine extends CraftAble {
    public final Artisan artisan;
    private ArtisanMachine(ItemType itemType, CraftingRecipe craftingRecipe, Artisan artisan){
        super(itemType, craftingRecipe);
        this.artisan = artisan;
    }

    public static final ArtisanMachine Keg = new ArtisanMachine(ItemType.Keg, CraftingRecipe.KegCR, );
    public static final ArtisanMachine Furnace = new ArtisanMachine(ItemType.Furnace, CraftingRecipe.FurnaceCR);
    public static final ArtisanMachine BeeHouse = new ArtisanMachine(ItemType.BeeHouse, CraftingRecipe.BeeHouseCR);
    public static final ArtisanMachine Loom = new ArtisanMachine(ItemType.Loom, CraftingRecipe.LoomCR);
    public static final ArtisanMachine MayonnaiseMachine = new ArtisanMachine(ItemType.MayonnaiseMachine, CraftingRecipe.MayonnaiseMachineCR);
    public static final ArtisanMachine OilMaker = new ArtisanMachine(ItemType.OilMaker, CraftingRecipe.OilMakerCR);
    public static final ArtisanMachine PreservesJar = new ArtisanMachine(ItemType.PreservesJar, CraftingRecipe.PreservesJarCR);
    public static final ArtisanMachine Dehydrator = new ArtisanMachine(ItemType.Dehydrator, CraftingRecipe.DehydratorCR);
    public static final ArtisanMachine CharcoalKlin = new ArtisanMachine(ItemType.CharcoalKlin, CraftingRecipe.CharCoalKlinCR);
    public static final ArtisanMachine FishSmoker = new ArtisanMachine(ItemType.FishSmoker, CraftingRecipe.FishSmokerCR);
    public static final ArtisanMachine CheesePress = new ArtisanMachine(ItemType.CheesePress, CraftingRecipe.CheesePressCR);

}
