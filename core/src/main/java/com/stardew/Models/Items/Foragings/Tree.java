package com.stardew.Models.Items.Foragings;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Enums.ItemType;
import com.stardew.Enums.Season;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.GameMap;

import java.util.ArrayList;
import java.util.Arrays;

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
    private boolean isThundered = false;

    private Tree(ItemType itemType, Fruit fruit, ForagingSeed source, int[] growthStages, int remainingHarvestCycle, ArrayList<Season> seasons) {
        super(itemType);
        hitsRemainedToDestroy = 6;
        this.fruit = fruit;
        this.source = source;
        this.growthStages = growthStages;
        this.remainingHarvestCycle = remainingHarvestCycle;
        this.seasons = seasons;
    }

    public boolean isThundered() {
        return isThundered;
    }

    public void setThundered(boolean thundered) {
        isThundered = thundered;
    }

    public void setNotWateredDays(int notWateredDays) {
        this.notWateredDays = notWateredDays;
    }

    public int getNotWateredDays() {
        return notWateredDays;
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
        tree.startTimeOfGrowth = App.getGame().dateAndTime.getAllDaysPassed();
        return tree;
    }

    @Override
    public Sprite getSprite(){
        int season = App.getGame().dateAndTime.getSeason().ordinal();
        if(isThundered)
            sprite = new Sprite(GameAssetManager.getTreeTextures().get(itemType)[0]);
        else if(currentGrowthStage != 3)
            sprite = new Sprite(GameAssetManager.getTreeTextures().get(itemType)[currentGrowthStage+1]);
        else
            sprite = new Sprite(GameAssetManager.getTreeTextures().get(itemType)[currentGrowthStage+1],  96*season, 0,
                96, 160);
        sprite.setPosition(position.getX()* GameMap.getTilePrintSize(), position.getY()* GameMap.getTilePrintSize());
        sprite.setSize((float) (((double) GameMap.getTilePrintSize() /40)*0.6*sprite.getWidth()),
            (float) (((double) GameMap.getTilePrintSize() /40)*0.75*sprite.getHeight()));
        return sprite;
    }

    @Override
    public Tree makeEdible(double energy){
        return this;
    }

    @Override
    public String details() {
        StringBuilder string = new StringBuilder();
        for(int a : growthStages){
            string.append(a + " - ");
        }
        int totalHarvest = Arrays.stream(growthStages).sum();
        return "Name : " + itemType.name() + "\n" +
                "Source : " + source.getItemType().name() + "\n" +
                "Stages : " + string.substring(0, string.length()-2) + "\n" +
                "Total Harvest Time : " + totalHarvest + "\n" +
                fruit.details() + "\n" +
                "Season : " + seasons.toString();
    }

    public void regrow(){
        if(remainingHarvestCycle == 0)
            return;
        remainingHarvestCycle --;
        currentGrowthStage = 0;
        startTimeOfGrowth = App.getGame().dateAndTime.getAllDaysPassed();
        isReadyForHarvest = false;
        notWateredDays = 0;
    }

    @Override
    public void update(float delta){
        if(!App.getGame().dateAndTime.isADayPassed())
            return;
        if(currentGrowthStage >= growthStages.length) {
            isReadyForHarvest = true;
            return;
        }
        notWateredDays++;
        System.out.println(startTimeOfGrowth + " " + App.getGame().dateAndTime.getAllDaysPassed());
        if(App.getGame().dateAndTime.getAllDaysPassed() - startTimeOfGrowth >=(growthStages[currentGrowthStage])){
            startTimeOfGrowth = App.getGame().dateAndTime.getAllDaysPassed();
            currentGrowthStage ++;
        }
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
    }};


}
