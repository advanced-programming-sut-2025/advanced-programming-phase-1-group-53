package Models.Items.Foragings;

import Enums.ItemType;

import java.util.ArrayList;

public class ForagingMineral  extends Plant{
    private ForagingMineral(ItemType itemType){
        super(itemType);
    }

    @Override
    public ForagingMineral clone() {
        return new ForagingMineral(getItemType());
    }

    @Override
    public ForagingMineral makeEdible(double energy){
        return this;
    }

    public static final ForagingMineral quartz = new ForagingMineral(ItemType.Quartz);
    public static final ForagingMineral earthCrystal = new ForagingMineral(ItemType.EarthCrystal);
    public static final ForagingMineral frozenTear = new ForagingMineral(ItemType.FrozenTear);
    public static final ForagingMineral fireQuartz = new ForagingMineral(ItemType.FireQuartz);
    public static final ForagingMineral emerald = new ForagingMineral(ItemType.Emerald);
    public static final ForagingMineral jade = new ForagingMineral(ItemType.Jade);
    public static final ForagingMineral ruby = new ForagingMineral(ItemType.Ruby);
    public static final ForagingMineral amethyst = new ForagingMineral(ItemType.Amethyst);
    public static final ForagingMineral topaz = new ForagingMineral(ItemType.Topaz);
    public static final ForagingMineral aquamarine = new ForagingMineral(ItemType.Aquamarine);
    public static final ForagingMineral copperOre = new ForagingMineral(ItemType.CopperOre);
    public static final ForagingMineral ironOre = new ForagingMineral(ItemType.IronOre);
    public static final ForagingMineral goldOre = new ForagingMineral(ItemType.GoldOre);
    public static final ForagingMineral iridiumOre = new ForagingMineral(ItemType.IridiumOre);
    public static final ForagingMineral diamond = new ForagingMineral(ItemType.Diamond);
    public static final ForagingMineral prismaticShard = new ForagingMineral(ItemType.PrismaticShard);
    public static final ForagingMineral Wood = new ForagingMineral(ItemType.Wood);
    public static final ForagingMineral Stone = new ForagingMineral(ItemType.Stone);
    public static final ForagingMineral Fiber = new ForagingMineral(ItemType.Fiber);


    public static final ArrayList<ForagingMineral> minerals = new ArrayList<>(){{
        add(quartz);
        add(earthCrystal);
        add(frozenTear);
        add(fireQuartz);
        add(emerald);
        add(jade);
        add(ruby);
        add(amethyst);
        add(topaz);
        add(aquamarine);
        add(copperOre);
        add(ironOre);
        add(goldOre);
        add(iridiumOre);
        add(diamond);
        add(prismaticShard);
        add(Stone);
        add(Wood);
        add(Fiber);
    }};


}
