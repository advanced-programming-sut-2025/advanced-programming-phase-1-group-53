package com.stardew.Server.Controllers;


import com.stardew.GameLogic.Enums.Menu;
import com.stardew.GameLogic.Models.Game.App;

public class MainMenuController {
    public void logout() {
        System.out.println("you rafti\nredirecting to loginMenu");
        App.setCurrentPlayer(null);
        App.setCurrentMenu(Menu.loginRegisterMenu);
    }
}
