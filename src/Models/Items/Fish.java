package Models.Items;

import Enums.FishKind;
import Enums.ItemType;
import Enums.Season;

public class Fish extends Item {
    private final FishKind fishKind;
    private final Season season;

    private Fish(ItemType itemType, FishKind fishKind, Season season){
        super(itemType);
        this.fishKind = fishKind;
        this.season = season;
    }

    public static final Fish Salmon = new Fish(ItemType.Salmon, FishKind.normal, Season.FALL);
    public static final Fish Sardine = new Fish(ItemType.Sardine, FishKind.normal, Season.FALL);
    public static final Fish Shad = new Fish(ItemType.Shad, FishKind.normal, Season.FALL);
    public static final Fish BlueDiscus = new Fish(ItemType.BlueDiscus, FishKind.normal, Season.FALL);
    public static final Fish MidnightCarp = new Fish(ItemType.MidnightCarp, FishKind.normal, Season.WINTER);
    public static final Fish Squid = new Fish(ItemType.Squid, FishKind.normal, Season.WINTER);
    public static final Fish Tuna = new Fish(ItemType.Tuna, FishKind.normal, Season.WINTER);
    public static final Fish Perch = new Fish(ItemType.Perch, FishKind.normal, Season.WINTER);
    public static final Fish Flounder = new Fish(ItemType.Flounder, FishKind.normal, Season.SPRING);
    public static final Fish LionFish = new Fish(ItemType.Lionfish, FishKind.normal, Season.SPRING);
    public static final Fish Herring = new Fish(ItemType.Herring, FishKind.normal, Season.SPRING);
    public static final Fish GhostFish = new Fish(ItemType.GhostFish, FishKind.normal, Season.SPRING);
    public static final Fish Tilapia = new Fish(ItemType.Tilapia, FishKind.normal, Season.SUMMER);
    public static final Fish Dorado = new Fish(ItemType.Dorado, FishKind.normal, Season.SUMMER);
    public static final Fish Sunfish = new Fish(ItemType.Sunfish, FishKind.normal, Season.SUMMER);
    public static final Fish RainbowTrout = new Fish(ItemType.RainbowTrout, FishKind.normal, Season.SUMMER);
    public static final Fish Legend = new Fish(ItemType.Legend, FishKind.legendary, Season.SPRING);
    public static final Fish GlacierFish = new Fish(ItemType.GlacierFish, FishKind.legendary, Season.WINTER);
    public static final Fish Angler = new Fish(ItemType.Angler, FishKind.legendary, Season.FALL);
    public static final Fish CrimsonFish = new Fish(ItemType.CrimsonFish, FishKind.legendary, Season.SUMMER);

}