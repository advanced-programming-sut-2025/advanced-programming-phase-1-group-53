package com.stardew.Views.TabMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.stardew.Main;
import com.stardew.Models.Game.Player;
import com.stardew.Models.Items.Fish;
import com.stardew.Views.GameMenu;
import com.stardew.Views.Tab;

import java.util.ArrayList;

public class MiniGameMenu extends Tab {
    private static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private static final float BAR_HEIGHT  = 1200;
    private static final float BAR_WIDTH = 200;
    private static float START_X = SCREEN_WIDTH/3;
    private static float START_Y = 100;
    private static MiniGameMenu miniGameMenu = null;
    private Fish fish;
    private Player player;
    private boolean isPerfect = true;
    private boolean isFishInBar = true;
    private boolean hasSnoarBobber;
    private final float RAISING_RATE = 4;

    private ArrayList<Sprite> sprites = new ArrayList<>();

    private TextButton showFishKind;
    private Sprite crown= new Sprite(new Texture("Infinity_Crown.png"));
    private Sprite fishSprite = new Sprite(new Texture("Walleye.png"));


    public static MiniGameMenu getInstance(){
        if(miniGameMenu == null)
            miniGameMenu = new MiniGameMenu();
        return miniGameMenu;
    }

    @Override
    public void show(){
        super.show();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta){
        fish.moveInMiniGame(delta);
        Gdx.gl.glClearColor(1, 0.6f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        getSprites();

        if (!batch.isDrawing()){
            batch.begin();
        }
        for(Sprite s : sprites){
            s.draw(batch);
        }
        if (batch.isDrawing()) {
            batch.end();
        }
    }

    private void getSprites(){
        sprites.clear();

    }

    @Override
    public boolean keyDown(int keycode){
        if(keycode == Input.Keys.ESCAPE){
            OceanMenu.getInstance().setChanged(true);
            Main.main.setScreen(OceanMenu.getInstance());
            return true;
        }
        return false;
    }

    public void setUpMiniGame(Player player, Fish fish){
        this.player = player;
        this.fish =fish;
    }
}
