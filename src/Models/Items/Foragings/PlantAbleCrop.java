package Models.Items.Foragings;


import Enums.ItemType;
import Enums.Season;
import Models.Game.App;
import Models.Items.CraftAbleAndArtisan.CraftAble;
import Models.Items.Item;

import java.util.ArrayList;

public class PlantAbleCrop extends Plant {
    private final ForagingSeed sourceSeed;
    private int remainingGrowthTimes;
    private final int[] growthStages;
    private int currentGrowthStage = 0;
    private long startTimeOfCurrentGrowth;
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

    public double getEnergy() {
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
        plantAbleCrop.startTimeOfCurrentGrowth = App.getGame().dateAndTime.getTime();
        return plantAbleCrop;
    }

    @Override
    public PlantAbleCrop makeEdible(double energy){
        setEdible();
        this.energy = energy;
        return this;
    }

    @Override
    public PlantAbleCrop makeSellPrice(double price) {
        baseSellPrice = price;
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
        if(App.getGame().dateAndTime.isADayPassed() && !hasDeluxe)
            notWateredDays ++;
        int a = hasSpeed ? 0 : 1;
        if(currentGrowthStage == growthStages.length- a)
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
            ForagingSeed.JazzSeeds, 1, new int[]{2,2,2,2}, Plant.spring, false).makeEdible(45).makeSellPrice(50);

    public static final PlantAbleCrop Carrot = new PlantAbleCrop(ItemType.Carrot,
            ForagingSeed.CarrotSeeds, 1, new int[]{1,1,1}, Plant.spring, false).makeEdible(75).makeSellPrice(35);

    public static final PlantAbleCrop Cauliflower = new PlantAbleCrop(ItemType.CauliFlower,
            ForagingSeed.CauliflowerSeeds, 1, new int[]{2,4,4,1}, Plant.spring, true).makeEdible(75).makeSellPrice(175);

    public static final PlantAbleCrop CoffeeBean = new PlantAbleCrop(ItemType.CoffeeBean,
            ForagingSeed.CoffeeBean, 2, new int[]{2,2,3,2}, Plant.springSummer, false).makeSellPrice(15);

    public static final PlantAbleCrop Garlic = new PlantAbleCrop(ItemType.Garlic,
            ForagingSeed.GarlicSeeds, 1, new int[]{1,1,1,1}, Plant.spring, false).makeEdible(20).makeSellPrice(60);

    public static final PlantAbleCrop GreenBean = new PlantAbleCrop(ItemType.GreenBean,
            ForagingSeed.BeanStarter, 3, new int[]{1,1,1,3,4}, Plant.spring, false).makeEdible(25).makeSellPrice(40);

    public static final PlantAbleCrop Kale = new PlantAbleCrop(ItemType.Kale,
            ForagingSeed.KaleSeeds, 1, new int[]{2,2,1}, Plant.spring, false).makeEdible(50).makeEdible(110);

    public static final PlantAbleCrop Parsnip = new PlantAbleCrop(ItemType.Parsnip,
            ForagingSeed.ParsnipSeeds, 1, new int[]{1,1,1,1}, Plant.spring, false).makeEdible(25).makeSellPrice(35);

    public static final PlantAbleCrop Potato = new PlantAbleCrop(ItemType.Potato,
            ForagingSeed.PotatoSeeds, 1, new int[]{1,1,2,1}, Plant.spring, false).makeEdible(25).makeSellPrice(80);

    public static final PlantAbleCrop Rhubarb = new PlantAbleCrop(ItemType.Rhubarb,
            ForagingSeed.RhubarbSeeds, 1, new int[]{2,2,2,3,4}, Plant.spring, false).makeSellPrice(220);

    public static final PlantAbleCrop Strawberry = new PlantAbleCrop(ItemType.Strawberry,
            ForagingSeed.StrawberrySeeds, 4, new int[]{1,1,2,2,2}, Plant.spring, false).makeEdible(50).makeEdible(120);
    public static final PlantAbleCrop Tulip = new PlantAbleCrop(ItemType.Tulip,
            ForagingSeed.TulipBulb, 1, new int[]{1,1,2,2}, Plant.spring, false).makeEdible(45).makeSellPrice(30);

    public static final PlantAbleCrop Rice = new PlantAbleCrop(ItemType.Rice,
            ForagingSeed.RiceShoot, 1, new int[]{2,2,3}, Plant.spring, false).makeEdible(3).makeSellPrice(30);

    public static final PlantAbleCrop Blueberry = new PlantAbleCrop(ItemType.Blueberry,
            ForagingSeed.BlueberrySeeds, 4, new int[]{3,3,4,2}, Plant.summer, false).makeEdible(25).makeEdible(50);

    public static final PlantAbleCrop Corn = new PlantAbleCrop(ItemType.Corn,
            ForagingSeed.CornSeeds, 4, new int[]{3,3,3,3}, Plant.summerFall, false).makeEdible(25).makeSellPrice(50);

    public static final PlantAbleCrop Hops = new PlantAbleCrop(ItemType.Hops,
            ForagingSeed.HopsStarter, 1, new int[]{1,2,3,4}, Plant.summer, false).makeEdible(45).makeSellPrice(25);

    public static final PlantAbleCrop HotPepper = new PlantAbleCrop(ItemType.HotPepper,
            ForagingSeed.PepperSeeds, 3, new int[]{1,1,1,1,1}, Plant.summer, false).makeEdible(13).makeSellPrice(40);

    public static final PlantAbleCrop Melon = new PlantAbleCrop(ItemType.Melon,
            ForagingSeed.MelonSeeds, 1, new int[]{2,3,3,3}, Plant.summer, true).makeEdible(113).makeSellPrice(250);

    public static final PlantAbleCrop Poppy = new PlantAbleCrop(ItemType.Poppy,
            ForagingSeed.PoppySeeds, 1, new int[]{2,2,2}, Plant.summer, false).makeEdible(45).makeSellPrice(140);

    public static final PlantAbleCrop Radish = new PlantAbleCrop(ItemType.Radish,
            ForagingSeed.RadishSeeds, 1, new int[]{1,2,1}, Plant.summer, false).makeEdible(45).makeSellPrice(90);

    public static final PlantAbleCrop RedCabbage = new PlantAbleCrop(ItemType.RedCabbage,
            ForagingSeed.RedCabbageSeeds, 1, new int[]{1,2,2,2}, Plant.summer, false).makeEdible(75).makeSellPrice(260);

    public static final PlantAbleCrop Starfruit = new PlantAbleCrop(ItemType.StarFruit,
            ForagingSeed.StarfruitSeeds, 1, new int[]{3,2,3,3}, Plant.summer, false).makeEdible(125).makeSellPrice(750);

    public static final PlantAbleCrop SummerSpangle = new PlantAbleCrop(ItemType.SummerSpangle,
            ForagingSeed.SpangleSeeds, 1, new int[]{2,3,1}, Plant.summer, false).makeEdible(45).makeSellPrice(90);

    public static final PlantAbleCrop SummerSquash = new PlantAbleCrop(ItemType.SummerSquash,
            ForagingSeed.SummerSquashSeeds, 3, new int[]{1,1,2,1}, Plant.summer, false).makeEdible(63).makeSellPrice(45);
    public static final PlantAbleCrop Sunflower = new PlantAbleCrop(ItemType.Sunflower,
            ForagingSeed.SunflowerSeeds, 1, new int[]{2,3,2}, Plant.summerFall, false).makeEdible(45).makeSellPrice(80);

    public static final PlantAbleCrop Tomato = new PlantAbleCrop(ItemType.Tomato,
            ForagingSeed.TomatoSeeds, 4, new int[]{2,2,2,2,3}, Plant.summer, false).makeEdible(20).makeSellPrice(60);

    public static final PlantAbleCrop Wheat = new PlantAbleCrop(ItemType.Wheat,
            ForagingSeed.WheatSeeds, 1, new int[]{1,1,1,1}, Plant.summerFall, false).makeSellPrice(25);

    public static final PlantAbleCrop Amaranth = new PlantAbleCrop(ItemType.Amaranth,
            ForagingSeed.AmaranthSeeds, 1, new int[]{2,2,2}, Plant.fall, false).makeEdible(50).makeSellPrice(150);

    public static final PlantAbleCrop Artichoke = new PlantAbleCrop(ItemType.Artichoke,
            ForagingSeed.ArtichokeSeeds, 1, new int[]{2,1,2,1}, Plant.fall, false).makeEdible(30).makeSellPrice(160);

    public static final PlantAbleCrop Beet = new PlantAbleCrop(ItemType.Beet,
            ForagingSeed.BeetSeeds, 1, new int[]{1,2,2}, Plant.fall, false).makeEdible(30).makeSellPrice(100);

    public static final PlantAbleCrop BokChoy = new PlantAbleCrop(ItemType.BokChoy,
            ForagingSeed.BokChoySeeds, 1, new int[]{1,1,1,1}, Plant.fall, false).makeEdible(25).makeSellPrice(80);

    public static final PlantAbleCrop Broccoli = new PlantAbleCrop(ItemType.Broccoli,
            ForagingSeed.BroccoliSeeds, 4, new int[]{2,2,2,2}, Plant.fall, false).makeEdible(63).makeSellPrice(70);

    public static final PlantAbleCrop Cranberry = new PlantAbleCrop(ItemType.Cranberry,
            ForagingSeed.CranberrySeeds, 5, new int[]{2,1,1,2}, Plant.fall, false).makeEdible(38).makeSellPrice(75);

    public static final PlantAbleCrop Eggplant = new PlantAbleCrop(ItemType.Eggplant,
            ForagingSeed.EggplantSeeds, 5, new int[]{1,1,1,1}, Plant.fall, false).makeEdible(20).makeSellPrice(60);

    public static final PlantAbleCrop FairyRose = new PlantAbleCrop(ItemType.FairyRose,
            ForagingSeed.FairySeeds, 1, new int[]{4,4,3}, Plant.fall, false).makeEdible(45).makeSellPrice(290);

    public static final PlantAbleCrop Grape = new PlantAbleCrop(ItemType.Grape,
            ForagingSeed.GrapeStarter, 3, new int[]{1,2,3,3}, Plant.fall, false).makeEdible(38).makeSellPrice(80);

    public static final PlantAbleCrop Pumpkin = new PlantAbleCrop(ItemType.Pumpkin,
            ForagingSeed.PumpkinSeeds, 1, new int[]{2,3,4,3}, Plant.fall, true).makeSellPrice(320);

    public static final PlantAbleCrop Yam = new PlantAbleCrop(ItemType.Yam,
            ForagingSeed.YamSeeds, 1, new int[]{3,3,3}, Plant.fall, false).makeEdible(45).makeSellPrice(160);

    public static final PlantAbleCrop SweetGemBerry = new PlantAbleCrop(ItemType.SweetGemBerry,
            ForagingSeed.RareSeed, 1, new int[]{4,6,6,6}, Plant.fall, false).makeSellPrice(3000);

    public static final PlantAbleCrop PowderMelon = new PlantAbleCrop(ItemType.PowderMelon,
            ForagingSeed.PowdermelonSeeds, 1, new int[]{2,1,2,1}, Plant.winter, true).makeEdible(63).makeSellPrice(60);
    public static final PlantAbleCrop AncientFruit = new PlantAbleCrop(ItemType.AncientFruit,
            ForagingSeed.AncientSeeds, 7, new int[]{7,7,7,5}, Plant.springSummerFall, false).makeSellPrice(550);


    public static final ArrayList<PlantAbleCrop> Vegetables = new ArrayList<>(){{
        add(Pumpkin);
        add(Eggplant);
        add(Broccoli);
        add(Tomato);
        add(RedCabbage);
        add(Corn);
        add(HotPepper);
        add(Potato);
        add(Garlic);
        add(Carrot);
    }};

    public static final ArrayList<PlantAbleCrop> allPlantAbleCrops = new ArrayList<>(){{
        add(BlueJazz);
        add(Carrot);
        add(Cauliflower);
        add(CoffeeBean);
        add(Garlic);
        add(GreenBean);
        add(Kale);
        add(Parsnip);
        add(Potato);
        add(Rhubarb);
        add(Strawberry);
        add(Tulip);
        add(Rice);
        add(Blueberry);
        add(Corn);
        add(Hops);
        add(HotPepper);
        add(Melon);
        add(Poppy);
        add(Radish);
        add(RedCabbage);
        add(Starfruit);
        add(SummerSpangle);
        add(SummerSquash);
        add(Sunflower);
        add(Tomato);
        add(Wheat);
        add(Amaranth);
        add(Artichoke);
        add(Beet);
        add(BokChoy);
        add(Broccoli);
        add(Cranberry);
        add(Eggplant);
        add(FairyRose);
        add(Grape);
        add(Pumpkin);
        add(Yam);
        add(SweetGemBerry);
        add(PowderMelon);
        add(AncientFruit);
    }};
}
