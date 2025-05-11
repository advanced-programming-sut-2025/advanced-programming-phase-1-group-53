package Models.Items.Buildings;

import Models.Position;

public class GreenHouse extends Building {
    private boolean isBuild = false;

    public GreenHouse(Position position) {
        super(position);
        this.buildGreenhouse();
    }

    public void build() {}
}
