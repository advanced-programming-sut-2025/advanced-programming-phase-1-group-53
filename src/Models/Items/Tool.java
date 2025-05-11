package Models.Items;

import Enums.ItemType;
import Enums.ToolLevel;

import java.util.HashMap;
import java.util.Map;

public class Tool extends Item {
    private ToolLevel level;
    private int energyConsumed;


    public Tool(ItemType itemType, ToolLevel toolLevel) {
        super(itemType);
        this.level = ToolLevel.normal;
        this.energyConsumed = Math.min(energy.get(itemType) - toolLevel.getLevel() + 1, 0);
    }


    public ToolLevel getLevel() {
        return level;
    }

    public void setLevel(ToolLevel level) {
        this.level = level;
    }

    public int getEnergyConsumed() {
        int level =0;
        if(itemType.equals(ItemType.Hoe) || itemType.equals(ItemType.WateringCan))
            level = App.getGame().getCurrentPlayer().abilities.getFarmingLevel()/4;
        if(itemType.equals(ItemType.Pickaxe))
            level = App.getGame().getCurrentPlayer().abilities.getMiningLevel()/4;
        if(itemType.equals(ItemType.Axe))
            level = App.getGame().getCurrentPlayer().abilities.getForagingLevel()/4;
        if(itemType.equals(ItemType.FishingPole))
            level = App.getGame().getCurrentPlayer().abilities.getFishingLevel()/4;
        return energyConsumed - level;
    }

    @Override
    public Tool clone(){
        return new Tool(getItemType(), level);
    }

    public void setEnergyConsumed(int energyConsumed) {
        this.energyConsumed = energyConsumed;
    }




    private final Map<ItemType, Integer> energy = new HashMap<>(){{
        put(ItemType.Hoe, 5);
        put(ItemType.Pickaxe, 5);
        put(ItemType.Axe, 5);
        put(ItemType.Scythe, 2);
        put(ItemType.MilkPail, 4);
        put(ItemType.Shear, 4);
        put(ItemType.FishingPole, 8);
        put(ItemType.WateringCan, 5);
        put(ItemType.Trashcan, 0);
    }};

    public static final Tool normalHoe = new Tool(ItemType.Hoe, ToolLevel.normal);
    public static final Tool cooperHoe = new Tool(ItemType.Hoe, ToolLevel.copper);
    public static final Tool ironHoe = new Tool(ItemType.Hoe, ToolLevel.iron);
    public static final Tool goldHoe = new Tool(ItemType.Hoe, ToolLevel.gold);
    public static final Tool iridiumHoe = new Tool(ItemType.Hoe, ToolLevel.iridium);
    public static final Tool normalPickaxe = new Tool(ItemType.Pickaxe, ToolLevel.normal);
    public static final Tool cooperPickaxe = new Tool(ItemType.Pickaxe, ToolLevel.copper);
    public static final Tool ironPickaxe = new Tool(ItemType.Pickaxe, ToolLevel.iron);
    public static final Tool goldPickaxe = new Tool(ItemType.Pickaxe, ToolLevel.gold);
    public static final Tool iridiumPickaxe = new Tool(ItemType.Pickaxe, ToolLevel.iridium);
    public static final Tool normalAxe = new Tool(ItemType.Axe, ToolLevel.normal);
    public static final Tool cooperAxe = new Tool(ItemType.Axe, ToolLevel.copper);
    public static final Tool ironAxe = new Tool(ItemType.Axe, ToolLevel.iron);
    public static final Tool goldAxe = new Tool(ItemType.Axe, ToolLevel.gold);
    public static final Tool iridiumAxe = new Tool(ItemType.Axe, ToolLevel.iridium);
    public static final Tool scythe = new Tool(ItemType.Scythe, ToolLevel.normal);
    public static final Tool milkPail = new Tool(ItemType.MilkPail, ToolLevel.normal);
    public static final Tool shear = new Tool(ItemType.Shear, ToolLevel.normal);
    public static final Tool normalFishingPole = new Tool(ItemType.FishingPole, ToolLevel.normal);
    public static final Tool bambooFishingPole = new Tool(ItemType.FishingPole, ToolLevel.bamboo);
    public static final Tool fiberglassFishingPole = new Tool(ItemType.FishingPole, ToolLevel.fiberglass);
    public static final Tool iridiumFishingPole = new Tool(ItemType.FishingPole, ToolLevel.iridium);

}
