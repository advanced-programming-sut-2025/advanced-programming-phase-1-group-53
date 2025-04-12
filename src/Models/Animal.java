package Models;

import Enums.AnimalType;

public class Animal {
    private final AnimalType animalType;
    private final String name;
    private Position position;
    private int friendship = 0;
    private int hungry = 0;

    public Animal(AnimalType animalType, String name, Position position) {
        this.animalType = animalType;
        this.name = name;
        this.position = position;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getFriendship() {
        return friendship;
    }

    public void setFriendship(int friendship) {
        this.friendship = friendship;
    }

    public int getHungry() {
        return hungry;
    }

    public void setHungry(int hungry) {
        this.hungry = hungry;
    }

    public int calculateProduct() {
        int product = 0;
        // TODO
        return product;
    }

    public void walk() {}
}
