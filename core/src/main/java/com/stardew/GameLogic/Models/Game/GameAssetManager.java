package com.stardew.GameLogic.Models.Game;

public class GameAssetManager {
    private static GameAssetManager gam = null;

    public static GameAssetManager getInstance(){
        if(gam == null)
            gam = new GameAssetManager();
        return gam;
    }
}
