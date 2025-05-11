package Models;

import Enums.TileKind;
import Models.Items.Item;

public class Tile {
    private final Position position;
    private TileKind tileKind;
    private Item item = null;

    public Tile(Position position, TileKind tileKind) {
        this.position = position;
        this.tileKind = tileKind;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Position getPosition() {
        return position;
    }

    public TileKind getTileKind() {
        return tileKind;
    }

    public void setTileKind(TileKind tileKind) {
        this.tileKind = tileKind;
    }
}
