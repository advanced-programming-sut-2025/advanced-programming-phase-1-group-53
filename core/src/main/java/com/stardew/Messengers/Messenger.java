package com.stardew.Messengers;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.stardew.Models.Game.App;
import com.stardew.Views.AppMenu;

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
//    sending message methods
    protected abstract void showChoosePlayerWindow();
    protected abstract void sendMessage(String contactName);

    //receive message methods
    public abstract void showInbox();
    public abstract void receiveMessage(String boxName);
}
