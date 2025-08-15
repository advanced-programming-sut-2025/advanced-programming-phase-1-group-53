package com.stardew.Models.Abilities;


public class Abilities {
    public final Cooking cooking = new Cooking();
    public final Fishing fishing = new Fishing();
    public final NormalFarming normalFarming = new NormalFarming();
    public final GreenhouseFarming greenhouseFarming = new GreenhouseFarming();
    public final CraftingAndArtisan crafting = new CraftingAndArtisan();
    public final DairyFarming dairyFarming = new DairyFarming();
    public final Shopping shopping = new Shopping();

    private final int[] abilities = new int[4];// 0 for mining 1 for fishing 2 for farming 3 for foraging

    private int miningLevel = 0;
    private int fishingLevel = 0;
    private int farmingLevel = 0;
    private int foragingLevel = 0;

    public Abilities() {
        for (int i = 0; i < 4; i++) {
            abilities[i] = 0;
        }
    }

    public int getMiningLevel() {
        return abilities[0];
    }

    public void setMiningLevel(int miningLevel) {
        abilities[0] = miningLevel;
    }

    public int[] getAbilities() {
        return abilities;
    }

    public int getFishingLevel() {
        return abilities[1];
    }

    public void setFishingLevel(int fishingLevel) {
        abilities[1] = fishingLevel;
    }

    public int getFarmingLevel() {
        return abilities[2] ;
    }

    public void setFarmingLevel(int farmingLevel) {

        abilities[2] = farmingLevel;
    }

    public int getForagingLevel() {
        return Math.min((abilities[3] - 50) / 100, 0);
    }

    public void setForagingLevel(int foragingLevel) {
        if (foragingLevel == 0) {
            abilities[3] += 10;
            this.foragingLevel += 10;
        } else
            abilities[3] += foragingLevel;
        this.foragingLevel += foragingLevel;
    }

}
