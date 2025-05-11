package Models.Abilities;


public class Abilities {
    public final Cooking cooking = new Cooking();
    public final Fishing fishing = new Fishing();
    public final Mining mining = new Mining();
    public final NormalFarming normalFarming = new NormalFarming();
    public final Foraging foraging = new Foraging();
    public final GreenhouseFarming greenhouseFarming = new GreenhouseFarming();
    public final Crafting crafting = new Crafting();

    private final int[] abilities = new int[4];// 0 for mining 1 for fishing 2 for farming 3 for foraging

    public int getMiningLevel() {
        return Math.min((abilities[0]-50)/100, 0);
    }

    public void setMiningLevel(int miningLevel) {
        if(miningLevel == 0)
            abilities[0] +=10;
        else
            abilities[0] += miningLevel;
    }

    public int[] getAbilities() {
        return abilities;
    }

    public int getFishingLevel() {
        return Math.min((abilities[1]-50)/100, 0);
    }

    public void setFishingLevel(int fishingLevel) {
        if(fishingLevel == 0)
            abilities[1] +=5;
        else
            abilities[1] += fishingLevel;
    }

    public int getFarmingLevel() {
        return Math.min((abilities[2]-50)/100, 0);
    }

    public void setFarmingLevel(int farmingLevel) {
        if(farmingLevel == 0)
            abilities[2] +=5;
        else
            abilities[2] += farmingLevel;
    }

    public int getForagingLevel() {
        return Math.min((abilities[3]-50)/100, 0);
    }

    public void setForagingLevel(int foragingLevel) {
        if(foragingLevel == 0)
            abilities[3] +=10;
        else
            abilities[3] += foragingLevel;
    }
}
