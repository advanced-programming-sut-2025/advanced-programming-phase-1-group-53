package Enums;

public enum PlantsAndMinerals {

    private Season season;
    private int price;
    private int energy;

    PlantsAndMinerals(Season season, int price, int energy) {
        this.season = season;
        this.price = price;
        this.energy = energy;
    }
}
