package com.stardew.Messengers.MessageMenus;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.stardew.Models.Game.App;
import com.stardew.Views.AppMenu;
import com.stardew.Views.NetworkMenus.PlayersMenu;

public class TextMessageScreen extends Messenger {
    String message;

    @Override
    public void show() {
        super.show();
    }

    @Override
    protected void sendMessage(String ContactName) {
        Window window = new Window("Send Text Message", skin);
        window.setSize(400, 300);
        window.setPosition((stage.getWidth() - 400) / 2f, (stage.getHeight() - 300) / 2f);
        com.badlogic.gdx.scenes.scene2d.ui.TextArea textArea = new com.badlogic.gdx.scenes.scene2d.ui.TextArea("", skin);
        window.add(textArea).width(350).height(100).pad(20).row();
        TextButton closeBtn = new TextButton("Close", skin);
        closeBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                window.remove();
            }
        });
        window.add(closeBtn).pad(20);
        stage.addActor(window);
    }
}

