package Models.Abilities;

import Enums.ItemType;
import Models.Game.App;
import Models.Items.CraftingRecipe;
import Models.Items.Item;
import Models.Items.Recipe;
import Models.MessageManager;
import Models.Result;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;

public class Crafting {
    private final ArrayList<Recipe> knownRecipes = new ArrayList<>();

    public void showCraftingRecipes(){
        for(Item item : App.getGame().getCurrentPlayer().backpack.getCraftingRecipes().keySet()){
            CraftingRecipe craftingRecipe = (CraftingRecipe) item;
            MessageManager.getMessage(Result.success(craftingRecipe.details()));
        }
    }

    public void craft(){
        for(Item item: App.getGame().getCurrentPlayer().backpack.getCraftingRecipes().keySet()){
            CraftingRecipe craftingRecipe = (CraftingRecipe) item;
            if(craftingRecipe.isAvailable()){
                if(!App.getGame().getCurrentPlayer().backpack.areItemsAvailable(craftingRecipe.getIngredients())){
                    MessageManager.getMessage(Result.failure("Insufficient material."));
                    return;
                }
                if(App.getGame().getCurrentPlayer().backpack.isInventoryFull()){
                    MessageManager.getMessage(Result.failure("Inventory is full."));
                    return;
                }
                for(Item item1 : craftingRecipe.getIngredients().keySet()){
                    App.getGame().getCurrentPlayer().backpack.getItems().compute(item1,
                            (key, oldVal) -> (oldVal- craftingRecipe.getIngredients().get(item1)));
                }
                App.getGame().getCurrentPlayer().backpack.addItem(craftingRecipe.getCrafting());
                App.getGame().getCurrentPlayer().energy.updateEnergy(2);
            }
        }
        MessageManager.getMessage(Result.failure("You haven't accessed the recipe yet."));
    }

    public void placeItem(ItemType itemType, int x, int y){
        if(App.getGame().getCurrentPlayer().backpack.areItemsAvailable(App.getGame().getItemByItemType(itemType), 1)){
            App.getGame().getCurrentPlayer().backpack.getItems().compute(App.getGame().getItemByItemType(itemType),
                    (k, v) -> (v-1));
            //TODO : place it on tiles
            MessageManager.getMessage(Result.success(itemType.name() + "placed successfully at" +x +", " + y));
        }
        else
            MessageManager.getMessage(Result.failure("The product is not available."));
    }

}
