package com.stardew.Client.Views;

import com.stardew.Server.Controllers.MainMenuController;
import com.stardew.Server.Controllers.ShareController;
import com.stardew.GameLogic.Enums.MainMenuCommand;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu implements AppMenu {
    private final MainMenuController controller = new MainMenuController();
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        if ((matcher = MainMenuCommand.exit.getMatcher(input)) != null) {
            ShareController.exit(scanner);
        } else if ((matcher = MainMenuCommand.logout.getMatcher(input)) != null) {
            controller.logout();
        } else if ((matcher = MainMenuCommand.enterMenu.getMatcher(input)) != null) {
            ShareController.enterMenu(matcher.group("menu"));
        } else if ((matcher = MainMenuCommand.showCurrentMenu.getMatcher(input)) != null) {
            ShareController.showCurrentMenu();
        } else {
            System.out.println("invalid command");
        }
    }
}

