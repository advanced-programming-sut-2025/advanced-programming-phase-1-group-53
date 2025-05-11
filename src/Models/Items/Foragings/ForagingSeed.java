package Models.Items.Foragings;

import Enums.ItemType;
import Enums.Season;
import Models.Game.App;
import Models.Items.Item;

import java.util.ArrayList;
import java.util.Random;

public class ForagingSeed  extends Plant{
    private ArrayList<Season> seasons;

    private ForagingSeed(ItemType itemType, ArrayList<Season> seasons) {
        super(itemType);
        this.seasons = seasons;
    }

    @Override
    ForagingSeed makeEdible(int energy){
        return this;
    }

    @Override
    public ForagingSeed clone(){
        return new ForagingSeed(getItemType(), seasons);
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public static final ForagingSeed JazzSeeds = new ForagingSeed(ItemType.JazzSeed,  Plant.spring);
    public static final ForagingSeed CarrotSeeds = new ForagingSeed(ItemType.CarrotSeed,  Plant.spring);
    public static final ForagingSeed CauliflowerSeeds = new ForagingSeed(ItemType.CauliFlowerSeed,  Plant.spring);
    public static final ForagingSeed CoffeeBean = new ForagingSeed(ItemType.CoffeeBean,  Plant.spring);
    public static final ForagingSeed GarlicSeeds = new ForagingSeed(ItemType.GarlicSeed,  Plant.spring);
    public static final ForagingSeed BeanStarter = new ForagingSeed(ItemType.BeanStarter,  Plant.spring);
    public static final ForagingSeed KaleSeeds = new ForagingSeed(ItemType.KaleSeed,  Plant.spring);
    public static final ForagingSeed ParsnipSeeds = new ForagingSeed(ItemType.ParsnipSeed , Plant.spring);
    public static final ForagingSeed PotatoSeeds = new ForagingSeed(ItemType.PotatoSeed,  Plant.spring);
    public static final ForagingSeed RhubarbSeeds = new ForagingSeed(ItemType.RhubarbSeed,  Plant.spring);
    public static final ForagingSeed StrawberrySeeds = new ForagingSeed(ItemType.StrawberrySeed,  Plant.spring);
    public static final ForagingSeed TulipBulb = new ForagingSeed(ItemType.TulipBulb,  Plant.spring);
    public static final ForagingSeed RiceShoot = new ForagingSeed(ItemType.RiceShoot,  Plant.spring);
    public static final ForagingSeed BlueberrySeeds = new ForagingSeed(ItemType.BlueberrySeed,  Plant.summer);
    public static final ForagingSeed CornSeeds = new ForagingSeed(ItemType.CornSeed, Plant.summer);
    public static final ForagingSeed HopsStarter = new ForagingSeed(ItemType.HopsStarter,  Plant.summer);
    public static final ForagingSeed PepperSeeds = new ForagingSeed(ItemType.PepperSeed,  Plant.summer);
    public static final ForagingSeed MelonSeeds = new ForagingSeed(ItemType.MelonSeed,  Plant.summer);
    public static final ForagingSeed PoppySeeds = new ForagingSeed(ItemType.PoppySeed,  Plant.summer);
    public static final ForagingSeed RadishSeeds = new ForagingSeed(ItemType.RadishSeed,  Plant.summer);
    public static final ForagingSeed RedCabbageSeeds = new ForagingSeed(ItemType.RedCabbageSeed,  Plant.summer);
    public static final ForagingSeed StarfruitSeeds = new ForagingSeed(ItemType.StarfruitSeed, Plant.summer);
    public static final ForagingSeed SpangleSeeds = new ForagingSeed(ItemType.SpangleSeed,  Plant.summer);
    public static final ForagingSeed SummerSquashSeeds = new ForagingSeed(ItemType.SummerSquashSeed,  Plant.summer);
    public static final ForagingSeed SunflowerSeeds = new ForagingSeed(ItemType.SunflowerSeed, Plant.summer);
    public static final ForagingSeed TomatoSeeds = new ForagingSeed(ItemType.TomatoSeed, Plant.summer);
    public static final ForagingSeed WheatSeeds = new ForagingSeed(ItemType.WheatSeed,  Plant.summer);
    public static final ForagingSeed AmaranthSeeds = new ForagingSeed(ItemType.AmaranthSeed,  Plant.fall);
    public static final ForagingSeed ArtichokeSeeds = new ForagingSeed(ItemType.ArtichokeSeed,  Plant.fall);
    public static final ForagingSeed BeetSeeds = new ForagingSeed(ItemType.BeetSeed, Plant.fall);
    public static final ForagingSeed BokChoySeeds = new ForagingSeed(ItemType.BokChoySeed,  Plant.fall);
    public static final ForagingSeed BroccoliSeeds = new ForagingSeed(ItemType.BroccoliSeed, Plant.fall);
    public static final ForagingSeed CranberrySeeds = new ForagingSeed(ItemType.CranberrySeed, Plant.fall);
    public static final ForagingSeed EggplantSeeds = new ForagingSeed(ItemType.EggplantSeed, Plant.fall);
    public static final ForagingSeed FairySeeds = new ForagingSeed(ItemType.FairySeed,  Plant.fall);
    public static final ForagingSeed GrapeStarter = new ForagingSeed(ItemType.GrapeStarter,  Plant.fall);
    public static final ForagingSeed PumpkinSeeds = new ForagingSeed(ItemType.PumpkinSeed,  Plant.fall);
    public static final ForagingSeed YamSeeds = new ForagingSeed(ItemType.YamSeed,  Plant.fall);
    public static final ForagingSeed RareSeed = new ForagingSeed(ItemType.RareSeed,  Plant.fall);
    public static final ForagingSeed PowdermelonSeeds = new ForagingSeed(ItemType.PowdermelonSeed,  Plant.winter);
    public static final ForagingSeed AncientSeeds = new ForagingSeed(ItemType.AncientSeed,  Plant.specialSeasons);
    public static final ForagingSeed MixedSeeds = new ForagingSeed(ItemType.MixedSeed,  Plant.specialSeasons);
    public static final ForagingSeed MysticTreeSeed = new ForagingSeed(ItemType.MysticTreeSeed, Plant.specialSeasons);
    public static final ForagingSeed CherrySapling = new ForagingSeed(ItemType.CherrySapling,  Plant.spring);
    public static final ForagingSeed BananaSapling = new ForagingSeed(ItemType.BananaSapling,  Plant.spring);
    public static final ForagingSeed MangoSapling = new ForagingSeed(ItemType.MangoSapling,  Plant.spring);
    public static final ForagingSeed AppleSapling = new ForagingSeed(ItemType.AppleSapling, Plant.spring);
    public static final ForagingSeed PeachSapling = new ForagingSeed(ItemType.PeachSapling,  Plant.spring);
    public static final ForagingSeed PomegranateSapling = new ForagingSeed(ItemType.PomegranateSapling, Plant.spring);
    public static final ForagingSeed ApricotSapling = new ForagingSeed(ItemType.ApricotSapling, Plant.spring);
    public static final ForagingSeed OrangeSapling = new ForagingSeed(ItemType.OrangeSapling,  Plant.spring);
    public static final ForagingSeed Acorns = new ForagingSeed(ItemType.Acorns, Plant.specialSeasons);
    public static final ForagingSeed MapleSeed = new ForagingSeed(ItemType.MapleSeed,  Plant.specialSeasons);
    public static final ForagingSeed PineCone = new ForagingSeed(ItemType.PineCone,  Plant.specialSeasons);
    public static final ForagingSeed MahoganySeed = new ForagingSeed(ItemType.MahoganySeed, Plant.specialSeasons);
    public static final ForagingSeed MushroomTreeSeed = new ForagingSeed(ItemType.MushroomTreeSeed,  Plant.specialSeasons);



    public static final ArrayList<ForagingSeed> springSeeds = new ArrayList<>(){{
        add(CauliflowerSeeds);
        add(PotatoSeeds);
        add(ParsnipSeeds);
        add(JazzSeeds);
        add(TulipBulb);
    }};

    public static final ArrayList<ForagingSeed> summerSeeds = new ArrayList<>(){{
        add(CornSeeds);
        add(PepperSeeds);
        add(RadishSeeds);
        add(WheatSeeds);
        add(PoppySeeds);
        add(SunflowerSeeds);
        add(SpangleSeeds);
    }};

    public static final ArrayList<ForagingSeed> fallSeeds = new ArrayList<>(){{
        add(ArtichokeSeeds);
        add(CornSeeds);
        add(EggplantSeeds);
        add(PumpkinSeeds);
        add(SunflowerSeeds);
        add(FairySeeds);
    }};

    public static final ArrayList<ForagingSeed> winterSeeds = new ArrayList<>(){{
        add(PowdermelonSeeds);
    }};

    public ForagingSeed randomiseMixedSeed(){
        Random random = new Random();
        if(getItemType().equals(ItemType.MixedSeed)){
            if(App.getGame().dateAndTime.getSeason().equals(Season.SPRING))
                return springSeeds.get(random.nextInt(springSeeds.size())).clone();
            if(App.getGame().dateAndTime.getSeason().equals(Season.SUMMER))
                return summerSeeds.get(random.nextInt(summerSeeds.size())).clone();
            if(App.getGame().dateAndTime.getSeason().equals(Season.FALL))
                return fallSeeds.get(random.nextInt(fallSeeds.size())).clone();
            if(App.getGame().dateAndTime.getSeason().equals(Season.WINTER))
                return winterSeeds.get(random.nextInt(winterSeeds.size())).clone();
        }
        return this;
    }

    public static final ArrayList<ForagingSeed> foragingSeeds = new ArrayList<>(){{
       add(JazzSeeds);
       add(CarrotSeeds);
       add(CauliflowerSeeds);
       add(CoffeeBean);
       add(GarlicSeeds);
       add(BeanStarter);
       add(KaleSeeds);
       add(ParsnipSeeds);
       add(PotatoSeeds);
       add(RhubarbSeeds);
       add(StrawberrySeeds);
       add(TulipBulb);
       add(RiceShoot);
       add(BlueberrySeeds);
       add(CornSeeds);
       add(HopsStarter);
       add(PepperSeeds);
       add(MelonSeeds);
       add(PoppySeeds);
       add(RadishSeeds);
       add(RedCabbageSeeds);
       add(StarfruitSeeds);
       add(SpangleSeeds);
       add(SummerSquashSeeds);
       add(SunflowerSeeds);
       add(TomatoSeeds);
       add(WheatSeeds);
       add(AmaranthSeeds);
       add(ArtichokeSeeds);
       add(BeetSeeds);
       add(BokChoySeeds);
       add(BroccoliSeeds);
       add(CranberrySeeds);
       add(EggplantSeeds);
       add(FairySeeds);
       add(GrapeStarter);
       add(PumpkinSeeds);
       add(YamSeeds);
       add(RareSeed);
       add(PowdermelonSeeds);
       add(AncientSeeds);
       add(MixedSeeds);
    }};
}

