package Models.Abilities;

import Enums.ItemType;
import Models.Game.App;
import Models.Items.Foragings.ForagingMineral;
import Models.Items.Foragings.Plant;
import Models.Items.Foragings.PlantAbleCrop;
import Models.Items.Foragings.Tree;
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
                if(plantAbleCrop.getNotWateredDays() == 2){
                    MessageManager.getMessage(Result.failure(plantAbleCrop.getItemType().name() + " at" +plantAbleCrop.getPosition().getX()+", "
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



    public void plowing(){

    }
    public void plant(ItemType itemType, int x, int y){
        Item item = App.getGame().getItemByItemType(itemType);
        if(item instanceof Plant && ! (item instanceof ForagingMineral)){
            if(App.getGame().getCurrentPlayer().backpack.getItems().get(item) > 0) {
                Plant plant = (Plant) App.getGame().getItemByItemType(itemType).clone();
                plant.getPosition().setX(x);
                plant.getPosition().setY(y);
                plantedPlants.add(plant);
                App.getGame().getCurrentPlayer().backpack.getItems().compute(item, (key, oldVal) -> (oldVal-1));
            }
            else{
                MessageManager.getMessage(Result.failure("Not enough of this seed in backpack."));
            }
        }
        else{
            MessageManager.getMessage(Result.failure("No such plant exists."));
        }
    }
    public void watering(){
        for(Plant plant : plantedPlants){
            if(plant instanceof PlantAbleCrop){
                PlantAbleCrop plantAbleCrop = (PlantAbleCrop) plant;
                plantAbleCrop.setNotWateredDays(0);
                plantedPlants.set(plantedPlants.indexOf(plant), plantAbleCrop);
            }
            if(plant instanceof Tree){
                Tree tree = (Tree) plant;
                tree.setNotWateredDays(0);
                plantedPlants.set(plantedPlants.indexOf(plant), tree);
            }
        }
    }
    public void fertilizing(){

    }

    public void crowAttack(){
        if(App.getGame().dateAndTime.getHour() == 0){
            ArrayList<Plant> removedProducts = new ArrayList<>();
            Random random = new Random();
            for(int i = 0; i < (plantedPlants.size()/16); i++){
                if(random.nextInt(4) == 0){
                    int j = random.nextInt(16);
                    j = Math.min(16*i+j, plantedPlants.size());
                    removedProducts.add(plantedPlants.get(j));
                }
            }
            for(Plant plant: removedProducts){
                plantedPlants.remove(plant);
            }
        }
    }

    public void showCraftInfo(ItemType itemType){
        if(App.getGame().getItemByItemType(itemType) instanceof Plant){
            MessageManager.getMessage(Result.success(( (Plant) App.getGame().getItemByItemType(itemType)).details()));
        }
    }

    public void showPlant(int x, int y){
        for(Plant plant : plantedPlants){
            if(plant.getPosition().getX() == x
            && plant.getPosition().getY() == y){
                MessageManager.getMessage(Result.success(plant.details()));
            }
            else
                MessageManager.getMessage(Result.failure("No plant with this coordinates."));
        }
    }

    public void harvesting(int x, int y){
        ArrayList<Plant> removedProducts = new ArrayList<>();
        for(Plant plant: plantedPlants){
            if(plant.getPosition().isHere(x, y)){
                App.getGame().getCurrentPlayer().backpack.getItems().put(plant,
                        App.getGame().getCurrentPlayer().backpack.getItems().getOrDefault(plant, 0) + 1);
                removedProducts.add(plant);
                MessageManager.getMessage(Result.success(plant.getItemType().name() + "harvested successfully."));
            }
        }
        for(Plant plant : removedProducts)
            plantedPlants.remove(plant);
        if(removedProducts.isEmpty())
            MessageManager.getMessage(Result.failure("Nothing to harvest here."));
    }
}
