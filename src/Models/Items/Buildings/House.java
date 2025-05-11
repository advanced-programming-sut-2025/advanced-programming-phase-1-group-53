package Models.Items.Buildings;

import Models.Position;

public class House extends Building{
    public House(Position position) {
        super(position);
        this.buildHouse();
    }
}
