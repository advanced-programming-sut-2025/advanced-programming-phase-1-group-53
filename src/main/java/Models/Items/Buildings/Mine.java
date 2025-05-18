package Models.Items.Buildings;

import Enums.MapsNames;
import Models.Position;

public class Mine extends Building {
    public Mine(Position position) {
        super(position);
        this.mapsName = MapsNames.Mine;
        this.buildMine();
    }
    // getMapsName() is inherited from Building
}
