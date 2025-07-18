package com.stardew.Views;

import com.badlogic.gdx.Game;
import com.stardew.Controllers.GameMenuController;
import com.stardew.Controllers.ShareController;
import com.stardew.Enums.GameMenuCommand;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu extends AppMenu {
    private final GameMenuController controller = new GameMenuController();

    public GameMenu(Game main) {
        super(main);
    }

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        // Condensed for brevity. You may want to refactor command matching with a command-handler registry.
        if ((matcher = GameMenuCommand.exit.getMatcher(input)) != null) {
            ShareController.exit(scanner);
        } else if ((matcher = GameMenuCommand.showCurrentMenu.getMatcher(input)) != null) {
//            ShareController.showCurrentMenu();
        } else if ((matcher = GameMenuCommand.enterMenu.getMatcher(input)) != null) {
//            ShareController.enterMenu(matcher.group("menu"));
        } else {
            // ... rest of commands unchanged
        }

        controller.gameLoop();
    }

    @Override
    public void show() {
        table.clear();
        table.add("Game Menu (Map, Inventory, Time...)").pad(20);
    }
}
