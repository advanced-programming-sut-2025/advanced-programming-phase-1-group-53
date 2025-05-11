package Models.Items;

import Enums.ItemType;

import java.util.ArrayList;

public class Artisan extends Item{
    private final int processingHours;
    private final ArrayList<Item> products = new ArrayList<>();
    private final ArrayList<ArrayList<Item>> ingredients = new ArrayList<>();

    private Artisan(ItemType itemType, int processingHours, ArrayList<Item> products, ArrayList<ArrayList<Item>> ingredients){
        super(itemType);
        this.processingHours = processingHours;
        this.products.addAll(products);
        this.ingredients.addAll(ingredients);
    }

    @Override
    public Artisan clone(){
        return new Artisan(getItemType(), processingHours, products, ingredients);
    }

    public static final Artisan
}
