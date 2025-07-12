package com.stardew.Models.Items.Buildings;

import com.stardew.Enums.MapsNames;
import com.stardew.Models.Position;

public class Mine extends Building {
    public Mine(Position position) {
        super(position);
        this.mapsName = MapsNames.Mine;
        this.buildMine();
    }
    // getMapsName() is inherited from Building
}
