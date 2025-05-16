package Models.Abilities;

import Enums.ItemType;
import Models.Game.App;
import Models.Game.Game;
import Models.Items.Item;
import Models.Items.Tool;
import Models.MessageManager;
import Models.Result;

public class Activity {
    public void equipTool(ItemType itemType){
        for(Item item : App.getGame().getCurrentPlayer().backpack.getItems().keySet()){
            if(item.getItemType().equals(itemType)) {
                App.getGame().getCurrentPlayer().backpack.setItemInHand(item);
            }
        }
        MessageManager.getMessage(Result.failure("No such tool in inventory."));
    }

    public void showCurrentTool(){
        if(App.getGame().getCurrentPlayer().backpack.getItemInHand() == null)
            MessageManager.getMessage(Result.failure("You don't have any tool currently."));
        else if(!(App.getGame().getCurrentPlayer().backpack.getItemInHand() instanceof Tool))
            MessageManager.getMessage(Result.failure("You handle some other item than a tool."));
        else
            MessageManager.getMessage(Result.success("Item in hand : " + App.getGame().getCurrentPlayer().backpack.getItemInHand().getItemType().name()));
    }

    public void showAvailableTools(){
        if(App.getGame().getCurrentPlayer().backpack.getTools().isEmpty())
            MessageManager.getMessage(Result.failure("No tool available in the backpack."));
        else
            MessageManager.getMessage(Result.success("Available tools : \n" + App.getGame().getCurrentPlayer().backpack.getTools().keySet().toString()));
    }

    public void upgradeTool(ItemType itemType){

    }

    public void useTool(int x, int y){
        switch (App.getGame().getCurrentPlayer().backpack.getItemInHand().getItemType()){
            case Hoe:

                break;
            case Axe:

                break;
            case Pickaxe:

                break;
            case WateringCan:

                break;
            case FishingPole:

                break;
            case Scythe:

                break;
            case Shear:

                break;
            case MilkPail:

                break;
            default:

        }
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

    public void addItem(ItemType itemType, int quantity){
        if(App.getGame().getCurrentPlayer().backpack.isInventoryFull()) {
            MessageManager.getMessage(Result.failure("Inventory is full."));
            return;
        }
        App.getGame().getCurrentPlayer().backpack.addItem(App.getGame().getItemByItemType(itemType), quantity);
    }

}
