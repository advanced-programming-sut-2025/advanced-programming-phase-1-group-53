package com.stardew.Models.Items.CraftAbleAndArtisan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Enums.ItemType;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.Items.CraftingRecipe;
import com.stardew.Models.Items.Item;

import java.util.ArrayList;

public class CraftAble extends Item {
    protected final CraftingRecipe craftingRecipe;

    protected CraftAble(ItemType itemType, CraftingRecipe craftingRecipe) {
        super(itemType);
        this.craftingRecipe = craftingRecipe;
    }

    public CraftingRecipe getCraftingRecipe() {
        return craftingRecipe;
    }

    @Override
    public CraftAble makeSellPrice(double price){
        baseSellPrice = price;
        return this;
    }

    @Override
    public Sprite getSprite(){
        float x = -1;
        float y = -1;
        if(sprite != null){
            x = sprite.getX();
            y = sprite.getY();
        }
        sprite = new Sprite(GameAssetManager.getCraftableSprites().get(itemType));
        if( x!= -1){
            sprite.setPosition(x, y);
        }
        return sprite;
    }

    @Override
    public CraftAble clone(){
        return new CraftAble(itemType, craftingRecipe);
    }

    public static final CraftAble GrassStarter = new CraftAble(ItemType.GrassStarter, CraftingRecipe.GrassStarterCR);
    public static final CraftAble MysticTreeSeed = new CraftAble(ItemType.MysticTreeSeed, CraftingRecipe.MysticTreeSeedCR).makeSellPrice(100);

    public static final ArrayList<CraftAble> allCraftables = new ArrayList<>(){{
        add(GrassStarter);
        add(MysticTreeSeed);
        addAll(Bomb.allBombs);
        addAll(ScareCrow.allScareCrows);
        addAll(Sprinkler.allSprinklers);
    }};
}
