package com.stardew.Views.TabMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew.Controllers.InGameControllers.AnimalMenuController;
import com.stardew.Enums.ItemType;
import com.stardew.Models.Game.App;
import com.stardew.Models.Items.*;
import com.stardew.Network.Client.ClientApp;
import com.stardew.Network.Common.Packet.ClientPacket.KeyboardPackets.ClickPacket;
import com.stardew.Network.Common.Packet.ClientPacket.KeyboardPackets.TextButtonType;
import com.stardew.Views.Tab;

import java.util.ArrayList;

public class AnimalMenu extends Tab {
    private static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private static final int START_X = (int) SCREEN_WIDTH / 4;
    private static final int START_Y = (int) 10;
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private boolean isChanged = true;
    private Animal animal;
    private static AnimalMenuController controller = new AnimalMenuController();
    int u = 0;


    @Override
    public void show() {
        if(App.getCurrentPlayer().backpack.getItems().get(App.getGame().getItemByItemType(ItemType.Hay)) != null)
            u = App.getCurrentPlayer().backpack.getItems().get(App.getGame().getItemByItemType(ItemType.Hay));

        sprites = new ArrayList<>();
        super.show();
        Gdx.input.setInputProcessor(stage);

        TextButton textButton = Tab.createTextButton("feed");
        textButton.setSize(100, 40);
        textButton.setPosition(SCREEN_WIDTH/2-20, SCREEN_HEIGHT/2 - 50);
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ClientApp.getInstance().getConnectionThread().sendPacket(new ClickPacket(App.getMyPlayer(), TextButtonType.feed, AnimalMenu.class));
                if(u > 0) {
                    animal.feed();
                    App.getCurrentPlayer().backpack.getItems().compute(App.getGame().getItemByItemType(ItemType.Hay),
                        (k, v)->(v-1));
                    isChanged = true;
                }
            }
        });
        stage.addActor(textButton);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 1, 0, 1);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        if (!batch.isDrawing()) {
            batch.begin();
        }
        for (Sprite s : sprites) {
            s.draw(batch);
        }
        if (batch.isDrawing()) {
            batch.end();
        }

        if(isChanged){
            sprites.clear();
            if(App.getCurrentPlayer().backpack.getItems().get(Item.Hay) != null)
                u = App.getCurrentPlayer().backpack.getItems().get(Item.Hay);
            for(int i = 0; i< u; i++){
                Sprite s = new Sprite(Item.Hay.getSprite());
                s.setPosition(SCREEN_WIDTH/3+i*(s.getWidth()+15), SCREEN_HEIGHT/2+120);
                sprites.add(s);
            }
            isChanged = false;
        }

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }

    public void setAnimal(Animal animal){
        this.animal = animal;
    }
}
