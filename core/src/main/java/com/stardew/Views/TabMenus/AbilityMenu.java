package com.stardew.Views.TabMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Controllers.AbilityMenuController;
import com.stardew.Controllers.GameMenuController;
import com.stardew.Main;
import com.stardew.Models.Abilities.Abilities;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Views.GameMenu;
import com.stardew.Views.Tab;

import java.util.ArrayList;
import java.util.Arrays;

public class AbilityMenu extends Tab {
    private static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private static int FIRST_LEVEL_MARGIN = 80;
    private static final int SECOND_LEVEL_MARGIN = 135;
    private static final int START_X =(int) SCREEN_WIDTH/4 +15;
    private static final int START_Y =(int) SCREEN_HEIGHT*19/32 - 26;
    private static final int FIRST_ABILITY_Y = START_Y+23;
    private static final int FIRST_ABILITY_X = START_X+354;
    private ArrayList<Sprite> sprites;
    private static AbilityMenu abilityMenu = null;
    private static AbilityMenuController controller = new AbilityMenuController();

    public static AbilityMenu getInstance(){
        if(abilityMenu == null)
            abilityMenu = new AbilityMenu();
        return abilityMenu;
    }

    @Override
    public void show(){
        sprites = new ArrayList<>();
        super.show();
        Gdx.input.setInputProcessor(this);
        int[] abilities = new int[4];
        abilities[0] = GameMenu.getInstance().getController().abilities.getAbilities()[1];
        abilities[1] = GameMenu.getInstance().getController().abilities.getAbilities()[3];
        abilities[2] = GameMenu.getInstance().getController().abilities.getAbilities()[0];
        abilities[3] = GameMenu.getInstance().getController().abilities.getAbilities()[2];
        System.out.println(Arrays.toString(abilities));
        Sprite s = new Sprite(GameAssetManager.getAbilityMenuTextures()[0]);
        s.setPosition(START_X, START_Y);
        sprites.add(s);
        for(int i = 0; i< abilities.length; i++){
            for(int j = 0; j< abilities[i]; j++){
                if(j%5 == 4){
                    s= new Sprite(GameAssetManager.getAbilityMenuTextures()[2]);
                    s.setSize(57, 36);
                }
                else {
                    s = new Sprite(GameAssetManager.getAbilityMenuTextures()[1]);
                    s.setSize(29, 36);
                }
                if(j >= 5){
                    s.setPosition(FIRST_ABILITY_X + j*36 + 26, FIRST_ABILITY_Y + i*68);
                }
                else{
                    s.setPosition(FIRST_ABILITY_X + j*36, FIRST_ABILITY_Y + i*68);
                }
                sprites.add(s);
            }
        }

        float tableWidth= sprites.get(0).getWidth();
        float tableHeight = sprites.get(0).getHeight();

        for(int i = 0; i<4; i++){
            Sprite ss = new Sprite(GameAssetManager.getInventorySprites()[i]);
            if(i==1){
                ss.setPosition(START_X + tableWidth/2 - 100 + i*50, START_Y + tableHeight);
                System.out.println(ss.getX()+" "+ss.getY());
            }
            else
                ss.setPosition(START_X + tableWidth/2 - 100 + i*50, START_Y + tableHeight+5);
            ss.setSize(50, 50);
            sprites.add(ss);
        }
    }


    @Override
    public void render(float delta){
        super.render(delta);
        if (!batch.isDrawing()) {
            batch.begin();
        }

        for(Sprite s : sprites){
            s.draw(batch);
        }

        if (batch.isDrawing()) {
            batch.end();
        }
    }

    @Override
    public boolean keyDown(int keycode) {

        return controller.keyDown(keycode).success();
    }
}
