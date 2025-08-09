package com.stardew.Models.Items.Buildings;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.Position;

public class Lake extends Building {
    public Lake(Position position) {
        super(position);
        this.sprite = new Sprite(GameAssetManager.getLakeTexture());
    }
}
