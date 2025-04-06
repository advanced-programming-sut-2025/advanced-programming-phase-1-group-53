package Enums;

public enum Fish {
    salmon(FishKind.normal, Season.FALL, 75),
    sardine(FishKind.normal, Season.FALL, 40),
    shad(FishKind.normal, Season.FALL, 60),
    BlueDiscus(FishKind.normal, Season.FALL, 120),
    MidnightCarp(FishKind.normal, Season.WINTER, 150),
    Squid(FishKind.normal, Season.WINTER, 80),
    Tuna(FishKind.normal, Season.WINTER, 100),
    Perch(FishKind.normal, Season.WINTER, 55),
    Flounder(FishKind.normal, Season.SPRING, 100),
    Lionfish(FishKind.normal, Season.SPRING, 100),
    Herring(FishKind.normal, Season.SPRING, 30),
    Ghostfish(FishKind.normal, Season.SPRING, 45),
    Tilapia(FishKind.normal, Season.SUMMER, 75),
    Dorado(FishKind.normal, Season.SUMMER, 100),
    Sunfish(FishKind.normal, Season.SUMMER, 30),
    RainbowTrout(FishKind.normal, Season.SUMMER, 65),
    Legend(FishKind.legendary, Season.SPRING, 5000),
    Glacierfish(FishKind.legendary, Season.WINTER, 1000),
    Angler(FishKind.legendary, Season.FALL, 900),
    Crimsonfish(FishKind.legendary, Season.SUMMER, 1500);

    private final FishKind fishKind;
    private final Season season;
    private final int cost;

    Fish(FishKind fishKind, Season season, int cost) {
        this.fishKind = fishKind;
        this.season = season;
        this.cost = cost;
    }

    public FishKind getFishKind() {
        return fishKind;
    }

    public Season getSeason() {
        return season;
    }

    public int getCost() {
        return cost;
    }
}
