package com.stardew;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew.Network.Client.ClientApp;
import com.stardew.Network.Server.ServerApp;

public class GameLauncher extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;

    private final String serverIp = "127.0.1.1";
    private final int port = 12345;
    private final String playerId = "Player1";

    public GameLauncher() {}

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("assets/libgdx.png");

        // اینجا میتونی به سرور وصل شی
        System.out.println("Connecting to server: " + serverIp + ":" + port + " as " + playerId);
        ClientApp app = ClientApp.getInstance();
        app.initializeClient(serverIp, port, playerId);
//        System.out.println(ServerApp.getInstance().getConnections().toString());
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 140, 210);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
