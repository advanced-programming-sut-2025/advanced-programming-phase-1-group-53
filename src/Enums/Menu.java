package Enums;

import Views.*;

import java.util.Scanner;

public enum Menu {
    loginRegisterMenu(new LoginRegisterMenu()),
    gameMenu(new GameMenu()),
    profileMenu(new ProfileMenu()),
    mainMenu(new MainMenu()),
    exitMenu(new ExitMenu());
    private final AppMenu menu;

    Menu(AppMenu menu) {
        this.menu = menu;
    }

    public void checkCommand(Scanner scanner) {
        this.menu.check(scanner);
    }
}
