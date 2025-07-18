package com.stardew.Views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.stardew.Main;

import java.util.Scanner;

public class LoginRegisterMenu extends AppMenu {

    public LoginRegisterMenu(Game main) {
        super(main);
    }

    @Override
    public void check(Scanner scanner) {

    }

    @Override
    public void show() {
        super.show();
        table.clear();

        TextButton loginBtn = new TextButton("Login", skin);
        TextButton signupBtn = new TextButton("Sign Up", skin);
        TextButton exitBtn = new TextButton("Exit", skin);

        loginBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Replace with real screen
                System.out.println("Login clicked");
                // main.setScreen(new GameMenu(main));
            }
        });

        signupBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Signup clicked");
                // main.setScreen(new SignUpMenu(main));
            }
        });

        exitBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        table.add(loginBtn).pad(10).row();
        table.add(signupBtn).pad(10).row();
        table.add(exitBtn).pad(10).row();
    }
}
