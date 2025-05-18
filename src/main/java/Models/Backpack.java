package Models;

import Enums.BackpackLevel;
import Enums.ItemType;
import Models.Game.App;
import Models.Items.*;
import Models.Items.CraftAbleAndArtisan.Artisan;
import Models.Items.CraftAbleAndArtisan.ArtisanGood;
import Models.Items.CraftAbleAndArtisan.ScareCrow;
import Models.Items.CraftAbleAndArtisan.Sprinkler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Backpack {
    private HashMap<Item, Integer> items = new HashMap<>();
    private BackpackLevel level;
    private Item itemInHand;
    private final ArrayList<CoopAndBarn> coopsAndBarns = new ArrayList<>();


    public Backpack() {
        for(Tool tool : Tool.allTools){
            if(tool.getItemType().equals(ItemType.FishingPole))
                continue;
            items.put(tool.clone(), 1);
        }
        items.put(Recipe.FriedEggRecipe, 1);
        items.put(Recipe.BakedFishRecipe, 1);
        items.put(Recipe.SaladRecipe, 1);
        items.put(Recipe.SalmonDinnerRecipe, 1);
        items.put(Recipe.VegetableMedelyRecipe, 1);
        items.put(Recipe.FarmerLunchRecipe, 1);
        items.put(Recipe.SurvivalBurgerRecipe, 1);
        items.put(Recipe.DishOtheSeaRecipe, 1);
        items.put(Recipe.MinersTreatRecipe, 1);
        items.put(Recipe.SeaFormPuddingRecipe, 1);



        for(CraftingRecipe recipe : CraftingRecipe.craftingRecipes){
            if(!(recipe.getItemType().equals(ItemType.DehydratorCR) || recipe.getItemType().equals(ItemType.FishSmokerCR)
                    || recipe.getItemType().equals(ItemType.GrassStarterCR)))
                items.put(recipe, 1);
        }
        this.level = BackpackLevel.small;
    }


    public void update(){
        for(Item item : items.keySet()){
            item.update();
        }
    }
    public void useTrashCan(ItemType itemType, int count){
        if(areItemsAvailable(App.getGame().getItemByItemType(itemType), count)){
            TrashCan trashCan = (TrashCan) App.getGame().getItemByItemType(ItemType.Trashcan);
            trashCan.useTrashCan(itemType, count);
        }
    }

    public ArrayList<CoopAndBarn> getCoopsAndBarns() {
        return coopsAndBarns;
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public void setItemInHand(Item itemInHand) {
        if(!items.containsKey(itemInHand)){
            MessageManager.getMessage(Result.failure("You have no number of the object."));
            return;
        }
        if(items.get(itemInHand) == 0){
            MessageManager.getMessage(Result.failure("You have no number of the object..."));
            return;
        }
        this.itemInHand = itemInHand;
        MessageManager.getMessage(Result.success("You are now handling a " + itemInHand.getItemType() + "."));
    }

    public ArrayList<Animal> getAnimals() {
        ArrayList<Animal> animals = new ArrayList<>();
        for(CoopAndBarn coopAndBarn : coopsAndBarns){
            animals.addAll(coopAndBarn.getAnimals());
        }
        return animals;
    }

    public Item getItemInHand() {
        return itemInHand;
    }

    public void howMuchWater(){
        for(Item item : items.keySet()){
            if(item.getItemType().equals(ItemType.WateringCan)) {
                MessageManager.getMessage(Result.success("level : " + ((WateringCan) item).getCurrentWaterLevel()));
            }
        }
    }


    public void showInventory(){
        if(items.isEmpty()){
            MessageManager.getMessage(Result.failure("Your inventory is empty."));
            return;
        }
        for(Item item : items.keySet()){
            if(item instanceof Artisan || item instanceof ScareCrow || item instanceof Sprinkler
                    || item instanceof Animal || item instanceof CoopAndBarn || item instanceof CraftingRecipe || item instanceof Recipe
                    || item instanceof Tool)
                continue;
            if(item instanceof ArtisanGood artisanGood && !artisanGood.isPicked())
                continue;
            if(items.get(item) != 0){
                MessageManager.getMessage(Result.success(item.getItemType().name() + ", Quantity : " + items.get(item)));
            }
        }
    }

    public void showCoopsAndBarns(){
        for(CoopAndBarn coopAndBarn : coopsAndBarns){
            MessageManager.getMessage(Result.success(coopAndBarn.getItemType().name()+
                    coopAndBarn.getPosition().getX() + ", " + coopAndBarn.getPosition().getY()));
        }
    }

    public void showFullInventoryTemporary(){
        if(items.isEmpty()){
            MessageManager.getMessage(Result.failure("Your inventory is empty."));
            return;
        }
        for(Item item : items.keySet()){
            if(items.get(item) != 0){
                MessageManager.getMessage(Result.success(item.getItemType().name() + ", Quantity : " + items.get(item)));
            }
        }
    }

    public BackpackLevel getLevel() {
        return level;
    }

    public void setLevel(BackpackLevel level) {
        this.level = level;
    }

    public int numOfEmptyPlaces(){
        int num = 0;
        for(Item item : items.keySet()){
            if(!(item instanceof Artisan || item instanceof ScareCrow || item instanceof Sprinkler
                    || item instanceof Animal || item instanceof CoopAndBarn || item instanceof CraftingRecipe || item instanceof Recipe))
                num ++;
        }
        return Math.max(0, level.getSize() - num);
    }

    public boolean isInventoryFull(){
        int num = 0;
        for(Item item : items.keySet()){
            if(!(item instanceof Artisan || item instanceof ScareCrow || item instanceof Sprinkler
                    || item instanceof Animal || item instanceof CoopAndBarn || item instanceof CraftingRecipe || item instanceof Recipe
                    || item instanceof Tool))
                num ++;
            if(num >= level.getSize())
                return true;
        }
        return false;
    }

    public void addItem(Item item, int amount){
        if(isInventoryFull()){
            System.out.println("ayeyuw");/////
            MessageManager.getMessage(Result.failure("ayeyuw"));
            return;
        }
        if(item == null) {
            System.out.println("sdyuw");/////
            MessageManager.getMessage(Result.failure("sdyuw"));
            return;
        }
        items.compute(item, (k, v) -> (v==null)? amount : (v+amount));
    }


    public void addItem(Item item){
        if(isInventoryFull()){
            MessageManager.getMessage(Result.failure("ayeyuw"));
            return;
        }
        if(item == null) {
            MessageManager.getMessage(Result.failure("sdyuw"));
            return;
        }
        items.compute(item, (k, v) -> (v==null)? 1 : v+1);
    }

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

    public Map<Item, Integer> getCookingRecipes(){
        return items.entrySet().stream()
                .filter(entry -> entry.getKey() instanceof Recipe)
                .collect(Collectors.toMap(
                        entry -> (Recipe) entry.getKey(),
                        Map.Entry::getValue
                ));
    }

    public boolean areItemsAvailable(Map<Item, Integer> needs){
        for(Item item : needs.keySet()){
            if(!items.containsKey(item))
                return false;
            if(items.get(item) < needs.get(item))
                return false;
        }
        return true;
    }

    public boolean areItemsAvailable(Item item, int amount){
        if(!items.containsKey(item))
            return false;
        if(items.get(item) < amount)
            return false;
        return true;
    }

    public Animal getAnimalByName(String name){
        for(Animal animal : getAnimals()){
            if(animal.getName().equals(name))
                return animal;
        }
        return null;
    }

}
