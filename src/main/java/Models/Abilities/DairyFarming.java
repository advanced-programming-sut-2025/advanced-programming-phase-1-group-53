package Models.Abilities;

import Enums.ItemType;
import Enums.WeatherType;
import Models.Game.App;
import Models.Game.Game;
import Models.Items.Animal;
import Models.Items.AnimalProduct;
import Models.Items.Item;
import Models.MessageManager;
import Models.Result;

public class DairyFarming {
    //TODO : build method inside somewhere else

    //TODO : buy animal method somewhere else

    public void pet(String name){
        Animal animal = App.getGame().getCurrentPlayer().backpack.getAnimalByName(name);
        if(animal == null){
            MessageManager.getMessage(Result.failure("No animal with such name."));
            return;
        }

        if(!areNextToEachOther(animal.getPosition().getX(), animal.getPosition().getY(),
                App.getGame().getCurrentPlayer().position.getX(), App.getGame().getCurrentPlayer().position.getY())){
            MessageManager.getMessage(Result.failure("You must be next to the animal to pet it."));
            return;
        }

        animal.updateFriendship(15);
        MessageManager.getMessage(Result.success("friendship increased by 15."));
    }

    public void cheatSetFriendship(String name, int amount){
        Animal animal = App.getGame().getCurrentPlayer().backpack.getAnimalByName(name);
        if(animal == null){
            MessageManager.getMessage(Result.failure("No animal with such name."));
            return;
        }

        animal.updateFriendship(amount);
        MessageManager.getMessage(Result.success("friendship updated."));
    }

    public void animalsShowDetails(){
        for(Animal animal : App.getGame().getCurrentPlayer().backpack.getAnimals()){
            MessageManager.getMessage(Result.success("Name : " + animal.getName() + "\nFriendship : " + animal.getFriendship() +
                    "\n isFedToday? " + (animal.isFedToday() ? "Yes" : "No") + "\n is Petted Today?" +
                    (animal.isPettedToday()? "Yes" : "No") + "\n ------------"));
        }
        if(App.getGame().getCurrentPlayer().backpack.getAnimals().isEmpty())
            MessageManager.getMessage(Result.failure("No animal available."));
    }

    public  void shepherdAnimal(String name, int x, int y){
        if(App.getGame().weather.getWeather().equals(WeatherType.SNOWY) ||
                App.getGame().weather.getWeather().equals(WeatherType.STORMY) ||
                App.getGame().weather.getWeather().equals(WeatherType.RAINY)){
            MessageManager.getMessage(Result.success("Animals can't move when snowy, rainy or stormy."));
            return;
        }
        Animal animal = App.getGame().getCurrentPlayer().backpack.getAnimalByName(name);
        if(animal == null){
            MessageManager.getMessage(Result.failure("No animal with such name."));
            return;
        }
        if(App.getGame().findTile(x, y).getTileKind().isWalkable()) {
            animal.changePlace(x, y);
            MessageManager.getMessage(Result.success("Animal is being shepherd."));
        }
        else{
            MessageManager.getMessage(Result.failure("The Animal can't go there."));
        }
    }

    public void showNotCollectedProducts(){
        StringBuilder stringBuilder;
        for(Animal animal : App.getGame().getCurrentPlayer().backpack.getAnimals()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Name : " + animal.getName());
            stringBuilder.append("\nRemained Products: \n");
            for (AnimalProduct animalProduct : animal.getProducedProducts()) {
                stringBuilder.append("Prod Name : "+animalProduct.getItemType().name() +"Price : "+ animalProduct.getBaseSellPrice());
            }
            MessageManager.getMessage(Result.success(stringBuilder.toString()));
        }
    }

    public void feed(String name){
        Animal animal = App.getGame().getCurrentPlayer().backpack.getAnimalByName(name);
        if(animal == null){
            MessageManager.getMessage(Result.failure("No animal with such name."));
            return;
        }

        Item hay = App.getGame().getItemByItemType(ItemType.Hay);
        if(!App.getGame().getCurrentPlayer().backpack.areItemsAvailable(hay, 1)){
            MessageManager.getMessage(Result.failure("There is no hay in inventory."));
            return;
        }
        App.getGame().getCurrentPlayer().backpack.getItems().compute(hay, (k, v) -> v-1);
        animal.setFedToday(true);

    }


    public void collectProduct(String name){
        Animal animal = App.getGame().getCurrentPlayer().backpack.getAnimalByName(name);
        if(animal == null){
            MessageManager.getMessage(Result.failure("No animal with such name."));
            return;
        }

        animal.collectProducts();
    }

    public void sellAnimal(String name){
        Animal animal = App.getGame().getCurrentPlayer().backpack.getAnimalByName(name);
        if(animal == null){
            MessageManager.getMessage(Result.failure("No animal with such name."));
            return;
        }

        App.getGame().getCurrentPlayer().backpack.getAnimals().remove(animal);
        App.getGame().getCurrentPlayer().personalInfo.updateGold((int)(animal.getBaseSellPrice() * ((double) animal.getFriendship()/1000 + 0.3)));
    }

    public boolean areNextToEachOther(int x1, int y1, int x2, int y2){
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        for(int i = 0; i<4; i++){
            if(dx[i] + x1 == x2 && dy[i] + y1 == y2)
                return true;
        }
        return false;
    }
}
