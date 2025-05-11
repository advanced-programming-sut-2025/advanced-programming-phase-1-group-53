package Models.Items.Foragings;


import Enums.ItemType;
import Enums.Season;
import Models.Game.App;

import java.util.ArrayList;

public class PlantAbleCrop extends Plant {
    private final ForagingSeed sourceSeed;
    private int remainingGrowthTimes;
    private final int[] growthStages;
    private int currentGrowthStage = 0;
    private long startTimeOfCurrentGrowth;
    private int energy;
    private final ArrayList<Season> seasons;
    private final boolean canBecomeGiant;
    private boolean isReadyForHarvest = false;
    private int notWateredDays = 0;

    private PlantAbleCrop(ItemType itemType, ForagingSeed sourceSeed, int remainingGrowthTimes, int[] growthStages, ArrayList<Season> seasons, boolean canBecomeGiant) {
        super(itemType);
        this.sourceSeed = sourceSeed;
        this.remainingGrowthTimes = remainingGrowthTimes;
        this.growthStages = growthStages;
        this.seasons = seasons;
        this.canBecomeGiant = canBecomeGiant;
        startTimeOfCurrentGrowth = App.getGame().dateAndTime.getTime();
    }

    public ForagingSeed getSourceSeed() {
        return sourceSeed;
    }

    public int getRemainingGrowthTimes() {
        return remainingGrowthTimes;
    }

    public int[] getGrowthStages() {
        return growthStages;
    }

    public int getCurrentGrowthStage() {
        return currentGrowthStage;
    }

    public int getNotWateredDays() {
        return notWateredDays;
    }

    public void setNotWateredDays(int notWateredDays) {
        this.notWateredDays = notWateredDays;
    }

    public void setReadyForHarvest(boolean readyForHarvest) {
        isReadyForHarvest = readyForHarvest;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setStartTimeOfCurrentGrowth(long startTimeOfCurrentGrowth) {
        this.startTimeOfCurrentGrowth = startTimeOfCurrentGrowth;
    }

    public void setCurrentGrowthStage(int currentGrowthStage) {
        this.currentGrowthStage = currentGrowthStage;
    }

    public void setRemainingGrowthTimes(int remainingGrowthTimes) {
        this.remainingGrowthTimes = remainingGrowthTimes;
    }

    public long getStartTimeOfCurrentGrowth() {
        return startTimeOfCurrentGrowth;
    }

    public int getEnergy() {
        return energy;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public boolean isCanBecomeGiant() {
        return canBecomeGiant;
    }

    public boolean isReadyForHarvest() {
        return isReadyForHarvest;
    }

    @Override
    public PlantAbleCrop clone(){
        PlantAbleCrop plantAbleCrop = new PlantAbleCrop(this.getItemType(), this.sourceSeed, this.remainingGrowthTimes, this.growthStages, this.seasons, this.canBecomeGiant);
        if(isEdible())
            plantAbleCrop.makeEdible(this.energy);
        return plantAbleCrop;
    }

    @Override
    PlantAbleCrop makeEdible(int energy){
        setEdible();
        this.energy = energy;
        return this;
    }
    public void update(){
        //TODO: currentSeasonMechanism
        if (currentGrowthStage != growthStages.length) {
            if (App.getGame().dateAndTime.getTime() - startTimeOfCurrentGrowth >= growthStages[currentGrowthStage]) {
                currentGrowthStage++;
                startTimeOfCurrentGrowth = App.getGame().dateAndTime.getTime();
            }
        }
        else if(App.getGame().dateAndTime.getTime() - startTimeOfCurrentGrowth >= 24){
            currentGrowthStage = 0;
            startTimeOfCurrentGrowth = App.getGame().dateAndTime.getTime();
            remainingGrowthTimes--;
        }
        if(App.getGame().dateAndTime.isADayPassed())
            notWateredDays ++;
        if(currentGrowthStage == growthStages.length)
            isReadyForHarvest = true;
    }

    public boolean isHarvestAble(){
        if(currentGrowthStage == growthStages.length){
            currentGrowthStage = 0;
            remainingGrowthTimes --;
            startTimeOfCurrentGrowth = App.getGame().dateAndTime.getTime();
            return true;
        }
        return false;
    }

    public static final PlantAbleCrop BlueJazz = new PlantAbleCrop(ItemType.BlueJazz,
            ForagingSeed.JazzSeeds, 1, new int[]{1,2,2,2},  Plant.spring, false).makeEdible(45);

    public static final PlantAbleCrop Carrot = new PlantAbleCrop(ItemType.Carrot,
            ForagingSeed.CarrotSeeds, 1, new int[]{1,1,1},  Plant.spring, false).makeEdible(75);

    public static final PlantAbleCrop Cauliflower = new PlantAbleCrop(ItemType.CauliFlower,
            ForagingSeed.CauliflowerSeeds, 1, new int[]{1,2,4,4,1},  Plant.spring, true).makeEdible(75);

    public static final PlantAbleCrop CoffeeBean = new PlantAbleCrop(ItemType.CoffeeBean,
            ForagingSeed.CoffeeBean, 3, new int[]{1,2,2,3,2}, Plant.springSummer, false);

    public static final PlantAbleCrop GreenBean = new PlantAbleCrop(ItemType.GreenBean,
            ForagingSeed.BeanStarter, 4, new int[]{1,1,1,3,4},  Plant.spring, false).makeEdible(25);

    public static final PlantAbleCrop Melon = new PlantAbleCrop(ItemType.Melon,
            ForagingSeed.MelonSeeds, 1, new int[]{1,2,3,3,3},  Plant.summer, true).makeEdible(113);

    public static final PlantAbleCrop Tomato = new PlantAbleCrop(ItemType.Tomato,
            ForagingSeed.TomatoSeeds, 5, new int[]{2,2,2,2,3},  Plant.summer, false).makeEdible(20);

    public static final PlantAbleCrop Corn = new PlantAbleCrop(ItemType.Corn,
            ForagingSeed.CornSeeds, 5, new int[]{2,3,3,3,3},  Plant.summerFall, false).makeEdible(25);

    public static final PlantAbleCrop Pumpkin = new PlantAbleCrop(ItemType.Pumpkin,
            ForagingSeed.PumpkinSeeds, 1, new int[]{1,2,3,4,3}, Plant.fall, true);

    public static final PlantAbleCrop AncientFruit = new PlantAbleCrop(ItemType.AncientFruit,
            ForagingSeed.AncientSeeds, 8, new int[]{2,7,7,7,5},  Plant.springSummerFall, false);

    public static final PlantAbleCrop Garlic = new PlantAbleCrop(ItemType.Garlic,
            ForagingSeed.GarlicSeeds, 1, new int[]{1,1,1,1},  Plant.spring, false).makeEdible(20);
    public static final PlantAbleCrop Wheat = new PlantAbleCrop();
    //TODO: add more crops
}
