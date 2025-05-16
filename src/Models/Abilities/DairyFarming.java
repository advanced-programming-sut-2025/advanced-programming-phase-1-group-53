package Models.Abilities;

import Models.Game.App;
import Models.Game.Game;
import Models.Items.Animal;
import Models.Items.AnimalProduct;
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

        if(/*! next to the animal*/true){
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
        Animal animal = App.getGame().getCurrentPlayer().backpack.getAnimalByName(name);
        if(animal == null){
            MessageManager.getMessage(Result.failure("No animal with such name."));
            return;
        }
        animal.changePlace(x, y);
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
}
