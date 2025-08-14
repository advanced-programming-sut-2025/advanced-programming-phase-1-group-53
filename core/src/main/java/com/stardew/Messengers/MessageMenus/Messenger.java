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
                //TODO
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

    protected abstract void showChoosePlayerWindow();
    protected abstract void sendMessage(String contactName);
}
