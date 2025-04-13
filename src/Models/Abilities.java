package Models;

public class Abilities {
    private int farmingXP = 0;
    private int fishingXP = 0;
    private int hikingXP = 0;
    private int miningXP = 0;


    // TODO درست کردن توابع برای تغییر ایکس پی به صورت مقداری
    public int getFarmingXP() {
        return farmingXP;
    }

    public void setFarmingXP(int farmingXP) {
        this.farmingXP = farmingXP;
    }

    public int getMiningXP() {
        return miningXP;
    }

    public void setMiningXP(int miningXP) {
        this.miningXP = miningXP;
    }

    public int getHikingXP() {
        return hikingXP;
    }

    public void setHikingXP(int hikingXP) {
        this.hikingXP = hikingXP;
    }

    public int getFishingXP() {
        return fishingXP;
    }

    public void setFishingXP(int fishingXP) {
        this.fishingXP = fishingXP;
    }

    public int findFarmingLevel() {
        return (this.farmingXP - 50) / 100;
    }

    public int findFishingLevel() {
        return (this.fishingXP - 50) / 100;
    }

    public int findHikingLevel() {
        return (this.hikingXP - 50) / 100;
    }

    public int findMiningLevel() {
        return (this.miningXP - 50) / 100;
    }

    public void fishing() {}

    public void showCraftingRecipe() {}

    public void crafting() {}

    public void placeItem() {}

    public void learnCraftingRecipe() {}

    public void showCookingRecipe() {}

    public void learnCookingRecipe() {}

    public void cooking() {}

    public void eating() {}

    public void useMachine() {}
}
