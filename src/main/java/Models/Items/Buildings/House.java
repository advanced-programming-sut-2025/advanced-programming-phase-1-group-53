package Models.Items.Buildings;

import Enums.MapsNames;
import Models.Items.Refrigerator;
import Models.Position;

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
