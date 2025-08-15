package com.stardew.Models.Items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.Enums.ItemType;
import com.stardew.Enums.ToolLevel;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.GameMap;
import com.stardew.Models.MessageManager;
import com.stardew.Models.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tool extends Item {
    protected ToolLevel level;
    protected int energyConsumed;
    protected float showingAngle = 0;
    protected boolean isMoving = false;
    protected boolean holdInPlace = false;
    protected int rotationMultiple = -1;


    public Tool(ItemType itemType, ToolLevel toolLevel) {
        super(itemType);
        this.level = ToolLevel.normal;
        this.energyConsumed = Math.min(energy.get(itemType) - toolLevel.getLevel() + 1, 0);
    }


    public ToolLevel getLevel() {
        return level;
    }

    protected void changeToolLevel(ToolLevel toolLevel){
        this.level = toolLevel;
    }

    public void setLevel(ToolLevel level) {
        this.level = level;
    }

    public int getEnergyConsumed() {
        int level =0;
        if(itemType.equals(ItemType.Hoe) || itemType.equals(ItemType.WateringCan))
            level = App.getGame().getCurrentPlayer().foodBuff.energyReductions[2]
                    + App.getGame().getCurrentPlayer().abilities.getFarmingLevel()/4;
        if(itemType.equals(ItemType.Pickaxe))
            level = App.getGame().getCurrentPlayer().foodBuff.energyReductions[0]
                    +App.getGame().getCurrentPlayer().abilities.getMiningLevel()/4;
        if(itemType.equals(ItemType.Axe))
            level = App.getGame().getCurrentPlayer().foodBuff.energyReductions[3]
                    +App.getGame().getCurrentPlayer().abilities.getForagingLevel()/4;
        if(itemType.equals(ItemType.FishingPole))
            level = App.getGame().getCurrentPlayer().foodBuff.energyReductions[1]
                    +App.getGame().getCurrentPlayer().abilities.getFishingLevel()/4;
        return energyConsumed - level;
    }

    @Override
    public Tool clone(){
        return new Tool(getItemType(), level).makeSellPrice(baseSellPrice).setHoldInPlace(holdInPlace);
    }

    @Override
    public Tool makeSellPrice(double price) {
        baseSellPrice = price;
        return this;
    }

    @Override
    public Sprite getSprite(){
        if(rotationMultiple == 1){
            TextureRegion textureRegion = new TextureRegion(GameAssetManager.getToolSprites().get(itemType).get(level));
            textureRegion.flip(true, false);
            sprite = new Sprite(textureRegion);
        }
        else
            sprite = new Sprite(GameAssetManager.getToolSprites().get(itemType).get(level));
        sprite.setOrigin(0, 0);
        sprite.setPosition((float) (App.getMyPlayer().getSprite().getX()+App.getMyPlayer().getSprite().getWidth()*0.7),
            (float) (App.getCurrentPlayer().getSprite().getY()+ App.getCurrentPlayer().getSprite().getHeight()*0.37));
        sprite.setSize((float) (((double) GameMap.getTilePrintSize() /40)*sprite.getWidth()*0.7),
            (float) (((double) GameMap.getTilePrintSize() /40)*sprite.getHeight()*0.7));
        sprite.setRotation(showingAngle);
        if(itemType.equals(ItemType.MilkPail) || itemType.equals(ItemType.WateringCan)){
            sprite.setPosition((float) (App.getCurrentPlayer().getSprite().getX()+App.getCurrentPlayer().getSprite().getWidth()*0.5),
                (float) (App.getCurrentPlayer().getSprite().getY()+ App.getCurrentPlayer().getSprite().getHeight()*0.37));
            if(itemType.equals(ItemType.MilkPail)){
                if(showingAngle >= 100 || showingAngle <= -100) {
                    isMoving = false;
                }
            }
            else{
                if(showingAngle >= 80 || showingAngle <= -30) {
                    isMoving = false;
                }
            }
            if(!holdInPlace) {
                showingAngle = 0;
                holdInPlace = true;
                isMoving=false;
            }
        }
        else{
            if(showingAngle >= 180 || showingAngle <= -90) {
                showingAngle = 0;
                isMoving = false;
            }
        }
       // else if(itemType.equals(ItemType.Pickaxe))
        return sprite;
    }

    @Override
    public void update(float delta){
        if(isMoving){
            showingAngle += delta * 500*rotationMultiple;
        }
    }

    public void setEnergyConsumed(int energyConsumed) {
        this.energyConsumed = energyConsumed;
    }


    public Tool setHoldInPlace(boolean b){
        this.holdInPlace = b;
        return this;
    }


    private final Map<ItemType, Integer> energy = new HashMap<>(){{
        put(ItemType.Hoe, 5);
        put(ItemType.Pickaxe, 5);
        put(ItemType.Axe, 5);
        put(ItemType.Scythe, 2);
        put(ItemType.MilkPail, 4);
        put(ItemType.Shear, 4);
        put(ItemType.FishingPole, 8);
        put(ItemType.WateringCan, 5);
        put(ItemType.Trashcan, 0);
    }};

    public static final Tool normalHoe = new Tool(ItemType.Hoe, ToolLevel.normal);
    public static final Tool copperHoe = new Tool(ItemType.Hoe, ToolLevel.copper);
    public static final Tool ironHoe = new Tool(ItemType.Hoe, ToolLevel.iron);
    public static final Tool goldHoe = new Tool(ItemType.Hoe, ToolLevel.gold);
    public static final Tool iridiumHoe = new Tool(ItemType.Hoe, ToolLevel.iridium);
    public static final Tool normalPickaxe = new Tool(ItemType.Pickaxe, ToolLevel.normal);
    public static final Tool copperPickaxe = new Tool(ItemType.Pickaxe, ToolLevel.copper);
    public static final Tool ironPickaxe = new Tool(ItemType.Pickaxe, ToolLevel.iron);
    public static final Tool goldPickaxe = new Tool(ItemType.Pickaxe, ToolLevel.gold);
    public static final Tool iridiumPickaxe = new Tool(ItemType.Pickaxe, ToolLevel.iridium);
    public static final Tool normalAxe = new Tool(ItemType.Axe, ToolLevel.normal);
    public static final Tool copperAxe = new Tool(ItemType.Axe, ToolLevel.copper);
    public static final Tool ironAxe = new Tool(ItemType.Axe, ToolLevel.iron);
    public static final Tool goldAxe = new Tool(ItemType.Axe, ToolLevel.gold);
    public static final Tool iridiumAxe = new Tool(ItemType.Axe, ToolLevel.iridium);
    public static final Tool scythe = new Tool(ItemType.Scythe, ToolLevel.normal);
    public static final Tool milkPail = new Tool(ItemType.MilkPail, ToolLevel.normal).makeSellPrice(1000).setHoldInPlace(true);
    public static final Tool shear = new Tool(ItemType.Shear, ToolLevel.normal).makeSellPrice(1000);
    public static final Tool normalFishingPole = new Tool(ItemType.FishingPole, ToolLevel.normal).makeSellPrice(25).setHoldInPlace(true);
    public static final Tool bambooFishingPole = new Tool(ItemType.FishingPole, ToolLevel.bamboo).makeSellPrice(500).setHoldInPlace(true);
    public static final Tool fiberglassFishingPole = new Tool(ItemType.FishingPole, ToolLevel.fiberglass).makeSellPrice(1800).setHoldInPlace(true);
    public static final Tool iridiumFishingPole = new Tool(ItemType.FishingPole, ToolLevel.iridium).makeSellPrice(7500).setHoldInPlace(true);


    public static final ArrayList<Tool> allTools = new ArrayList<>(){{
        add(normalHoe);
        add(normalAxe);
        add(normalPickaxe);
        add(scythe);
        add(shear);
        add(milkPail);
        add(normalFishingPole);
        add(WateringCan.normalWateringCan);
    }};

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        if(App.getCurrentPlayer().getDirection() == 3 ||App.getCurrentPlayer().getDirection() == 2 ) {
            rotationMultiple = 1;
        }
        else {
            rotationMultiple = -1;
        }
        showingAngle = 0;
        isMoving = moving;
    }

    public void useTool(int x, int y){
        if(!(App.getGame().getCurrentPlayer().energy.getEnergy() > energyConsumed)){
            MessageManager.getMessage(Result.failure("Not enough energy to continue."));
            return;
        }
        if(itemType.equals(ItemType.Hoe)){

        }
        else if(itemType.equals(ItemType.Axe)){

        }
        else if(itemType.equals(ItemType.Pickaxe)){

        }
        else if(itemType.equals(ItemType.Scythe)){

        }
        else if(itemType.equals(ItemType.Shear)){

        }
        else if(itemType.equals(ItemType.MilkPail)){

        }
        else if(itemType.equals(ItemType.FishingPole)){

        }
    }
}
