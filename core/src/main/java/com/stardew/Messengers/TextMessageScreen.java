package com.stardew.Messengers;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.stardew.Models.Game.App;
import com.stardew.Network.Client.ClientApp;
import com.stardew.Network.Common.Packet.ClientPacket.ContactPackets.SendPrivateMessagePacket;
import com.stardew.Network.Common.Packet.ClientPacket.ContactPackets.SendPublicMessagePacket;
import com.stardew.Views.STab;

public class TextMessageScreen extends Messenger {
    String message;

    @Override
    public void show() {
        super.show();
    }


    @Override
    protected void showChoosePlayerWindow() {
        java.util.List<String> playerNames = new java.util.ArrayList<>();
        for (var player : com.stardew.Models.Game.App.getInstance().getPlayers()) {
            String displayName = player.getUsername();
            playerNames.add("public");
            if (player.getUsername().equals(com.stardew.Models.Game.App.getMyPlayer().getUsername())) {
                displayName += " (You)";
            }
            playerNames.add(displayName);
        }

        SelectBox<String> selectBox = new SelectBox<>(skin);
        selectBox.setItems(playerNames.toArray(new String[0]));

        Window window = new Window("Choose Player", skin);
        window.setSize(600, 800);
        window.setPosition((stage.getWidth() - 600) / 2f, (stage.getHeight() - 800) / 2f);
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
    protected void sendMessage(String contactName) {
        this.contactName = contactName;
        Window window = new Window("Send Text Message", skin);
        window.setSize(600, 800);
        window.setPosition((stage.getWidth() - 600) / 2f, (stage.getHeight() - 800) / 2f);
        com.badlogic.gdx.scenes.scene2d.ui.TextArea textArea = new com.badlogic.gdx.scenes.scene2d.ui.TextArea("", skin);
        window.add(textArea).width(500).height(200).pad(30).row();

        TextButton messagingBtn = STab.createTextButton("send");
        messagingBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                if (contactName.equalsIgnoreCase("public")) {
                    ClientApp.getInstance().getConnectionThread().sendPacket(
                        new SendPublicMessagePacket(App.getMyPlayer(), textArea.getText(), contactName, true)
                    );
                }
                else {
                    ClientApp.getInstance().getConnectionThread().sendPacket(
                        new SendPrivateMessagePacket(App.getMyPlayer(), textArea.getText(), contactName)
                    );
                }
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
    public void showInbox() {

    }

    @Override
    public void receiveMessage(MessageBox box) {

    }
}
