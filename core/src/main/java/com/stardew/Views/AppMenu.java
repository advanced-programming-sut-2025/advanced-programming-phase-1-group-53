package com.stardew.Views;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;

public abstract class AppMenu implements Screen {
    protected Game main;
    protected Stage stage;
    protected Skin skin;
    protected Table table;
    public AppMenu(Game main) {
        this.main = main;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/LibGdx-Skin-main/NzSkin.json"));
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
    }

    protected AppMenu() {
    }

    public abstract void check(String scanner);

    @Override
    public void show() {
        stage.clear(); // Clear old actors if switching back
        stage.addActor(table);
    }

    @Override public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    public Table getTable() {
        return table;
    }
}
