package com.stardew;

import com.badlogic.gdx.Game;
import com.stardew.Views.LoginRegisterMenu;

public class Main extends Game {
    public final static Main main = new Main();

    @Override
    public void create() {
        setScreen(new LoginRegisterMenu(this));  // Or any other AppMenu subclass
    }
}
