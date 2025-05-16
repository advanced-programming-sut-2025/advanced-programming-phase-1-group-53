package Models.Items.CraftAbleAndArtisan;

import Enums.ItemType;
import Models.Items.CraftingRecipe;
import Models.Items.Item;

import java.util.ArrayList;

public class CraftAble extends Item {
    protected final CraftingRecipe craftingRecipe;

    protected CraftAble(ItemType itemType, CraftingRecipe craftingRecipe) {
        super(itemType);
        this.craftingRecipe = craftingRecipe;
    }

    public CraftingRecipe getCraftingRecipe() {
        return craftingRecipe;
    }

    @Override
    public CraftAble makeSellPrice(double price){
        baseSellPrice = price;
        return this;
    }

    @Override
    public CraftAble clone(){
        return new CraftAble(itemType, craftingRecipe);
    }

    public static final CraftAble GrassStarter = new CraftAble(ItemType.GrassStarter, CraftingRecipe.GrassStarterCR);
    public static final CraftAble MysticTreeSeed = new CraftAble(ItemType.MysticTreeSeed, CraftingRecipe.MysticTreeSeedCR).makeSellPrice(100);

    public static final ArrayList<CraftAble> allCraftables = new ArrayList<>(){{
        add(GrassStarter);
        add(MysticTreeSeed);
    }};
}
