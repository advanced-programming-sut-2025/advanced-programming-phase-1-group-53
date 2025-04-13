package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class House extends Building{
    private final HashMap<Food, Integer> refrigerator = new HashMap<>();
    private final HashMap<Machine, Integer> myMachines = new HashMap<>();

    public House(Position position, Position doorPosition) {
        super(position, doorPosition);
    }
}
