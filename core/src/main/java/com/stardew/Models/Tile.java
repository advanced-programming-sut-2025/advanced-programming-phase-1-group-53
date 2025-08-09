package com.stardew.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Enums.Season;
import com.stardew.Enums.TileKind;
import com.stardew.Enums.WeatherType;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.Items.Item;

public class Tile {
    private Sprite sprite = new Sprite();
    private final Position position;
    private TileKind tileKind;
    private Item item = null;

    public Tile(Position position, TileKind tileKind) {
        this.position = position;
        this.tileKind = tileKind;
        if(tileKind.equals(TileKind.grass))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[0],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.wall))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[1],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.asphalt))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[2],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.shippingBin))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[2],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.structure))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[0],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.shop))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[2],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.NPC))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[1],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.mine))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[3],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.wateredPlowed))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[6],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.soiledPlowed))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[7],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.plowed))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[4],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else
            sprite = new Sprite(GameAssetManager.getTilesTextures()[0],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
    }

    public Sprite getSprite() {
        if(tileKind.equals(TileKind.grass)) {
            if(App.getGame().dateAndTime.getSeason().equals(Season.WINTER))
                sprite = new Sprite(GameAssetManager.getTilesTextures()[5], GameMap.getTilePrintSize(), GameMap.getTilePrintSize());
            else
                sprite = new Sprite(GameAssetManager.getTilesTextures()[0], GameMap.getTilePrintSize(), GameMap.getTilePrintSize());
        }
        else if(tileKind.equals(TileKind.wall))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[1],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.asphalt))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[2],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.shippingBin))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[2],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.water))
            sprite = new Sprite(GameAssetManager.getWaterSprites()[0]);
        else if(tileKind.equals(TileKind.lightWater))
            sprite = new Sprite(GameAssetManager.getWaterSprites()[1]);
        else if(tileKind.equals(TileKind.veryLightWater))
            sprite = new Sprite(GameAssetManager.getWaterSprites()[2]);
        else if(tileKind.equals(TileKind.structure)){
            if(App.getGame().dateAndTime.getSeason().equals(Season.WINTER))
                sprite = new Sprite(GameAssetManager.getTilesTextures()[5], GameMap.getTilePrintSize(), GameMap.getTilePrintSize());
            else
                sprite = new Sprite(GameAssetManager.getTilesTextures()[0], GameMap.getTilePrintSize(), GameMap.getTilePrintSize());
        }
        else if(tileKind.equals(TileKind.shop))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[2],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.soiledPlowed))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[7],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.NPC))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[1],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.wateredPlowed))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[6],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.mine))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[3],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.plowed))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[4],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.sand))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[9],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.shore))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[10],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else if(tileKind.equals(TileKind.foragingMineral))
            sprite = new Sprite(GameAssetManager.getTilesTextures()[3],GameMap.getTilePrintSize() ,GameMap.getTilePrintSize());
        else{
            if(App.getGame().dateAndTime.getSeason().equals(Season.WINTER))
                sprite = new Sprite(GameAssetManager.getTilesTextures()[5], GameMap.getTilePrintSize(), GameMap.getTilePrintSize());
            else
                sprite = new Sprite(GameAssetManager.getTilesTextures()[0], GameMap.getTilePrintSize(), GameMap.getTilePrintSize());
        }
        sprite.setSize(GameMap.getTilePrintSize(), GameMap.getTilePrintSize());
        return sprite;
    }

    public void update(float delta){
        if(tileKind.equals(TileKind.wateredPlowed)){
            if(App.getGame().dateAndTime.isADayPassed()){
                if(App.getGame().weather.getWeather().equals(WeatherType.RAINY)){
                    tileKind = TileKind.wateredPlowed;
                }
                else{
                    tileKind = TileKind.plowed;
                }
            }
        }
    }

    public void setSprite(Texture texture){
        sprite.setTexture(texture);
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

    public String getDetails(){
        StringBuilder sb = new StringBuilder();
        sb.append("tile kind : " + tileKind);
        if(item != null)
            sb.append("\nitem : " + item.getItemType());
        sb.append("\nposition : "+ position.getX() + ", " + position.getY());
        return sb.toString();
    }
}
