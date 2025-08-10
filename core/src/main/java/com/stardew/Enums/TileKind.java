package com.stardew.Enums;

public enum TileKind {
    structure(false, false),
    house(false, false),
    builtGreenhouse(true, true),
    greenhouse(false, true),
    mine(true, true),
    lake(false, false),
    empty(true,true),
    grass(true, true),
    coop(false, true),
    shippingBin(false, true),
    water(false, false),
    sand(true, true),
    shore(true, false),
    lightWater(false, false),
    veryLightWater(false, false),
    artisan(false, false),
    plantation(true, true),
    foraging(false, true),
    foragingMineral(false, true),
    wall(false, false),
    wateredPlowed(true, true),
    soiledPlowed(true, true),
    asphalt(true, false),
    plowed(true, true),
    door(true, false),
    shop(false, false),
    NPC(false, false);

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
