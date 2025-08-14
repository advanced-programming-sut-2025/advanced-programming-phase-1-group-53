package com.stardew.Controllers;


import com.badlogic.gdx.Game;
import com.stardew.Models.Game.App;
import com.stardew.Views.ProfileMenu;

public class MainMenuController {
    public void openPersonalInfoScreen(Game main) {
        main.setScreen(new ProfileMenu(main, App.getMyPlayer().getPersonalInfo().getName()));
    }
}
