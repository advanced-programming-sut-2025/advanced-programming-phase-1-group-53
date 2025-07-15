package com.stardew.Client.Views;

import com.stardew.GameLogic.Enums.Menu;
import com.stardew.GameLogic.Models.Game.App;

import java.util.Scanner;

public class AppView {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        do {
            App.getCurrentMenu().checkCommand(scanner);
        } while (App.getCurrentMenu() != Menu.exitMenu);
    }
}
