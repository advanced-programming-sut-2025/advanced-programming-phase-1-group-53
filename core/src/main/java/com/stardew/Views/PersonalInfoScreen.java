package com.stardew.Views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.Models.PersonalInfo;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import java.io.*;
import com.badlogic.gdx.Gdx;

public class PersonalInfoScreen extends AppMenu {
    private String usernameStr;
    private PersonalInfo info;
    private TextField username;
    private TextField nickname;
    private TextField email;
    private TextField coupleEmail;
    private TextField gender;
    private TextField gold;
    private Label errorLabel;

    public PersonalInfoScreen(Game main, String usernameStr) {
        super(main);
        this.usernameStr = usernameStr;
        this.info = loadPersonalInfo();
    }

    @Override
    public void check(java.util.Scanner scanner) {
    }

    @Override
    public void show() {
        table.clear();
        Label title = new Label("Personal Info", skin);
        table.add(title).pad(20).row();
        if (info != null) {
            username = new TextField("Name: " + info.getName(), skin);
            table.add(username).pad(10).row();
            nickname = new TextField("Nickname: " + info.getNickname(), skin);
            table.add(nickname).pad(10).row();
            email = new TextField("Email: " + info.getEmail(), skin);
            table.add(email).pad(10).row();
            coupleEmail = new TextField("Couple Email: " + (info.getCoupleEmail() != null ? info.getCoupleEmail() : "-"), skin);
            table.add(coupleEmail).pad(10).row();
            gender = new TextField("Gender: " + (info.getGender() != null ? info.getGender().toString() : "-"), skin);
            table.add(gender).pad(10).row();
            gold = new TextField("Gold: " + info.getGold(), skin);
            table.add(gold).pad(10).row();
            TextButton saveButton = new TextButton("Save info", skin);
            saveButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    savePersonalInfo();
                }
            });
            table.add(saveButton).pad(20).row();
            errorLabel = new Label("", skin);
            table.add(errorLabel).pad(10).row();
        } else {
            table.add(new Label("No personal info available.", skin)).pad(10).row();
        }
        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new MainMenu(main));
            }
        });
        table.add(backButton).pad(20).row();
    }

    private PersonalInfo loadPersonalInfo() {
        File file = new File("profiles/" + usernameStr + ".json");
        if (!file.exists()) return null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) sb.append(line);
            file.delete();
            return PersonalInfo.fromJson(sb.toString());
        } catch (Exception e) {
            return null;
        }
    }

    private void savePersonalInfo() {
        if (writePersonalInfoToFile()) {
            errorLabel.setText("Saved successfully.");
        }
    }

    private boolean writePersonalInfoToFile() {
        File file = new File("profiles/" + usernameStr + ".json");
        if (file.exists()) {
            errorLabel.setText("Error: File already exists!");
            return false;
        }
        try {
            updatePersonalInfoFromFields();
            writeJsonToFile(file, info.toJson());
            return true;
        } catch (Exception e) {
            errorLabel.setText("Error saving info: " + e.getMessage());
            return false;
        }
    }

    private void updatePersonalInfoFromFields() {
        info.setName(username.getText());
        info.setNickname(nickname.getText());
        info.setEmail(email.getText());
        info.setCoupleEmail(coupleEmail.getText().isEmpty() ? null : coupleEmail.getText());
        info.setGold(Integer.parseInt(gold.getText().replaceAll("\\D", "")));
    }

    private void writeJsonToFile(File file, String json) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(json);
        }
    }
}
