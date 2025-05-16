package Models.Items;

import Enums.FishKind;
import Enums.ItemType;
import Enums.Season;

import java.util.ArrayList;

public class Fish extends Item {
    private final FishKind fishKind;
    private final Season season;

    private Fish(ItemType itemType, FishKind fishKind, Season season){
        super(itemType);
        this.fishKind = fishKind;
        this.season = season;
    }

    public FishKind getFishKind() {
        return fishKind;
    }

    public Season getSeason() {
        return season;
    }

    @Override
    public Fish makeSellPrice(double price){
        this.baseSellPrice = price;
        return this;
    }

    @Override
    public Fish clone(){
        return new Fish(itemType, fishKind, season).makeSellPrice(baseSellPrice);
    }

    public static final Fish Salmon = new Fish(ItemType.Salmon, FishKind.normal, Season.FALL).makeSellPrice(75);
    public static final Fish Sardine = new Fish(ItemType.Sardine, FishKind.normal, Season.FALL).makeSellPrice(40);
    public static final Fish Shad = new Fish(ItemType.Shad, FishKind.normal, Season.FALL).makeSellPrice(60);
    public static final Fish BlueDiscus = new Fish(ItemType.BlueDiscus, FishKind.normal, Season.FALL).makeSellPrice(120);
    public static final Fish MidnightCarp = new Fish(ItemType.MidnightCarp, FishKind.normal, Season.WINTER).makeSellPrice(150);
    public static final Fish Squid = new Fish(ItemType.Squid, FishKind.normal, Season.WINTER).makeSellPrice(80);
    public static final Fish Tuna = new Fish(ItemType.Tuna, FishKind.normal, Season.WINTER).makeSellPrice(100);
    public static final Fish Perch = new Fish(ItemType.Perch, FishKind.normal, Season.WINTER).makeSellPrice(55);
    public static final Fish Flounder = new Fish(ItemType.Flounder, FishKind.normal, Season.SPRING).makeSellPrice(100);
    public static final Fish LionFish = new Fish(ItemType.Lionfish, FishKind.normal, Season.SPRING).makeSellPrice(100);
    public static final Fish Herring = new Fish(ItemType.Herring, FishKind.normal, Season.SPRING).makeSellPrice(30);
    public static final Fish GhostFish = new Fish(ItemType.GhostFish, FishKind.normal, Season.SPRING).makeSellPrice(45);
    public static final Fish Tilapia = new Fish(ItemType.Tilapia, FishKind.normal, Season.SUMMER).makeSellPrice(75);
    public static final Fish Dorado = new Fish(ItemType.Dorado, FishKind.normal, Season.SUMMER).makeSellPrice(100);
    public static final Fish Sunfish = new Fish(ItemType.Sunfish, FishKind.normal, Season.SUMMER).makeSellPrice(30);
    public static final Fish RainbowTrout = new Fish(ItemType.RainbowTrout, FishKind.normal, Season.SUMMER).makeSellPrice(65);
    public static final Fish Legend = new Fish(ItemType.Legend, FishKind.legendary, Season.SPRING).makeSellPrice(5000);
    public static final Fish GlacierFish = new Fish(ItemType.GlacierFish, FishKind.legendary, Season.WINTER).makeSellPrice(1000);
    public static final Fish Angler = new Fish(ItemType.Angler, FishKind.legendary, Season.FALL).makeSellPrice(900);
    public static final Fish CrimsonFish = new Fish(ItemType.CrimsonFish, FishKind.legendary, Season.SUMMER).makeSellPrice(1500);


    public static final ArrayList<Fish> SpringFishes = new ArrayList<>(){{
        add(Flounder);
        add(Herring);
        add(GhostFish);
        add(LionFish);
        add(Legend);
    }};
    public static final ArrayList<Fish>SummerFishes = new ArrayList<>(){{
        add(Tilapia);
        add(Dorado);
        add(Sunfish);
        add(RainbowTrout);
        add(CrimsonFish);
    }};
    public static final ArrayList<Fish> FallFishes = new ArrayList<>(){{
        add(Salmon);
        add(Sardine);
        add(Shad);
        add(BlueDiscus);
        add(Angler);
    }};
    public static final ArrayList<Fish> WinterFishes = new ArrayList<>(){{
        add(MidnightCarp);
        add(Squid);
        add(Tuna);
        add(Perch);
        add(GlacierFish);
    }};
    public static final ArrayList<Fish> fishes = new ArrayList<>(){{
        addAll(SpringFishes);
        addAll(SummerFishes);
        addAll(FallFishes);
        addAll(WinterFishes);
    }};
}