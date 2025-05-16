package Models.Items.CraftAbleAndArtisan;

import Enums.ItemType;
import Models.Items.CraftingRecipe;

import java.util.ArrayList;

public class Sprinkler extends CraftAble {
    private Sprinkler(ItemType itemType, CraftingRecipe craftingRecipe){
        super(itemType, craftingRecipe);
    }

    public static final Sprinkler NormalSprinkler = new Sprinkler(ItemType.Sprinkler, CraftingRecipe.SprinklerCR);
    public static final Sprinkler QualitySprinkler = new Sprinkler(ItemType.QualitySprinkler, CraftingRecipe.QualitySprinklerCR);
    public static final Sprinkler IridiumSprinkler = new Sprinkler(ItemType.IridiumSprinkler, CraftingRecipe.IridiumSprinklerCR);

    public static final ArrayList<Sprinkler> allSprinklers = new ArrayList<>(){{
        add(NormalSprinkler);
        add(QualitySprinkler);
        add(IridiumSprinkler);
    }};
}
