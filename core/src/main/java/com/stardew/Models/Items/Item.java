package com.stardew.Models.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Controllers.GameMenuController;
import com.stardew.Enums.ItemType;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.GameMap;
import com.stardew.Models.Position;

import java.util.ArrayList;

public class Item {
    protected Sprite sprite;
    protected ItemType itemType;
    protected final Position position = new Position(0, 0, 0, 0);
    protected double baseSellPrice = 0;
    protected boolean isEdible = false;
    protected double energy = 0;

    public Item clone(){
        return new Item(itemType).setSprite(this.sprite.getTexture());
    }
    public Item(ItemType itemType){
        this.itemType = itemType;
    }


    public Sprite fixSpriteCoordinatesForPrint(){
        sprite.setX(position.getX()* GameMap.getTilePrintSize() - GameMenuController.getPrintStartX());
        sprite.setY(position.getY()*GameMap.getTilePrintSize() - GameMenuController.getPrintStartY());
        return sprite;
    }


    public Item setSprite(Texture texture){
        try{
            this.sprite = new Sprite(texture);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

    public Sprite getSprite(){
        sprite = new Sprite(GameAssetManager.getItemsSprites().get(itemType));
        return fixSpriteCoordinatesForPrint();
    }

    public double getBaseSellPrice() {
        return baseSellPrice;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Position getPosition() {
        return position;
    }

    public Item makeEdible(double energy){
        isEdible = true;
        this.energy = energy ;
        return this;
    }

    public boolean isEdible(){
        return isEdible;
    }

    public void update(float delta){
        return;
    }

    public Item makeSellPrice(double price){
        baseSellPrice = price;
        return this;
    }

    public double getEnergy() {
        return energy;
    }

    public static final Item Sugar = new Item(ItemType.Sugar).makeSellPrice(100);
    public static final Item Bouquet = new Item(ItemType.Bouquet).makeSellPrice(1000);
    public static final Item WeddingRing = new Item(ItemType.WeddingRing).makeSellPrice(10000);
    public static final Item SpeedGro = new Item(ItemType.SpeedGro).makeSellPrice(100);
    public static final Item DeluxeSoil = new Item(ItemType.DeluxeSoil).makeSellPrice(100);
    public static final Item QualitySoil = new Item(ItemType.QualitySoil).makeSellPrice(100);
    public static final Item Hay = new Item(ItemType.Hay);
    public static final Item BasicSoil = new Item(ItemType.BasicSoil).makeSellPrice(100);
    public static final Item TroutSoup = new Item(ItemType.TroutSoup).makeSellPrice(250).makeEdible(100);
    public static final Item WheatFlour = new Item(ItemType.WheatFlour).makeSellPrice(125);
    public static final Item Well = new Item(ItemType.Well).makeSellPrice(1000);

    public static final ArrayList<Item> allItems = new ArrayList<>(){{
        add(Sugar);
        add(Bouquet);
        add(WeddingRing);
        add(SpeedGro);
        add(DeluxeSoil);
        add(QualitySoil);
        add(Hay);
        add(BasicSoil);
        add(TroutSoup);
        add(WheatFlour);
        add(Well);
    }};

}
