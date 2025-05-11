package Models.Items.CraftAbles;

import Enums.ItemType;
import Models.Items.CraftingRecipe;

public class Bomb extends CraftAble {
    private final int radius;
    private Bomb(ItemType itemType, CraftingRecipe craftingRecipe, int radius){
        super(itemType, craftingRecipe);
        this.radius = radius;
    }

    public void blow(){

    }

    public static final Bomb CherryBomb = new Bomb(ItemType.CherryBomb, CraftingRecipe.CherryBombCR, 3);
    public static final Bomb Bomb = new Bomb(ItemType.Bomb, CraftingRecipe.BombCR, 5);
    public static final Bomb MegaBomb = new Bomb(ItemType.MegaBomb, CraftingRecipe.MegaBombCR, 7);
}
