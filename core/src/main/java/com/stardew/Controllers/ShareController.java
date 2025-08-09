package com.stardew.Controllers;

import com.stardew.Enums.Menu;
import com.stardew.Models.Game.App;

import java.util.Scanner;

public class ShareController {
    public static void exit(Scanner scanner) {
        scanner.close();
        App.setCurrentMenu(Menu.exitMenu);
    }

    public static void showCurrentMenu() {
        System.out.println(App.getCurrentMenu().toString());
    }

    public static void enterMenu(String input) {
        Menu menu = Menu.findMenu(input.toLowerCase());
        if (menu == null) {
            System.out.println("Invalid menu name.");
        } else {
            App.setCurrentMenu(menu);
            System.out.println("Entered " + menu.toString());
        }
    }
}
