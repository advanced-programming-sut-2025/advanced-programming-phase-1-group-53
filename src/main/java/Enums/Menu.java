package Enums;

import Views.*;

import java.util.Scanner;

public enum Menu {
    loginRegisterMenu(new LoginRegisterMenu()),
    gameMenu(new GameMenu()),
    profileMenu(new ProfileMenu()),
    mainMenu(new MainMenu()),
    exitMenu(new ExitMenu()),
    signUpMenu(new SignUpMenu());
    private final AppMenu menu;

    Menu(AppMenu menu) {
        this.menu = menu;
    }

    public void checkCommand(Scanner scanner) {
        this.menu.check(scanner);
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
