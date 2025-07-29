package com.stardew.Controllers;


import com.badlogic.gdx.Game;
import com.stardew.Models.Game.App;

public class MainMenuController {
    public void openPersonalInfoScreen(Game main) {
        main.setScreen(new com.stardew.Views.PersonalInfoScreen(main, App.getCurrentPlayer().getPersonalInfo().getName()));
    }
}
