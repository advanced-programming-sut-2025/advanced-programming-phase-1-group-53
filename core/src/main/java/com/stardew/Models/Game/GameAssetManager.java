package com.stardew.Models.Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.stardew.Enums.ItemType;
import com.stardew.Enums.ToolLevel;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class GameAssetManager {
    private final static Texture[] BUILDINGS_IN_DIFFERENT_SEASONS = new Texture[]{
        new Texture("Buildings/Pelican Town Spring.png"),
        new Texture("Buildings/Pelican Town Summer.png"),
        new Texture("Buildings/Pelican Town Fall.png"),
        new Texture("Buildings/Pelican Town Winter.png")
    };

    public static final Texture collect = new Texture("Collect_Button.png");

    private static final HashMap<String, Texture> NPC_Textures = new HashMap<>(){{
        put("Abigail", new Texture("NPC/Abigail.png"));
        put("Harvey", new Texture("NPC/Harvey.png"));
        put("Lia", new Texture("NPC/Leah.png"));
        put("Robin", new Texture("NPC/Robin.png"));
        put("Sebastian", new Texture("NPC/Sebastian.png"));
    }};

    private static final HashMap<String, TextureRegion[][]> NPC_SPRITES = new HashMap<>(){{
        put("Abigail", new TextureRegion[4][4]);
        put("Harvey", new TextureRegion[4][4]);
        put("Lia", new TextureRegion[4][4]);
        put("Robin", new TextureRegion[4][4]);
        put("Sebastian", new TextureRegion[4][4]);
    }};

    static {
        for(String s : NPC_SPRITES.keySet()){
            for(int i = 0; i< 4; i++){
                for(int j = 0; j< 4; j++){
                    NPC_SPRITES.get(s)[i][j] = new TextureRegion(NPC_Textures.get(s), j*16, i*32, 16, 32);
                }
            }
        }
    }

    public static HashMap<String, TextureRegion[][]> getNpcSprites(){
        return NPC_SPRITES;
    }
    private final static Texture FULL_ENERGY_SPRITE = new Texture("energy_bar.png");
    private final static Texture EMPTY_ENERGY_BAR_SPRITE = new Texture("empty_energy.png");
    private final static Texture LAKE_TEXTURE = new Texture("Buildings/lakeSprite.png");
    private final static Texture[] GREENHOUSE_SPRITES = new Texture[]{new Texture("Buildings/greenhouse.png"),
        new Texture("Buildings/broken greenhouse.png")};
    private final static Texture[] TILES_TEXTURES = new Texture[]{new Texture("Tiles/Flooring_44.png"),
    new Texture("Tiles/Flooring_55.png"), new Texture("Tiles/Flooring_30.png"),
    new Texture("Tiles/Flooring_52.png"), new Texture("Tiles/Flooring_64.png"),
    new Texture("Tiles/Flooring_38.png"), new Texture("Tiles/Flooring_58.png"),
    new Texture("Tiles/Flooring_62.png"), new Texture("Tiles/Flooring_26.png"),
        new Texture("Tiles/Flooring_25.png"),new Texture("Tiles/Flooring_35.png")};
    private final static Texture[] INVENTORY_SPRITES = new Texture[]{
        new Texture("Menus/InventoryWidget.png"),new Texture("Menus/Social_Widget.png"),
        new Texture("Menus/Map_Widget.png"), new Texture("Menus/Skills_Widget.png"),
        new Texture("Menus/Inventory Rows.png"), new Texture("Menus/Level_Farmer.png")
    };

    private final static HashMap<ItemType, Texture> ITEMS_SPRITES = new HashMap<>(){{
        put(ItemType.DeluxeSoil, new Texture("Items/Deluxe_Retaining_Soil.png"));
        put(ItemType.Hay, new Texture("Items/Hay.png"));
    }};

    private final static HashMap<ItemType, Texture> CRAFTABLE_SPRITES = new HashMap<>(){{
        put(ItemType.Bomb, new Texture("Craftables/Bomb.png"));
        put(ItemType.CherryBomb, new Texture("Craftables/Cherry_Bomb.png"));
        put(ItemType.DeluxeScareCrow, new Texture("Craftables/Deluxe_Scarecrow.png"));
        put(ItemType.GrassStarter, new Texture("Craftables/Grass_Starter.png"));
        put(ItemType.IridiumSprinkler, new Texture("Craftables/Iridium_Sprinkler.png"));
        put(ItemType.MegaBomb, new Texture("Craftables/Mega_Bomb.png"));
        put(ItemType.MysticTreeSeed, new Texture("Craftables/Mystic_Tree_Seed.png"));
        put(ItemType.QualitySprinkler, new Texture("Craftables/Quality_Sprinkler.png"));
        put(ItemType.ScareCrow, new Texture("Craftables/Scarecrow.png"));
        put(ItemType.Sprinkler, new Texture("Craftables/Sprinkler.png"));
        put(ItemType.BeeHouse, new Texture("Craftables/Bee_House.png"));
        put(ItemType.CharcoalKlin, new Texture("Craftables/Charcoal_Kiln.png"));
        put(ItemType.CheesePress, new Texture("Craftables/Cheese_Press.png"));
        put(ItemType.Dehydrator, new Texture("Craftables/Dehydrator.png"));
        put(ItemType.FishSmoker, new Texture("Craftables/Fish_Smoker.png"));
        put(ItemType.Furnace, new Texture("Craftables/Furnace.png"));
        put(ItemType.Keg, new Texture("Craftables/Keg.png"));
        put(ItemType.Loom, new Texture("Craftables/Loom.png"));
        put(ItemType.MayonnaiseMachine, new Texture("Craftables/Mayonnaise_Machine.png"));
        put(ItemType.OilMaker, new Texture("Craftables/Oil_Maker.png"));
        put(ItemType.PreservesJar, new Texture("Craftables/Preserves_Jar.png"));
    }};

    private final static HashMap<ItemType, Texture> FOOD_SPRITES = new HashMap<>(){{
        put(ItemType.BakedFish, new Texture("Foods/Baked_Fish.png"));
        put(ItemType.Bread, new Texture("Foods/Bread.png"));
        put(ItemType.Cookie, new Texture("Foods/Cookie.png"));
        put(ItemType.DishOTheSea, new Texture("Foods/Dish_O%27_The_Sea.png"));
        put(ItemType.FarmersLunch, new Texture("Foods/Farmer%27s_Lunch.png"));
        put(ItemType.FriedEgg, new Texture("Foods/Fried_Egg.png"));
        put(ItemType.FruitSalad, new Texture("Foods/Fruit_Salad.png"));
        put(ItemType.HashBrowns, new Texture("Foods/Hashbrowns.png"));
        put(ItemType.MakiRoll, new Texture("Foods/Maki_Roll.png"));
        put(ItemType.MinersTreat, new Texture("Foods/Miner%27s_Treat.png"));
        put(ItemType.Omelet, new Texture("Foods/Omelet.png"));
        put(ItemType.Pancakes, new Texture("Foods/Pancakes.png"));
        put(ItemType.Pizza, new Texture("Foods/Pizza.png"));
        put(ItemType.PumpkinPie, new Texture("Foods/Pumpkin_Pie.png"));
        put(ItemType.RedPlate, new Texture("Foods/Red_Plate.png"));
        put(ItemType.Salad, new Texture("Foods/Salad.png"));
        put(ItemType.SalmonDinner, new Texture("Foods/Salmon_Dinner.png"));
        put(ItemType.SeaFormPudding, new Texture("Foods/Seafoam_Pudding.png"));
        put(ItemType.Spaghetti, new Texture("Foods/Spaghetti.png"));
        put(ItemType.SurvivalBurger, new Texture("Foods/Survival_Burger.png"));
        put(ItemType.Tortilla, new Texture("Foods/Tortilla.png"));
        put(ItemType.TripleShotEspresso, new Texture("Foods/Triple_Shot_Espresso.png"));
        put(ItemType.TroutSoup, new Texture("Foods/Trout_Soup.png"));
        put(ItemType.VegetableMedley, new Texture("Foods/Vegetable_Medley.png"));
    }};

    private final static HashMap<ItemType, Texture> FRUIT_SPRITES = new HashMap<>(){{
        put(ItemType.Apple, new Texture("Foragings/Fruits/Apple_Slices.png"));
    }};

    private static final Texture[] ABILITY_MENU_TEXTURES = new Texture[]{
        new Texture("Menus/abilityMenu.png"), new Texture("Menus/ability widget.png"),
        new Texture("Menus/ability widget2.png")
    };

    private final static TextureRegion[] JOJA_MART_SPRITES = generateTextureRegion(0, 820, 250, 170);
    private final static TextureRegion[] PIERRE_SPRITES = generateTextureRegion(80,176,156,146);
    private final static TextureRegion[] STARDROP_SPRITES = generateTextureRegion(240, 176, 112, 146);
    private final static TextureRegion[] FISH_STORE_AND_HOUSE_SPRITES = generateTextureRegion(384, 640, 136,164);
    private final static TextureRegion[] BLACK_SMITH_SPRITES = generateTextureRegion(128*2,0,128,176);
    private final static TextureRegion[] CARPETNER_SPRITES = generateTextureRegion(0, 0, 128, 176);
    private final static TextureRegion[] MARNIE_RANCH_SPRITES = generateTextureRegion(128, 0, 128, 176);
    public final static Texture ALEX = new Texture("Sprites/Alex/alexSprites.png");
    private final static TextureRegion[][] ALEX_TEXTURES = new TextureRegion[4][3];
    private final static HashMap<ItemType, Texture> SEED_TEXTURES = new HashMap<>(){{
        put(ItemType.MapleSeed, new Texture("Foragings/Seeds/Maple_Seed.png"));
        put(ItemType.SummerSquashSeed, new Texture("Foragings/Seeds/Summer_Squash_Seeds.png"));
        put(ItemType.RhubarbSeed, new Texture("Foragings/Seeds/Rhubarb_Seeds.png"));
        put(ItemType.StrawberrySeed, new Texture("Foragings/Seeds/Strawberry_Seeds.png"));
        put(ItemType.PumpkinSeed, new Texture("Foragings/Seeds/Pumpkin_Seeds.png"));
        put(ItemType.RedCabbageSeed, new Texture("Foragings/Seeds/Red_Cabbage_Seeds.png"));
        put(ItemType.MixedSeed, new Texture("Foragings/Seeds/Mixed_Seeds.png"));
        put(ItemType.KaleSeed, new Texture("Foragings/Seeds/Kale_Seeds.png"));
        put(ItemType.CornSeed, new Texture("Foragings/Seeds/Corn_Seeds.png"));
        put(ItemType.PineCone, new Texture("Foragings/Seeds/Pineapple_Seeds.png"));
        put(ItemType.BroccoliSeed, new Texture("Foragings/Seeds/Broccoli_Seeds.png"));
        put(ItemType.CauliFlowerSeed, new Texture("Foragings/Seeds/Cauliflower_Seeds.png"));
        put(ItemType.BokChoySeed, new Texture("Foragings/Seeds/Bok_Choy_Seeds.png"));
        put(ItemType.ArtichokeSeed, new Texture("Foragings/Seeds/Artichoke_Seeds.png"));
        put(ItemType.MysticTreeSeed, new Texture("Foragings/Seeds/Mystic_Tree_Seed.png"));
        put(ItemType.WheatSeed, new Texture("Foragings/Seeds/Wheat_Seeds.png"));
        put(ItemType.SunflowerSeed, new Texture("Foragings/Seeds/Sunflower_Seeds.png"));
        put(ItemType.SpangleSeed, new Texture("Foragings/Seeds/Spangle_Seeds.png"));
        put(ItemType.PotatoSeed, new Texture("Foragings/Seeds/Potato_Seeds.png"));
        put(ItemType.RadishSeed, new Texture("Foragings/Seeds/Radish_Seeds.png"));
        put(ItemType.MelonSeed, new Texture("Foragings/Seeds/Melon_Seeds.png"));
        put(ItemType.ParsnipSeed, new Texture("Foragings/Seeds/Parsnip_Seeds.png"));
        put(ItemType.PoppySeed, new Texture("Foragings/Seeds/Poppy_Seeds.png"));
        put(ItemType.FairySeed, new Texture("Foragings/Seeds/Fairy_Seeds.png"));
        put(ItemType.CranberrySeed, new Texture("Foragings/Seeds/Cranberry_Seeds.png"));
        put(ItemType.BeetSeed, new Texture("Foragings/Seeds/Beet_Seeds.png"));
        put(ItemType.AmaranthSeed, new Texture("Foragings/Seeds/Amaranth_Seeds.png"));
        put(ItemType.MahoganySeed, new Texture("Foragings/Seeds/Mahogany_Seed.png"));
        put(ItemType.YamSeed, new Texture("Foragings/Seeds/Yam_Seeds.png"));
        put(ItemType.TomatoSeed, new Texture("Foragings/Seeds/Tomato_Seeds.png"));
        put(ItemType.StarfruitSeed, new Texture("Foragings/Seeds/Starfruit_Seeds.png"));
        put(ItemType.PowdermelonSeed, new Texture("Foragings/Seeds/Powdermelon_Seeds.png"));
        put(ItemType.RareSeed, new Texture("Foragings/Seeds/Rare_Seed.png"));
        put(ItemType.PepperSeed, new Texture("Foragings/Seeds/Pepper_Seeds.png"));
        put(ItemType.JazzSeed, new Texture("Foragings/Seeds/Jazz_Seeds.png"));
        put(ItemType.GarlicSeed, new Texture("Foragings/Seeds/Garlic_Seeds.png"));
        put(ItemType.EggplantSeed, new Texture("Foragings/Seeds/Eggplant_Seeds.png"));
        put(ItemType.CarrotSeed, new Texture("Foragings/Seeds/Carrot_Seeds.png"));
        put(ItemType.BlueberrySeed, new Texture("Foragings/Seeds/Blueberry_Seeds.png"));
        put(ItemType.AncientSeed, new Texture("Foragings/Seeds/Ancient_Seed.png"));
        put(ItemType.TulipBulb, new Texture("Foragings/Seeds/Tulip_Bulb.png"));
        put(ItemType.AppleSapling, new Texture("Foragings/Seeds/Apple_Sapling.png"));
        put(ItemType.ApricotSapling, new Texture("Foragings/Seeds/Apricot_Sapling.png"));
        put(ItemType.Acorns, new Texture("Foragings/Seeds/Apricot_Sapling.png"));
        put(ItemType.BananaSapling, new Texture("Foragings/Seeds/Banana_Sapling.png"));
        put(ItemType.CherrySapling, new Texture("Foragings/Seeds/Cherry_Sapling.png"));
        put(ItemType.MangoSapling, new Texture("Foragings/Seeds/Mango_Sapling.png"));
        put(ItemType.OrangeSapling, new Texture("Foragings/Seeds/Orange_Sapling.png"));
        put(ItemType.PeachSapling, new Texture("Foragings/Seeds/Peach_Sapling.png"));
        put(ItemType.PomegranateSapling, new Texture("Foragings/Seeds/Pomegranate_Sapling.png"));
    }} ;

    private final static HashMap<ItemType, HashMap<ToolLevel, Texture>> TOOL_SPRITES = new HashMap<>(){{
        put(ItemType.Axe, new HashMap<>(){{
            put(ToolLevel.normal, new Texture("Tools/Axe/Axe.png"));
            put(ToolLevel.copper, new Texture("Tools/Axe/Copper_Axe.png"));
            put(ToolLevel.iron, new Texture("Tools/Axe/Steel_Axe.png"));
            put(ToolLevel.gold, new Texture("Tools/Axe/Gold_Axe.png"));
            put(ToolLevel.iridium, new Texture("Tools/Axe/Iridium_Axe.png"));
        }});
        put(ItemType.Pickaxe, new HashMap<>(){{
            put(ToolLevel.normal, new Texture("Tools/Pickaxe/Pickaxe.png"));
            put(ToolLevel.copper, new Texture("Tools/Pickaxe/Copper_Pickaxe.png"));
            put(ToolLevel.iron, new Texture("Tools/Pickaxe/Steel_Pickaxe.png"));
            put(ToolLevel.gold, new Texture("Tools/Pickaxe/Gold_Pickaxe.png"));
            put(ToolLevel.iridium, new Texture("Tools/Pickaxe/Iridium_Pickaxe.png"));
        }});
        put(ItemType.Hoe, new HashMap<>(){{
            put(ToolLevel.normal, new Texture("Tools/Hoe/Hoe.png"));
            put(ToolLevel.copper, new Texture("Tools/Hoe/Copper_Hoe.png"));
            put(ToolLevel.iron, new Texture("Tools/Hoe/Steel_Hoe.png"));
            put(ToolLevel.gold, new Texture("Tools/Hoe/Gold_Hoe.png"));
            put(ToolLevel.iridium, new Texture("Tools/Hoe/Iridium_Hoe.png"));
        }});
        put(ItemType.MilkPail, new HashMap<>(){{
            put(ToolLevel.normal, new Texture("Tools/Milk_Pail.png"));
        }});
        put(ItemType.Scythe, new HashMap<>(){{
            put(ToolLevel.normal, new Texture("Tools/Scythe.png"));
        }});
        put(ItemType.Shear, new HashMap<>(){{
            put(ToolLevel.normal, new Texture("Tools/Shears.png"));
        }});
        put(ItemType.WateringCan, new HashMap<>(){{
            put(ToolLevel.normal, new Texture("Tools/Watering_Can/Watering_Can.png"));
            put(ToolLevel.copper, new Texture("Tools/Watering_Can/Copper_Watering_Can.png"));
            put(ToolLevel.iron, new Texture("Tools/Watering_Can/Steel_Watering_Can.png"));
            put(ToolLevel.gold, new Texture("Tools/Watering_Can/Gold_Watering_Can.png"));
            put(ToolLevel.iridium, new Texture("Tools/Watering_Can/Iridium_Watering_Can.png"));
        }});
        put(ItemType.FishingPole, new HashMap<>(){{
            put(ToolLevel.normal, new Texture("Tools/Fishing_Pole/Training_Rod.png"));
            put(ToolLevel.bamboo, new Texture("Tools/Fishing_Pole/Bamboo_Pole.png"));
            put(ToolLevel.fiberglass, new Texture("Tools/Fishing_Pole/Fiberglass_Rod.png"));
            put(ToolLevel.iridium, new Texture("Tools/Fishing_Pole/Iridium_Rod.png"));
        }});
    }};

    private final static HashMap<ItemType, Texture[]> FORAGING_TREE_SPRITES = new HashMap<>(){{
        put(ItemType.MapleTree, new Texture[]{new Texture("Foragings/Trees/Maple_Stage_5.png"),
        new Texture("Foragings/Trees/Maple_stump_Spring.png")});
        put(ItemType.MahoganyTree, new Texture[]{new Texture("Foragings/Trees/Mahogany_Stage_5.png"),
            new Texture("Foragings/Trees/Mahogany_stump_Spring.png")} );
        put(ItemType.MushroomTree, new Texture[]{new Texture("Foragings/Trees/MushroomTree_Stage_5.png"),
            new Texture("Foragings/Trees/MushroomTree_Stage_5.png")});
        put(ItemType.PineTree,  new Texture[]{new Texture("Foragings/Trees/Pine_Stage_5.png"),
            new Texture("Foragings/Trees/Pine_stump_Spring.png")});
    }};

    private final static HashMap<ItemType, Texture[]> TREE_TEXTURES = new HashMap<>(){{
        put(ItemType.AppleTree, new Texture[]{
            new Texture("Foragings/Trees/AppleTreeLightning.png"), new Texture("Foragings/Trees/Apple_Sapling.png"),
            new Texture("Foragings/Trees/Apple_Stage_2.png"), new Texture("Foragings/Trees/Apple_Stage_4.png")
        , new Texture("Foragings/Trees/Apple_Stage_5.png"), new Texture("Foragings/Trees/Apple_Stage_5_Fruit.png")});
        put(ItemType.OrangeTree, new Texture[]{
            new Texture("Foragings/Trees/OrangeTreeLightning.png"), new Texture("Foragings/Trees/Orange_Sapling.png"),
            new Texture("Foragings/Trees/Orange_Stage_2.png"), new Texture("Foragings/Trees/Orange_Stage_4.png")
            , new Texture("Foragings/Trees/Orange_Stage_5.png"), new Texture("Foragings/Trees/Orange_Stage_5_Fruit.png")});
        put(ItemType.BananaTree, new Texture[]{
            new Texture("Foragings/Trees/BananaTreeLightning.png"), new Texture("Foragings/Trees/Banana_Sapling.png"),
            new Texture("Foragings/Trees/Banana_Stage_2.png"), new Texture("Foragings/Trees/Banana_Stage_4.png")
            , new Texture("Foragings/Trees/Banana_Stage_5.png"), new Texture("Foragings/Trees/Banana_Stage_5_Fruit.png")});
        put(ItemType.MangoTree, new Texture[]{
            new Texture("Foragings/Trees/MangoTreeLightning.png"), new Texture("Foragings/Trees/Mango_Sapling.png"),
            new Texture("Foragings/Trees/Mango_Stage_2.png"), new Texture("Foragings/Trees/Mango_Stage_4.png")
            , new Texture("Foragings/Trees/Mango_Stage_5.png"), new Texture("Foragings/Trees/Mango_Stage_5_Fruit.png")});
        put(ItemType.ApricotTree, new Texture[]{
            new Texture("Foragings/Trees/ApricotTreeLightning.png"), new Texture("Foragings/Trees/Apricot_Sapling.png"),
            new Texture("Foragings/Trees/Apricot_Stage_2.png"), new Texture("Foragings/Trees/Apricot_Stage_4.png")
            , new Texture("Foragings/Trees/Apricot_Stage_5.png"), new Texture("Foragings/Trees/Apricot_Stage_5_Fruit.png")});
        put(ItemType.PeachTree, new Texture[]{
            new Texture("Foragings/Trees/PeachTreeLightning.png"), new Texture("Foragings/Trees/Peach_Sapling.png"),
            new Texture("Foragings/Trees/Peach_Stage_2.png"), new Texture("Foragings/Trees/Peach_Stage_4.png")
            , new Texture("Foragings/Trees/Peach_Stage_5.png"), new Texture("Foragings/Trees/Peach_Stage_5_Fruit.png")});
        put(ItemType.MysticTree, new Texture[]{
            new Texture("Foragings/Trees/Mystic_Tree_Stump.png"), new Texture("Foragings/Trees/Mystic_Tree_Stage_1.png"),
            new Texture("Foragings/Trees/Mystic_Tree_Stage_2.png"), new Texture("Foragings/Trees/Mystic_Tree_Stage_3.png")
            , new Texture("Foragings/Trees/Mystic_Tree_Stage_4.png"), new Texture("Foragings/Trees/Mystic_Tree_Stage_5.png")});
        put(ItemType.PomegranateTree, new Texture[]{
            new Texture("Foragings/Trees/PomegranateTreeLightning.png"), new Texture("Foragings/Trees/Pomegranate_Sapling.png"),
            new Texture("Foragings/Trees/Pomegranate_Stage_2.png"), new Texture("Foragings/Trees/Pomegranate_Stage_4.png")
            , new Texture("Foragings/Trees/Pomegranate_Stage_5.png"), new Texture("Foragings/Trees/Pomegranate_Stage_5_Fruit.png")});
        put(ItemType.OakTree, new Texture[]{
            new Texture("Foragings/Trees/Oak_stump_Spring.png"), new Texture("Foragings/Trees/Oak_Stage_1.png"),
            new Texture("Foragings/Trees/Oak_Stage_2.png"), new Texture("Foragings/Trees/Oak_Stage_3.png")
            , new Texture("Foragings/Trees/Oak_Stage_4.png"), new Texture("Foragings/Trees/Oak_Stage_5.png")});
        put(ItemType.CherryTree, new Texture[]{
            new Texture("Foragings/Trees/CherryTreeLightning.png"), new Texture("Foragings/Trees/Cherry_Sapling.png"),
            new Texture("Foragings/Trees/Cherry_Stage_2.png"), new Texture("Foragings/Trees/Cherry_Stage_4.png")
            , new Texture("Foragings/Trees/Cherry_Stage_5.png"), new Texture("Foragings/Trees/Cherry_Stage_5_Fruit.png")});
    }};

    private static final ArrayList<Texture> BUFF_SPRITES = new ArrayList<>(){{
        add(new Texture("Buffs/Farming_Skill_Icon.png"));
        add(new Texture("Buffs/Fishing_Skill_Icon.png"));
        add(new Texture("Buffs/Foraging_Skill_Icon.png"));
        add(new Texture("Buffs/Max_Energy_Buff.png"));
        add(new Texture("Buffs/Mining_Skill_Icon.png"));
    }};

    private static final Texture BACKGROUND_SPRITE = new Texture("background.png");


    private static final HashMap<ItemType, Texture> MINERAL_SPRITES = new HashMap<>(){{
        put(ItemType.Amethyst, new Texture("Foragings/Minerals/Amethyst.png"));
        put(ItemType.Aquamarine, new Texture("Foragings/Minerals/Aquamarine.png"));
        put(ItemType.CopperOre, new Texture("Foragings/Minerals/Copper_Ore.png"));
        put(ItemType.Diamond, new Texture("Foragings/Minerals/Diamond.png"));
        put(ItemType.EarthCrystal, new Texture("Foragings/Minerals/Earth_Crystal.png"));
        put(ItemType.Emerald, new Texture("Foragings/Minerals/Emerald.png"));
        put(ItemType.Fiber, new Texture("Foragings/Minerals/Fiber.png"));
        put(ItemType.FireQuartz, new Texture("Foragings/Minerals/Fire_Quartz.png"));
        put(ItemType.FrozenTear, new Texture("Foragings/Minerals/Frozen_Tear.png"));
        put(ItemType.GoldOre, new Texture("Foragings/Minerals/Gold_Ore.png"));
        put(ItemType.IridiumOre, new Texture("Foragings/Minerals/Iridium_Ore.png"));
        put(ItemType.IronOre, new Texture("Foragings/Minerals/Iron_Ore.png"));
        put(ItemType.Jade, new Texture("Foragings/Minerals/Jade.png"));
        put(ItemType.PrismaticShard, new Texture("Foragings/Minerals/Prismatic_Shard.png"));
        put(ItemType.Quartz, new Texture("Foragings/Minerals/Quartz.png"));
        put(ItemType.Ruby, new Texture("Foragings/Minerals/Ruby.png"));
        put(ItemType.Stone, new Texture("Foragings/Minerals/Stone.png"));
        put(ItemType.Topaz, new Texture("Foragings/Minerals/Topaz.png"));
        put(ItemType.Wood, new Texture("Foragings/Minerals/Wood.png"));
    }};
    static {
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                int k =j;
                if(j==3)
                    k =2;
                ALEX_TEXTURES[i][k] = new TextureRegion(ALEX, 22+j*16, 16+32*i, 16, 32);
            }
        }
    }

    private static TextureRegion[] generateTextureRegion(int x, int y, int w, int h){
        return new TextureRegion[]{
            new TextureRegion(BUILDINGS_IN_DIFFERENT_SEASONS[0], x, y, w, h),
            new TextureRegion(BUILDINGS_IN_DIFFERENT_SEASONS[1], x, y, w, h),
            new TextureRegion(BUILDINGS_IN_DIFFERENT_SEASONS[2], x, y, w, h),
            new TextureRegion(BUILDINGS_IN_DIFFERENT_SEASONS[3], x, y, w, h)
        };
    }

    private static HashMap<ItemType, Texture> COOPS_SPRITES = new HashMap<>(){{
        put(ItemType.NormalCoop, new Texture("CoopAndBarn/Coop.png"));
        put(ItemType.NormalBarn, new Texture("CoopAndBarn/Barn.png"));
        put(ItemType.BigCoop, new Texture("CoopAndBarn/Big_Coop.png"));
        put(ItemType.DeluxeCoop, new Texture("CoopAndBarn/Deluxe_Coop.png"));
        put(ItemType.BigBarn, new Texture("CoopAndBarn/Big_Barn.png"));
        put(ItemType.DeluxeBarn, new Texture("CoopAndBarn/Deluxe_Barn.png"));
    }};

    private static final HashMap<ItemType, TextureRegion[][]> ANIMAL_SPRITES = new HashMap<>(){{
        put(ItemType.Dino, new TextureRegion[4][4]);
        put(ItemType.Duck, new TextureRegion[4][4]);
        put(ItemType.Goat, new TextureRegion[4][4]);
        put(ItemType.Pig, new TextureRegion[4][4]);
        put(ItemType.Rabbit, new TextureRegion[4][4]);
        put(ItemType.Sheep, new TextureRegion[4][4]);
        put(ItemType.Hen, new TextureRegion[4][4]);
        put(ItemType.Cow, new TextureRegion[4][4]);
    }};

    private static final HashMap<ItemType, Texture> SHOW_ANIMALS = new HashMap<>(){{
        put(ItemType.Dino, new Texture("Animals/ShowAnimals/Dinosaur.png"));
        put(ItemType.Duck, new Texture("Animals/ShowAnimals/Duck.png"));
        put(ItemType.Goat, new Texture("Animals/ShowAnimals/Goat.png"));
        put(ItemType.Pig, new Texture("Animals/ShowAnimals/Pig.png"));
        put(ItemType.Rabbit, new Texture("Animals/ShowAnimals/Rabbit.png"));
        put(ItemType.Sheep, new Texture("Animals/ShowAnimals/Sheep.png"));
        put(ItemType.Hen, new Texture("Animals/ShowAnimals/White_Chicken.png"));
        put(ItemType.Cow, new Texture("Animals/ShowAnimals/White_Cow.png"));
    }};

    static {
        for(ItemType itemType : ANIMAL_SPRITES.keySet()){
            if(itemType.equals(ItemType.Hen)){
                for(int i = 0; i< 4; i++) {
                    int height = 16*i;
                    if(i == 3)
                        height +=48;
                    for(int j =0; j<4; j++){
                        ANIMAL_SPRITES.get(itemType)[i][j] =new TextureRegion(new Texture("Animals/Chicken_White.png"),
                            16*j, height, 16, 16);
                    }
                }
            }
            if(itemType.equals(ItemType.Cow)){
                int[] heights = new int[]{32, 32, 64, 0};
                for(int i = 0; i< 4; i++) {
                    for(int j =0; j<4; j++){
                        ANIMAL_SPRITES.get(itemType)[i][j] =new TextureRegion(new Texture("Animals/Cow_White.png"),
                            32*j, heights[i], 32, 32);
                    }
                }
            }
            if(itemType.equals(ItemType.Dino)){
                int[] heights = new int[]{48, 16, 32, 0};
                for(int i = 0; i< 4; i++) {
                    for(int j =0; j<4; j++){
                        ANIMAL_SPRITES.get(itemType)[i][j] =new TextureRegion(new Texture("Animals/Dinosaur.png"),
                            16*j, heights[i], 16, 16);
                    }
                }
            }
            if(itemType.equals(ItemType.Duck)){
                int[] heights = new int[]{0, 16, 192, 208};
                for(int i = 0; i< 4; i++) {
                    for(int j =0; j<4; j++){
                        ANIMAL_SPRITES.get(itemType)[i][j] =new TextureRegion(new Texture("Animals/Duck.png"),
                            16*j, heights[i], 16, 16);
                    }
                }
            }
            if(itemType.equals(ItemType.Goat)){
                int[] heights = new int[]{32, 32, 64, 0};
                for(int i = 0; i< 4; i++) {
                    for(int j =0; j<4; j++){
                        ANIMAL_SPRITES.get(itemType)[i][j] =new TextureRegion(new Texture("Animals/Goat.png"),
                            32*j, heights[i], 32, 32);
                    }
                }
            }
            if(itemType.equals(ItemType.Pig)){
                int[] heights = new int[]{32, 32, 64, 0};
                for(int i = 0; i< 4; i++) {
                    for(int j =0; j<4; j++){
                        ANIMAL_SPRITES.get(itemType)[i][j] =new TextureRegion(new Texture("Animals/Pig.png"),
                            32*j, heights[i], 32, 32);
                    }
                }
            }
            if(itemType.equals(ItemType.Rabbit)){
                int[] heights = new int[]{48, 16, 32, 0};
                for(int i = 0; i< 4; i++) {
                    for(int j =0; j<4; j++){
                        ANIMAL_SPRITES.get(itemType)[i][j] =new TextureRegion(new Texture("Animals/Rabbit.png"),
                            16*j, heights[i], 16, 16);
                    }
                }
            }
            if(itemType.equals(ItemType.Sheep)){
                int[] heights = new int[]{32, 32, 64, 0};
                for(int i = 0; i< 4; i++) {
                    for(int j =0; j<4; j++){
                        ANIMAL_SPRITES.get(itemType)[i][j] =new TextureRegion(new Texture("Animals/Sheep.png"),
                            32*j, heights[i], 32, 32);
                    }
                }
            }
        }
    }

    private static final HashMap<ItemType, Texture> ANIMAL_PRODUCTS_TEXTURE = new HashMap<>(){{
        put(ItemType.DinoEgg, new Texture("AnimalProducts/Dinosaur_Egg.png"));
        put(ItemType.DuckEgg, new Texture("AnimalProducts/Duck_Egg.png"));
        put(ItemType.DuckFeather, new Texture("AnimalProducts/Duck_Feather.png"));
        put(ItemType.Egg, new Texture("AnimalProducts/Egg.png"));
        put(ItemType.GoatMilk, new Texture("AnimalProducts/Goat_Milk.png"));
        put(ItemType.BigEgg, new Texture("AnimalProducts/Large_Egg.png"));
        put(ItemType.BigGoatMilk, new Texture("AnimalProducts/Large_Goat_Milk.png"));
        put(ItemType.BigCowMilk, new Texture("AnimalProducts/Large_Milk.png"));
        put(ItemType.CowMilk, new Texture("AnimalProducts/Milk.png"));
        put(ItemType.RabbitLeg, new Texture("AnimalProducts/Rabbit%27s_Foot.png"));
        put(ItemType.Truffle, new Texture("AnimalProducts/Truffle.png"));
        put(ItemType.SheepWool, new Texture("AnimalProducts/Wool.png"));
        put(ItemType.RabbitWool, new Texture("AnimalProducts/Wool.png"));
    }};

    private final static HashMap<ItemType, Texture> FISH_SPRITES = new HashMap<>(){{
        put(ItemType.Angler, new Texture("Fishes/Angler.png"));
        put(ItemType.BlueDiscus, new Texture("Fishes/Blue_Discus.png"));
        put(ItemType.CrimsonFish, new Texture("Fishes/Crimsonfish.png"));
        put(ItemType.Dorado, new Texture("Fishes/Dorado.png"));
        put(ItemType.Flounder, new Texture("Fishes/Flounder.png"));
        put(ItemType.GhostFish, new Texture("Fishes/Ghostfish.png"));
        put(ItemType.GlacierFish, new Texture("Fishes/Glacierfish.png"));
        put(ItemType.Herring, new Texture("Fishes/Herring.png"));
        put(ItemType.Legend, new Texture("Fishes/Legend.png"));
        put(ItemType.Lionfish, new Texture("Fishes/Lionfish.png"));
        put(ItemType.MidnightCarp, new Texture("Fishes/Midnight_Carp.png"));
        put(ItemType.Perch, new Texture("Fishes/Perch.png"));
        put(ItemType.RainbowTrout, new Texture("Fishes/Rainbow_Trout.png"));
        put(ItemType.Salmon, new Texture("Fishes/Salmon.png"));
        put(ItemType.Sardine, new Texture("Fishes/Sardine.png"));
        put(ItemType.Shad, new Texture("Fishes/Shad.png"));
        put(ItemType.Squid, new Texture("Fishes/Squid.png"));
        put(ItemType.Sunfish, new Texture("Fishes/Sunfish.png"));
        put(ItemType.Tilapia, new Texture("Fishes/Tilapia.png"));
        put(ItemType.Tuna, new Texture("Fishes/Tuna.png"));
    }};

    private static final TextureRegion[] WATER_SPRITES = new TextureRegion[]{
        new TextureRegion(new Texture("Tiles/88633.png"), 64,80, 16, 16),
        new TextureRegion(new Texture("Tiles/88633.png"), 112,80, 16, 16),
        new TextureRegion(new Texture("Tiles/88633.png"), 80,96, 16, 16),
    };

    private static final Texture SHIPPING_BIN_SPRITE = new Texture("Mini-Shipping_Bin.png");

    private static final Texture HEART_SPRITE = new Texture("Heart.png");

    public static TextureRegion[] getWaterSprites(){
        return WATER_SPRITES;
    }

    public static Texture getHeartSprite(){
        return HEART_SPRITE;
    }

    public static HashMap<ItemType, Texture> getFishSprites(){
        return FISH_SPRITES;
    }

    public static HashMap<ItemType, Texture> getShowAnimals(){
        return SHOW_ANIMALS;
    }

    public static HashMap<ItemType, Texture> getAnimalProductsTexture(){
        return ANIMAL_PRODUCTS_TEXTURE;
    }
    public static TextureRegion[] getJojaMartSprites(){
        return JOJA_MART_SPRITES;
    }

    public static Texture getFullEnergySprite(){
        return FULL_ENERGY_SPRITE;
    }

    public static Texture getEmptyEnergyBarSprite(){
        return EMPTY_ENERGY_BAR_SPRITE;
    }

    public static TextureRegion[] getPierreSprites(){
        return PIERRE_SPRITES;
    }

    public static HashMap<ItemType, Texture[]> getTreeTextures(){
        return TREE_TEXTURES;
    }

    public static HashMap<ItemType, Texture> getSeedTextures(){
        return SEED_TEXTURES;
    }

    public static HashMap<ItemType, Texture> getMineralSprites(){
        return MINERAL_SPRITES;
    }

    public static Texture[] getGreenhouseSprite(){
        return GREENHOUSE_SPRITES;
    }



    public static TextureRegion[] getStardropSprites(){
        return STARDROP_SPRITES;
    }

    public static HashMap<ItemType, HashMap<ToolLevel, Texture>> getToolSprites(){
        return TOOL_SPRITES;
    }

    public static TextureRegion[] getFishStoreSprites(){
        return FISH_STORE_AND_HOUSE_SPRITES;
    }

    public static TextureRegion[] getBlackSmithSprites(){
        return BLACK_SMITH_SPRITES;
    }

    public static Texture getBackgroundSprite(){
        return BACKGROUND_SPRITE;
    }

    public static Texture getLakeTexture(){
        return LAKE_TEXTURE;
    }

    public static HashMap<ItemType, Texture[]> getForagingTreeSprites(){
        return FORAGING_TREE_SPRITES;
    }

    public static Texture[] getAbilityMenuTextures(){
        return ABILITY_MENU_TEXTURES;
    }

    public static TextureRegion[] getCarpetnerSprites(){
        return CARPETNER_SPRITES;
    }

    public static HashMap<ItemType, Texture> getCraftableSprites(){
        return CRAFTABLE_SPRITES;
    }


    public static TextureRegion[] getMarnieRanchSprites(){
        return MARNIE_RANCH_SPRITES;
    }

    public static HashMap<ItemType, Texture> getItemsSprites(){
        return ITEMS_SPRITES;
    }

    public static HashMap<ItemType, Texture> getFruitSprites(){
        return FRUIT_SPRITES;
    }

    public static HashMap<ItemType, Texture> getFoodSprites(){
        return FOOD_SPRITES;
    }

    public static ArrayList<Texture> getBuffSprites(){
        return BUFF_SPRITES;
    }

    public static HashMap<ItemType, Texture> getCoopsSprites(){
        return COOPS_SPRITES;
    }

    public static HashMap<ItemType, TextureRegion[][]> getAnimalSprites(){
        return ANIMAL_SPRITES;
    }

    public static Texture getShippingBinSprite(){
        return SHIPPING_BIN_SPRITE;
    }

    public static Texture[] getInventorySprites(){
        return INVENTORY_SPRITES;
    }

    public static Texture[] getTilesTextures(){
        return TILES_TEXTURES;
    }
    public static TextureRegion[][] getAlexTextures(){
        return ALEX_TEXTURES;
    }
}
