package Models.Items.CraftAbles;

import Enums.ItemType;
import Models.Abilities.Crafting;
import Models.Items.CraftingRecipe;
import Models.Items.Item;

public class CraftAble extends Item {
    protected final CraftingRecipe craftingRecipe;

    protected CraftAble(ItemType itemType, CraftingRecipe craftingRecipe) {
        super(itemType);
        this.craftingRecipe = craftingRecipe;
    }

    @Override
    public CraftAble clone(){
        return new CraftAble(itemType, craftingRecipe);
    }

    public static final CraftAble GrassStarter = new CraftAble(ItemType.GrassStarter, CraftingRecipe.GrassStarterCR);
    public static final CraftAble MysticTreeSeed = new CraftAble(ItemType.MysticTreeSeed, CraftingRecipe.MysticTreeSeedCR);


}
