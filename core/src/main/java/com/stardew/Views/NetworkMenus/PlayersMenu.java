package com.stardew.Views.NetworkMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.stardew.Models.Game.App;
import com.stardew.Views.AppMenu;

import java.util.ArrayList;

public class PlayersMenu extends AppMenu {

    public PlayersMenu() {
        super(App.main);
    }

    @Override
    public void show() {
        table.clear();
        Label title = new Label("Players Menu", skin);
        table.add(title).pad(20).row();
        TextButton previewBtn = new TextButton("Preview Player", skin);
        previewBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                showPlayersWindow(table);
            }
        });
        table.add(previewBtn).pad(20).row();
        TextButton backBtn = new TextButton("Back", skin);
        backBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                App.main.setScreen(new NetMainMenu(App.main));
            }
        });
        table.add(backBtn).pad(60).row();
    }

    @Override
    public void check(String scanner) {}

    public static SelectBox<String> showPlayersWindow(Table table) {
        Window window = new Window("Player Info", skin);
        window.setSize(400, 500);
        window.setPosition((Gdx.graphics.getWidth() - 400) / 2f, (Gdx.graphics.getHeight() - 500) / 2f);
        java.util.List<String> playerNames = new java.util.ArrayList<>();
        for (var player : App.getInstance().getPlayers()) {
            String displayName = player.getUsername();
            if (player.getUsername().equals(App.getMyPlayer().getUsername())) {
                displayName += " (You)";
            }
            playerNames.add(displayName);
        }
        SelectBox<String> selectBox = new SelectBox<>(skin);
        selectBox.setItems(playerNames.toArray(new String[0]));
        window.add(selectBox).width(350).height(60).pad(20).row();

        TextButton closeBtn = new TextButton("Close", skin);
        Window finalWindow = window;
        closeBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                finalWindow.remove();
            }
        });
        window.add(closeBtn).pad(20);
        stage.addActor(window);

        return selectBox;
    }
}
