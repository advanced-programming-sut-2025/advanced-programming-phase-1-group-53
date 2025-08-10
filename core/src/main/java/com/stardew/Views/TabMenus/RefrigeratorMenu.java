package com.stardew.Views.TabMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Controllers.GameMenuController;
import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.Items.Item;
import com.stardew.Views.GameMenu;
import com.stardew.Views.Tab;

import java.sql.Ref;
import java.util.ArrayList;

public class RefrigeratorMenu extends Tab {
    private static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private static int FIRST_LEVEL_MARGIN = 80;
    private static final int SECOND_LEVEL_MARGIN = 135;
    private static final int START_X =(int) SCREEN_WIDTH/4 - 50;
    private static final int START_Y =(int) SCREEN_HEIGHT/5;
    private static final int ROWS_WIDTH =900;
    private static final int ROWS_HEIGHT = 300;
    private static final int WIDGET_HEIGHT =50;
    private static final int WIDGET_WIDTH = 50;
    private static final int FARMER_LEVEL_WIDTH = 90;
    private static final int FARMER_LEVEL_HEIGHT = 80;
    private boolean isChanged = true;
    private static RefrigeratorMenu refrigeratorMenu = null;
    private static ArrayList<Sprite> sprites = new ArrayList<>(){{
        add(new Sprite(GameAssetManager.getInventorySprites()[4]));
    }};
    private static ArrayList<Sprite> refSprites = new ArrayList<>(){{
        add(new Sprite(GameAssetManager.getInventorySprites()[4]));
    }};
    private ArrayList<Item> inventoryItems = new ArrayList<>();
    private ArrayList<Item> refItems = new ArrayList<>();
    private static int[] SpriteX = new int[]{START_X, START_X+20};
    private static int[] SpriteY = new int[]{START_Y+FARMER_LEVEL_HEIGHT+FIRST_LEVEL_MARGIN,START_Y
    };
    private static int[] SpriteWidth = new int[]{ROWS_WIDTH,FARMER_LEVEL_WIDTH};
    private static int[] SpriteHeight = new int[]{ROWS_HEIGHT, FARMER_LEVEL_HEIGHT};

    private RefrigeratorMenu(){

    }

    public static RefrigeratorMenu getInstance(){
        if(refrigeratorMenu == null)
            refrigeratorMenu = new RefrigeratorMenu();
        return refrigeratorMenu;
    }

    @Override
    public void show(){
        super.show();
        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void render(float delta){
        super.render(delta);

        if(isChanged){
            inventoryItems = new ArrayList<>();
            refItems = new ArrayList<>();
            sprites = new ArrayList<>(){{
                add(new Sprite(GameAssetManager.getInventorySprites()[4]));
            }};
            refSprites = new ArrayList<>(){{
                add(new Sprite(GameAssetManager.getInventorySprites()[4]));
            }};

            for(int i = 0; i<1; i++){
                sprites.get(i).setSize(SpriteWidth[i], SpriteHeight[i]);
                sprites.get(i).setPosition(SpriteX[i], SpriteY[i]);
                refSprites.get(i).setSize(SpriteWidth[i], SpriteHeight[i]);
                refSprites.get(i).setPosition(SpriteX[i], SpriteY[i]+ROWS_HEIGHT +SECOND_LEVEL_MARGIN);
            }

            try {
                for (Item item : App.getCurrentPlayer().backpack.showInventory()) {
                    inventoryItems.add(item);
                    System.out.println(item.getItemType());
                    Sprite s = item.getSprite();
                    sprites.add(s);
                }

                for(Item item : App.getCurrentPlayer().getFarm().getHouse().getRefrigerator().getFoods().keySet()){
                    refItems.add(item);
                    Sprite s = item.getSprite();
                    refSprites.add(s);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

            for(int i = 1; i<sprites.size(); i++){
                int y = START_Y+FARMER_LEVEL_HEIGHT+FIRST_LEVEL_MARGIN;
                if(i%2==0){
                    y+=(ROWS_HEIGHT/2);
                }
                int x = START_X;
                x+=((i-1)/2)*ROWS_WIDTH/12;
                sprites.get(i).setPosition(x+(float) ROWS_WIDTH /36, y+ (float) (ROWS_HEIGHT/6));
                sprites.get(i).setSize((float) ROWS_WIDTH /24,(float) (ROWS_HEIGHT/4));
            }

            for(int i = 1; i<refSprites.size(); i++){
                int y = START_Y+FARMER_LEVEL_HEIGHT+FIRST_LEVEL_MARGIN+ROWS_HEIGHT+SECOND_LEVEL_MARGIN;
                if(i%2==0){
                    y+=(ROWS_HEIGHT/2);
                }
                int x = START_X;
                x+=((i-1)/2)*ROWS_WIDTH/12;
                refSprites.get(i).setPosition(x+(float) ROWS_WIDTH /36, y+ (float) (ROWS_HEIGHT/6));
                refSprites.get(i).setSize((float) ROWS_WIDTH /24,(float) (ROWS_HEIGHT/4));
            }
            isChanged = false;
        }
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

        for(Sprite s : refSprites){
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
        if (button == Input.Buttons.LEFT) {
            for(int i = 1; i< sprites.size(); i++){
                Item item = inventoryItems.get(i-1);
                Sprite s = sprites.get(i);
                boolean b1 = GameMenuController.coordinateCollision(screenX, 0, s.getX(), s.getWidth());
                boolean b2 = GameMenuController.coordinateCollision(SCREEN_HEIGHT-screenY, 0,s.getY(), s.getHeight());
                if(b1 && b2){
                    App.getCurrentPlayer().getFarm().getHouse().getRefrigerator().putItem(item, 1);
                    isChanged = true;
                    return true;
                }
            }

            for(int i = 1; i< refSprites.size(); i++){
                Item item = refItems.get(i-1);
                Sprite s = refSprites.get(i);
                boolean b1 = GameMenuController.coordinateCollision(screenX, 0, s.getX(), s.getWidth());
                boolean b2 = GameMenuController.coordinateCollision(SCREEN_HEIGHT-screenY, 0,s.getY(), s.getHeight());
                if(b1 && b2){
                    try {
                        App.getCurrentPlayer().getFarm().getHouse().getRefrigerator().pickItem(item, 1);
                        isChanged = true;
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
