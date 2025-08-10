package com.stardew.Views;

import com.badlogic.gdx.Game;
import com.stardew.Controllers.MainMenuController;
import com.stardew.Controllers.ShareController;
import com.stardew.Enums.MainMenuCommand;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu extends AppMenu {
    private final MainMenuController controller = new MainMenuController();

    public MainMenu(Game main) {
        super(main);
    }

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

    @Override
    public void show() {
        table.clear();
        table.add("Main Menu").pad(20);
    }
}

