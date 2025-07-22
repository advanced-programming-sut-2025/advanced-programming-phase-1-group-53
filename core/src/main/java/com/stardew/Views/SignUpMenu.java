package com.stardew.Views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.stardew.Controllers.SignUpMenuController;

import java.util.Scanner;

public class SignUpMenu extends AppMenu {

    private TextField usernameField;
    private TextField nicknameField;
    private TextField emailField;
    private TextField genderField;
    private TextField passwordField;
    private TextField confirmPasswordField;
    private Label messageLabel;

    private SignUpMenuController controller;

    public SignUpMenu(Game main) {
        super(main);
        controller = new SignUpMenuController(); // Instantiate the controller
    }

    @Override
    public void check(Scanner scanner) {
        // Not used for GUI
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
        nicknameField = new TextField("", skin);
        emailField = new TextField("", skin);
        genderField = new TextField("", skin);
        passwordField = new TextField("", skin);
        confirmPasswordField = new TextField("", skin);

        usernameField.setMessageText("Username");
        nicknameField.setMessageText("Nickname");
        emailField.setMessageText("Email");
        genderField.setMessageText("Gender (MALE/FEMALE)");
        passwordField.setMessageText("Password");
        confirmPasswordField.setMessageText("Confirm Password");

        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');
        confirmPasswordField.setPasswordMode(true);
        confirmPasswordField.setPasswordCharacter('*');

        TextButton signupBtn = new TextButton("Sign Up", skin);
        TextButton backBtn = new TextButton("Back", skin);

        messageLabel = new Label("", skin);
        messageLabel.setAlignment(Align.center);
        messageLabel.setWrap(true);

        signupBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleSignup();
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
        table.add(nicknameField).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(emailField).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(genderField).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(passwordField).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(confirmPasswordField).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(signupBtn).width(buttonWidth).height(buttonHeight).pad(pad).row();
        table.add(backBtn).width(buttonWidth).height(buttonHeight).pad(pad).row();
        table.add(messageLabel).width(fieldWidth).pad(pad).row();
    }

    private void handleSignup() {
        String username = usernameField.getText().trim();
        String nickname = nicknameField.getText().trim();
        String email = emailField.getText().trim();
        String gender = genderField.getText().trim().toUpperCase();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (username.isEmpty() || nickname.isEmpty() || email.isEmpty() ||
            gender.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            messageLabel.setText("Please fill in all fields.");
            return;
        }

        // Pass values to controller
        String message = controller.register(username, password, confirmPassword, nickname, email, gender);

        System.out.println(message);
        // You could inspect controller state if needed, or redirect immediately
//        messageLabel.setText("Account created. Set security question next.");
//        usernameField.setText("");
//        nicknameField.setText("");
//        emailField.setText("");
//        genderField.setText("");
//        passwordField.setText("");
//        confirmPasswordField.setText("");

        // Optionally go to question screen or login screen
        // main.setScreen(new QuestionMenu(main, controller.getPlayer()));
    }
}
