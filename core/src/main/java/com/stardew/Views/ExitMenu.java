package com.stardew.Views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class ExitMenu extends AppMenu {

    public ExitMenu(Game main) {
        super(main);
    }

    @Override
    public void check(String scanner) {}

    @Override
    public void show() {
        table.clear();
        Gdx.app.exit();
    }
}
