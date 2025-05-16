package Models.Items;

import Enums.ItemType;
import Models.Game.App;
import Models.MessageManager;
import Models.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Refrigerator extends Item{
    private final Map<Item, Integer> foods = new HashMap<>();

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
        foods.compute(item, (k, v) -> (v-quantity));
        App.getGame().getCurrentPlayer().backpack.addItem(item, quantity);
    }


}
