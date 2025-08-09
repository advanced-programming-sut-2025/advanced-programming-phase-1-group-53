package com.stardew.Models.Items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Enums.ItemType;
import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Game;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.Game.Player;
import com.stardew.Models.GameMap;
import com.stardew.Views.TabMenus.SellingMenu;

import java.util.HashMap;
import java.util.Map;

public class ShippingBin extends Item{
    private final Map<Item, Player> items = new HashMap<>();
    private SellingMenu sellingMenu = new SellingMenu();

    private ShippingBin(ItemType itemType){
        super(itemType);
    }

    public Map<Item, Player> getItems() {
        return items;
    }

    @Override
    public ShippingBin clone(){
        return new ShippingBin(itemType);
    }

    @Override
    public void update(float delta){
        if (App.getGame().dateAndTime.isADayPassed()) {
            for (Item item : items.keySet()) {
                System.out.println((int) item.getBaseSellPrice()+" mm");
                if(App.getGame().getItemByItemType(item.getItemType()) != null){
                    items.get(item).personalInfo.updateGold((int) App.getGame().getItemByItemType(item.getItemType()).getBaseSellPrice());
                }
                else
                    items.get(item).personalInfo.updateGold((int) item.getBaseSellPrice());
            }
            items.clear();
        }
    }

    public void setUpSellingMenu(){
        Main.main.setScreen(sellingMenu);
    }


    @Override
    public Sprite getSprite(){
        sprite = new Sprite(GameAssetManager.getShippingBinSprite());
        sprite.setSize((float) (sprite.getWidth()*2.5), (float) (sprite.getHeight()*1.5));
        sprite.setPosition(position.getX()* GameMap.getTilePrintSize(), position.getY()*GameMap.getTilePrintSize());
        return sprite;
    }

    public static final ShippingBin ShippingBin = new ShippingBin(ItemType.ShippingBin);
}
