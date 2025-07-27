package com.stardew.Views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.stardew.Models.Game.App;
import com.stardew.Models.PersonalInfo;

public class PersonalInfoScreen extends AppMenu {
    public PersonalInfoScreen(Game main) {
        super(main);
    }

    @Override
    public void check(java.util.Scanner scanner) {
        // Not used in graphical UI
    }

    @Override
    public void show() {
        table.clear();
        PersonalInfo info = App.getCurrentPlayer().getPersonalInfo();
        Label title = new Label("Personal Info", skin);
        table.add(title).pad(20).row();
        if (info != null) {
            table.add(new Label("Name: " + info.getName(), skin)).pad(10).row();
            table.add(new Label("Nickname: " + info.getNickname(), skin)).pad(10).row();
            table.add(new Label("Email: " + info.getEmail(), skin)).pad(10).row();
            table.add(new Label("Couple Email: " + (info.getCoupleEmail() != null ? info.getCoupleEmail() : "-"), skin)).pad(10).row();
            table.add(new Label("Gender: " + (info.getGender() != null ? info.getGender().toString() : "-"), skin)).pad(10).row();
            table.add(new Label("Gold: " + info.getGold(), skin)).pad(10).row();
            table.add(new Label("Security Question: " + (info.getSecurityQuestion() != null ? info.getSecurityQuestion() : "-"), skin)).pad(10).row();
            table.add(new Label("Security Answer: " + info.getSecurityAnswer(), skin)).pad(10).row();
        } else {
            table.add(new Label("No personal info available.", skin)).pad(10).row();
        }
        com.badlogic.gdx.scenes.scene2d.ui.TextButton backButton = new com.badlogic.gdx.scenes.scene2d.ui.TextButton("Back", skin);
        backButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                main.setScreen(new MainMenu(main));
            }
        });
        table.add(backButton).pad(20).row();
    }
}
