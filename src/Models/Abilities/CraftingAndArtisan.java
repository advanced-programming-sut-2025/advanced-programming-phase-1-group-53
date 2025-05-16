package Models.Abilities;

import Enums.ItemType;
import Models.Game.App;
import Models.Game.Game;
import Models.Items.CraftAbleAndArtisan.Artisan;
import Models.Items.CraftAbleAndArtisan.ArtisanGood;
import Models.Items.CraftAbleAndArtisan.CraftAble;
import Models.Items.CraftingRecipe;
import Models.Items.Item;
import Models.Items.Recipe;
import Models.MessageManager;
import Models.Result;

import java.util.ArrayList;
import java.util.List;

public class CraftingAndArtisan {
    private final ArrayList<Recipe> knownRecipes = new ArrayList<>();

    public void showCraftingRecipes(boolean showAll) {
        if (!showAll) {
            for (Item item : App.getGame().getCurrentPlayer().backpack.getCraftingRecipes().keySet()) {
                if(!(item instanceof CraftingRecipe craftingRecipe)){
                    MessageManager.getMessage(Result.failure("jancs"));
                    return;
                }
                if (!craftingRecipe.isAvailable())
                    continue;
                System.out.println(craftingRecipe.details() + "\n");
                MessageManager.getMessage(Result.success(craftingRecipe.details()));
            }
        }
        else{
            for (Item item : App.getGame().getCurrentPlayer().backpack.getCraftingRecipes().keySet()) {
                if(!(item instanceof CraftingRecipe craftingRecipe)){
                    MessageManager.getMessage(Result.failure("jancs"));
                    return;
                }
                System.out.println(craftingRecipe.details() + "\n");
                MessageManager.getMessage(Result.success(craftingRecipe.details()));
            }
        }
    }

    public void craft(ItemType itemType){
        if(!(App.getGame().getItemByItemType(itemType) instanceof CraftAble craftAble)) {
            System.out.println("The item is not craft able.");////
            MessageManager.getMessage(Result.failure("The item is not craft able."));
            return;
        }
        if(App.getGame().getCurrentPlayer().backpack.isInventoryFull()){
            System.out.println("Inventory is full.");///
            MessageManager.getMessage(Result.failure("Inventory is full."));
            return;
        }
        if(!App.getGame().getCurrentPlayer().backpack.areItemsAvailable(craftAble.getCraftingRecipe().getIngredients())){
            MessageManager.getMessage(Result.failure("Insufficient material."));
            System.out.println("Insufficient material.");///
            return;
        }
        if(!craftAble.getCraftingRecipe().isAvailable() || !App.getGame().getCurrentPlayer().backpack.
                areItemsAvailable(craftAble.getCraftingRecipe(), 1)){
            System.out.println("You haven't accessed the recipe yet.");///
            MessageManager.getMessage(Result.failure("You haven't accessed the recipe yet."));
            return;
        }
        for(Item item : craftAble.getCraftingRecipe().getIngredients().keySet()){
            App.getGame().getCurrentPlayer().backpack.getItems().compute(item, (k,v) -> (v-craftAble.getCraftingRecipe().getIngredients().get(item)));
        }
        App.getGame().getCurrentPlayer().backpack.addItem(craftAble);
        App.getGame().getCurrentPlayer().energy.updateEnergy(-2);
        MessageManager.getMessage(Result.success(itemType.name() + " was crafted successfully."));
    }

    public void artisanUse(ItemType itemType, List<ItemType> ingredients){
        if(!(App.getGame().getItemByItemType(itemType) instanceof Artisan artisan)){
            System.out.println("The called item is not an artisan machine.");/////
            MessageManager.getMessage(Result.failure("The called item is not an artisan machine."));
            return;
        }
        if(App.getGame().getCurrentPlayer().backpack.getItems().containsKey(App.getGame().getItemByItemType(itemType))){
            MessageManager.getMessage(Result.failure("You don't have the artisan machine."));
            System.out.println("You don't have the artisan machine.");/////
            return;
        }

        artisan.useArtisan(ingredients);

        System.out.println("Artisan worked successfully. Come later to collect the product");/////
    }

    public void artisanGet(ItemType itemType){
        if(!(App.getGame().getItemByItemType(itemType) instanceof ArtisanGood)){
            MessageManager.getMessage(Result.failure("The item is not even an artisan good."));
            return;
        }

        App.getGame().getItemByItemType(itemType);

        for(Item item : App.getGame().getCurrentPlayer().backpack.getItems().keySet()){
            if(item.getItemType().equals(itemType)){
                ArtisanGood artisanGood = (ArtisanGood) item;
                if((!artisanGood.isPicked()) && artisanGood.getProcessingTimeLeft() == 0){
                    App.getGame().getCurrentPlayer().backpack.getItems().remove(artisanGood);
                    MessageManager.getMessage(Result.success("a" + itemType.name()+"was added to your inventory"));
                }
                if((!artisanGood.isPicked()) && artisanGood.getProcessingTimeLeft() > 0){
                    MessageManager.getMessage(Result.failure("The item isn't prepared yet."));
                }
            }
        }
    }
}
