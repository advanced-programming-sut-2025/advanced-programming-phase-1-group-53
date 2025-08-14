package com.stardew.Messengers.MessageMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.stardew.Models.Game.App;
import com.stardew.Views.AppMenu;
import com.stardew.Views.STab;

public abstract class Messenger extends AppMenu {
    SelectBox<String> playersIndex;
    String contactName;
    Window contactWindow;

    public Messenger() {
        super(App.main);
    }

    @Override
    public void check(String scanner) {
    }

    @Override
    public void show() {
        table.clear();
        Label title = new Label("Radio Menu", skin);
        table.add(title).pad(20).row();
        TextButton sendMessageBtn = new TextButton("Send Message", skin);
        sendMessageBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                showChoosePlayerWindow();
            }
        });
        table.add(sendMessageBtn).pad(10).row();
        TextButton inboxBtn = new TextButton("Inbox", skin);
        inboxBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {

            }
        });
        table.add(inboxBtn).pad(10).row();
        TextButton joinRadio = new TextButton("Join a radio", skin);
        joinRadio.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                // TODO khosro
            }
        });
        table.add(joinRadio).pad(10).row();
        TextButton backBtn = new TextButton("Back", skin);
        backBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                if (main != null) main.setScreen(new com.stardew.Views.NetworkMenus.NetMainMenu(main));
            }
        });
        table.add(backBtn).pad(10).row();
    }

    protected void showChoosePlayerWindow() {
        java.util.List<String> playerNames = new java.util.ArrayList<>();
        for (var player : com.stardew.Models.Game.App.getInstance().getPlayers()) {
            String displayName = player.getUsername();
            if (player.getUsername().equals(com.stardew.Models.Game.App.getMyPlayer().getUsername())) {
                displayName += " (You)";
            }
            playerNames.add(displayName);
        }
        SelectBox<String> selectBox = new SelectBox<>(skin);
        selectBox.setItems(playerNames.toArray(new String[0]));
        Window window = new Window("Choose Player", skin);
        window.setSize(400, 300);
        window.setPosition((stage.getWidth() - 400) / 2f, (stage.getHeight() - 300) / 2f);
        window.add(selectBox).width(350).height(60).pad(20).row();
        TextButton closeBtn = new TextButton("Close", skin);
        closeBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                window.remove();
            }
        });
        window.add(closeBtn).pad(20);
        stage.addActor(window);
        selectBox.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                // TODO: use selectBox.getSelected() for further logic
            }
        });
    }

    public void showPlayersWindow() {
        contactWindow = new Window("Player Info", skin);
        contactWindow.setSize(400, 500);
        contactWindow.setPosition((Gdx.graphics.getWidth() - 400) / 2f, (Gdx.graphics.getHeight() - 500) / 2f);
        java.util.List<String> playerNames = new java.util.ArrayList<>();
        for (var player : App.getInstance().getPlayers()) {
            String displayName = player.getUsername();
            if (player.getUsername().equals(App.getMyPlayer().getUsername())) {
                displayName += " (You)";
            }
            playerNames.add(displayName);
        }
        SelectBox<String> selectBox = new SelectBox<>(skin);
        selectBox.setItems(playerNames.toArray(new String[0]));
        contactWindow.add(selectBox).width(350).height(60).pad(20).row();

        TextButton messagingBtn = STab.createTextButton("message");
        messagingBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                if (selectBox.getSelected() == null) {
                    return;
                }
                sendMessage(selectBox.getSelected());
            }
        });

        TextButton closeBtn = new TextButton("Close", skin);
        Window finalWindow = contactWindow;
        closeBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                finalWindow.remove();
            }
        });
        contactWindow.add(closeBtn).pad(20);
        stage.addActor(contactWindow);
    }
    protected abstract void sendMessage(String contactName);
}
