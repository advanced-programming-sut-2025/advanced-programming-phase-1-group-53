package Models.Items;

import Enums.ItemType;
import Enums.ToolLevel;
import Models.Game.App;
import Models.MessageManager;
import Models.Result;

import java.util.ArrayList;
import java.util.HashMap;

public class TrashCan extends Tool{
    private final int returnPercent;

    public TrashCan(ItemType itemType, ToolLevel toolLevel, int returnPercent) {
        super(itemType, toolLevel);
        this.returnPercent = returnPercent;
    }

    public void eliminate(ItemType itemType, int quantity){
        int gold = App.getGame().getCurrentPlayer().personalInfo.getGold();
        if(quantity == 0){
            App.getGame().getCurrentPlayer().backpack.getItems().remove(App.getGame().getItemByItemType(itemType));
        }
        else if(App.getGame().getCurrentPlayer().backpack.getItems().get(App.getGame().getItemByItemType(itemType)) >= quantity){
            App.getGame().getCurrentPlayer().backpack.getItems().compute(App.getGame().getItemByItemType(itemType),
                    (key, oldVal) -> (oldVal-quantity));
        }
        else {
            MessageManager.getMessage(Result.failure("Not enough quantity of the product is available."));
            return;
        }
        if(App.getGame().getItemByItemType(itemType).getBaseSellPrice() == 0){
            MessageManager.getMessage(Result.failure("The item is not sell able."));
            return;
        }
        App.getGame().getCurrentPlayer().personalInfo.setGold((int) (gold + returnPercent * quantity * App.getGame().getItemByItemType(itemType).getBaseSellPrice()));
    }

    public static final TrashCan normalTrashCan = new TrashCan(ItemType.Trashcan, ToolLevel.normal, 0);
    public static final TrashCan copperTrashCan = new TrashCan(ItemType.Trashcan, ToolLevel.copper, 15);
    public static final TrashCan ironTrashCan = new TrashCan(ItemType.Trashcan, ToolLevel.iron, 30);
    public static final TrashCan goldTrashCan = new TrashCan(ItemType.Trashcan, ToolLevel.gold, 45);
    public static final TrashCan iridiumTrashCan = new TrashCan(ItemType.Trashcan, ToolLevel.iridium, 60);

    public static final ArrayList<TrashCan> allTrashCans = new ArrayList<>(){{
        add(normalTrashCan);
        add(copperTrashCan);
        add(ironTrashCan);
        add(goldTrashCan);
        add(iridiumTrashCan);
    }};
}
