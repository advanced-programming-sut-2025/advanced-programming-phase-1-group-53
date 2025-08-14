package com.stardew.Controllers;

import com.stardew.Models.Game.App;
import com.stardew.Network.Client.ClientApp;

import java.util.Scanner;

public class ShareController {
    public static void exit(Scanner scanner) {
        if (scanner != null) {
            scanner.close();
        }
        ClientApp.getInstance().getConnectionThread().end();
    }

    public static void showCurrentMenu() {
    }

    public static void enterMenu(String menu) {

    }

//    public static void showCurrentMenu() {
//        System.out.println(App.getCurrentMenu().toString());
//    }
//
//    public static void enterMenu(String input) {
////        Menu menu = Menu.findMenu(input.toLowerCase());
//        if (menu == null) {
//            System.out.println("Invalid menu name.");
//        } else {
//            App.setCurrentMenu(menu);
//            System.out.println("Entered " + menu.toString());
//        }
//    }
}
