package com.stardew.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Tab implements Screen, InputProcessor {
    private static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    protected  float START_X = SCREEN_WIDTH/3;
    protected  float START_Y = SCREEN_HEIGHT /3;
    protected float WIDTH = SCREEN_WIDTH /3;
    protected float HEIGHT = SCREEN_HEIGHT /3;
    public static Skin skin = new Skin(Gdx.files.internal("Skin/comic-ui.json"));
    public static Skin stardewSkin =  new Skin(Gdx.files.internal("skin/LibGdx-Skin-main/NzSkin.json"));
    protected boolean menuVisible = false;
    protected Stage stage;
    protected Table table = new Table();
    protected SpriteBatch batch;
    TextButton textButton;

    public static TextButton createTextButton(String text){
        TextButton tb= new TextButton(text, skin);
        tb.setSize(60, 80);
        return tb;
    }

    public static TextButton createTextButton(String text, int width, int height){
        TextButton tb= new TextButton(text, skin);
        tb.setSize(width, height);
        return tb;
    }

    public static Label createLabel(String text){
        return new Label(text, skin);
    }

    public static TextField createTextField(String text){
        return new TextField(text, skin);
    }

    public static SelectBox createSelectBox(Array<String> strings){
        SelectBox sb = new SelectBox<>(skin);
        sb.setItems(strings);
        return sb;
    }

    public static Label createLabel(String text, int width, int height){
        Label label= new Label(text, skin);
        label.setSize(width, height);
        return label;
    }

    public static Dialog createDialog(String message, String buttonMsg) {
        Dialog dialog = new Dialog("Pop-up", skin) {
            protected void result(Object object) {
                this.hide();
            }
        };
        dialog.text(message);
        dialog.button(buttonMsg);
        return dialog;
    }

    @Override
    public void show(){
        batch = new SpriteBatch();
        table = new Table();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        table.setFillParent(true);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("lll");
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    } //

    @Override
    public boolean keyTyped(char character) {
        return false;
    } //

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    } //

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    } //

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
