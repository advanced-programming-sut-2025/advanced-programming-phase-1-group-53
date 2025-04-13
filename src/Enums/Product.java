package Enums;

import java.util.HashMap;
import java.util.Map;

public enum Product {
    CooperOre(-1, 2000, true, 75, null,75, "CopperOre", null),
    IronOre(-1, 2000, true, 150,null,150, "IronOre", null),
    GoldOre(-1, 2000, true, 400,null,400, "GoldOre", null),
    Coal(-1, 2000, true, 150,null,150, "Coal", null),
    IridiumOre(-1, 2000, true, 1000,null, 1000, "IridiumOre", null),
    CooperBar(-1, 2000, true, -1,null,-1, "CopperBar", Map.of("CooperOre", 5)),
    IronBar(-1, 2000, true, -1,null, -1, "IronBar", Map.of("IronOre", 5)),
    GoldBar(-1, 2000, true, -1,null, -1, "GoldBar", Map.of("GoldOre", 5)),
    IridiumBar(-1, 2000, true, -1, null,-1, "IridiumBar", Map.of("IridiumOre", 5)),
    CooperTool(-1, 1, true, 2000,null, 2000, "CopperTool", Map.of("CopperBar", 5)),
    SteelTool(-1, 1, true, 5000,null, 5000, "SteelTool", Map.of("IronBar", 5)),
    GoldTool(-1, 1, true, 10000, null,10000, "GoldTool", Map.of("GoldBar", 5)),
    IridiumTool(-1, 1, true, 25000,null, 25000, "IridiumTool", Map.of("IridiumBar", 5)),
    CooperTrashCan(-1, 1, true, 1000,null, 1000, "CopperTrashCan", Map.of("CopperBar", 5)),
    SteelTrashCan(-1, 1, true, 2500, null,2500, "SteelTrashCan", Map.of("IronBar", 5)),
    GoldTrashCan(-1, 1, true, 5000, null,5000, "GoldTrashCan", Map.of("GoldBar", 5)),
    IridiumTrashCan(-1, 1, true, 12500, null,12500, "IridiumTrashCan", Map.of("IridiumBar", 5)),
    Hay(-1, 2000, true, 50,null, 50, "Hay", null),
    MilkPail(-1, 1, true, 1000,null, 1000, "MilkPail", null),
    Shear(-1, 1, true, 1000,null, 1000, "Shear", null),
    Hen(-1, 2, true, 800, null,800, "Hen", null),
    Cow(-1, 2, true, 1500, null,1500, "Cow", null),
    Goat(-1, 2, true, 4000,null, 4000, "Goat", null),
    Sheep(-1, 2, true, 8000,null, 8000, "Sheep", null),
    Rabbit(-1, 2, true, 8000,null, 8000, "Rabbit", null),
    Duck(-1, 2, true, 1200,null, 1200, "Duck", null),
    Pig(-1, 2, true, 16000, null,16000, "Pig", null),
    Dino(-1, 2, true, 14000, null,14000, "Dino", null),
    Beer(-1, 2000, true, 400, null,400, "Beer", null),
    Salad(-1, 2000, true, 220, null,220, "Salad", null),
    Pizza(-1, 2000, true, 600, null,600, "Pizza", null),
    Spaghetti(-1, 2000, true, 240,null, 240, "Spaghetti", null),
    BreadRec(-1, 2000, true, 120,null, 120, "BreadRecipe", null),
    CoffeeRec(-1, 2000, true, 300,null, 300, "CoffeeRecipe", null),
    HashBrownRec(-1, 1, true, 50,null, 50, "HashBrownRecipe", null),
    OmeletRec(-1, 1, true, 100,null, 100, "OmeletRecipe", null),
    BreadRec(-1, 1, true, 100,null, 100, "BreadRecipe", null),
    TortillaRec(-1, 1, true, 100,null, 100, "TortillaRecipe", null),
    PancakesRec(-1, 1, true, 100, null,100, "PancakesRecipe", null),
    MakiRollRec(-1, 1, true, 300,null, 300, "MakiRollRecipe", null),
    PizzaRec(-1, 1, true, 150,null, 150, "PizzaRecipe", null),
    TripleShotEspressoRec(-1, 1, true, 5000,null, 5000, "TripleShotEspressoRecipe", null),
    CookieRec(-1, 1, true, 300, null,300, "CookieRecipe", null),
    JojaCola(-1,2000, true, 75, null,75, "JojaCola", null),
    AncientSeed(-1, 1,true,500, null,500, "AncientSeed", null),
    GrassStarter(-1, 2000, true,125,null, 125, "GrassStarter", null),
    Sugar(-1, 2000, true,125, null,125, "Sugar", null),
    WheatFlour(-1, 2000, true, 125, null,125, "WheatFlour", null),
    Rice(-1, 2000, true, 250, null,250, "Rice", null),
    ParsnipSeed(-1, 5, true, 25,null, 25, "ParsnipSeed", null),
    BeanStarter(-1, 5, true, 75,null, 75, "BeanStarter", null),
    CauliFlowerSeed(-1, 5, true, 100, null,100, "CauliFlowerSeed", null),
    PotatoSeed(-1, 5, true, 62, null,62, "PotatoSeed", null),
    StrawberrySeed(-1, 5, true, 100, null,100, "StrawBerrySeed", null),
    TulipBulb(-1, 5, true, 25, null,25, "TulipBulb", null),
    KaleSeed(-1, 5, true, 87, null,87, "KaleSeed", null),
    CoffeeBean(-1, 5, true, 200, null,200, "CoffeeBean", null),
    CarrotSeed(-1, 5, true, 5, null,5, "CarrotSeed", null),
    RhubarbSeed(-1, 5, true, 100, null,100, "RhubarbSeed", null),
    JazzSeed(-1, 5, true, 37, null,37, "JazzSeed", null),
    TomatoSeed(-1, 5, true, 62,null, 62, "TomatoSeed", null),
    PepperSeed(-1, 5, true, 50, null,50, "PepperSeed", null),
    WheatSeed(-1, 5, true, 12 , null,12, "WheatSeed", null),
    SummerSquashSeed(-1, 5 , true, 10,null, 10, "SummerSqashSeed", null),
    RadishSeed(-1, 5, true, 50, null,50, "RadishSeed", null),
    MelonSeed(-1, 5, true, 100, null,100, "MelonSeed", null),
    HopsStarter(-1, 5, true, 75,null, 75, "HopsStarter", null),
    PoppySeed(-1, 5, true, 125, null,125, "PoppySeed", null),
    SpangleSeed(-1,5, true, 62, null,62, "SpangleSeed", null),
    StarFruit(-1, 5, true, 400, null,400, "StarFruit", null),
    CoffeBean(-1, 1, true, 200, null,200, "CoffeBean", null),
    SunflowerSeed(-1, 5, true, 100,null, 100, "SunflowerSeed", null),
    FishSmoker(-1, 1, true, 1000, null, 1000, "FishSmoker", null),
    TroutSoup(-1, 1, true, 250, null, 250, "TroutSoup", null),
    BambooPole(-1, 1, true, 500, null, 500, "BambooPole", null),
    TrainingRod(-1, 1, true, 25, null, 25, "TrainingRod", null),
    FiberglassRod(2, 1, true, 1800, null, 1800, "FiberglassRod", null),
    IridiumRod(4, 1, true, 7500, null, 7500, "IridiumRod", null),
    ParsnipSeedPierre(-1, 5, true, 20,Season.SPRING, 30, "ParsnipSeed", null),
    BeanStarterPierre(-1, 5, true, 60,Season.SPRING, 90, "BeanStarter", null),
    CauliFlowerSeedPierre(-1, 5, true, 80,Season.SPRING, 120, "CauliFlowerSeed", null),
    PotatoSeedPierre(-1, 5, true, 50,Season.SPRING, 75, "PotatoSeed", null),
    TulipBulbPierre(-1, 5, true, 20,Season.SPRING, 30, "TulipBulb", null),
    KaleSeedPierre(-1, 5, true, 70,Season.SPRING, 105, "KaleSeed", null),
    JazzSeedPierre(-1, 5, true, 30, Season.SPRING,45, "JazzSeed", null),
    GarlicSeedPierre(-1, 5, true, 40, Season.SPRING,60, "GarlicSeed", null),
    TomatoSeedPierre(-1, 5, true, 40,Season.SPRING, 60, "TomatoSeed", null),
    PepperSeedPierre(-1, 5, true, 50, null,50, "PepperSeed", null),
    WheatSeedPierre(-1, 5, true, 12 , null,12, "WheatSeed", null),
    SummerSquashSeedPierre(-1, 5 , true, 10,null, 10, "SummerSqashSeed", null),
    RadishSeedPierre(-1, 5, true, 50, null,50, "RadishSeed", null),
    MelonSeedPierre(-1, 5, true, 100, null,100, "MelonSeed", null),
    HopsStarterPierre(-1, 5, true, 75,null, 75, "HopsStarter", null),
    PoppySeedPierre(-1, 5, true, 125, null,125, "PoppySeed", null),
    SpangleSeedPierre(-1,5, true, 62, null,62, "SpangleSeed", null),
    StarFruitPierre(-1, 5, true, 400, null,400, "StarFruit", null),
    CoffeBeanPierre(-1, 1, true, 200, null,200, "CoffeBean", null),
    SunflowerSeedPierre(-1, 5, true, 100,null, 100, "SunflowerSeed", null),

    private String name;
    private final int price;
    private final int offPrice;
    private final Season offSeason;
    private boolean availability;
    private final int dailyLimit;
    private final int fishingSkillReq;
    private final Map<String, Integer> ingredients;


    Product(int fishingSkillReq, int dailyLimit, boolean availability,int offPrice, Season offSeason,  int price, String name, Map<String, Integer> ingredients) {
        this.fishingSkillReq = fishingSkillReq;
        this.dailyLimit = dailyLimit;
        this.availability = availability;
        this.offPrice = offPrice;
        this.offSeason = offSeason;
        this.price = price;
        this.name = name;
        this.ingredients = ingredients;
    }
}
