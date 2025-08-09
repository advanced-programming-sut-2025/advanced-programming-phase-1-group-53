package com.stardew.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.stardew.Client.GameLauncher;

public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return;

//        String serverIp = args.length > 0 ? args[0] : "127.0.0.1";
//        int port = args.length > 1 ? Integer.parseInt(args[1]) : 7777;
//        String playerId = args.length > 2 ? args[2] : "Player1";
//
//        System.out.println("Client starting...");
//        System.out.println("Connecting to " + serverIp + ":" + port + " as " + playerId);

        new Lwjgl3Application(new GameLauncher(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Stardew Valley");
        configuration.useVsync(true);
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
        configuration.setWindowedMode(640, 480);
        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
        return configuration;
    }
}
