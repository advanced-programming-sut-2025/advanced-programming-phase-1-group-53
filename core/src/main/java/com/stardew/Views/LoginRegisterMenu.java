package com.stardew.Views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.stardew.Controllers.LoginRegisterMenuController;
import com.stardew.Models.Game.App;
import com.stardew.Views.NetworkMenus.NetSignUpMenu;

public class LoginRegisterMenu extends AppMenu {
    LoginRegisterMenuController controller = new LoginRegisterMenuController();

    public LoginRegisterMenu(Game main) {
        super(main);
    }

    @Override
    public void check(String scanner) {
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

        TextButton continueBtn = new TextButton("Continue", skin);
        continueBtn.setSize(buttonWidth, buttonHeight);

        TextButton forgotPasswordBtn = new TextButton("Forgot Password", skin);
        forgotPasswordBtn.setSize(buttonWidth, buttonHeight);

        TextButton logoutBtn = new TextButton("Logout", skin);
        logoutBtn.setSize(buttonWidth, buttonHeight);

        TextButton phase3Btn = new TextButton("Enter Phase 3", skin);
        phase3Btn.setSize(2*buttonWidth, 2*buttonHeight);


        Label messageLabel = new Label("", skin);
//        messageLabel.setColor(Color.RED);

        // Button listeners
        loginBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                System.out.println("Login clicked");
                messageLabel.setText(controller.login(username.getText(), password.getText(), main));
                System.out.println(messageLabel.getText());
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

        continueBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String result = controller.loginWithLastUser();
                messageLabel.setText(result);
                System.out.println(result);
                main.setScreen(new MainMenu(main));
            }
        });

        forgotPasswordBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.setScreen(new ForgottenPassword(main));
            }
        });

        logoutBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                App.setCurrentPlayer(null);
                messageLabel.setText("Logged out successfully.");
                System.out.println("Logged out successfully.");
            }
        });

        phase3Btn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                App.main.setScreen(new NetSignUpMenu(App.main));
            }
        });

        // Layout
        table.add(continueBtn).width(buttonWidth).height(buttonHeight).pad(pad).row();
        table.add(messageLabel).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(username).width(fieldWidth).height(fieldHeight).pad(pad).row();
//        table.add(email).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(password).width(fieldWidth).height(fieldHeight).pad(pad).row();
        table.add(loginBtn).width(buttonWidth).height(buttonHeight).pad(pad).row();
        table.add(signupBtn).width(buttonWidth).height(buttonHeight).pad(pad).row();
        table.add(forgotPasswordBtn).width(buttonWidth).height(buttonHeight).pad(pad).row();
        table.add(logoutBtn).width(buttonWidth).height(buttonHeight).pad(pad).row();
        table.add(exitBtn).width(buttonWidth).height(buttonHeight).pad(pad).row();
        table.add(phase3Btn).width(buttonWidth).height(buttonHeight).pad(pad).row();
    }
}
