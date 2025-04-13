package Enums;

import java.util.ArrayList;

public enum Tree {

    private ArrayList<Integer> stages;
    private Season season;
    private Product fruit;
    private int harvestTime;
    private int harvestCycle;
    private int price;
    private int energy;

    Tree(ArrayList<Integer> stages, Season season, Product fruit, int harvestTime, int harvestCycle, int price, int energy) {
        this.stages = stages;
        this.season = season;
        this.fruit = fruit;
        this.harvestTime = harvestTime;
        this.harvestCycle = harvestCycle;
        this.price = price;
        this.energy = energy;
    }
}
