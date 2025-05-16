package Models.Items.CraftAbleAndArtisan;

import Enums.ItemType;
import Models.Game.App;
import Models.Items.CraftingRecipe;
import Models.Items.Fish;
import Models.Items.Item;
import Models.MessageManager;
import Models.Result;

import java.util.*;

public class Artisan extends CraftAble {
    private final ArrayList<ArtisanGood> products = new ArrayList<>();
    private Artisan(ItemType itemType, ArrayList<ArtisanGood> products, CraftingRecipe craftingRecipe){
        super(itemType, craftingRecipe);
        this.products.addAll(products);
    }

    public ArrayList<ArtisanGood> getProducts() {
        return products;
    }

    public void useArtisan(List<ItemType> ingredients){
        if(itemType.equals(ItemType.BeeHouse) && ingredients.isEmpty()){
            App.getGame().getCurrentPlayer().backpack.addItem(ArtisanGood.Honey.clone());
            MessageManager.getMessage(Result.success("Artisan processing Honey"));
            return;
        }
        for(ArtisanGood artisanGood : products){
            HashSet<ItemType> newSet = new HashSet<>();
            for(Item item : artisanGood.getIngredients().keySet())
                newSet.add(item.getItemType());
            if(newSet.equals(new HashSet<>(ingredients))){
                if(!App.getGame().getCurrentPlayer().backpack.areItemsAvailable(artisanGood.getIngredients()))
                    MessageManager.getMessage(Result.failure("Not enough material(useArtisan)"));
                App.getGame().getCurrentPlayer().backpack.addItem(artisanGood.clone());
                for(Item item : artisanGood.getIngredients().keySet()){
                    App.getGame().getCurrentPlayer().backpack.getItems().compute(item, (k, v)->(v-artisanGood.getIngredients().get(item)));
                }
                MessageManager.getMessage(Result.success("Artisan processing "+artisanGood.getItemType().name()));
                return;
            }
        }
    }

    @Override
    public Artisan clone(){
        return new Artisan(getItemType(), products, craftingRecipe);
    }



    public static final Artisan BeeHouse = new Artisan(ItemType.BeeHouse, ArtisanGood.BeeHouseGoods, CraftingRecipe.BeeHouseCR);
    public static final Artisan Keg = new Artisan(ItemType.Keg, ArtisanGood.KegGoods, CraftingRecipe.KegCR);
    public static final Artisan Dehydrator = new Artisan(ItemType.Dehydrator, ArtisanGood.DehydratorGoods, CraftingRecipe.DehydratorCR);
    public static final Artisan CharCoalKlin = new Artisan(ItemType.CharcoalKlin, ArtisanGood.CharCoalKlinGoods, CraftingRecipe.CharCoalKlinCR);
    public static final Artisan CheesePress = new Artisan(ItemType.CheesePress, ArtisanGood.CheesePressGoods, CraftingRecipe.CheesePressCR);
    public static final Artisan MayonnaiseMachine = new Artisan(ItemType.MayonnaiseMachine, ArtisanGood.MayonnaiseMachineGoods, CraftingRecipe.MayonnaiseMachineCR);
    public static final Artisan Loom = new Artisan(ItemType.Loom, ArtisanGood.LoomGoods, CraftingRecipe.LoomCR);
    public static final Artisan OilMaker = new Artisan(ItemType.OilMaker, ArtisanGood.OilMakerGoods, CraftingRecipe.OilMakerCR);
    public static final Artisan PreservesJar = new Artisan(ItemType.PreservesJar, ArtisanGood.PreservesJarGoods, CraftingRecipe.PreservesJarCR);
    public static final Artisan FishSmoker = new Artisan(ItemType.FishSmoker, ArtisanGood.FishSmokerGoods, CraftingRecipe.FishSmokerCR);
    public static final Artisan Furnace = new Artisan(ItemType.Furnace, ArtisanGood.FurnaceGoods, CraftingRecipe.FurnaceCR);

    public static final ArrayList<Artisan> allArtisan = new ArrayList<>(){{
        add(BeeHouse);
        add(Keg);
        add(Dehydrator);
        add(CharCoalKlin);
        add(CheesePress);
        add(MayonnaiseMachine);
        add(Loom);
        add(OilMaker);
        add(PreservesJar);
        add(FishSmoker);
        add(Furnace);
    }};
}

/*
Set setOfIngred = new HashSet(ingredients);
        if(setOfIngred.equals(Set.of(ItemType.Wheat))){
            if(!App.getGame().getCurrentPlayer().backpack.areItemsAvailable(Map.of(App.getGame().getItemByItemType(ItemType.Wheat), 1))){
                MessageManager.getMessage(Result.failure("Not enough material."));
                return;
            }
            App.getGame().getCurrentPlayer().backpack.getItems().compute(App.getGame().getItemByItemType(ItemType.Wheat), (k,v) -> (v-1));
            App.getGame().getCurrentPlayer().backpack.addItem(App.getGame().getItemByItemType(ItemType.Beer));
        }
        if(setOfIngred.equals(Set.of(ItemType.Rice))){
            if(!App.getGame().getCurrentPlayer().backpack.areItemsAvailable(Map.of(App.getGame().getItemByItemType(ItemType.Rice), 1))){
                MessageManager.getMessage(Result.failure("Not enough material."));
                return;
            }
            App.getGame().getCurrentPlayer().backpack.getItems().compute(App.getGame().getItemByItemType(ItemType.Rice), (k,v) -> (v-1));
            App.getGame().getCurrentPlayer().backpack.addItem(App.getGame().getItemByItemType(ItemType.Vinegar));
        }
        if(setOfIngred.equals(Set.of(ItemType.CoffeeBean))){
            if(!App.getGame().getCurrentPlayer().backpack.areItemsAvailable(Map.of(App.getGame().getItemByItemType(ItemType.CoffeeBean), 1))){
                MessageManager.getMessage(Result.failure("Not enough material."));
                return;
            }
            App.getGame().getCurrentPlayer().backpack.getItems().compute(App.getGame().getItemByItemType(ItemType.CoffeeBean), (k,v) -> (v-1));
            App.getGame().getCurrentPlayer().backpack.addItem(App.getGame().getItemByItemType(ItemType.Coffee));
        }
        if(ingredients.size() == 1 && App.getGame().getItemByItemType(ingredients.get(0)) instanceof PlantAbleCrop){
            if(!App.getGame().getCurrentPlayer().backpack.areItemsAvailable(Map.of(App.getGame().getItemByItemType(ingredients.get(0)), 1))){
                MessageManager.getMessage(Result.failure("Not enough material."));
                return;
            }
            App.getGame().getCurrentPlayer().backpack.getItems().compute(App.getGame().getItemByItemType(ingredients.get(0)), (k,v) -> (v-1));
            //TODO:App.getGame().getCurrentPlayer().backpack.addItem(App.getGame().getItemByItemType(ItemType.Juice));
        }
        if(setOfIngred.equals(Set.of(ItemType.Honey))){
            if(!App.getGame().getCurrentPlayer().backpack.areItemsAvailable(Map.of(App.getGame().getItemByItemType(ItemType.Honey), 1))){
                MessageManager.getMessage(Result.failure("Not enough material."));
                return;
            }
            App.getGame().getCurrentPlayer().backpack.getItems().compute(App.getGame().getItemByItemType(ItemType.Honey), (k,v) -> (v-1));
            App.getGame().getCurrentPlayer().backpack.addItem(App.getGame().getItemByItemType(ItemType.Mead));
        }
        if(setOfIngred.equals(Set.of(ItemType.Hops))){
            if(!App.getGame().getCurrentPlayer().backpack.areItemsAvailable(Map.of(App.getGame().getItemByItemType(ItemType.Hops), 1))){
                MessageManager.getMessage(Result.failure("Not enough material."));
                return;
            }
            App.getGame().getCurrentPlayer().backpack.getItems().compute(App.getGame().getItemByItemType(ItemType.Hops), (k,v) -> (v-1));
            App.getGame().getCurrentPlayer().backpack.addItem(App.getGame().getItemByItemType(ItemType.PaleAle));
        }
        if(ingredients.size() == 1 && App.getGame().getItemByItemType(ingredients.get(0)) instanceof Fruit){
            if(!App.getGame().getCurrentPlayer().backpack.areItemsAvailable(Map.of(App.getGame().getItemByItemType(ingredients.get(0)), 1))){
                MessageManager.getMessage(Result.failure("Not enough material."));
                return;
            }
            App.getGame().getCurrentPlayer().backpack.getItems().compute(App.getGame().getItemByItemType(ingredients.get(0)), (k,v) -> (v-1));
            //TODO:App.getGame().getCurrentPlayer().backpack.addItem(App.getGame().getItemByItemType(ItemType.Wine));
        }
 */
