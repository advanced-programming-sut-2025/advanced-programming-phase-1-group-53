package Models;

import Enums.BackpackLevel;
import Enums.Fish;
import Models.Items.CraftingRecipe;
import Models.Items.Item;
import Models.Items.Tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Backpack {
    private HashMap<Item, Integer> items;
    private HashMap<Product, Integer> products;
    private BackpackLevel level;
    private Item itemInHand;

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public void setItemInHand(Item itemInHand) {
        this.itemInHand = itemInHand;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public Item getItemInHand() {
        return itemInHand;
    }

    public Backpack() {
        this.level = BackpackLevel.small;
    }

    public void showInventory(){

    }

    public void addTool(Tool tool, int amount){

    }

    public void addItem(Item item, int amount){

    }
    public void addFish(Fish fish, int amount) {

    }

    public BackpackLevel getLevel() {
        return level;
    }

    public void setLevel(BackpackLevel level) {
        this.level = level;
    }

    public void addToBackPack(Fish fish) {}

    public void addToBackPack(Tool tool) {}

    public Map<Item, Integer> getTools(){
        return items.entrySet().stream()
                .filter(entry -> entry.getKey() instanceof Tool)
                .collect(Collectors.toMap(
                        entry -> (Tool) entry.getKey(),
                        Map.Entry::getValue
                ));
    }

    public Map<Item, Integer> getCraftingRecipes(){
        return items.entrySet().stream()
                .filter(entry -> entry.getKey() instanceof CraftingRecipe)
                .collect(Collectors.toMap(
                        entry -> (CraftingRecipe) entry.getKey(),
                        Map.Entry::getValue
                ));
    }
}
