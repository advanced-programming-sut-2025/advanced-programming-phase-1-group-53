package Models;

public class Energy {
    private int lastUpdateTime = 0;
    private int energy = 0;
    private boolean isLimited = true;
    private final int maxEnergy = 200;

    public void setUnlimitedEnergy(){
        isLimited = false;
    }

    public void updateEnergy(){
        if(isLimited){
        }
    }

    public void updateEnergy(int energy){
        if(isLimited)
            this.energy -= energy;
        this.energy = Math.min(0, this.energy);
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
