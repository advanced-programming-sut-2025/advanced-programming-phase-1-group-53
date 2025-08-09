package com.stardew.Models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Enums.ItemType;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.GameAssetManager;

import java.util.ArrayList;

public class FoodBuff {
    private float finishingTime = 0;
    private ItemType food;
    public final int[] energyReductions = new int[4];
    private Sprite sprite = null;

    public void espressoBuff(){
        if(food.equals(ItemType.TripleShotEspresso))
            App.getGame().getCurrentPlayer().energy.updateEnergy(100);
        sprite = new Sprite(GameAssetManager.getBuffSprites().get(3));
    }

    public void redPlateBuff(){
        if(food.equals(ItemType.RedPlate))
            App.getGame().getCurrentPlayer().energy.updateEnergy(50);
        sprite = new Sprite(GameAssetManager.getBuffSprites().get(3));
    }

    public void hashBrownBuff(){
        if(food.equals(ItemType.HashBrowns))
            energyReductions[2] = 1;
        sprite = new Sprite(GameAssetManager.getBuffSprites().get(0));
    }
    public void pancakeBuff(){
        if(food.equals(ItemType.HashBrowns) )
            energyReductions[3] = 1;
        sprite = new Sprite(GameAssetManager.getBuffSprites().get(2));
    }
    public void farmersLunchBuff(){
        if(food.equals(ItemType.HashBrowns))
            energyReductions[2] = 1;
        sprite = new Sprite(GameAssetManager.getBuffSprites().get(0));
    }
    public void survivalBurgerBuff(){
        if(food.equals(ItemType.HashBrowns) )
            energyReductions[3] = 1;
        sprite = new Sprite(GameAssetManager.getBuffSprites().get(2));
    }
    public void dishOTheSeaBuff(){
        if(food.equals(ItemType.HashBrowns) )
            energyReductions[1] = 1;
        sprite = new Sprite(GameAssetManager.getBuffSprites().get(1));
    }
    public void seaFormPuddingBuff(){
        if(food.equals(ItemType.HashBrowns))
            energyReductions[1] = 1;
        sprite = new Sprite(GameAssetManager.getBuffSprites().get(1));
    }
    public void minersTreatBuff(){
        if(food.equals(ItemType.HashBrowns) )
            energyReductions[0] = 1;
        sprite = new Sprite(GameAssetManager.getBuffSprites().get(4));
    }

    public void activateBuff(ItemType itemType){
        food = itemType;
        float current = App.getGame().dateAndTime.getHour();
        finishingTime = switch (itemType){
            case HashBrowns -> 5;
            case Pancakes -> 11;
            case RedPlate -> 3;
            case TripleShotEspresso -> 5;
            case FarmersLunch -> 5;
            case SurvivalBurger -> 5;
            case DishOTheSea -> 5;
            case SeaFormPudding -> 10;
            case MinersTreat -> 5;
            default -> 0;
        };
        finishingTime += current;
        switch (food){
            case HashBrowns -> hashBrownBuff();
            case Pancakes -> pancakeBuff();
            case RedPlate -> redPlateBuff();
            case TripleShotEspresso -> espressoBuff();
            case FarmersLunch -> farmersLunchBuff();
            case SurvivalBurger -> survivalBurgerBuff();
            case DishOTheSea -> dishOTheSeaBuff();
            case SeaFormPudding -> seaFormPuddingBuff();
            case MinersTreat -> minersTreatBuff();
        }
    }

    public void update(float delta){
        if(food == null)
            return;
        float current = App.getGame().dateAndTime.getHour();
        if(current >= finishingTime){
            finishingTime = 0;
            sprite = new Sprite();
            for(int i= 0; i<4; i++){
                energyReductions[i] = 0;
                if(food.equals(ItemType.TripleShotEspresso) || food.equals(ItemType.RedPlate)){
                    Energy.setMaxEnergy(Energy.getMaxEnergy()/2);
                    App.getCurrentPlayer().energy.setEnergy(Math.min(App.getCurrentPlayer().energy.getEnergy(),
                        Energy.getMaxEnergy()));
                }
            }
        }
    }

    public Sprite getSprite(){
        if(sprite != null)
            return sprite;
        return new Sprite();
    }
}
