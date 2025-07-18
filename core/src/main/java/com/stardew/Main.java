package com.stardew;

import com.badlogic.gdx.Game;
import com.stardew.Views.LoginRegisterMenu;

public class Main extends Game {
    @Override
    public void create() {
        setScreen(new LoginRegisterMenu(this));  // Or any other AppMenu subclass
    }
}
