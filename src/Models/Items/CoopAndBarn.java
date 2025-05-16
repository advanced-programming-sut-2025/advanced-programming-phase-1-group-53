package Models.Items;

import Enums.ItemType;
import Models.MessageManager;
import Models.Result;

import java.util.ArrayList;

public class CoopAndBarn extends Item{
    private int capacity;
    private final ArrayList<Animal> animals = new ArrayList<>();

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

    public static final ArrayList<CoopAndBarn> COOP_AND_BARN = new ArrayList<>(){{
        add(Coop);
        add(Barn);
    }};
}
