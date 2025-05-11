package Models.Items.CraftAbles;

import Enums.ItemType;
import Models.Items.CraftingRecipe;

public class ScareCrow extends CraftAble {
    private ScareCrow(ItemType itemType, CraftingRecipe craftingRecipe){
        super(itemType, craftingRecipe);
    }

    public static final ScareCrow NormalScareCrow = new ScareCrow(ItemType.ScareCrow, CraftingRecipe.ScareCrowCR);
    public static final ScareCrow DeluxeScareCrow = new ScareCrow(ItemType.DeluxeScareCrow, CraftingRecipe.DeluxeScareCrowCR);

}
