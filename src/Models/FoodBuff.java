package Models;

import Enums.ItemType;
import Models.Game.App;

import java.util.ArrayList;

public class FoodBuff {
    private int hoursLeft = 0;
    private ItemType food;
    public final int[] energyReductions = new int[4];

    public void espressoBuff(){
        if(food.equals(ItemType.TripleShotEspresso) && hoursLeft > 0)
            App.getGame().getCurrentPlayer().energy.updateEnergy(100);
        if(hoursLeft == 0){
            App.getGame().getCurrentPlayer().energy.setMaxEnergy(200);
        }
    }

    public void redPlateBuff(){
        if(food.equals(ItemType.RedPlate) && hoursLeft > 0)
            App.getGame().getCurrentPlayer().energy.updateEnergy(50);
        if(hoursLeft == 0){
            App.getGame().getCurrentPlayer().energy.setMaxEnergy(200);
        }
    }

    public void hashBrownBuff(){
        if(food.equals(ItemType.HashBrowns) && hoursLeft > 0)
            energyReductions[2] = 1;
        if(hoursLeft == 0)
            energyReductions[2] = 0;
    }
    public void pancakeBuff(){
        if(food.equals(ItemType.HashBrowns) && hoursLeft > 0)
            energyReductions[3] = 1;
        if(hoursLeft == 0)
            energyReductions[3] = 0;
    }
    public void farmersLunchBuff(){
        if(food.equals(ItemType.HashBrowns) && hoursLeft > 0)
            energyReductions[2] = 1;
        if(hoursLeft == 0)
            energyReductions[2] = 0;
    }
    public void survivalBurgerBuff(){
        if(food.equals(ItemType.HashBrowns) && hoursLeft > 0)
            energyReductions[3] = 1;
        if(hoursLeft == 0)
            energyReductions[3] = 0;
    }
    public void dishOTheSeaBuff(){
        if(food.equals(ItemType.HashBrowns) && hoursLeft > 0)
            energyReductions[1] = 1;
        if(hoursLeft == 0)
            energyReductions[1] = 0;
    }
    public void seaFormPuddingBuff(){
        if(food.equals(ItemType.HashBrowns) && hoursLeft > 0)
            energyReductions[1] = 1;
        if(hoursLeft == 0)
            energyReductions[1] = 0;
    }
    public void minersTreatBuff(){
        if(food.equals(ItemType.HashBrowns) && hoursLeft > 0)
            energyReductions[0] = 1;
        if(hoursLeft == 0)
            energyReductions[0] = 0;
    }

    public void activateBuff(ItemType itemType){
        food = itemType;
        hoursLeft = switch (itemType){
            case HashBrowns -> 5;
            case Pancakes -> 11;
            case RedPlate -> 3;
            case TripleShotEspresso -> 5;
            case FarmersLunch -> 5;
            case SurvivalBurger -> 5;
            case DishOTheSea -> 5;
            case SeaFormPudding -> 10;
            case MinersTreat -> 5;
            default -> 0;
        };
    }

    public void update(){
        hoursLeft = (int)Math.max(0, hoursLeft - App.getGame().dateAndTime.getPassedHours());
        switch (food){
            case HashBrowns -> hashBrownBuff();
            case Pancakes -> pancakeBuff();
            case RedPlate -> redPlateBuff();
            case TripleShotEspresso -> espressoBuff();
            case FarmersLunch -> farmersLunchBuff();
            case SurvivalBurger -> survivalBurgerBuff();
            case DishOTheSea -> dishOTheSeaBuff();
            case SeaFormPudding -> seaFormPuddingBuff();
            case MinersTreat -> minersTreatBuff();
        }
    }
}
