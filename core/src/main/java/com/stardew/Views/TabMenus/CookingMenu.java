package com.stardew.Views.TabMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew.Controllers.GameMenuController;
import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.Items.Food;
import com.stardew.Models.Items.Item;
import com.stardew.Network.Client.ClientApp;
import com.stardew.Network.Common.Packet.ClientPacket.KeyboardPackets.TouchDownPacket;
import com.stardew.Views.GameMenu;
import com.stardew.Views.Tab;

import java.util.ArrayList;

public class CookingMenu extends Tab {
    private static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private static final int START_X =(int) SCREEN_WIDTH/7;
    private static final int START_Y =(int) SCREEN_HEIGHT/3;
    private static final int MENU_HEIGHT = 600;
    private static final int VERTICAL_MARGIN = 30;
    private static final int HORIZONTAL_MARGIN = 50;
    private static int MAX_WIDTH = 0;
    private static CookingMenu cookingMenu=null;
    private TextButton textButton = null;

    private final ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private ArrayList<Food> allFoods = new ArrayList<>();

    private CookingMenu(){
        allFoods.addAll(Food.allFoods);
        int currentHeight = 0;
        int currentWidth = 0;
        for(Item item : allFoods){
            MAX_WIDTH = Math.max(MAX_WIDTH, (int) item.getSprite().getWidth());
        }
        MAX_WIDTH*=2;
        Sprite back = new Sprite(GameAssetManager.getBackgroundSprite());
        back.setPosition(START_X, START_Y);
        back.setSize(1200, MENU_HEIGHT);
        sprites.add(back);
        for(Food item : allFoods){
            Sprite s = item.getSprite();
            s.setSize((float) (s.getWidth()*2), (float) (s.getHeight()*2));
            if(currentHeight+ s.getHeight()> MENU_HEIGHT){
                currentWidth+=HORIZONTAL_MARGIN;
                currentWidth += MAX_WIDTH;
                currentHeight= 0;
            }
            s.setPosition(START_X+currentWidth, START_Y+currentHeight);
            currentHeight+=s.getHeight();
            currentHeight+= VERTICAL_MARGIN;
            if(!item.getRecipe().isAvailable()){
                System.out.println(item.getItemType()+"kjkh");
                s.setColor(0.3f, 0.3f, 0.3f, 1);
            }
            item.getSprite().setSize(s.getWidth(), s.getHeight());
            item.getSprite().setPosition(s.getX(), s.getY());
            items.add(item);
            sprites.add(s);
        }
    }

    public static CookingMenu getInstance(){
        if(cookingMenu == null)
            cookingMenu = new CookingMenu();
        return cookingMenu;
    }

    @Override
    public void show(){
        super.show();
        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        ScreenUtils.clear(0, 1, 1, 1);
        if (!batch.isDrawing()) {
            batch.begin();
        }

        for(Sprite s : sprites){
            s.draw(batch);
        }

        if (batch.isDrawing()) {
            batch.end();
        }
        stage = new Stage();
        if(textButton != null)
            stage.addActor(textButton);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public boolean keyDown(int keycode) {

        System.out.println(keycode);
        if(keycode == Input.Keys.ESCAPE){
            Main.main.setScreen(GameMenu.getInstance());
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        ClientApp.getInstance().getConnectionThread().sendPacket(new TouchDownPacket(App.getMyPlayer(), screenX, screenY, pointer, button, CookingMenu.class));

        if(button == Input.Buttons.LEFT) {
            for(int i = 0;i<allFoods.size(); i++){
                Sprite s = sprites.get(i+1);
                boolean b1 = GameMenuController.coordinateCollision(screenX, 0, s.getX(), s.getWidth());
                boolean b2 = GameMenuController.coordinateCollision(SCREEN_HEIGHT-screenY, 0, s.getY(), s.getHeight());
                if(b1 && b2){
                    textButton = createTextButton(allFoods.get(i).getRecipe().details());
                    textButton.setPosition(s.getX(), s.getY());
                    textButton.setSize(7*textButton.getWidth(), 7*textButton.getHeight());
                    return true;
                }
            }
            textButton = null;
        }

        else if(button == Input.Buttons.RIGHT) {
            for(int i = 0;i<allFoods.size(); i++){
                Sprite s = sprites.get(i+1);
                boolean b1 = GameMenuController.coordinateCollision(screenX, 0, s.getX(), s.getWidth());
                boolean b2 = GameMenuController.coordinateCollision(SCREEN_HEIGHT-screenY, 0, s.getY(), s.getHeight());
                if(b1 && b2){
                    GameMenu.getInstance().getController().abilities.cooking.prepare(allFoods.get(i).getItemType());
                    return true;
                }
            }
            textButton = null;
        }
        return false;
    }
}
