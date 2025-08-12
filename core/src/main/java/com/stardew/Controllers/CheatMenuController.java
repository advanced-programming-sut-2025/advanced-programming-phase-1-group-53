package com.stardew.Controllers;

import com.badlogic.gdx.Screen;

public class CheatMenuController implements Screen{
    private static CheatMenuController controller;
    public static Screen getInstance() {
        if (controller == null) {
            controller = new CheatMenuController();
        }
        return controller;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
