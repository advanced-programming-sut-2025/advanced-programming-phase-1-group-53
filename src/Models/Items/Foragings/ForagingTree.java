package Models.Items.Foragings;

import Enums.ItemType;
import Enums.Season;

import java.util.ArrayList;

public class ForagingTree  extends Plant{
    private final ArrayList<Season> seasons;

    private ForagingTree(ItemType itemType,  ArrayList<Season> seasons){
        super(itemType);
        this.seasons = seasons;
    }

    @Override
    public ForagingTree makeEdible(double energy){
        return this;
    }

    @Override
    public ForagingTree clone(){
        return new ForagingTree(getItemType(), seasons);
    }

    public static final ForagingTree acorns = new ForagingTree(ItemType.Acorns,  Plant.specialSeasons);
    public static final ForagingTree mapleSeed = new ForagingTree(ItemType.MapleSeed,  Plant.specialSeasons);
    public static final ForagingTree pineCone = new ForagingTree(ItemType.PineCone,  Plant.specialSeasons);
    public static final ForagingTree mahoganySeed = new ForagingTree(ItemType.MahoganySeed, Plant.specialSeasons);
    public static final ForagingTree mushroomTreeSeed = new ForagingTree(ItemType.MushroomTreeSeed, Plant.specialSeasons);

    public static final ArrayList<ForagingTree> trees = new ArrayList<>(){{
        add(acorns);
        add(mapleSeed);
        add(pineCone);
        add(mahoganySeed);
        add(mushroomTreeSeed);
    }};

    public ArrayList<Season> getSeasons() {
        return seasons;
    }
}
