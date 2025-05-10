package Enums;

public enum ToolType {
    Hoe(5),
    Pickaxe(5),
    Axe(5),
    WateringCam(5),
    FishingPole(8),
    Scythe(2),
    MilkPail(4),
    Shear(4),
    Trashcan(0);
    private final int initialEnergyConsumed;
    ToolType(int initialEnergyConsumed) {
        this.initialEnergyConsumed = initialEnergyConsumed;
    }
    public int getInitialEnergyConsumed() {
        return initialEnergyConsumed;
    }
}
