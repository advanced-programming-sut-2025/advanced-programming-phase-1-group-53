package com.stardew.Views.TabMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.Game.Player;
import com.stardew.Models.MessageManager;
import com.stardew.Views.GameMenu;
import com.stardew.Views.Tab;

import java.util.ArrayList;

public class KickingMenu extends Tab {

    private static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private static final int START_X =(int) SCREEN_WIDTH/8;
    private static final int START_Y =(int) 286;
    private static final float ROW_WIDTH = SCREEN_WIDTH/2;
    private static final float ROW_HEIGHT = SCREEN_HEIGHT/16;
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private Player player = null;

    public KickingMenu(Player player){
        this.player = player;
    }

    @Override
    public void show(){
        super.show();

        TextButton tb = MessageManager.createTextButton("vote for kicking : "+ player.personalInfo.getName());
        tb.setPosition(SCREEN_WIDTH/2 - 70, SCREEN_HEIGHT/2 + 100);
        tb.setSize(0, 0);
        stage.addActor(tb);

        TextButton back = Tab.createTextButton("agree");
        back.setPosition(SCREEN_WIDTH/2-300, SCREEN_HEIGHT/2 - 150);
        back.setSize(150, 70);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.main.setScreen(GameMenu.getInstance());
            }
        });
        stage.addActor(back);

        TextButton dis = Tab.createTextButton("disagree");
        dis.setPosition(SCREEN_WIDTH/2+150, SCREEN_HEIGHT/2 - 150);
        dis.setSize(150, 70);
        dis.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.main.setScreen(GameMenu.getInstance());
            }
        });
        stage.addActor(dis);
    }


    @Override
    public void render(float delta){
        ScreenUtils.clear(1, 1, 1, 1);
        if (!batch.isDrawing()) {
            batch.begin();
        }
        for(Sprite s : sprites){
            try{
                s.draw(batch);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        if (batch.isDrawing()) {
            batch.end();
        }

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }
}
