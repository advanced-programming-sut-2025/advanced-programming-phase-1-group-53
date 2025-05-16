package Models.Items.Foragings;

import Enums.ItemType;
import Enums.Season;
import Models.Game.App;
import Models.Items.Food;

import java.util.ArrayList;

public class Tree  extends Plant{
    private final ForagingSeed source;
    private final int[] growthStages;
    private final Fruit fruit;
    private int remainingHarvestCycle;
    private final ArrayList<Season> seasons;
    private int currentGrowthStage = 0;
    private long startTimeOfGrowth;
    private boolean isReadyForHarvest = false;
    private int notWateredDays =0;

    private Tree(ItemType itemType, Fruit fruit, ForagingSeed source, int[] growthStages, int remainingHarvestCycle, ArrayList<Season> seasons) {
        super(itemType);
        this.fruit = fruit;
        this.source = source;
        this.growthStages = growthStages;
        this.remainingHarvestCycle = remainingHarvestCycle;
        this.seasons = seasons;
    }

    public void setRemainingHarvestCycle(int remainingHarvestCycle) {
        this.remainingHarvestCycle = remainingHarvestCycle;
    }

    public void setCurrentGrowthStage(int currentGrowthStage) {
        this.currentGrowthStage = currentGrowthStage;
    }

    public void setStartTimeOfGrowth(long startTimeOfGrowth) {
        this.startTimeOfGrowth = startTimeOfGrowth;
    }

    public void setReadyForHarvest(boolean readyForHarvest) {
        isReadyForHarvest = readyForHarvest;
    }

    public int getNotWateredDays() {
        return notWateredDays;
    }

    public void setNotWateredDays(int notWateredDays) {
        this.notWateredDays = notWateredDays;
    }

    public ForagingSeed getSource() {
        return source;
    }

    public int[] getGrowthStages() {
        return growthStages;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public int getRemainingHarvestCycle() {
        return remainingHarvestCycle;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public int getCurrentGrowthStage() {
        return currentGrowthStage;
    }

    public long getStartTimeOfGrowth() {
        return startTimeOfGrowth;
    }

    public boolean isReadyForHarvest() {
        return isReadyForHarvest;
    }

    @Override
    public Tree clone(){
        Tree tree = new Tree(getItemType(), this.fruit, this.source, this.growthStages, this.remainingHarvestCycle, this.seasons);
        tree.startTimeOfGrowth = App.getGame().dateAndTime.getTime();
        return tree;
    }

    @Override
    public Tree makeEdible(double energy){
        return this;
    }

    public void update(){
        if(currentGrowthStage != growthStages.length) {
            if (App.getGame().dateAndTime.getTime() - startTimeOfGrowth > growthStages[currentGrowthStage]) {
                currentGrowthStage++;
                if (currentGrowthStage == growthStages.length) {
                    currentGrowthStage = 0;
                    remainingHarvestCycle--;
                }
                startTimeOfGrowth = App.getGame().dateAndTime.getTime();
            }
        }
        if(App.getGame().dateAndTime.isADayPassed() && !hasDeluxe)
            notWateredDays ++;
        int a = hasSpeed? 0 : 1;
        if(currentGrowthStage == growthStages.length-a)
            isReadyForHarvest = true;
    }


    public static final Tree ApricotTree = new Tree(ItemType.ApricotTree,  Fruit.Apricot,
            ForagingSeed.ApricotSapling, new int[]{7,7,7,7}, 1, Plant.spring);
    public static final Tree CherryTree = new Tree(ItemType.CherryTree,  Fruit.Cherry,
            ForagingSeed.CherrySapling, new int[]{7,7,7,7}, 1, Plant.spring);
    public static final Tree BananaTree = new Tree(ItemType.BananaTree,  Fruit.Banana,
            ForagingSeed.BananaSapling, new int[]{7,7,7,7}, 1, Plant.summer);
    public static final Tree MangoTree = new Tree(ItemType.MangoTree,  Fruit.Mango,
            ForagingSeed.MangoSapling, new int[]{7,7,7,7}, 1, Plant.summer);
    public static final Tree OrangeTree = new Tree(ItemType.OrangeTree,  Fruit.Orange,
            ForagingSeed.OrangeSapling, new int[]{7,7,7,7}, 1, Plant.summer);
    public static final Tree PeachTree = new Tree(ItemType.PeachTree,  Fruit.Peach,
            ForagingSeed.PeachSapling, new int[]{7,7,7,7}, 1, Plant.summer);
    public static final Tree AppleTree = new Tree(ItemType.AppleTree,  Fruit.Apple,
            ForagingSeed.AppleSapling, new int[]{7,7,7,7}, 1, Plant.fall);
    public static final Tree PomegranateTree = new Tree(ItemType.PomegranateTree, Fruit.Pomegranate,
            ForagingSeed.PomegranateSapling, new int[]{7,7,7,7}, 1, Plant.fall);
    public static final Tree OakTree = new Tree(ItemType.OakTree,  Fruit.OakResin,
            ForagingSeed.Acorns, new int[]{7,7,7,7}, 1, Plant.specialSeasons);
    public static final Tree MapleTree = new Tree(ItemType.MapleTree,  Fruit.MapleSyrup,
            ForagingSeed.MapleSeed, new int[]{7,7,7,7}, 1, Plant.specialSeasons);
    public static final Tree PineTree = new Tree(ItemType.PineTree,  Fruit.PineTar,
            ForagingSeed.PineCone, new int[]{7,7,7,7}, 1, Plant.specialSeasons);
    public static final Tree MahoganyTree = new Tree(ItemType.MahoganyTree,  Fruit.Sap,
            ForagingSeed.MahoganySeed, new int[]{7,7,7,7}, 1, Plant.specialSeasons);
    public static final Tree MushroomTree = new Tree(ItemType.MushroomTree,  Fruit.CommonMushroom,
            ForagingSeed.MushroomTreeSeed, new int[]{7,7,7,7}, 1, Plant.specialSeasons);
    public static final Tree MysticTree = new Tree(ItemType.MysticTree,  Fruit.MysticSyrup,
            ForagingSeed.MysticTreeSeed, new int[]{7,7,7,7}, 1, Plant.specialSeasons);

    public static final ArrayList<Tree> allTrees = new ArrayList<>(){{
        add(MysticTree);
        add(ApricotTree);
        add(CherryTree);
        add(BananaTree);
        add(MangoTree);
        add(OrangeTree);
        add(PeachTree);
        add(AppleTree);
        add(PomegranateTree);
        add(OakTree);
        add(MapleTree);
        add(MahoganyTree);
        add(MushroomTree);
        add(PineTree);
    }};
}
