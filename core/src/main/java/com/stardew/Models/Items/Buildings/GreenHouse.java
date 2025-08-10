package com.stardew.Models.Items.Buildings;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Enums.MapsNames;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.Position;

public class GreenHouse extends Building {
    private boolean isBuild = false;

    public GreenHouse(Position position) {
        super(position);
        this.mapsName = MapsNames.GreenHouse;
        this.buildGreenhouse();
        sprite = new Sprite(GameAssetManager.getGreenhouseSprite()[isBuild? 0:1]);
    }

    public void build() {}
    public void letsBuildGreenhouse() {
        this.isBuild = true;
    }
    public boolean isBuild() {
        return isBuild;
    }
}
