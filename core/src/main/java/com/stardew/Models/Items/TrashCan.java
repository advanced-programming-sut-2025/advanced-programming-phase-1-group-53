package com.stardew.Models.Items;

import com.stardew.Enums.ItemType;
import com.stardew.Enums.ToolLevel;
import com.stardew.Models.Game.App;
import com.stardew.Models.MessageManager;
import com.stardew.Models.Result;

import java.util.ArrayList;
import java.util.HashMap;

public class TrashCan extends Tool{
    private final int returnPercent;
    private HashMap<Item, Integer> itemsToEliminate;

    public TrashCan(ItemType itemType, ToolLevel toolLevel, int returnPercent) {
        super(itemType, toolLevel);
        this.returnPercent = returnPercent;
        itemsToEliminate = new HashMap<>();
    }

    @Override
    public void update(){
        if(itemsToEliminate.isEmpty())
            return;
        if(App.getGame().dateAndTime.isADayPassed()){
            for(Item item : itemsToEliminate.keySet())
                eliminate(item.getItemType(), itemsToEliminate.get(item));
            itemsToEliminate = new HashMap<>();
        }
    }

    public void useTrashCan(ItemType itemType, int quantity){
        if(App.getGame().getItemByItemType(itemType) == null){
            MessageManager.getMessage(Result.failure("No item with such type."));
            return;
        }

        itemsToEliminate.compute(App.getGame().getItemByItemType(itemType), (k, v) -> (v == null)? 1 : v+quantity);
    }

    public void useTrashCan(ItemType itemType){
        if(App.getGame().getItemByItemType(itemType) == null){
            MessageManager.getMessage(Result.failure("No item with such type."));
            return;
        }

        if(!App.getGame().getCurrentPlayer().backpack.getItems().containsKey(App.getGame().getItemByItemType(itemType))){
            System.out.println("hdcvadas");
            return;
        }
        int quantity = App.getGame().getCurrentPlayer().backpack.getItems().get(App.getGame().getItemByItemType(itemType));

        itemsToEliminate.compute(App.getGame().getItemByItemType(itemType), (k, v) -> (v == null)? 1 : v+
                quantity);
    }

    private void eliminate(ItemType itemType, int quantity){
        int gold = App.getGame().getCurrentPlayer().personalInfo.getGold();
        if(quantity == 0){
            System.out.println("esdcrtrrd");
            return;
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
