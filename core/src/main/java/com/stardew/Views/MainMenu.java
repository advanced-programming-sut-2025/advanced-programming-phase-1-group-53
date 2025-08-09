package com.stardew.Views;

import com.stardew.Controllers.MainMenuController;
import com.stardew.Controllers.ShareController;
import com.stardew.Enums.MainMenuCommand;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu implements AppMenu {
    private final MainMenuController controller = new MainMenuController();
    @Override
    public void check(String s) {
        Scanner scanner = new Scanner(System.in);
        String input = s.trim();
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

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

