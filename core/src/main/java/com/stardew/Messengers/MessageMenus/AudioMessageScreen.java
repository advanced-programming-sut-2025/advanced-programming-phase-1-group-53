package com.stardew.Messengers.MessageMenus;

import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.stardew.Views.NetworkMenus.PlayersMenu;
import com.stardew.Views.STab;

public class AudioMessageScreen extends Messenger {
    @Override
    public void show() {
        super.show();
    }

    @Override
    protected void sendMessage(String contactName) {
        Window window = new Window("Send Audio Message", skin);
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

    void sendAudio(String contactName) {
        Window window = new Window("Send Audio Message", skin);
        window.setSize(400, 300);
        window.setPosition((stage.getWidth() - 400) / 2f, (stage.getHeight() - 300) / 2f);
        Table dropArea = new Table();
        Label dropLabel = new Label("Drop files here", skin);
        dropArea.add(dropLabel).expand().fill().pad(40);
        window.add(dropArea).width(350).height(100).pad(20).row();
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
