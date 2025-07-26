package com.stardew.Views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.stardew.Controllers.ForgottenPasswordController;
import com.stardew.Controllers.LoginRegisterMenuController;
import com.stardew.Main;

import java.util.Scanner;

public class ForgottenPassword extends AppMenu{
    private final ForgottenPasswordController controller;
    private TextField usernameField;
    private TextField emailField;
    private TextField passwordField;
    private TextField confirmPasswordField;
    private Label messageLabel;

    public ForgottenPassword(Game main) {
        super(main);
        controller = new ForgottenPasswordController();
    }

    @Override
    public void show() {
        super.show();
        table.clear();

        float fieldWidth = 400f;
        float fieldHeight = 50f;
        float buttonWidth = 300f;
        float buttonHeight = 60f;
        float pad = 20f;

        usernameField = new TextField("", skin);
        emailField = new TextField("", skin);
        passwordField = new TextField("", skin);
        confirmPasswordField = new TextField("", skin);
        messageLabel = new Label("", skin);
        messageLabel.setAlignment(Align.center);

        usernameField.setMessageText("Username");
        emailField.setMessageText("Email");
        passwordField.setMessageText("New password");
        confirmPasswordField.setMessageText("Confirm new Password");

        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');
        confirmPasswordField.setPasswordMode(true);
        confirmPasswordField.setPasswordCharacter('*');

        TextButton saveBtn = new TextButton("Save changes", skin);
        TextButton backBtn = new TextButton("Back", skin);


        messageLabel = new Label("", skin);
        messageLabel.setAlignment(Align.center);
        messageLabel.setWrap(true);


        saveBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!passwordField.getText().equals(confirmPasswordField.getText())) {
                    messageLabel.setText("Passwords do not match");
                    return;
                }
                boolean success = controller.changePassword(usernameField.getText(), passwordField.getText());
                if (success) {
                    messageLabel.setText("Password updated successfully!");
                } else {
                    messageLabel.setText("Password update failed. Check requirements or username.");
                }
            }
        });

        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.setScreen(new LoginRegisterMenu(main));
            }
        });

        // Layout
        table.add(usernameField).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(emailField).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(passwordField).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(confirmPasswordField).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(saveBtn).width(buttonWidth).height(buttonHeight).pad(pad).row();
        table.add(backBtn).width(buttonWidth).height(buttonHeight).pad(pad).row();
        table.add(messageLabel).width(fieldWidth).pad(pad).row();
    }

    @Override
    public void check(Scanner scanner) {

    }
}
