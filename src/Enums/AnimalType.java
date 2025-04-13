package Enums;

public enum AnimalType {
    Hen(95, 50, "BigHenEgg", "HenEgg", 800, 4, null),
    Duck(250, 95, "DuckFeather", "DuckEgg", 1200, 8, null),
    Rabbit(565, 340, "RabbitLeg", "Wool", 8000, 12, null),
    Dino(0, 350, null, "DinoEgg", 14000,8, null),
    Cow(190, 125,"MassiveCowMilk", "CowMilk", 1500, 4, null),
    Goat(345, 225, "MassiveGoatMilk", "Milk", 4000, 8, null),
    Sheep(0, 340, null, "Wool", 8000, 12, null),
    Pig(0, 625, null, "Truffle", 16000, 12, Season.WINTER);
    private final int priceOfPurch;
    private final String firstProd;
    private final String secondProd;
    private final int firstProdPrice;
    private final int secondProdPrice;
    private final int minimumCapacityNeeded;
    private final Season productionException;

    AnimalType(int secondProdPrice, int firstProdPrice, String secondProd, String firstProd, int priceOfPurch, int minimumCapacityNeeded, Season ProductionException) {
        this.secondProdPrice = secondProdPrice;
        this.firstProdPrice = firstProdPrice;
        this.secondProd = secondProd;
        this.firstProd = firstProd;
        this.priceOfPurch = priceOfPurch;
        this.minimumCapacityNeeded = minimumCapacityNeeded;
        this.productionException = ProductionException;
    }
}
