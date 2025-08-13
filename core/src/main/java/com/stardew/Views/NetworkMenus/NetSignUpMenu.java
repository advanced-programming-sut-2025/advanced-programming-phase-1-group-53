package com.stardew.Views.NetworkMenus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.stardew.Controllers.NetworkControllers.NetSignupController;
import com.stardew.Controllers.SignUpMenuController;
import com.stardew.Models.Game.App;
import com.stardew.Views.AppMenu;
import com.stardew.Views.LoginRegisterMenu;

public class NetSignUpMenu extends AppMenu {

    private TextField usernameField;
    private TextField nicknameField;
    private TextField emailField;
    private TextField genderField;
    private TextField passwordField;
    private TextField confirmPasswordField;
    private TextField securityAnswerField;
    private Label securityQuestionLabel;
    private Label messageLabel;

    private NetSignupController controller;
    private int securityQuestionIndex; // Store the index of the shown question

    public NetSignUpMenu(Game main) {
        super(main);
        controller = new NetSignupController(); // Instantiate the controller
    }

    @Override
    public void check(String scanner) {
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

        usernameField = com.stardew.Views.STab.createTextField("");
        nicknameField = com.stardew.Views.STab.createTextField("");
        emailField = com.stardew.Views.STab.createTextField("");
        genderField = com.stardew.Views.STab.createTextField("");
        passwordField = com.stardew.Views.STab.createTextField("");
        confirmPasswordField = com.stardew.Views.STab.createTextField("");

        usernameField.setMessageText("Username");
        nicknameField.setMessageText("Nickname");
        emailField.setMessageText("Email");
        genderField.setMessageText("Gender (MALE/FEMALE)");
        passwordField.setMessageText("Password");
        confirmPasswordField.setMessageText("Confirm Password");

        usernameField.setText("Username");
        nicknameField.setText("Nickname");
        emailField.setText("Email@gmail.com");
        genderField.setText("MALE");
        passwordField.setText("dgaG12#A");
        confirmPasswordField.setText("Cjnkjsd");
        //TODO erase the code above

        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');
        confirmPasswordField.setPasswordMode(true);
        confirmPasswordField.setPasswordCharacter('*');

        TextButton signupBtn = com.stardew.Views.STab.createTextButton("Sign Up", (int)buttonWidth, (int)buttonHeight);
        TextButton backBtn = com.stardew.Views.STab.createTextButton("Back", (int)buttonWidth, (int)buttonHeight);


        // Get a random question and its index from the controller
        SignUpMenuController.SecurityQuestion securityQuestion = controller.getRandomQuestionWithIndex();
        securityQuestionIndex = securityQuestion.index;
        securityQuestionLabel = new Label(securityQuestion.question, skin);
        securityAnswerField = com.stardew.Views.STab.createTextField("");
        securityAnswerField.setMessageText("Answer");


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
                main.setScreen(new NetMainMenu(main));
            }
        });

        // Layout
        table.add(usernameField).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(nicknameField).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(emailField).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(genderField).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(passwordField).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(confirmPasswordField).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(securityQuestionLabel).width(fieldWidth).pad(pad).row();
        table.add(securityAnswerField).width(fieldWidth).height(fieldHeight).pad(pad).row();
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
        String answer = securityAnswerField.getText().trim();

        if (username.isEmpty() || nickname.isEmpty() || email.isEmpty() ||
            gender.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || answer.isEmpty()) {
            messageLabel.setText("Please fill in all fields.");
            return;
        }

        // Check the answer for the security question
        boolean answerCorrect = false;
        controller.setSecurityQuestionIndex(securityQuestionIndex);
        switch (controller.getSecurityQuestionIndex()) {
            case 0:
                answerCorrect = answer.equals("9");
                break;
            case 1:
                answerCorrect = answer.equals("7");
                break;
            case 2:
                answerCorrect = answer.equals("6");
                break;
        }
        if (!answerCorrect) {
            messageLabel.setText("Incorrect answer to the security question.");
//            return;
        }

        // Pass values to controller
        String message = controller.register(username, password, confirmPassword, nickname, email, gender, main);

        System.out.println(message);
        if (!message.isEmpty()) {
            messageLabel.setText(message);
            return;
        }
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
