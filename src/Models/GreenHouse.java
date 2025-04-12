package Models;

public class GreenHouse extends Building {
    private boolean isBuild = false;
    private final GreenhouseFarming farming = new GreenhouseFarming();

    public GreenHouse(Position position, Position doorPosition) {
        super(position, doorPosition);
    }

    public void build() {}
}
