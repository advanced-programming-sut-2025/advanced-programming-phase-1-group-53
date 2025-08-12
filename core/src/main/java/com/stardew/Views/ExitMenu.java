package com.stardew.Views;

import com.badlogic.gdx.Game;

public class ExitMenu extends AppMenu {

    public ExitMenu(Game main) {
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
