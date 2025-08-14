package com.stardew.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.stardew.Main;

public class DesktopLauncher {
    public static void main (String[] arg) {
        Main.getInstance().setPlayerId(arg[0]);

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("My Game");
        config.setWindowedMode(1600, 1600);
        config.useVsync(true);
        new Lwjgl3Application(Main.getInstance(), config);

    }
}
