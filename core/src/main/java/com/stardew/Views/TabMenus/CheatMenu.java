package com.stardew.Views.TabMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.Enums.MessageTypes;
import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.MessageManager;
import com.stardew.Views.GameMenu;
import com.stardew.Views.Tab;

public class CheatMenu extends Tab {
    private static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private static int FIRST_LEVEL_MARGIN = 80;
    private static final int SECOND_LEVEL_MARGIN = 135;
    private static final int START_X =(int) SCREEN_WIDTH/4;
    private static final int START_Y =(int) SCREEN_HEIGHT/3;
    private static CheatMenu cheatMenu = null;
    private TextField cheatField;
    private TextButton submitButton;
    private TextButton backButton;
    private static Sprite[] sprites = new Sprite[]{

    };

    public static CheatMenu getInstance(){
        if(cheatMenu == null)
            cheatMenu = new CheatMenu();
        return cheatMenu;
    }

    @Override
    public void show(){
        super.show();
        submitButton = Tab.createTextButton("submit");
        backButton = Tab.createTextButton("back");
        cheatField = Tab.createTextField("enter cheat code");
        submitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO packet

                try{
                    GameMenu.getInstance().check(cheatField.getText());
                    if(cheatField.getText().contains("advance")){
                        Main.main.setScreen(new TimeCheatMenu());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(cheatField.getText());
            }
        });
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO packet

                Main.main.setScreen(GameMenu.getInstance());
            }
        });
        table.add(cheatField).width(300).height(80).center();
        table.row();
        table.add(backButton);
        table.add(submitButton);
        stage.addActor(table);
    }


    @Override
    public void render(float delta){
        String s = cheatField.getText();
        super.render(delta);
    }

    @Override
    public boolean keyDown(int keycode){
        //TODO packet

        if(keycode == Input.Keys.ENTER){
            GameMenu.getInstance().check(cheatField.getText());
        }
        if(keycode == Input.Keys.K){
            Main.main.setScreen(GameMenu.getInstance());
        }
        return false;
    }
}
