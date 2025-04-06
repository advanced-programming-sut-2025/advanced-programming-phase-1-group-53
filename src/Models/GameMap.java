package Models;

import java.util.ArrayList;

public class GameMap {
    private final ArrayList<ArrayList<Tile>> map;

    private void generateRandomChangeableItems() {

    }

    public GameMap() {
        this.map = new ArrayList<>();
    }

    public ArrayList<ArrayList<Tile>> getMap() {
        return map;
    }
}
