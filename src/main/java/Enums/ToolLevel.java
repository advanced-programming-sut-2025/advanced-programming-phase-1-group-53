package Enums;

public enum ToolLevel {
    normal(1),
    iron(3),
    gold(4),
    copper(2),
    iridium(5),
    bamboo(1),
    fiberglass(3);
    private final int level;
    ToolLevel(int level) {
        this.level = level;
    }
    public int getLevel(){
        return level;
    }
}
