package Models.Items.Buildings;

import Enums.MapsNames;
import Models.Position;

public class GreenHouse extends Building {
    private boolean isBuild = false;

    public GreenHouse(Position position) {
        super(position);
        this.mapsName = MapsNames.GreenHouse;
        this.buildGreenhouse();
    }

    public void build() {}
    public void buildGreenhouse() {
        this.isBuild = true;
    }
    public boolean isBuild() {
        return isBuild;
    }
    // getMapsName() is inherited from Building
}
