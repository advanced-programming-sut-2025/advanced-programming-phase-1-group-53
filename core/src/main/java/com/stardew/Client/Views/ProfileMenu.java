package com.stardew.Client.Views;

import com.stardew.Server.Controllers.ProfileMenuController;
import com.stardew.Server.Controllers.ShareController;
import com.stardew.GameLogic.Enums.ProfileMenuCommand;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu implements AppMenu {
    private final ProfileMenuController controller = new ProfileMenuController();
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        if ((matcher = ProfileMenuCommand.exit.getMatcher(input)) != null) {
            ShareController.exit(scanner);
        } else if ((matcher = ProfileMenuCommand.changePassword.getMatcher(input)) != null) {
            // expects old and new password from user input after command
            System.out.print("Enter old password: ");
            String oldPassword = scanner.nextLine().trim();
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine().trim();
            controller.changePassword(newPassword, oldPassword);
        } else if ((matcher = ProfileMenuCommand.showCurrentMenu.getMatcher(input)) != null) {
            ShareController.showCurrentMenu();
        } else if ((matcher = ProfileMenuCommand.enterMenu.getMatcher(input)) != null) {
            ShareController.enterMenu(matcher.group("menu"));
        } else if ((matcher = ProfileMenuCommand.changeUsername.getMatcher(input)) != null) {
            controller.changeUsername(matcher.group("username"));
        } else if ((matcher = ProfileMenuCommand.changeEmail.getMatcher(input)) != null) {
            controller.changeEmail(matcher.group("email"));
        } else if ((matcher = ProfileMenuCommand.changeNickname.getMatcher(input)) != null) {
            controller.changeNickname(matcher.group("nickname"));
        } else if ((matcher = ProfileMenuCommand.showInfo.getMatcher(input)) != null) {
            controller.showUserInfo();
        } else {
            System.out.println("invalid command");
        }
    }
}
