package Models.Items.Foragings;

import Enums.ItemType;
import Enums.Season;

import java.util.ArrayList;

public class ForagingCrop  extends Plant{
    private ArrayList<Season> seasons;

    private ForagingCrop(ItemType itemType, ArrayList<Season> seasons, double energy){
        super(itemType);
        this.seasons = seasons;
        makeEdible(energy);
    }

    @Override
    public ForagingCrop makeEdible(double energy){
        this.setEdible();
        this.energy = energy;
        return this;
    }

    @Override
    public ForagingCrop clone(){
        return new ForagingCrop(this.getItemType(), this.seasons, this.energy);
    }

    public static final ForagingCrop commonMushroom = new ForagingCrop(ItemType.CommonMushroom, Plant.specialSeasons, 38);
    public static final ForagingCrop daffodil = new ForagingCrop(ItemType.Daffodil,  Plant.spring, 0);
    public static final ForagingCrop dandelion = new ForagingCrop(ItemType.Dandelion,  Plant.spring, 25);
    public static final ForagingCrop leek = new ForagingCrop(ItemType.Leek,  Plant.spring, 40);
    public static final ForagingCrop morel = new ForagingCrop(ItemType.Morel,  Plant.spring, 20);
    public static final ForagingCrop salmonberry = new ForagingCrop(ItemType.Salmonberry,  Plant.spring, 25);
    public static final ForagingCrop springOnion = new ForagingCrop(ItemType.SpringOnion,  Plant.spring, 13);
    public static final ForagingCrop wildHorseradish = new ForagingCrop(ItemType.WildHorseRadish,  Plant.spring, 13);
    public static final ForagingCrop fiddleheadFern = new ForagingCrop(ItemType.FiddleHeadFern,  Plant.summer, 25);
    public static final ForagingCrop grape = new ForagingCrop(ItemType.Grape,  Plant.summer, 38);
    public static final ForagingCrop redMushroom = new ForagingCrop(ItemType.RedMushroom, Plant.summer, -50);
    public static final ForagingCrop spiceBerry = new ForagingCrop(ItemType.SpiceBerry,  Plant.summer, 25);
    public static final ForagingCrop sweetPea = new ForagingCrop(ItemType.SweatPea,  Plant.summer, 0);
    public static final ForagingCrop blackberry = new ForagingCrop(ItemType.Blackberry,  Plant.fall, 25);
    public static final ForagingCrop chanterelle = new ForagingCrop(ItemType.Chanterelle, Plant.fall, 75);
    public static final ForagingCrop hazelnut = new ForagingCrop(ItemType.Hazelnut,  Plant.fall, 38);
    public static final ForagingCrop purpleMushroom = new ForagingCrop(ItemType.PurpleMushroom,  Plant.fall, 30);
    public static final ForagingCrop wildPlum = new ForagingCrop(ItemType.WildPlum, Plant.fall, 25);
    public static final ForagingCrop crocus = new ForagingCrop(ItemType.Crocus,  Plant.winter, 0);
    public static final ForagingCrop crystalFruit = new ForagingCrop(ItemType.CrystalFruit, Plant.winter, 63);
    public static final ForagingCrop holly = new ForagingCrop(ItemType.Holly,  Plant.winter, -37);
    public static final ForagingCrop snowYam = new ForagingCrop(ItemType.SnowYam, Plant.winter, 30);
    public static final ForagingCrop winterRoot = new ForagingCrop(ItemType.WinterRoot, Plant.winter, 25);

    public static final ArrayList<ForagingCrop> foragingCrops = new ArrayList<>(){{
        add(commonMushroom);
        add(daffodil);
        add(dandelion);
        add(leek);
        add(morel);
        add(salmonberry);
        add(springOnion);
        add(wildHorseradish);
        add(fiddleheadFern);
        add(grape);
        add(redMushroom);
        add(spiceBerry);
        add(sweetPea);
        add(blackberry);
        add(chanterelle);
        add(hazelnut);
        add(purpleMushroom);
        add(wildPlum);
        add(crocus);
        add(crystalFruit);
        add(holly);
        add(snowYam);
        add(winterRoot);
    }};

    public static final ArrayList<ForagingCrop> Mushrooms = new ArrayList<>(){{
        add(commonMushroom);
        add(redMushroom);
        add(purpleMushroom);
    }};

    public ArrayList<Season> getSeasons() {
        return seasons;
    }
}
