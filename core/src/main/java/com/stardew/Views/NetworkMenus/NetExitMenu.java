package com.stardew.Views.NetworkMenus;

import com.badlogic.gdx.Game;
import com.stardew.Views.AppMenu;

public class NetExitMenu extends AppMenu {

    public NetExitMenu(Game main) {
        super(main);
    }

    @Override
    public void check(String scanner) {}

    @Override
    public void show() {
        table.clear();
        table.add("Exit Screen Placeholder").pad(20);
    }
}