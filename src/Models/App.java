package Models;

import Enums.Menu;

public class App {
    private static Menu currentMenu = Menu.loginRegisterMenu;
    private static Game game;

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu menu) {
        currentMenu = menu;
    }
}
