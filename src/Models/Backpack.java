package Models;

import Enums.BackpackLevel;
import Enums.Fish;

import java.util.ArrayList;
import java.util.HashMap;

public class Backpack {
    private final ArrayList<Tool> tools;
    private final HashMap<Fish, Integer> fishes;
    // TODO بثیه چیز های مثل ماهی
    private BackpackLevel level;

    public Backpack() {
        this.tools = new ArrayList<>();
        this.level = BackpackLevel.small;
        this.fishes = new HashMap<>();
    }

    public ArrayList<Tool> getTools() {
        return tools;
    }

    public BackpackLevel getLevel() {
        return level;
    }

    public void setLevel(BackpackLevel level) {
        this.level = level;
    }

    public HashMap<Fish, Integer> getFishes() {
        return fishes;
    }

    public void addToBackPack(Fish fish) {}

    public void addToBackPack(Tool tool) {}
}
