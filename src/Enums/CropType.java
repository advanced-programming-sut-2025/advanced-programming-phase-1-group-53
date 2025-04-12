package Enums;

import java.util.EnumSet;

public enum CropType {
    BLUE_JAZZ("Jazz Seeds", new int[]{1,2,2,2}, 7, 0, 50, true, 45, 20, EnumSet.of(Season.SPRING), false),
    CARROT("Carrot Seeds", new int[]{1,1,1}, 3, 0, 35, true, 75, 33, EnumSet.of(Season.SPRING), false),
    CAULIFLOWER("Cauliflower Seeds", new int[]{1,2,4,4,1}, 12, 0, 175, true, 75, 33, EnumSet.of(Season.SPRING), true),
    COFFEE_BEAN("Coffee Bean", new int[]{1,2,2,3,2}, 10, 0, 15, false, 0, 0, EnumSet.of(Season.SPRING, Season.SUMMER), false),
    GARLIC("Garlic Seeds", new int[]{1,1,1,1}, 4, 0, 60, true, 20, 9, EnumSet.of(Season.SPRING), false),
    GREEN_BEAN("Bean Starter", new int[]{1,1,1,3,4}, 10, 3, 40, true, 25, 11, EnumSet.of(Season.SPRING), false),
    KALE("Kale Seeds", new int[]{1,2,2,1}, 6, 0, 110, true, 50, 22, EnumSet.of(Season.SPRING), false),
    PARSNIP("Parsnip Seeds", new int[]{1,1,1,1}, 4, 0, 35, true, 25, 11, EnumSet.of(Season.SPRING), false),
    POTATO("Potato Seeds", new int[]{1,1,1,2,1}, 6, 0, 80, true, 25, 11, EnumSet.of(Season.SPRING), false),
    RHUBARB("Rhubarb Seeds", new int[]{2,2,2,3,4}, 13, 0, 220, false, 0, 0, EnumSet.of(Season.SPRING), false),
    STRAWBERRY("Strawberry Seeds", new int[]{1,1,2,2,2}, 8, 4, 120, true, 50, 22, EnumSet.of(Season.SPRING), false),
    TULIP("Tulip Bulb", new int[]{1,1,2,2}, 6, 0, 30, true, 45, 20, EnumSet.of(Season.SPRING), false),
    UNMILLED_RICE("Rice Shoot", new int[]{1,2,2,3}, 8, 0, 30, true, 3, 1, EnumSet.of(Season.SPRING), false),
    BLUEBERRY("Blueberry Seeds", new int[]{1,3,3,4,2}, 13, 4, 50, true, 25, 11, EnumSet.of(Season.SUMMER), false),
    CORN("Corn Seeds", new int[]{2,3,3,3,3}, 14, 4, 50, true, 25, 11, EnumSet.of(Season.SUMMER, Season.FALL), false),
    HOPS("Hops Starter", new int[]{1,1,2,3,4}, 11, 1, 25, true, 45, 20, EnumSet.of(Season.SUMMER), false),
    HOT_PEPPER("Pepper Seeds", new int[]{1,1,1,1,1}, 5, 3, 40, true, 13, 5, EnumSet.of(Season.SUMMER), false),
    MELON("Melon Seeds", new int[]{1,2,3,3,3}, 12, 0, 250, true, 113, 50, EnumSet.of(Season.SUMMER), true),
    POPPY("Poppy Seeds", new int[]{1,2,2,2}, 7, 0, 140, true, 45, 20, EnumSet.of(Season.SUMMER), false),
    RADISH("Radish Seeds", new int[]{2,1,2,1}, 6, 0, 90, true, 45, 20, EnumSet.of(Season.SUMMER), false),
    RED_CABBAGE("Red Cabbage Seeds", new int[]{2,1,2,2,2}, 9, 0, 260, true, 75, 33, EnumSet.of(Season.SUMMER), false),
    STARFRUIT("Starfruit Seeds", new int[]{2,3,2,3,3}, 13, 0, 750, true, 125, 56, EnumSet.of(Season.SUMMER), false),
    SUMMER_SPANGLE("Spangle Seeds", new int[]{1,2,3,1}, 8, 0, 90, true, 45, 20, EnumSet.of(Season.SUMMER), false),
    SUMMER_SQUASH("Summer Squash Seeds", new int[]{1,1,1,2,1}, 6, 3, 45, true, 63, 28, EnumSet.of(Season.SUMMER), false),
    SUNFLOWER("Sunflower Seeds", new int[]{1,2,3,2}, 8, 0, 80, true, 45, 20, EnumSet.of(Season.SUMMER, Season.FALL), false),
    TOMATO("Tomato Seeds", new int[]{2,2,2,2,3}, 11, 4, 60, true, 20, 9, EnumSet.of(Season.SUMMER), false),
    WHEAT("Wheat Seeds", new int[]{1,1,1,1}, 4, 0, 25, false, 0, 0, EnumSet.of(Season.SUMMER, Season.FALL), false),
    AMARANTH("Amaranth Seeds", new int[]{1,2,2,2}, 7, 0, 150, true, 50, 22, EnumSet.of(Season.FALL), false),
    ARTICHOKE("Artichoke Seeds", new int[]{2,2,1,2,1}, 8, 0, 160, true, 30, 13, EnumSet.of(Season.FALL), false),
    BEET("Beet Seeds", new int[]{1,1,2,2}, 6, 0, 100, true, 30, 13, EnumSet.of(Season.FALL), false),
    BOK_CHOY("Bok Choy Seeds", new int[]{1,1,1,1}, 4, 0, 80, true, 25, 11, EnumSet.of(Season.FALL), false),
    BROCCOLI("Broccoli Seeds", new int[]{2,2,2,2}, 8, 4, 70, true, 63, 28, EnumSet.of(Season.FALL), false),
    CRANBERRIES("Cranberry Seeds", new int[]{1,2,1,1,2}, 7, 5, 75, true, 38, 17, EnumSet.of(Season.FALL), false),
    EGGPLANT("Eggplant Seeds", new int[]{1,1,1,1}, 5, 5, 60, true, 20, 9, EnumSet.of(Season.FALL), false),
    FAIRY_ROSE("Fairy Seeds", new int[]{1,4,4,3}, 12, 0, 290, true, 45, 20, EnumSet.of(Season.FALL), false),
    GRAPE("Grape Starter", new int[]{1,1,2,3,3}, 10, 3, 80, true, 38, 17, EnumSet.of(Season.FALL), false),
    PUMPKIN("Pumpkin Seeds", new int[]{1,2,3,4,3}, 13, 0, 320, false, 0, 0, EnumSet.of(Season.FALL), true),
    YAM("Yam Seeds", new int[]{1,3,3,3}, 10, 0, 160, true, 45, 20, EnumSet.of(Season.FALL), false),
    SWEET_GEM_BERRY("Rare Seed", new int[]{2,4,6,6,6}, 24, 0, 3000, false, 0, 0, EnumSet.of(Season.FALL), false),
    POWDERMELON("Powdermelon Seeds", new int[]{1,2,1,2,1}, 7, 0, 60, true, 63, 28, EnumSet.of(Season.WINTER), true),
    ANCIENT_FRUIT("Ancient Seeds", new int[]{2,7,7,7,5}, 28, 7, 550, false, 0, 0, EnumSet.of(Season.SPRING, Season.SUMMER, Season.FALL), false);

    public final String seedName;
    public final int[] growthStages;
    public final int totalGrowthDays;
    public final int regrowDays;
    public final int baseSeedPrice;
    public final boolean isEdible;
    public final int baseSellPrice;
    public final int energyRestored;
    public final EnumSet<Season> seasons;
    public final boolean isGiantCrop;

    CropType(String seedName, int[] growthStages, int totalGrowthDays, int regrowDays, int baseSeedPrice,
         boolean isEdible, int baseSellPrice, int energyRestored,
         EnumSet<Season> seasons, boolean isGiantCrop) {
        this.seedName = seedName;
        this.growthStages = growthStages;
        this.totalGrowthDays = totalGrowthDays;
        this.regrowDays = regrowDays;
        this.baseSeedPrice = baseSeedPrice;
        this.isEdible = isEdible;
        this.baseSellPrice = baseSellPrice;
        this.energyRestored = energyRestored;
        this.seasons = seasons;
        this.isGiantCrop = isGiantCrop;
    }
}
