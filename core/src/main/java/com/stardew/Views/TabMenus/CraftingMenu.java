package com.stardew.Views.TabMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew.Controllers.GameMenuController;
import com.stardew.Enums.ItemType;
import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.GameMap;
import com.stardew.Models.Items.CraftAbleAndArtisan.Artisan;
import com.stardew.Models.Items.CraftAbleAndArtisan.CraftAble;
import com.stardew.Models.Items.Item;
import com.stardew.Models.MessageManager;
import com.stardew.Models.Result;
import com.stardew.Views.GameMenu;
import com.stardew.Views.Tab;

import java.util.ArrayList;

public class CraftingMenu extends Tab {
    private static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private static final int START_X =(int) SCREEN_WIDTH/7;
    private static final int START_Y =(int) SCREEN_HEIGHT/3;
    private static final int MENU_HEIGHT = 600;
    private static final int VERTICAL_MARGIN = 30;
    private static final int HORIZONTAL_MARGIN = 50;
    private static int MAX_WIDTH = 0;
    private static CraftingMenu craftingMenu =null;
    private TextButton  textButton = null;

    private final ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private ArrayList<CraftAble> allCraftables = new ArrayList<>();

    private CraftingMenu(){
        allCraftables.addAll(CraftAble.allCraftables);
        allCraftables.addAll(Artisan.allArtisan);
        int currentHeight = 0;
        int currentWidth = 0;
        for(Item item : allCraftables){
            MAX_WIDTH = Math.max(MAX_WIDTH, (int) item.getSprite().getWidth());
        }
        MAX_WIDTH*=2;
        Sprite back = new Sprite(GameAssetManager.getBackgroundSprite());
        back.setPosition(START_X, START_Y);
        back.setSize(1200, MENU_HEIGHT);
        sprites.add(back);
        for(Item item : allCraftables){
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
            if(!((CraftAble)item).getCraftingRecipe().isAvailable()){
                s.setColor(0.3f, 0.3f, 0.3f, 1);
            }
            item.getSprite().setSize(s.getWidth(), s.getHeight());
            item.getSprite().setPosition(s.getX(), s.getY());
            items.add(item);
            sprites.add(s);
        }
    }

    public static CraftingMenu getInstance(){
        if(craftingMenu == null)
            craftingMenu = new CraftingMenu();
        return craftingMenu;
    }

    @Override
    public void show(){
        super.show();
        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void render(float delta){
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
        //TODO packet

        System.out.println(keycode);
        if(keycode == Input.Keys.ESCAPE){
            Main.main.setScreen(GameMenu.getInstance());
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //TODO packet

        if(button == Input.Buttons.LEFT) {
            for(int i = 0;i<allCraftables.size(); i++){
                Sprite s = sprites.get(i+1);
                boolean b1 = GameMenuController.coordinateCollision(screenX, 0, s.getX(), s.getWidth());
                boolean b2 = GameMenuController.coordinateCollision(SCREEN_HEIGHT-screenY, 0, s.getY(), s.getHeight());
                if(b1 && b2){
                    textButton = createTextButton(allCraftables.get(i).getCraftingRecipe().details());
                    textButton.setPosition(s.getX(), s.getY());
                    textButton.setSize(7*textButton.getWidth(), 7*textButton.getHeight());
                    return true;
                }
            }
            textButton = null;
        }

        else if(button == Input.Buttons.RIGHT) {
            for(int i = 0;i<allCraftables.size(); i++){
                Sprite s = sprites.get(i+1);
                boolean b1 = GameMenuController.coordinateCollision(screenX, 0, s.getX(), s.getWidth());
                boolean b2 = GameMenuController.coordinateCollision(SCREEN_HEIGHT-screenY, 0, s.getY(), s.getHeight());
                if(b1 && b2){
                    GameMenu.getInstance().getController().abilities.crafting.craft(allCraftables.get(i).getItemType());
                    return true;
                }
            }
            textButton = null;
        }
        return false;
    }
}
