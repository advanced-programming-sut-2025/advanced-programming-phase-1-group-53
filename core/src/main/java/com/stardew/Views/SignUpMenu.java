package com.stardew.Views;

import com.stardew.Controllers.SignUpMenuController;
import com.stardew.Controllers.ShareController;
import com.stardew.Enums.SignUpMenuCommand;

import java.util.Scanner;
import java.util.regex.Matcher;

public class SignUpMenu implements AppMenu {
    private final SignUpMenuController controller = new SignUpMenuController();

    @Override
    public void check(String s) {
        Scanner scanner = new Scanner(System.in);
        String input = s.trim();
        Matcher matcher;

        if ((matcher = SignUpMenuCommand.register.getMatcher(input)) != null) {
            controller.register(
                matcher.group("username"),
                matcher.group("password"),
                matcher.group("passwordConfirm"),
                matcher.group("nickName"),
                matcher.group("email"),
                matcher.group("gender")
            );
        } else if ((matcher = SignUpMenuCommand.exit.getMatcher(input)) != null) {
            ShareController.exit(scanner);
        } else if ((matcher = SignUpMenuCommand.enterMenu.getMatcher(input)) != null) {
            ShareController.enterMenu(matcher.group("menu"));
        } else if ((matcher = SignUpMenuCommand.showCurrentMenu.getMatcher(input)) != null) {
            ShareController.showCurrentMenu();
        } else if ((matcher = SignUpMenuCommand.pickQuestion.getMatcher(input)) != null) {
            controller.handleQuestions(matcher.group("questionNumber"),
                matcher.group("answer"), matcher.group("answerConfirm"));
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
