package com.stardew.Models.Items.Buildings;

import com.stardew.Enums.MapsNames;
import com.stardew.Models.Items.Refrigerator;
import com.stardew.Models.Position;

public class House extends Building{
    private final Refrigerator refrigerator;
    public House(Position position) {
        super(position);
        this.mapsName = MapsNames.House;
        this.buildHouse();
        refrigerator = new Refrigerator();
    }

    public Refrigerator getRefrigerator() {
        return refrigerator;
    }
// getMapsName() is inherited from Building
}
