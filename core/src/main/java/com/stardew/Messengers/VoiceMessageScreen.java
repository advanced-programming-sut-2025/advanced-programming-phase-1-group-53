package com.stardew.Messengers;

import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.stardew.Views.STab;

public class VoiceMessageScreen extends Messenger {
    @Override
    public void show() {
        super.show();
    }

    @Override
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
        window.setSize(600, 500);
        window.setPosition((stage.getWidth() - 600) / 2f, (stage.getHeight() - 500) / 2f);
        window.add(selectBox).width(500).height(80).pad(30).row();

        TextButton messagingBtn = STab.createTextButton("send");
        messagingBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                if (selectBox.getSelected() == null) {
                    return;
                }
                sendMessage(selectBox.getSelected());
            }
        });
        window.add(messagingBtn).pad(30).row();

        TextButton closeBtn = new TextButton("Close", skin);
        closeBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                window.remove();
            }
        });
        window.add(closeBtn).pad(30);
        stage.addActor(window);
    }

    @Override
    public void sendMessage(String contactName) {
        Window window = new Window("Send Audio Message", skin);
        window.setSize(600, 500);
        window.setPosition((stage.getWidth() - 600) / 2f, (stage.getHeight() - 500) / 2f);
        Table dropArea = new Table();
        Label dropLabel = new Label("Drop files here", skin);
        dropArea.add(dropLabel).expand().fill().pad(40);
        window.add(dropArea).width(500).height(200).pad(30).row();
        TextButton closeBtn = new TextButton("Close", skin);
        closeBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                window.remove();
            }
        });
        window.add(closeBtn).pad(30);
        stage.addActor(window);
    }

    @Override
    public void showInbox() {

    }

    @Override
    public void receiveMessage(MessageBox box) {

    }
}
