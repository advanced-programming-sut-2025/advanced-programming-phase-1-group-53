package Models;

public class Energy {
    private int lastUpdateTime = 0;
    private int energy = 200;
    private boolean isLimited = true;
    private int maxEnergy = 200;

    public void setUnlimitedEnergy(){
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

    public void updateEnergy(){
        if(isLimited){
        }
    }

    public void updateMaxEnergy(int a){
        maxEnergy += a;
    }

    public void updateEnergy(int energy){
        if(isLimited)
            this.energy -= energy;
        this.energy = Math.min(0, this.energy);
    }
}
