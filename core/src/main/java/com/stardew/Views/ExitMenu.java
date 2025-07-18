package com.stardew.Views;

import com.badlogic.gdx.Game;

import java.util.Scanner;

public class ExitMenu extends AppMenu {

    public ExitMenu(Game main) {
        super(main);
    }

    @Override
    public void check(Scanner scanner) {}

    @Override
    public void show() {
        table.clear();
        table.add("Exit Screen Placeholder").pad(20);
    }
}
