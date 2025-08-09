package com.stardew.Views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.Gdx;
import com.stardew.Controllers.GameMenuController;
import com.stardew.Models.Game.App;

import java.util.Scanner;

public class GameMenu extends AppMenu {
    private final GameMenuController controller = new GameMenuController();
    private String currentPlayerName = App.getCurrentPlayer().getPersonalInfo().getName();

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
}
