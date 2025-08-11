package com.stardew.Views.TabMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.Game.Player;
import com.stardew.Models.GameMap;
import com.stardew.Models.Items.Buildings.Building;
import com.stardew.Models.Items.Buildings.Shop;
import com.stardew.Models.Items.Foragings.ForagingMineral;
import com.stardew.Models.Items.Foragings.ForagingTree;
import com.stardew.Models.Items.Foragings.Tree;
import com.stardew.Models.Tile;
import com.stardew.Views.GameMenu;
import com.stardew.Views.Tab;

import java.util.ArrayList;

public class MapMenu extends Tab {
    private static final int SCALE = 5;
    private static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private static final int START_X =(int) SCREEN_WIDTH/5;
    private static final int START_Y =(int) SCREEN_HEIGHT/6;
    private static MapMenu mapMenu = null;
    private static int[] SpriteX = new int[]{};
    private static int[] SpriteY = new int[]{};
    private static int[] SpriteWidth = new int[]{ };
    private static int[] SPriteHeight = new int[]{};
    private ArrayList<Sprite> sprites;
    ArrayList<Sprite> sprites1;

    public static MapMenu getInstance(){
        if(mapMenu == null)
            mapMenu = new MapMenu();
        return mapMenu;
    }

    private void getSprites(){
        sprites = new ArrayList<>();

        for(Tile[] tt : App.getGame().getGameMap().getTiles()){
            for(Tile t : tt){
                Sprite s = t.getSprite();
                s.setX((float) GameMap.getTilePrintSize() /SCALE *t.getPosition().getX());
                s.setY((float) GameMap.getTilePrintSize() /SCALE *t.getPosition().getY());
                sprites.add(s);
            }
        }

        for(Tile[] tt : App.getGame().getGameMap().getTiles()){
            for(Tile t : tt){
                if(true){
                    if(t.getItem() == null)
                        continue;
                    if(t.getItem().getClass() != ForagingTree.class && t.getItem().getClass() != ForagingMineral.class&&
                        t.getItem().getClass() != Tree.class)
                        continue;
                    Sprite s = t.getItem().getSprite();
                    s.setPosition(s.getX()/SCALE, s.getY()/SCALE);
                    sprites.add(s);
                }
            }
        }

        //print structures
        for(Player p : App.getGame().players){
            for(Building b : p.getFarm().getBuildings()){
                if (true) {
                    Sprite s = b.fixSpriteCoordinatesForPrint();
                    s.setPosition(s.getX()/SCALE, s.getY()/SCALE);
                    sprites.add(s);
                }
            }
        }


        //print shops
        for (Shop shop : Shop.shops) {
            if (true) {
                Sprite s = shop.fixSpriteCoordinatesForPrint();
                s.setPosition(s.getX()/SCALE, s.getY()/SCALE);
                sprites.add(s);
            }
        }

        for(int i = 0; i<4; i++){
            Sprite s = new Sprite(GameAssetManager.getInventorySprites()[i]);
            float y = ((float)(GameMap.getTilePrintSize() / SCALE) * GameMap.getMapSize());
            if(i !=2)
                y+=5;
            s.setPosition( (float) SCREEN_WIDTH/2 - 100 - START_X + 50 *i , y);
            s.setSize(50*SCALE, 50*SCALE);
            sprites.add(s);
        }
    }

    @Override
    public void show(){
        super.show();
        Gdx.input.setInputProcessor(this);
        getSprites();
        sprites1 = new ArrayList<>(sprites);
        for(Sprite s : sprites1){
            s.setPosition(s.getX() + START_X, s.getY()+START_Y);
            s.setSize((float) (s.getWidth()/SCALE), (float) (s.getHeight()/SCALE));
        }
    }


    @Override
    public void render(float delta){
        super.render(delta);
        if (!batch.isDrawing()) {
            batch.begin();
        }
        for(Sprite s : sprites1){
            s.draw(batch);
        }

        Sprite s = App.getCurrentPlayer().getSprite();
        s.setPosition((float) (s.getX()/SCALE) + START_X, (float) (s.getY()/SCALE) + START_Y);
        s.setSize((float) (s.getWidth()/SCALE * 1.7), (float) (s.getHeight()/SCALE * 1.7));
        s.draw(batch);

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
        if(keycode == Input.Keys.M){
            Main.main.setScreen(AbilityMenu.getInstance());
            return true;
        }
        return false;
    }
}
