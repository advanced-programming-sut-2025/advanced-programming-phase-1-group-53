package com.stardew.Views.NetworkMenus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Network.Client.ClientApp;
import com.stardew.Network.Server.ServerApp;
import com.stardew.Views.AppMenu;

public class NetExitMenu extends AppMenu {

    public NetExitMenu(Game main) {
        super(main);
    }

    @Override
    public void check(String scanner) {}

    @Override
    public void show() {
        Gdx.app.exit();
        Player player = App.getMyPlayer();
        App.getInstance().getPlayers().remove(player);
        ServerApp.getInstance().getConnections().get(player.personalInfo.getConnectionId()).end();
        ClientApp.getInstance().getConnectionThread().end();
    }
}
