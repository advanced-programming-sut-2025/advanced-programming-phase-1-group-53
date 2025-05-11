package Models.Items.CraftAbles;

import Enums.ItemType;
import Models.Items.CraftingRecipe;

public class Sprinkler extends CraftAble {
    private Sprinkler(ItemType itemType, CraftingRecipe craftingRecipe){
        super(itemType, craftingRecipe);
    }

    public static final Sprinkler NormalSprinkler = new Sprinkler(ItemType.Sprinkler, CraftingRecipe.SprinklerCR);
    public static final Sprinkler QualitySprinkler = new Sprinkler(ItemType.QualitySprinkler, CraftingRecipe.QualitySprinklerCR);
    public static final Sprinkler IridiumSprinkler = new Sprinkler(ItemType.IridiumSprinkler, CraftingRecipe.IridiumSprinklerCR);

}
