package com.stardew.Messengers;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.Models.Game.App;
import com.stardew.Network.Client.ClientApp;
import com.stardew.Network.Common.Packet.ClientPacket.AudioPackets.RequestAudioPacket;
import com.stardew.Network.Common.Packet.ClientPacket.AudioPackets.UploadAudioPacket;
import com.stardew.Network.Common.Packet.ClientPacket.ContactPackets.SendPrivateMessagePacket;
import com.stardew.Network.Common.Packet.ClientPacket.ContactPackets.SendPublicMessagePacket;
import com.stardew.Views.STab;

import java.util.ArrayList;

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
                continue;
            }
            playerNames.add(displayName);
        }

        SelectBox<String> selectBox = new SelectBox<>(skin);
        selectBox.setItems(playerNames.toArray(new String[0]));

        Window window = new Window("Choose Player", skin);
        window.setSize(600, 500);
        window.setPosition((stage.getWidth() - 600) / 2f, (stage.getHeight() - 500) / 2f);
        window.add(selectBox).width(500).height(80).pad(30).row();

        TextButton messagingBtn = STab.createTextButton("send request");
        messagingBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                if (selectBox.getSelected() == null) {
                    return;
                }
                ClientApp.getInstance().getConnectionThread().sendPacket(
                    new RequestAudioPacket(
                        ClientApp.getInstance().getConnectionThread().getClientId(),
                        App.getMyPlayer().getUsername(),
                        selectBox.getSelected()
                    )
                );
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
        this.contactName = contactName;
        Window window = new Window("Audio Name", skin);
        window.setSize(600, 800);
        window.setPosition((stage.getWidth() - 600) / 2f, (stage.getHeight() - 800) / 2f);
        TextField textArea = STab.createTextField("");
        window.add(textArea).width(500).height(200).pad(30).row();

        TextButton messagingBtn = STab.createTextButton("send");
        messagingBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                ClientApp.getInstance().getConnectionThread().sendPacket(
                    new UploadAudioPacket(
                        ClientApp.getInstance().getConnectionThread().getClientId(),
                        App.getMyPlayer().getUsername(),
                        textArea.getText(),
                        "", //TODO what the hell is this
                        contactName
                    )
                );
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
        SelectBox<String> selectBox = new SelectBox<>(skin);
        selectBox.setItems(App.getMyPlayer().getAudioRequests().toArray(new String[0]));

        Window window = new Window("Inbox", skin);
        window.setSize(600, 800);
        window.setPosition((stage.getWidth() - 600) / 2f, (stage.getHeight() - 800) / 2f);
        window.add(selectBox).width(500).height(80).pad(30).row();

        TextButton selectBtn = STab.createTextButton("Send Audio");
        selectBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                if (selectBox.getSelected() != null) {
                    sendMessage(selectBox.getSelected());
                }
            }
        });
        window.add(selectBtn).pad(30).row();

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
}
