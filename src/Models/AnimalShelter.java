package Models;

import Enums.CoopAndBarnType;
import Enums.CoopOrBarn;

import java.util.ArrayList;

public class AnimalShelter {
    private final CoopOrBarn type;
    private final CoopAndBarnType level;
    private final Position position;
    private final ArrayList<Animal> animals = new ArrayList<>();

    public AnimalShelter(CoopOrBarn type, CoopAndBarnType level, Position position) {
        this.type = type;
        this.level = level;
        this.position = position;
    }

    public CoopOrBarn getType() {
        return type;
    }

    public CoopAndBarnType getLevel() {
        return level;
    }

    public Position getPosition() {
        return position;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }
}
