package com.stardew.Models;

import com.stardew.Models.Game.App;

public class Energy {
    private int lastUpdateTime = 0;
    private boolean isLimited = true;
    private int maxEnergy = 200;
    private int energy = maxEnergy;
    private int energyConsumedThisTurn = 0;


    public void setUnlimitedEnergy(){
        energy = maxEnergy;
        isLimited = false;
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

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
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
