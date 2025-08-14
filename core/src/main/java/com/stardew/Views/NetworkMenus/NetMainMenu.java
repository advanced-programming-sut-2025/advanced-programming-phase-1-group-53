package com.stardew.Views.NetworkMenus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.stardew.Controllers.NetworkControllers.NetMainMenuController;
import com.stardew.Controllers.ShareController;
import com.stardew.Models.Game.App;
import com.stardew.Views.*;


public class NetMainMenu extends AppMenu {
    private final NetMainMenuController controller = new NetMainMenuController();

    public NetMainMenu(Game main) {
        super(main);
    }

    @Override
    public void check(String scanner) {
        // Command-based input removed for graphical UI
    }

    @Override
    public void show() {
        table.clear();
        Label title = new Label("Main Menu", skin);
        table.add(title).pad(20).row();

//        TextButton personalInfoButton = new TextButton("Personal Info", skin);
//        personalInfoButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
//            @Override
//            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
//                controller.openPersonalInfoScreen(main);
//            }
//        });
//        table.add(personalInfoButton).pad(10).row();

        TextButton signupMenuBtn = new TextButton("Signup", skin);
        signupMenuBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                main.setScreen(new NetSignUpMenu(main));
            }
        });
        table.add(signupMenuBtn).pad(10).row();

        TextButton lobbyMenuBtn = new TextButton("", skin);
        lobbyMenuBtn.setText("Lobby Menu");
        lobbyMenuBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                if (App.getMyPlayer() == null) {
//                    main.setScreen(new NetMainMenu(main));
                    STab.createDialog("You need to register first!", "Dismiss").show(stage);
                    return;
                }
                main.setScreen(new LobbyMenu());
            }
        });
        table.add(lobbyMenuBtn).pad(10).row();

        TextButton onlinePlayersBtn = STab.createTextButton("Online Players");
        onlinePlayersBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                App.main.setScreen(new PlayersMenu());
            }
        });
        table.add(onlinePlayersBtn).pad(10).row();

        TextButton exitButton = new TextButton("Exit", skin);
        exitButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                ShareController.exit(null); // No Scanner needed for graphical exit
            }
        });
        table.add(exitButton).pad(10).row();

        TextButton logoutButton = new TextButton("enter phase 2", skin);
        logoutButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                main.setScreen(new LoginRegisterMenu(main));
            }
        });
        table.add(logoutButton).pad(10).row();
    }
}
