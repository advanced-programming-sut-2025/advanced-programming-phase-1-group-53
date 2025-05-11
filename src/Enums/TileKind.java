package Enums;

public enum TileKind {
    structure(false, false),
    empty(true,true),
    wood(false, true),
    rock(false, true),
    tree(false, true),
    grass(true, true),
    plantation(true, true),
    wall(false, false);

    private final boolean isWalkable;
    private final boolean isChangeable;

    TileKind(boolean isWalkable, boolean isChangeable) {
        this.isWalkable = isWalkable;
        this.isChangeable = isChangeable;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public boolean isChangeable() {
        return isChangeable;
    }
}
