package Models;

import Enums.TileKind;
import Models.Items.Item;

public class Tile {
    private Position position;
    private TileKind tileKind;
    private Item item;

    public Tile(Position position, TileKind tileKind, Item item) {
        this.position = position;
        this.tileKind = tileKind;
        this.item = item;
    }
}
