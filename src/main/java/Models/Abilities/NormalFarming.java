package Models.Abilities;

import Enums.ItemType;
import Enums.TileKind;
import Models.Game.App;
import Models.Items.Foragings.*;
import Models.Items.Item;
import Models.MessageManager;
import Models.Result;

import java.util.ArrayList;
import java.util.Random;

public class NormalFarming{
    private final ArrayList<Plant> plantedPlants = new ArrayList<>();


    public ArrayList<Plant> getPlantedPlants() {
        return plantedPlants;
    }

    public void update(){
        ArrayList<Plant> removedProducts = new ArrayList<>();
        for(int i =0 ; i< plantedPlants.size(); i++){
            if(plantedPlants.get(i) instanceof PlantAbleCrop plantAbleCrop){
                plantAbleCrop.update();
                if(plantAbleCrop.getNotWateredDays() >= 2){
                    MessageManager.getMessage(Result.failure(plantAbleCrop.getItemType().name() + " at " +plantAbleCrop.getPosition().getX()+", "
                            + plantAbleCrop.getPosition().getY()+ " dried out because of lack of water"));
                    removedProducts.add(plantAbleCrop);
                }
                plantedPlants.set(i, plantAbleCrop);
            }
            if(plantedPlants.get(i) instanceof Tree tree){
                tree.update();
                if(tree.getNotWateredDays() == 2){
                    MessageManager.getMessage(Result.failure(tree.getItemType().name() + " at" +tree.getPosition().getX()+", "
                            + tree.getPosition().getY()+ " dried out because of lack of water"));
                    removedProducts.add(tree);
                }
                plantedPlants.set(i, tree);
            }
        }
        for(Plant plant : removedProducts){
            plantedPlants.remove(plant);
        }
    }


    public void plant(ItemType itemType, int x, int y){
        if(!App.getGame().findTile(x, y).getTileKind().equals(TileKind.plowed)){
            MessageManager.getMessage(Result.failure("The chosen tile must be plowed."));
        }
        Item item = App.getGame().getItemByItemType(itemType);
        if(item instanceof ForagingSeed){
            if(App.getGame().getCurrentPlayer().backpack.getItems().get(item) > 0) {
                Plant plant = (Plant) App.getGame().getItemByItemType(itemType).clone();
                for(PlantAbleCrop crop : PlantAbleCrop.allPlantAbleCrops){
                    if(crop.getSourceSeed().getItemType().equals(plant.getItemType())) {
                        Plant plant1 = crop.clone();
                        plant1.getPosition().setX(x);
                        plant1.getPosition().setY(y);
                        plantedPlants.add(plant1);
                        App.getGame().findTile(x, y).setItem(plant1);
                    }
                }

                for(Tree crop: Tree.allTrees){
                    if(crop.getSource().getItemType().equals(plant.getItemType())) {
                        Plant plant1 = crop.clone();
                        plant1.getPosition().setX(x);
                        plant1.getPosition().setY(y);
                        plantedPlants.add(plant1);
                        App.getGame().findTile(x, y).setItem(plant1);
                    }
                }

                App.getGame().getCurrentPlayer().backpack.getItems().compute(item, (key, oldVal) -> (oldVal-1));
                MessageManager.getMessage(Result.success(plantedPlants.get(0).getItemType().name() + " was planted successfully."));
            }
            else{
                MessageManager.getMessage(Result.failure("Not enough of this seed in backpack."));
                return;
            }
        }
        else{
            MessageManager.getMessage(Result.failure("No such plant exists."));
            return;
        }
    }
    public void water(int x, int y){
        if(App.getGame().findTile(x, y) == null){
            MessageManager.getMessage(Result.failure("sjaciuas"));
            return;
        }
        if(App.getGame().findTile(x, y).getItem() instanceof Plant) {
            Plant plant =(Plant) App.getGame().findTile(x, y).getItem();
            if (plant instanceof PlantAbleCrop plantAbleCrop) {
                plantAbleCrop.setNotWateredDays(0);
                plantedPlants.set(plantedPlants.indexOf(plant), plantAbleCrop);
            }
            if (plant instanceof Tree) {
                Tree tree = (Tree) plant;
                tree.setNotWateredDays(0);
                plantedPlants.set(plantedPlants.indexOf(plant), tree);
            }
            //MessageManager.getMessage(Result.success("Watered successfully."));
        }
    }

    public void fertilize(ItemType itemType, int x, int y){
        if(App.getGame().getItemByItemType(itemType) == null){
            MessageManager.getMessage(Result.failure("shqu"));
            return;
        }
        if(App.getGame().findTile(x, y).getItem() == null){
            MessageManager.getMessage(Result.failure("No item here."));
            return;
        }
        if(!(App.getGame().findTile(x, y).getItem() instanceof Tree || App.getGame().findTile(x, y).getItem() instanceof PlantAbleCrop)){
            MessageManager.getMessage(Result.failure(App.getGame().findTile(x, y).getItem().getItemType().name()));
            return;
        }
        Plant plant =(Plant) App.getGame().findTile(x, y).getItem();
        Item fertilizer = App.getGame().getItemByItemType(itemType);
        if(! App.getGame().getCurrentPlayer().backpack.areItemsAvailable(fertilizer, 1)){
            MessageManager.getMessage(Result.failure("No fertilizer in inventory."));
            return;
        }
        if(fertilizer.getItemType().equals(ItemType.SpeedGro)){
            plant.setHasSpeed(true);
            App.getGame().getCurrentPlayer().backpack.getItems().compute(fertilizer, (k, v) -> (v-1));
            MessageManager.getMessage(Result.success(plant.getItemType().name() + " was fertilized by " + fertilizer.getItemType().name()));
            return;
        }
        if(fertilizer.getItemType().equals(ItemType.DeluxeSoil)){
            plant.setHasDeluxe(true);
            App.getGame().getCurrentPlayer().backpack.getItems().compute(fertilizer, (k, v) -> (v-1));
            MessageManager.getMessage(Result.success(plant.getItemType().name() + " was fertilized by " + fertilizer.getItemType().name()));
            return;
        }
        MessageManager.getMessage(Result.success("The plant was not fertilized."));
    }

    public void crowAttack(){
        if(App.getGame().dateAndTime.isADayPassed()){
            ArrayList<Plant> removedProducts = new ArrayList<>();
            Random random = new Random();
            for(int i = 0; i <= plantedPlants.size()/16; i++){
                if(random.nextInt(4) == 0){
                    int j = random.nextInt(16);
                    j = Math.min(16*i+j, plantedPlants.size()-1);
                    removedProducts.add(plantedPlants.get(j));
                }
            }
            for(Plant plant: removedProducts){
                plantedPlants.remove(plant);
                App.getGame().findTile(plant.getPosition().getX(), plant.getPosition().getY()).setItem(null);
                MessageManager.getMessage(Result.success("Plant " + plant.getItemType() + " at " + plant.getPosition().getX()
                 + ", " + plant.getPosition().getY() + " was destroyed by crows."));
            }
        }
    }

    public void showCraftInfo(ItemType itemType){
        if(App.getGame().getItemByItemType(itemType) instanceof Plant){
            MessageManager.getMessage(Result.success(( (Plant) App.getGame().getItemByItemType(itemType)).details()));
        }
    }

    public void showPlants(int x, int y){
        for(Plant plant : plantedPlants){
            if(plant.getPosition().getX() == x
            && plant.getPosition().getY() == y){
                MessageManager.getMessage(Result.success(plant.getItemType().name()));
            }
            else
                MessageManager.getMessage(Result.failure("No plant with this coordinates."));
        }
    }

    public void harvest(int x, int y){
        if(App.getGame().getCurrentPlayer().backpack.isInventoryFull()){
            MessageManager.getMessage(Result.failure("Inventory is full."));
            return;
        }
        ArrayList<Plant> removedProducts = new ArrayList<>();
        for(Plant plant: plantedPlants){
            if(plant.getPosition().isHere(x, y)){
                if(!(plant instanceof PlantAbleCrop plantAbleCrop && plantAbleCrop.isReadyForHarvest()))
                    return;
                if(!(plant instanceof Tree tree && tree.isReadyForHarvest()))
                    return;
                if(plant instanceof Tree treee){
                    treee.regrow();
                    plantedPlants.set(plantedPlants.indexOf(plant),treee);
                    if(treee.getRemainingHarvestCycle() == 0)
                        removedProducts.add(treee);
                }
                if(plant instanceof PlantAbleCrop plantAbleCrop1){
                    plantAbleCrop1.regrow();
                    plantedPlants.set(plantedPlants.indexOf(plant),plantAbleCrop1);
                    if(plantAbleCrop1.getRemainingGrowthTimes() == 0)
                        removedProducts.add(plantAbleCrop1);
                }
                App.getGame().getCurrentPlayer().backpack.addItem(App.getGame().getItemByItemType(plant.getItemType()));
            }
        }
        for(Plant plant : removedProducts) {
            plantedPlants.remove(plant);
            App.getGame().findTile(x, y).setItem(null);
            MessageManager.getMessage(Result.success(plant.getItemType().name() + "harvested successfully."));
        }
        if(removedProducts.isEmpty())
            MessageManager.getMessage(Result.failure("Nothing to harvest here."));
    }
}
