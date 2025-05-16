package Models.Abilities;

import Enums.ItemType;
import Models.Game.App;
import Models.Game.Game;
import Models.Items.Food;
import Models.Items.Foragings.ForagingCrop;
import Models.Items.Foragings.Fruit;
import Models.Items.Foragings.PlantAbleCrop;
import Models.Items.Item;
import Models.Items.Recipe;
import Models.MessageManager;
import Models.Result;

public class Cooking {
    public void putItemInRef(ItemType itemType){
        //TODO : call put method in ref
    }

    public void pickItemFromRef(ItemType itemType){
        //TODO
    }

    public void showCookingRecipes(){
        for(Item item : App.getGame().getCurrentPlayer().backpack.getCookingRecipes().keySet()){
            Recipe recipe = (Recipe) item;
            if(recipe.isAvailable()) {
                MessageManager.getMessage(Result.success(recipe.details()));
            }
        }
    }

    public void prepare(ItemType itemType){
        if(App.getGame().getCurrentPlayer().backpack.isInventoryFull()){
            MessageManager.getMessage(Result.failure("Not enough space in inventory."));
            return;
        }
        Item item = App.getGame().getItemByItemType(itemType);
        if(!(item instanceof Food)){
            MessageManager.getMessage(Result.failure("No food with such name."));
            return;
        }

        Food food = (Food) item;
        if(!App.getGame().getCurrentPlayer().backpack.areItemsAvailable(food.getRecipe().getIngredients())
        /* && TODO: for ref*/) {
            MessageManager.getMessage(Result.failure("Not enough material inside refrigerator and inventory."));
            return;
        }
        if(App.getGame().getCurrentPlayer().backpack.areItemsAvailable(food.getRecipe().getIngredients())){
            for(Item item1 : food.getRecipe().getIngredients().keySet()){
                App.getGame().getCurrentPlayer().backpack.getItems().compute(item1, (k, v) -> (v-food.getRecipe().getIngredients().get(item1)));
            }
        }
        //TODO for ref
        App.getGame().getCurrentPlayer().backpack.addItem(food, 1);
        App.getGame().getCurrentPlayer().energy.updateEnergy(3);
        MessageManager.getMessage(Result.success("Food was prepared successfully."));
    }

    public void eat(ItemType itemType){
        Item item = App.getGame().getItemByItemType(itemType);
        if(!App.getGame().getCurrentPlayer().backpack.areItemsAvailable(item, 1)) {
            MessageManager.getMessage(Result.failure("No number of the item in inventory"));
            return;
        }
        if(!item.isEdible()){
            MessageManager.getMessage(Result.failure("The item is not edible."));
            return;
        }
        App.getGame().getCurrentPlayer().energy.updateEnergy((int)item.getEnergy());
        App.getGame().getCurrentPlayer().backpack.getItems().compute(item, (k, v) -> (v-1));
        MessageManager.getMessage(Result.success("a "+item.getItemType().name() + " was ate."));
        App.getGame().getCurrentPlayer().foodBuff.activateBuff(item.getItemType());
    }
}
