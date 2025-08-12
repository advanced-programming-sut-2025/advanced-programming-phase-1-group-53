package com.stardew.Controllers.NetworkControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.TimeUtils;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Views.AppMenu;

public class PlayersMenu extends AppMenu {
    public PlayersMenu() {
        super(App.main);
    }
//
//    @Override
//    public void render(float delta) {
//        super.render(delta);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            // do nothing
//        }
//        App.main.setScreen(this);
//    }

    @Override
    public void show() {
        super.show();

        Table playersTable = new Table();
        for (Player player : App.getInstance().getPlayers()) {
            playersTable.add(new TextButton(player.getUsername(), skin)).pad(20, 40, 20, 40).row();
        }
        ScrollPane scrollPane = new ScrollPane(playersTable, skin);
        Window window = new Window("Players", skin);
        window.setSize(800, 600);
        window.setPosition((Gdx.graphics.getWidth() - 800) / 2f, (Gdx.graphics.getHeight() - 600) / 2f);
        window.add(scrollPane).expand().fill().pad(20);
        window.setMovable(true);
        table.clear();
        table.add(window).fill();
    }

    @Override
    public void check(String scanner) {

    }
}
