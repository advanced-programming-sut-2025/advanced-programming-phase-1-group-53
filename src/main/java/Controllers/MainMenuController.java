package Controllers;


import Enums.Menu;
import Models.Game.App;

public class MainMenuController {
    public void logout() {
        App.setCurrentPlayer(null);
        App.setCurrentMenu(Menu.loginRegisterMenu);
    }
}
