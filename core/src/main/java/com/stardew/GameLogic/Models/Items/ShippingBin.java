package com.stardew.GameLogic.Models.Items;

import com.stardew.GameLogic.Enums.ItemType;
import com.stardew.GameLogic.Models.Game.App;

import java.util.HashMap;
import java.util.Map;

public class ShippingBin extends Item{
    private final Map<Item, Integer> items = new HashMap<>();

    private ShippingBin(ItemType itemType){
        super(itemType);
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    @Override
    public ShippingBin clone(){
        return new ShippingBin(itemType);
    }

    @Override
    public void update(){
        if(App.getGame().dateAndTime.isADayPassed()) {
            if (App.getGame().dateAndTime.isADayPassed()) {
                for (Item item : items.keySet()) {
                    App.getGame().getCurrentPlayer().personalInfo.updateGold((int) (items.get(item) * item.getBaseSellPrice()));
                }
            }
        }
    }

    public static final ShippingBin ShippingBin = new ShippingBin(ItemType.ShippingBin);
}
