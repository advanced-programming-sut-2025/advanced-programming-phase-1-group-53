package Models.Items;

import Enums.ItemType;

public class Food extends Item{
    private int energy;
    private final Recipe recipe;

    private Food(ItemType itemType, int energy, Recipe recipe){
        super(itemType);
        this.energy = energy;
        this.recipe = recipe;
    }

    @Override
    public Food clone(){
        return new Food(getItemType(), energy, recipe);
    }

    public static final Food FriedEgg = new Food(Ite);
    public static final Food
    public static final Food
    public static final Food
    public static final Food
    public static final Food
    public static final Food
    public static final Food
    public static final Food
    public static final Food
    public static final Food
    public static final Food
    public static final Food
    public static final Food
    public static final Food
    public static final Food
}
