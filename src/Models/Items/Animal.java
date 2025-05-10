package Models.Items;

import Enums.AnimalType;
import Enums.ItemType;
import Models.Position;

public class Animal extends Item{
    private final Position position;
    private int friendship = 0;
    private int hunger = 0;
    private final int cycleOfProduce;
    private int daysToProduce;

    public Animal(ItemType itemType, Position position, int cycleOfProduce) {
        super(itemType);
        this.position = position;
        this.cycleOfProduce = cycleOfProduce;
    }

    public int getFriendship() {
        return friendship;
    }

    public void setFriendship(int friendship) {
        this.friendship = friendship;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hungry) {
        this.hunger = hungry;
    }

    public void update(){
        if(){
            daysToProduce --;
        }
        if(daysToProduce == 0){
            //TODO : place the product
            daysToProduce = cycleOfProduce;
        }
    }

    public void walk() {}
}
