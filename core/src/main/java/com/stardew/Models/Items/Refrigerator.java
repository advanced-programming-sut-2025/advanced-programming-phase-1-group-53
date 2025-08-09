package com.stardew.Models.Items;

import com.stardew.Enums.ItemType;
import com.stardew.Models.Game.App;
import com.stardew.Models.MessageManager;
import com.stardew.Models.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Refrigerator extends Item{
    private static final Map<Item, Integer> foods = new HashMap<>();

    public Refrigerator(){
        super(ItemType.Refrigerator);
    }


    public boolean areItemsAvailable(Map<Item, Integer> needs){
        for(Item item : needs.keySet()){
            if(foods.get(item) < needs.get(item))
                return false;
        }
        return true;
    }

    public Map<Item, Integer> getFoods() {
        return foods;
    }

    public void putItem(Item item, int quantity){
        if(!item.isEdible()) {
            MessageManager.getMessage(Result.failure("Item is not edible."));
            return;
        }
        if(!App.getGame().getCurrentPlayer().backpack.getItems().containsKey(item)){
            MessageManager.getMessage(Result.failure("There isn't any number of the item in inventory."));
            return;
        }
        if(App.getGame().getCurrentPlayer().backpack.getItems().get(item) < quantity){
            MessageManager.getMessage(Result.failure("Not enough quantity of the item."));
            return;
        }
        foods.compute(item, (k,v) -> (v==null)? 1:v+1);
        App.getGame().getCurrentPlayer().backpack.getItems().compute(item, (k, v) -> (v-quantity));
    }

    public void pickItem(Item item, int quantity){
        if(!foods.containsKey(item)){
            MessageManager.getMessage(Result.failure("No such food in refrigerator."));
            return;
        }
        if(foods.get(item) < quantity){
            MessageManager.getMessage(Result.failure("Not enough quantity of the food."));
            return;
        }
       // System.out.println(quantity+" "+foods.get(item));
        foods.compute(item, (k, v) -> (v-quantity));
        App.getGame().getCurrentPlayer().backpack.addItem(item, quantity);
        if(foods.get(item) == 0)
            foods.remove(item);
    }
}
