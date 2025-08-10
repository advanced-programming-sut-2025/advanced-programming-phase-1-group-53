package com.stardew.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.Gdx;
import com.stardew.Controllers.GameMenuController;
import com.stardew.Controllers.ShareController;
import com.stardew.Enums.GameMenuCommand;
import com.stardew.Enums.ItemType;
import com.stardew.Enums.MessageTypes;
import com.stardew.Enums.TileKind;
import com.stardew.Main;
import com.stardew.Models.*;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.*;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.Game.Player;
import com.stardew.Models.Items.*;
import com.stardew.Models.Items.CraftAbleAndArtisan.Artisan;
import com.stardew.Models.Items.Foragings.ForagingMineral;
import com.stardew.Views.TabMenus.*;
import com.stardew.Models.Game.App;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.addListener;

public class GameMenu extends AppMenu implements InputProcessor {
    private static float TOTAL_TIME_SPENT = 0;
    private static int SCREEN_WIDTH;
    private static int SCREEN_HEIGHT;
    private static GameMenu gameMenu = null;
    private SpriteBatch batch;
    private Sprite sprite;
    private Stage stage;
    private float thunderAlpha = 0f;
    private boolean isThunderActive = false;
    private ShapeRenderer shapeRenderer;
    private boolean SHOW_TILE_DETAILS = false;
    private final GameMenuController controller = new GameMenuController();
    private float nightScreenAlpha = 0;
    private boolean isGettingDark = false;
    private boolean goingInHouse =false;
    private boolean isGettingLight = false;
    private boolean setToolToMouse = false;
    private float mouseY = 0;
    private float mouseX = 0;
    private boolean isGoingInCoop = false;
    private boolean showFullTiles = false;


    public GameMenuController getController() {
        return controller;
    }

    private GameMenu(){

    }

    public static GameMenu getInstance(){
        if(gameMenu == null)
            gameMenu = new GameMenu();
        return gameMenu;
    }

    public static void renewInstance(){
        gameMenu = null;
    }

    public GameMenu(Game main) {
        super(main);
    }

    @Override
    public void check(Scanner scanner) {

    }

    @Override
    public void show() {
        table.clear();
        Label title = new Label("Game Menu", skin);
        table.add(title).pad(20).row();

        TextButton playersButton = new TextButton("Players", skin);
        playersButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                showPlayersWindow();
            }
        });
        table.add(playersButton).pad(10).row();

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                main.setScreen(new MainMenu(main));
            }
        });
        table.add(backButton).pad(20).row();
    }

    private void showPlayersWindow() {
        Array<String> usernames = new Array<String>();
        try {
            String projectRoot = System.getProperty("user.dir");
            com.badlogic.gdx.files.FileHandle profilesDir = Gdx.files.absolute(projectRoot + "/profiles");
            if (profilesDir.exists()) {
                for (com.badlogic.gdx.files.FileHandle file : profilesDir.list()) {
                    String currentPlayerName = App.getCurrentPlayer().getPersonalInfo().getName();
                    if (file.extension().equals("json")
                        && !file.nameWithoutExtension().equalsIgnoreCase("lastlog")
                        && !file.nameWithoutExtension().equalsIgnoreCase(currentPlayerName)
                    ) {
                        usernames.add(file.nameWithoutExtension());
                    }
                }
            }
        } catch (Exception e) {
        }
        if (usernames.size == 0) {
            usernames.add("ali");
            usernames.add("mammad");
            usernames.add("sadra");
        }

        Window playersWindow = new Window("Select Players", skin);
        playersWindow.setSize(600, 700);
        playersWindow.setPosition(Gdx.graphics.getWidth() / 2 - playersWindow.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - playersWindow.getHeight() / 2);

        TextButton profileDropdown1 = new TextButton(App.getCurrentPlayer().getPersonalInfo().getName(), skin);
        playersWindow.add(new Label("Profile 1:", skin)).pad(10);
        playersWindow.add(profileDropdown1).pad(10).row();

        SelectBox<String> profileDropdown2 = new SelectBox<String>(skin);
        profileDropdown2.setItems(usernames);
        playersWindow.add(new Label("Profile 2:", skin)).pad(10);
        playersWindow.add(profileDropdown2).pad(10).row();

        SelectBox<String> profileDropdown3 = new SelectBox<String>(skin);
        profileDropdown3.setItems(usernames);
        playersWindow.add(new Label("Profile 3:", skin)).pad(10);
        playersWindow.add(profileDropdown3).pad(10).row();

        SelectBox<String> profileDropdown4 = new SelectBox<String>(skin);
        profileDropdown4.setItems(usernames);
        playersWindow.add(new Label("Profile 4:", skin)).pad(10);
        playersWindow.add(profileDropdown4).pad(10).row();

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                playersWindow.remove();
            }
        });
        playersWindow.add(closeButton).pad(10).colspan(2);

        stage.addActor(playersWindow);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

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
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
