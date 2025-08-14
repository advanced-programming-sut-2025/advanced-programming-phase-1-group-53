package com.stardew.Views.NetworkMenus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
        //TODO Khosro: close the related connection threads
    }
}
