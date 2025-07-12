package com.stardew.Models.Items.Buildings;

import com.stardew.Enums.MapsNames;
import com.stardew.Models.Position;

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
