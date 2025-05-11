package Models.Items;

import Enums.ItemType;
import Enums.ToolLevel;

public class WateringCan extends Tool{
    private int waterCapacity;
    private int currentWaterLevel;

    public WateringCan(ItemType itemType, ToolLevel toolLevel, int waterCapacity) {
        super(itemType, toolLevel);
        this.waterCapacity = waterCapacity;
        this.currentWaterLevel = 0;
    }

    public int getCurrentWaterLevel() {
        return currentWaterLevel;
    }

    public void setCurrentWaterLevel(int water){
        currentWaterLevel = Math.max(water + currentWaterLevel, currentWaterLevel);
    }

    public static final WateringCan noramalWateringCan = new WateringCan(ItemType.WateringCan, ToolLevel.normal, 40);
    public static final WateringCan copperWateringCan = new WateringCan(ItemType.WateringCan, ToolLevel.copper, 55);
    public static final WateringCan ironWateringCan = new WateringCan(ItemType.WateringCan, ToolLevel.iron, 70);
    public static final WateringCan goldWateringCan = new WateringCan(ItemType.WateringCan, ToolLevel.gold, 85);
    public static final WateringCan iridiumWateringCan = new WateringCan(ItemType.WateringCan, ToolLevel.iridium, 100);

}
