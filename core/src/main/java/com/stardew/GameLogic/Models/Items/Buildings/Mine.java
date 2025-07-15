package com.stardew.GameLogic.Models.Items.Buildings;

import com.stardew.GameLogic.Enums.MapsNames;
import com.stardew.GameLogic.Models.Position;

public class Mine extends Building {
    public Mine(Position position) {
        super(position);
        this.mapsName = MapsNames.Mine;
        this.buildMine();
    }
    // getMapsName() is inherited from Building
}
