package Models.Items;

import Enums.ItemType;
import Models.Position;

public class Item {
    protected final ItemType itemType;
    protected final Position position = new Position(0, 0, 0, 0);

    public Item clone(){
        return new Item(itemType);
    }
    public Item(ItemType itemType){
        this.itemType = itemType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Position getPosition() {
        return position;
    }
}
