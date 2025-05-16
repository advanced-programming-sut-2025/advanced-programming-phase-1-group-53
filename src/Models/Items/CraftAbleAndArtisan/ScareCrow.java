package Models.Items.CraftAbleAndArtisan;

import Enums.ItemType;
import Models.Items.CraftingRecipe;

import java.util.ArrayList;

public class ScareCrow extends CraftAble {
    private ScareCrow(ItemType itemType, CraftingRecipe craftingRecipe){
        super(itemType, craftingRecipe);
    }

    public static final ScareCrow NormalScareCrow = new ScareCrow(ItemType.ScareCrow, CraftingRecipe.ScareCrowCR);
    public static final ScareCrow DeluxeScareCrow = new ScareCrow(ItemType.DeluxeScareCrow, CraftingRecipe.DeluxeScareCrowCR);

    public static final ArrayList<ScareCrow> allScareCrows = new ArrayList<>(){{
        add(NormalScareCrow);
        add(DeluxeScareCrow);
    }};
}
