package Models.Items.Buildings;

import Enums.MapsNames;
import Models.Position;

public class House extends Building{
    public House(Position position) {
        super(position);
        this.mapsName = MapsNames.House;
        this.buildHouse();
    }
    // getMapsName() is inherited from Building
}
