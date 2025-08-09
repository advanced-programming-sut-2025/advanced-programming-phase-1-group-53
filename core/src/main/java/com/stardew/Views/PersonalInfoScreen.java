package com.stardew.Views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.Enums.Regex;
import com.stardew.Models.PersonalInfo;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import java.io.*;

import com.stardew.Controllers.PersonalInfoController;

public class PersonalInfoScreen extends AppMenu {
    private String usernameStr;
    private PersonalInfo info;
    private String oldUsername;
    private TextField username;
    private TextField nickname;
    private TextField email;
    private TextButton coupleEmail;
    private TextButton gender;
    private TextButton gold;
    private Label errorLabel;
    private PersonalInfoController controller = new PersonalInfoController();
    private TextButton changePasswordButton;
    private TextField newPasswordField;
    private TextField confirmPasswordField;
    private Label passwordMessageLabel;

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
            // Avatar
            username = new TextField(info.getName(), skin);
            username.setSize(400, 60);
            nickname = new TextField(info.getNickname(), skin);
            nickname.setSize(400, 60);
            // Play count
            // Gold collection record
            table.add(username).pad(20).width(400).height(60);
            table.add(nickname).pad(20).width(400).height(60).row();

            email = new TextField(info.getEmail(), skin);
            email.setSize(400, 60);
            coupleEmail = new TextButton("Couple Email: " + (info.getCoupleEmail() != null ? info.getCoupleEmail() : "-"), skin);
            coupleEmail.setSize(400, 60);
            table.add(email).pad(20).width(400).height(60);
            table.add(coupleEmail).pad(20).width(400).height(60).row();

            gender = new TextButton("Gender: " + (info.getGender() != null ? info.getGender().toString() : "-"), skin);
            gender.setSize(400, 60);
            gold = new TextButton("Gold: " + info.getGold(), skin);
            gold.setSize(400, 60);
            table.add(gender).pad(20).width(400).height(60);
            table.add(gold).pad(20).width(400).height(60).row();

            TextButton saveButton = new TextButton("Save info", skin);
            saveButton.setSize(400, 60);
            saveButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (username.getText().isEmpty() || nickname.getText().isEmpty() || email.getText().isEmpty()) {
                        errorLabel.setText("Please fill in all fields.");
                        return;
                    }
                    if (!Regex.email.regexMatcher(email.getText())) {
                        errorLabel.setText("Invalid email address.");
                        return;
                    }
                    savePersonalInfo();
                }
            });
            changePasswordButton = new TextButton("Change Password", skin);
            changePasswordButton.setSize(400, 60);
            table.add(saveButton).pad(20).width(400).height(60);
            table.add(changePasswordButton).pad(20).width(400).height(60).row();

            newPasswordField = new TextField("", skin);
            newPasswordField.setMessageText("New Password");
            newPasswordField.setPasswordMode(true);
            newPasswordField.setPasswordCharacter('*');
            newPasswordField.setSize(400, 60);
            confirmPasswordField = new TextField("", skin);
            confirmPasswordField.setMessageText("Confirm New Password");
            confirmPasswordField.setPasswordMode(true);
            confirmPasswordField.setPasswordCharacter('*');
            confirmPasswordField.setSize(400, 60);
            table.add(newPasswordField).pad(20).width(400).height(60);
            table.add(confirmPasswordField).pad(20).width(400).height(60).row();

            errorLabel = new Label("", skin);
            passwordMessageLabel = new Label("", skin);
            table.add(errorLabel).pad(20).width(400).height(60).row();
            table.add(passwordMessageLabel).pad(20).width(400).height(60).row();

            changePasswordButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (newPasswordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()) {
                        passwordMessageLabel.setText("Please fill in both password fields.");
                        return;
                    }
                    else if (!newPasswordField.getText().equals(confirmPasswordField.getText())) {
                        passwordMessageLabel.setText("Passwords do not match");
                        return;
                    }
                    boolean success = controller.changePassword(usernameStr, newPasswordField.getText());
                    if (success) {
                        passwordMessageLabel.setText("Password updated successfully!");
                    } else {
                        passwordMessageLabel.setText("Password update failed. Check requirements or username.");
                    }
                }
            });
        } else {
            table.add(new Label("No personal info available.", skin)).pad(20).width(400).height(60).row();
        }
        TextButton backButton = new TextButton("Back", skin);
        backButton.setSize(400, 60);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new MainMenu(main));
            }
        });
        table.add(backButton).pad(20).width(400).height(60).row();
    }

    private PersonalInfo loadPersonalInfo() {
        File file = new File("profiles/" + usernameStr + ".json");
        if (!file.exists()) return null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) sb.append(line);
            if (!file.delete()) {
                System.out.println("couldn't delete file " + file);
            }
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
        if (file.exists() && !username.getText().equals(usernameStr)) {
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
    }

    private void writeJsonToFile(File file, String json) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(json);
        }
    }
}
