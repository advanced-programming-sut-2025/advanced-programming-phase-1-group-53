package com.stardew.Views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.stardew.Controllers.LoginRegisterMenuController;
import com.stardew.Main;

import java.util.Scanner;

public class LoginRegisterMenu extends AppMenu {
    LoginRegisterMenuController controller = new LoginRegisterMenuController();

    public LoginRegisterMenu(Game main) {
        super(main);
    }

    @Override
    public void check(Scanner scanner) {
        // Not used in GUI flow
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

        // Username Field
        TextField username = new TextField("", skin);
        username.setMessageText("Username");
        username.setSize(fieldWidth, fieldHeight);

//        // Email Field
//        TextField email = new TextField("", skin);
//        email.setMessageText("Email");
//        email.setSize(fieldWidth, fieldHeight);

        // Password Field
        TextField password = new TextField("", skin);
        password.setMessageText("Enter password");
        password.setPasswordMode(true);
        password.setPasswordCharacter('*');
        password.setSize(fieldWidth, fieldHeight);

        // Buttons
        TextButton loginBtn = new TextButton("Login", skin);
        loginBtn.setSize(buttonWidth, buttonHeight);

        TextButton signupBtn = new TextButton("Sign Up", skin);
        signupBtn.setSize(buttonWidth, buttonHeight);

        TextButton exitBtn = new TextButton("Exit", skin);
        exitBtn.setSize(buttonWidth, buttonHeight);

        // Button listeners
        loginBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                System.out.println("Login clicked");
                controller.login(username.getText(), password.getText(), main);
            }
        });

        signupBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Signup clicked");
                 main.setScreen(new SignUpMenu(main));
            }
        });

        exitBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        // Layout
        table.add(username).width(fieldWidth).height(fieldHeight).pad(pad).row();
//        table.add(email).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(password).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(loginBtn).width(buttonWidth).height(buttonHeight).pad(pad).row();
        table.add(signupBtn).width(buttonWidth).height(buttonHeight).pad(pad).row();
        table.add(exitBtn).width(buttonWidth).height(buttonHeight).pad(pad).row();
    }
}
