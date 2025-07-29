package com.stardew.Views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.stardew.Controllers.GameMenuController;

import java.util.Scanner;

public class GameMenu extends AppMenu {
    private final GameMenuController controller = new GameMenuController();

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

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                main.setScreen(new MainMenu(main));
            }
        });
        table.add(backButton).pad(20).row();
        // Add more buttons for game actions here as needed
    }
}
