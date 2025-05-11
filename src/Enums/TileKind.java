package Enums;

public enum TileKind {
    structure(false, false),
    empty(true,true),
    grass(true, true),
    plantation(true, true),
    wall(false, false),
    asphalt(true, false),
    plowed(true, true),
    door(true, false);

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
