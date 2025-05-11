package Models.Abilities;

import Models.Game.App;
import Models.Game.Game;
import Models.Items.Item;
import Models.Items.Tool;
import Models.MessageManager;
import Models.Result;

public class Activity {
    App app = App.getInstance();
    Game game = App.getGame();
    public void equipTool(String toolName){
        for(Item item : game.getPlayer(game.getNumOfTurn()).backpack.getItems().keySet()){
            if(item.getItemType().name().equals(toolName)) {
                game.getCurrentPlayer().backpack.getItems().remove(item);
                game.getCurrentPlayer().backpack.setItemInHand(item);
            }
        }
        MessageManager.getMessage(Result.failure("No such tool in inventory."));
    }

    public void showCurrentTool(){
        if(game.getPlayer(game.getNumOfTurn()).backpack.getItemInHand() == null)
            MessageManager.getMessage(Result.failure("You don't have any tool currently."));
        else if(!(game.getPlayer(game.getNumOfTurn()).backpack.getItemInHand() instanceof Tool))
            MessageManager.getMessage(Result.failure("You handle some other item than a tool."));
        else
            MessageManager.getMessage(Result.success(game.getPlayer(game.getNumOfTurn()).backpack.getItemInHand().getItemType().name()));
    }

    public void showAvailableTools(){
        if(game.getPlayer(game.getNumOfTurn()).backpack.getTools().isEmpty())
            MessageManager.getMessage(Result.failure("No tool available in the backpack."));
        else
            MessageManager.getMessage(Result.success(game.getPlayer(game.getNumOfTurn()).backpack.getTools().keySet().toString()));
    }

    public void upgradeTool(String tool){

    }

    public void useTool(String direction){
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

}
