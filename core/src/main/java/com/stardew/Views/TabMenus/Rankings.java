package com.stardew.Views.TabMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew.Controllers.InGameControllers.AnimalMenuController;
import com.stardew.Enums.ItemType;
import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.Game.Player;
import com.stardew.Models.Items.Animal;
import com.stardew.Models.Items.Item;
import com.stardew.Models.Product;
import com.stardew.Network.Client.ClientApp;
import com.stardew.Network.Common.Packet.ClientPacket.KeyboardPackets.ClickPacket;
import com.stardew.Network.Common.Packet.ClientPacket.KeyboardPackets.TextButtonType;
import com.stardew.Views.GameMenu;
import com.stardew.Views.Tab;

import java.util.ArrayList;

public class Rankings extends Tab {
    private static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private static final int START_X =(int) SCREEN_WIDTH/4;
    private static final int START_Y =(int) 10;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private static final float MARGIN = 30;
    private static final float ROW_HEIGHT = 150;
    private static final float ROW_WIDTH = 1000;
    private boolean isChanged = true;
    private boolean showAll = true;
    private static final int NUM_OF_ITEMS_IN_A_PAGE = 5;
    private int currentPage  = 0;
    SelectBox selectBox;


    public void createRow(float x, float y, Player player){
        Sprite back = new Sprite(GameAssetManager.getBackgroundSprite());
        back.setSize(ROW_WIDTH, ROW_HEIGHT);
        back.setPosition(x, y);
        Sprite s = player.getSprite();
        s.setSize(ROW_WIDTH/7, ROW_HEIGHT*4/5);
        s.setPosition(x, y+ROW_HEIGHT/10);
        sprites.add(back);
        sprites.add(s);
        TextButton details = Tab.createTextButton("name : "+player.personalInfo.getName()+",  quantity : "
            +player.personalInfo.getGold()+" ");
        details.setSize(0, 0);
        details.setPosition(x+ROW_WIDTH/6+20,y+20);
        stage.addActor(details);
    }

    @Override
    public void show(){
        selectBox =Tab.createSelectBox(new Array<>(){{
            add("show all");
            add("show availables");
        }});
        sprites = new ArrayList<>();
        super.show();
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void render(float delta){
        if(selectBox != null) {
            boolean s = showAll;
            if(selectBox.getSelectedIndex() == 0)
                showAll = true;
            else
                showAll = false;
            if(showAll != s)
                isChanged = true;
        }
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClearColor(0, 0, 0, 1); // RGB + Alpha

        if (!batch.isDrawing()) {
            batch.begin();
        }
        for (Sprite s : sprites) {
            s.draw(batch);
        }
        if (batch.isDrawing()) {
            batch.end();
        }

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        if(isChanged){
            System.out.println("ppp");
            stage.clear();
            sprites.clear();
            TextButton back = Tab.createTextButton("back");
            back.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Main.main.setScreen(GameMenu.getInstance());
                }
            });
            back.setPosition(SCREEN_WIDTH/2-70, START_Y);
            back.setSize(140, 80);
            stage.addActor(back);
            float currentY = START_Y+100;
            for(int i = currentPage*NUM_OF_ITEMS_IN_A_PAGE; i<
                Math.min((currentPage+1)*NUM_OF_ITEMS_IN_A_PAGE, players.size()); i++){
                Player player = players.get(i);
                createRow(START_X,currentY,player);
                currentY += (MARGIN + ROW_HEIGHT);
            }
            selectBox.setPosition(START_X, currentY);
            selectBox.setSize(ROW_WIDTH/2, ROW_HEIGHT/3);
            stage.addActor(selectBox);
            isChanged = false;
        }
    }

    public void setProducts(ArrayList<Player> players){
        this.players = new ArrayList<>();
        this.players.addAll(players);
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }
}
