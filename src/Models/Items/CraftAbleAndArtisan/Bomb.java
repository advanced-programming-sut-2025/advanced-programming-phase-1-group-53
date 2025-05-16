package Models.Items.CraftAbleAndArtisan;

import Enums.ItemType;
import Models.Items.CraftingRecipe;

import java.util.ArrayList;

public class Bomb extends CraftAble {
    private final int radius;
    private Bomb(ItemType itemType, CraftingRecipe craftingRecipe, int radius){
        super(itemType, craftingRecipe);
        this.radius = radius;
    }

    @Override
    public Bomb makeSellPrice(double price){
        baseSellPrice = price;
        return this;
    }

    @Override
    public Bomb clone(){
        return new Bomb(itemType, craftingRecipe, radius);
    }

    public void blow(){

    }

    public static final Bomb CherryBomb = new Bomb(ItemType.CherryBomb, CraftingRecipe.CherryBombCR, 3).makeSellPrice(50);
    public static final Bomb Bomb = new Bomb(ItemType.Bomb, CraftingRecipe.BombCR, 5).makeSellPrice(50);
    public static final Bomb MegaBomb = new Bomb(ItemType.MegaBomb, CraftingRecipe.MegaBombCR, 7).makeSellPrice(50);

    public static final ArrayList<Bomb> allBombs = new ArrayList<>(){{
        add(CherryBomb);
        add(Bomb);
        add(MegaBomb);
    }};
}
