package com.stardew.GameLogic.Models;

import com.stardew.GameLogic.Enums.TileKind;
import com.stardew.GameLogic.Models.Game.App;
import com.stardew.GameLogic.Models.Items.Item;

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

    public void pickItem(){
        if(item == null){
            System.out.println("jsdbjhbs");
            return;
        }
        App.getGame().getCurrentPlayer().backpack.addItem(App.getGame().getItemByItemType(item.getItemType()));
        item = null;
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
