package Models.Abilities;

import Enums.*;
import Models.Game.App;
import Models.Game.Game;
import Models.Items.Fish;
import Models.Items.Tool;
import Models.MessageManager;
import Models.Result;

import java.util.ArrayList;
import java.util.Random;

public class Fishing {

    public void fishing(){
        if(!App.getGame().getCurrentPlayer().backpack.getItemInHand().getItemType().equals(ItemType.FishingPole)){
            MessageManager.getMessage(Result.failure("You're not handling a fishing pole."));
        }
        double mIndex = switch (App.getGame().weather.getWeather()){
            case SUNNY -> 1.5;
            case RAINY -> 1.2;
            case STORMY -> 0.5;
            case SNOWY -> 1;
            default -> 1;
        };
        Tool pole = (Tool) App.getGame().getCurrentPlayer().backpack.getItemInHand();
        double poleCoeff = switch (pole.getLevel()){
            case bamboo -> 0.5;
            case normal -> 0.1;
            case fiberglass -> 0.9;
            case iridium -> 1.2;
            default -> 0;
        };
        int skill = App.getGame().getCurrentPlayer().abilities.getFishingLevel();
        double quality = new Random().nextFloat(0, 1) * (skill + 2) * poleCoeff / (7-mIndex);
        double qualityCoeff = 1;
        if(quality > 0)
            qualityCoeff = 1;
        if(quality > 0.5)
            qualityCoeff = 1.25;
        if(quality > 0.7)
            qualityCoeff = 1.5;
        if(quality > 0.9)
            qualityCoeff = 2;
        int quantity =(int) Math.ceil(new Random().nextDouble(0, 1) * mIndex * (skill + 2));
        Fish fish;
        ArrayList<Fish> currentSeason = switch (App.getGame().dateAndTime.getSeason()){
            case SPRING -> Fish.SpringFishes;
            case SUMMER -> Fish.SummerFishes;
            case FALL -> Fish.FallFishes;
            case WINTER -> Fish.WinterFishes;
            default -> null;
        };
        if(currentSeason == null){
            MessageManager.getMessage(Result.failure("haucv"));
        }
        for(int i = 0; i< quantity; i++){
            while (true){
                fish = currentSeason.get(new Random().nextInt(5));
                if(fish.getFishKind().equals(FishKind.normal) || App.getGame().getCurrentPlayer().abilities.getFishingLevel() == 4){
                    break;
                }
            }
            App.getGame().getCurrentPlayer().backpack.addItem(fish.clone().makeSellPrice(fish.getBaseSellPrice() * qualityCoeff));
        }
    }
}
