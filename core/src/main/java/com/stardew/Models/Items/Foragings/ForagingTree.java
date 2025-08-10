package com.stardew.Models.Items.Foragings;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Enums.ItemType;
import com.stardew.Enums.Season;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.GameMap;

import java.util.ArrayList;

public class ForagingTree  extends Plant{
    private final ArrayList<Season> seasons;
    private boolean isThundered = false;

    private ForagingTree(ItemType itemType,  ArrayList<Season> seasons){
        super(itemType);
        hitsRemainedToDestroy = 5;
        this.seasons = seasons;
    }

    public boolean isThundered() {
        return isThundered;
    }

    public void setThundered(boolean thundered) {
        isThundered = thundered;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    @Override
    public ForagingTree makeEdible(double energy){
        return this;
    }

    @Override
    public ForagingTree clone(){
        return new ForagingTree(getItemType(), seasons);
    }

    @Override
    public Sprite getSprite(){
        int season = App.getGame().dateAndTime.getSeason().ordinal();
        if(itemType == ItemType.MushroomTree) {
            sprite = new Sprite(GameAssetManager.getForagingTreeSprites().get(itemType)[0]);
            sprite.setSize((float) (GameMap.getTilePrintSize()*1.23), (float) (GameMap.getTilePrintSize()*2.6));
        }
        else if(isThundered) {
            sprite = new Sprite(GameAssetManager.getForagingTreeSprites().get(itemType)[1]);
            sprite.setSize((float) (GameMap.getTilePrintSize()), (float) (GameMap.getTilePrintSize()*0.8));
        }
        else {
            sprite = new Sprite(GameAssetManager.getForagingTreeSprites().get(itemType)[0], 104 * season, 0,
                104, 180);
            sprite.setSize((float) (GameMap.getTilePrintSize()*1.23), (float) (GameMap.getTilePrintSize()*2.6));
        }
        sprite.setPosition(position.getX()* GameMap.getTilePrintSize(), position.getY()* GameMap.getTilePrintSize());
        return sprite;
    }

    @Override
    public String details(){
        return "Name : " + itemType.name() + "\n" +
                "Season : " + seasons.toString();
    }

    public static final ForagingTree mapleTree = new ForagingTree(ItemType.MapleTree,  Plant.specialSeasons);
    public static final ForagingTree pineTree = new ForagingTree(ItemType.PineTree,  Plant.specialSeasons);
    public static final ForagingTree mahoganyTree = new ForagingTree(ItemType.MahoganyTree, Plant.specialSeasons);
    public static final ForagingTree mushroomTree = new ForagingTree(ItemType.MushroomTree, Plant.specialSeasons);

    public static final ArrayList<ForagingTree> trees = new ArrayList<>(){{
        add(mapleTree);
        add(pineTree);
        add(mahoganyTree);
        add(mushroomTree);
    }};
}
