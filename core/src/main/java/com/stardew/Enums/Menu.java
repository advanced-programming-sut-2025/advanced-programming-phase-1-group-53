package com.stardew.Enums;

import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Views.*;

import java.util.Scanner;

public enum Menu {
    loginRegisterMenu(new LoginRegisterMenu(Main.main)),
    gameMenu(GameMenu.getInstance()),
    profileMenu(new ProfileMenu(Main.main, App.getCurrentPlayer().getPersonalInfo().getName())),
    mainMenu(new MainMenu(Main.main)),
    exitMenu(new ExitMenu(Main.main)),
    signUpMenu(new SignUpMenu(Main.main))
    ;
    private final AppMenu menu;

    Menu(AppMenu menu) {
        this.menu = menu;
    }

    public void checkCommand(String s) {
        this.menu.check(s);
    }

    public static Menu findMenu(String input) {
        switch (input) {
            case "loginregistermenu":
                return loginRegisterMenu;
            case "signupmenu":
                return signUpMenu;
            case "gamemenu":
                return gameMenu;
            case "profilemenu":
                return profileMenu;
            case "mainmenu":
                return mainMenu;
            default:
                return null;
        }
    }
}
