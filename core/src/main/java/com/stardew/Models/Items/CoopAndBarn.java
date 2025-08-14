package com.stardew.Models.Items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Enums.ItemType;
import com.stardew.Main;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.MessageManager;
import com.stardew.Models.Result;
import com.stardew.Views.Tab;
import com.stardew.Views.TabMenus.CoopMenu;

import java.util.ArrayList;

public class CoopAndBarn extends Item{
    private int capacity;
    private final ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Animal> outAnimals = new ArrayList<>();
    private CoopMenu coopMenu;

    private CoopAndBarn(ItemType itemType){
        super(itemType);
        capacity = switch (itemType){
            case NormalBarn, NormalCoop -> 4;
            case DeluxeBarn, DeluxeCoop -> 8;
            case BigBarn, BigCoop -> 12;
            default -> 0;
        };
    }

    @Override
    public void update(float delta){
        for(Animal animal : animals){
            animal.update(delta);
        }
    }

    public ArrayList<Animal> getOutAnimals() {
        return outAnimals;
    }

    public boolean hasCapacity(){
        if(animals.size()>= capacity)
            return false;
        return true;
    }

    public void initializeForClient(){
        coopMenu=new CoopMenu();
    }

    public void setUpCoopMenu(){
        coopMenu.setChanged(true);
        coopMenu.setAnimals(animals);
        Main.main.setScreen(coopMenu);
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    @Override
    public Sprite getSprite(){
        float x = -1;
        float y = -1;
        if(sprite != null){
            x = sprite.getX();
            y = sprite.getY();
        }
        sprite = new Sprite(GameAssetManager.getCoopsSprites().get(itemType));
        if( x!= -1){
            sprite.setPosition(x, y);
        }
        return sprite;
    }

    @Override
    public CoopAndBarn clone(){
        return new CoopAndBarn(itemType);
    }

    public CoopAndBarn upgrade(ItemType itemType){
        if(this.itemType.name().contains("Coop")){
            this.itemType = itemType;
            capacity = switch (itemType){
                case NormalCoop -> 4;
                case DeluxeCoop -> 8;
                case BigCoop -> 12;
                default -> 0;
            };
        }
        if(this.itemType.name().contains("Barn")) {
            this.itemType = itemType;
            capacity = switch (itemType) {
                case NormalBarn -> 4;
                case DeluxeBarn -> 8;
                case BigBarn -> 12;
                default -> 0;
            };
        }
        return this;
    }

    public CoopAndBarn addAnimal(Animal animal){
        if(animals.size() == capacity){
            MessageManager.getMessage(Result.failure("Not enough capacity in the barn."));
            return this;
        }
        animals.add(animal);
        return this;
    }

    public CoopAndBarn removeAnimal(String name){
        Animal removedAnimal = null;
        for(Animal animal : animals){
            if(animal.getName().equals(name)){
                removedAnimal = animal;
                break;
            }
        }
        if(removedAnimal != null){
            animals.remove(removedAnimal);
            MessageManager.getMessage(Result.success("Animal removed from the barn successfully."));
            return this;
        }
        MessageManager.getMessage(Result.failure("No animal with such name in the barn."));
        return this;
    }

    public static final CoopAndBarn Barn= new CoopAndBarn(ItemType.NormalBarn);
    public static final CoopAndBarn Coop = new CoopAndBarn(ItemType.NormalCoop);
    public static final CoopAndBarn DeluxeBarn= new CoopAndBarn(ItemType.DeluxeBarn);
    public static final CoopAndBarn DeluxeCoop = new CoopAndBarn(ItemType.DeluxeCoop);
    public static final CoopAndBarn BigBarn= new CoopAndBarn(ItemType.BigBarn);
    public static final CoopAndBarn BigCoop = new CoopAndBarn(ItemType.BigCoop);


    public static final ArrayList<CoopAndBarn> COOP_AND_BARN = new ArrayList<>(){{
        add(Coop);
        add(Barn);
        add(DeluxeBarn);
        add(DeluxeCoop);
        add(BigBarn);
        add(BigCoop);
    }};
}
