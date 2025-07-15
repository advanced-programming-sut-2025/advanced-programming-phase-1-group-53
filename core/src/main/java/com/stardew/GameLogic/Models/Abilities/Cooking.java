package com.stardew.GameLogic.Models.Abilities;

import com.stardew.GameLogic.Enums.ItemType;
import com.stardew.GameLogic.Models.Game.App;
import com.stardew.GameLogic.Models.Items.Buildings.House;
import com.stardew.GameLogic.Models.Items.Food;
import com.stardew.GameLogic.Models.Items.Item;
import com.stardew.GameLogic.Models.Items.Recipe;
import com.stardew.GameLogic.Models.MessageManager;
import com.stardew.GameLogic.Models.Result;

public class Cooking {
    public void putItemInRef(ItemType itemType){
        if(!(App.getGame().getGameMap().findBuilding(App.getGame().getCurrentPlayer().position.getX(),
                App.getGame().getCurrentPlayer().position.getY()) instanceof House house)) {
            MessageManager.getMessage(Result.failure("You are not in your house."));
            return;
        }
        house.getRefrigerator().putItem(App.getGame().getItemByItemType(itemType), 1);
        MessageManager.getMessage(Result.success("Item was successfully put inside the refrigerator."));
    }

    public void pickItemFromRef(ItemType itemType){
        if(!(App.getGame().getGameMap().findBuilding(App.getGame().getCurrentPlayer().position.getX(),
                App.getGame().getCurrentPlayer().position.getY()) instanceof House house)) {
            MessageManager.getMessage(Result.failure("You are not in your house."));
            return;
        }
        house.getRefrigerator().pickItem(App.getGame().getItemByItemType(itemType), 1);
        MessageManager.getMessage(Result.success("Item was successfully picked from the refrigerator."));
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
        if(!(App.getGame().getGameMap().findBuilding(App.getGame().getCurrentPlayer().position.getX(),
                App.getGame().getCurrentPlayer().position.getY()) instanceof House house)) {
            MessageManager.getMessage(Result.failure("You are not in your house."));
            return;
        }

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
        if(!(App.getGame().getCurrentPlayer().backpack.areItemsAvailable(food.getRecipe().getIngredients()) ||
        house.getRefrigerator().areItemsAvailable(food.getRecipe().getIngredients()))) {
            MessageManager.getMessage(Result.failure("Not enough material inside refrigerator and inventory."));
            return;
        }
        if(App.getGame().getCurrentPlayer().backpack.areItemsAvailable(food.getRecipe().getIngredients())){
            for(Item item1 : food.getRecipe().getIngredients().keySet()){
                App.getGame().getCurrentPlayer().backpack.getItems().compute(item1, (k, v) -> (v-food.getRecipe().getIngredients().get(item1)));
            }
        }
        if(house.getRefrigerator().areItemsAvailable(food.getRecipe().getIngredients())){
            for(Item item1 : food.getRecipe().getIngredients().keySet()){
                house.getRefrigerator().getFoods().compute(item1, (k, v) -> (v-food.getRecipe().getIngredients().get(item1)));
            }
        }
        App.getGame().getCurrentPlayer().backpack.addItem(food, 1);
        App.getGame().getCurrentPlayer().energy.updateEnergy(-3);
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
