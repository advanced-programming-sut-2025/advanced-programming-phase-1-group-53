package com.stardew.Controllers;


import com.stardew.Models.Game.App;

public class MainMenuController {
    public void logout() {
        System.out.println("you rafti\nredirecting to loginMenu");
        App.setCurrentPlayer(null);
//        App.setCurrentMenu(Menu.loginRegisterMenu);
    }
}
