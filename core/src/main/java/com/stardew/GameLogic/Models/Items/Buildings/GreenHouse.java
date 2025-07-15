package com.stardew.GameLogic.Models.Items.Buildings;

import com.stardew.GameLogic.Enums.MapsNames;
import com.stardew.GameLogic.Models.Position;

public class GreenHouse extends Building {
    private boolean isBuild = false;

    public GreenHouse(Position position) {
        super(position);
        this.mapsName = MapsNames.GreenHouse;
        this.buildGreenhouse();
    }

    public void build() {}
    public void letsBuildGreenhouse() {
        this.isBuild = true;
    }
    public boolean isBuild() {
        return isBuild;
    }
}
