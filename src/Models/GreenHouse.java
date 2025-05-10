package Models;

public class GreenHouse extends Building {
    private boolean isBuild = false;

    public GreenHouse(Position position, Position doorPosition) {
        super(position, doorPosition);
    }

    public void build() {}
}
