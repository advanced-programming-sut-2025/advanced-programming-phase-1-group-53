package com.stardew.Views.NetworkMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Views.AppMenu;
import com.stardew.Views.STab;

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
            TextButton playerBtn = new TextButton(player.getUsername(), skin);
            playersTable.add(playerBtn).pad(20, 40, 20, 40);
            if (player.getCurrentLobby() != null) {
                TextButton lobbyBtn = new TextButton(player.getCurrentLobby().getName(), skin);
                playersTable.add(lobbyBtn).pad(20, 40, 20, 40);
            }
            playersTable.row();
        }
        TextButton backBtn = STab.createTextButton("back");
        backBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                App.main.setScreen(new NetMainMenu(App.main));
            }
        });
        playersTable.add(backBtn).pad(60).row();

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
