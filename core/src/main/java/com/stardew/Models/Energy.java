package com.stardew.Models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Views.GameMenu;

public class Energy {
    private Sprite fullEnergySprite ;
    private float fullEnergySpriteWidth;
    private float fullEnergySpriteHeight ;

    private int lastUpdateTime = 0;
    private boolean isLimited = true;
    private static int maxEnergy = 2000000;
    private int energy =maxEnergy;
    private int energyConsumedThisTurn = 0;


    public void setUnlimitedEnergy(){
        energy = maxEnergy;
        isLimited = false;
    }

    public Sprite[] getSprite() {
        fullEnergySprite= new Sprite(GameAssetManager.getFullEnergySprite());
        fullEnergySpriteWidth =  fullEnergySprite.getWidth();
        fullEnergySpriteHeight =  fullEnergySprite.getHeight();
        float v = 1- ((float)energy / maxEnergy);
        Sprite sprite = new Sprite(GameAssetManager.getEmptyEnergyBarSprite(), GameAssetManager.getEmptyEnergyBarSprite().getWidth(),
            (int)(GameAssetManager.getEmptyEnergyBarSprite().getHeight()*v));
        fullEnergySprite.setPosition((float) (GameMenu.getScreenWidth()*15/16), (float) (GameMenu.getScreenHeight()/16));
        sprite.setPosition((float) (GameMenu.getScreenWidth()*15/16)+10, (float) (GameMenu.getScreenHeight()/16)+
            ((1-v)*(fullEnergySprite.getHeight()-5))+6.5f);
        fullEnergySprite.setSize((float) (((double) GameMap.getTilePrintSize() /40)*fullEnergySpriteWidth*0.5),
            (float) (((double) GameMap.getTilePrintSize() /40)*fullEnergySpriteHeight*0.5));
        sprite.setSize((float) (((double) GameMap.getTilePrintSize() /40)*sprite.getWidth()*0.5),
            (float) (((double) GameMap.getTilePrintSize() /40)*sprite.getHeight()*0.5));
        return new Sprite[]{fullEnergySprite, sprite};
    }

    public int getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(int lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public boolean isLimited() {
        return isLimited;
    }

    public void setLimited(boolean limited) {
        isLimited = limited;
    }

    public static int getMaxEnergy() {
        return maxEnergy;
    }

    public static void setMaxEnergy(int maxEnergyy) {
        maxEnergy = maxEnergyy;
    }

    public void updateMaxEnergy(int a){
        maxEnergy += a;
    }

    public void showEnergy(){
        MessageManager.getMessage(Result.success("Remained energy : " + energy + "(out of " + maxEnergy + ")."));
    }

    public void update(){
        if(energy == 0){
            App.getGame().dateAndTime.setMorning();
        }
        if(energyConsumedThisTurn == 50)
            App.getGame().goToNextPlayer();
    }

    public void updateEnergy(int energy){
        if(isLimited)
            this.energy = Math.min(this.energy + energy, maxEnergy);
        this.energy = Math.max(0, this.energy);
        if(energy < 0)
            energyConsumedThisTurn -= energy;
    }
}
