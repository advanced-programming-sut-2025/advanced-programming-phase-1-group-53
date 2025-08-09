package com.stardew.Models.Items.Buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.Enums.MapsNames;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.Items.Refrigerator;
import com.stardew.Models.Position;

public class House extends Building{
    private final Refrigerator refrigerator;
    private final TextureRegion[] textureRegions = GameAssetManager.getFishStoreSprites();
    public House(Position position) {
        super(position);
        this.mapsName = MapsNames.House;
        this.buildHouse();
        refrigerator = new Refrigerator();
        sprite = new Sprite(new TextureRegion(textureRegions[0]));
    }

    @Override
    public Sprite getSprite(){
        try{
            sprite = new Sprite(textureRegions[App.getGame().dateAndTime.getSeason().ordinal()]);
            return super.getSprite();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Refrigerator getRefrigerator() {
        return refrigerator;
    }
// getMapsName() is inherited from Building
}
