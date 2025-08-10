package com.stardew;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew.Enums.ItemType;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.GameMap;
import com.stardew.Network.Client.ClientApp;
//import com.stardew.Views.AppView;
import com.stardew.Views.GameMenu;
import com.stardew.Views.LoginRegisterMenu;
import com.stardew.Views.Tab;
import com.stardew.Views.TabMenus.AbilityMenu;
import com.stardew.Views.TabMenus.InventoryMenu;
import com.stardew.Views.TabMenus.MapMenu;

import com.badlogic.gdx.Game;
import com.stardew.Views.LoginRegisterMenu;

import java.util.Scanner;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public static Main main = new Main();
    private SpriteBatch batch;
    public static Sprite sprite;
    public Stage stage;

    private final String serverIp = "127.0.1.1";
    private final int port = 12345;
    private final String playerId = "Player1";

    @Override
    public void create() {
//        stage = new Stage();
//        TextButton textButton = Tab.createTextButton("kkk");
//        textButton.setSize(0, 0);
//        textButton.setPosition(30, 0);
//        stage.addActor(textButton);
        System.out.println("Connecting to server: " + serverIp + ":" + port + " as " + playerId);
        ClientApp app = ClientApp.getInstance();
        app.initializeClient(serverIp, port, playerId);

        try{
            setScreen(new Tab());
            batch = new SpriteBatch();
            sprite = new Sprite(new Texture("Animals/Duck.png"),16*3, 16*3, 16, 16);
            sprite.setSize(sprite.getWidth()*3, sprite.getHeight()*3);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void render() {
//        ScreenUtils.clear(1, 1, 1, 1);
//        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
//        stage.draw();
        try{
//            batch.begin();
//            sprite.draw(batch);
//            batch.end();
            super.render();
            //System.out.println(sprite.getWidth() + " " + sprite.getHeight());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        sprite.getTexture().dispose();
        setScreen(new LoginRegisterMenu(main));  // Or any other AppMenu subclass
    }
}
