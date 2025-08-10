package com.stardew.Enums;

import com.stardew.Views.*;

import java.util.Scanner;

public enum Menu {
//    loginRegisterMenu(new LoginRegisterMenu()),
//    gameMenu(GameMenu.getInstance()),
//    profileMenu(new ProfileMenu()),
//    mainMenu(new MainMenu()),
//    exitMenu(new ExitMenu()),
//    signUpMenu(new SignUpMenu())
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
//            case "loginregistermenu":
//                return loginRegisterMenu;
//            case "signupmenu":
//                return signUpMenu;
//            case "gamemenu":
//                return gameMenu;
//            case "profilemenu":
//                return profileMenu;
//            case "mainmenu":
//                return mainMenu;
            default:
                return null;
        }
    }
}
