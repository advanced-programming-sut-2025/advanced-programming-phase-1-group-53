package com.stardew.Views;

import com.stardew.Controllers.LoginRegisterMenuController;
import com.stardew.Controllers.ShareController;
import com.stardew.Enums.LoginRegisterMenuCommand;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginRegisterMenu implements AppMenu {
    private final LoginRegisterMenuController controller = new LoginRegisterMenuController();

    @Override
    public void check(String s) {
        Scanner scanner = new Scanner(System.in) ;
        String input = s.trim();
        Matcher matcher;

        if ((matcher = LoginRegisterMenuCommand.login.getMatcher(input)) != null) {
            String username = matcher.group("username");
            String password = matcher.group("password");
            controller.login(username, password);
        } else if ((matcher = LoginRegisterMenuCommand.exit.getMatcher(input)) != null) {
            ShareController.exit(scanner);
        } else if ((matcher = LoginRegisterMenuCommand.enterMenu.getMatcher(input)) != null) {
            ShareController.enterMenu(matcher.group("menu"));
        } else if ((matcher = LoginRegisterMenuCommand.showCurrentMenu.getMatcher(input)) != null) {
            ShareController.showCurrentMenu();
        } else if ((matcher = LoginRegisterMenuCommand.forgetPassword.getMatcher(input)) != null) {
            controller.handleForgetPassword(matcher.group("username"));
        } else if ((matcher = LoginRegisterMenuCommand.answer.getMatcher(input)) != null) {
            boolean correctAnswer = controller.handleAnswer(matcher.group("answer"));
            if (correctAnswer) {
                System.out.println("wanna we create you password?");
                String answer = scanner.nextLine().trim();
                if (answer.equalsIgnoreCase("no")) {
                    System.out.println("please enter your new password");
                    String newPassword = scanner.nextLine().trim();
                    controller.newPassword(newPassword);
                } else {
                    String newPassword = controller.generatePassword();
                    System.out.println("your new password is: " + newPassword);
                    controller.newPassword(newPassword);
                }
            }
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
