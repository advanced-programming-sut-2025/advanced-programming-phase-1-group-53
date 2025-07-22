package com.stardew.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stardew.Enums.Regex;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Models.PersonalInfo;

import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LoginRegisterMenuController {
    private final List<Player> players = App.getInstance().getPlayers();
    private Player temporaryPlayer = null;
    Player player = null;

    public String login(String username, String password) {
        FileHandle userFile = Gdx.files.local("profiles/" + username + ".json");
        if (!userFile.exists()) {
            return "User not found.";
        }

        String json = userFile.readString();
        Gson gson = new Gson();

        Type playerType = new TypeToken<PersonalInfo>() {}.getType();

        PersonalInfo pI = gson.fromJson(json, playerType);

        Player p = new Player(pI);

        if (p.personalInfo.getName().equalsIgnoreCase(username)) {
            String hashedPassword = hashPassword(password);
            if (p.personalInfo.getPassword().equals(hashedPassword)) {
                App.setCurrentPlayer(p);
//                    App.setCurrentMenu(Menu.mainMenu);
//                main.setScreen(new MainMenu(main));
                return "User logged in successfully!";
            } else {
                player = p;
                return "Incorrect password.";
            }
        }

        return "Username not found.";
    }

    public void handleForgetPassword(String username) {
        for (Player p : players) {
            if (p.personalInfo.getName().equalsIgnoreCase(username)) {
                temporaryPlayer = p;
                System.out.println("answer the security question: ");
                return;
            }
        }
        System.out.println("Username not found.");
    }
    public boolean handleAnswer(String answer) {
        if (temporaryPlayer == null) {
            System.out.println("No reset in progress. Use 'Forget Password' first.");
            return false;
        } else {
            if (Integer.parseInt(answer) == temporaryPlayer.personalInfo.getSecurityAnswer()) {
                System.out.println("Please enter your new password.");
                return true;
            }
        }
        temporaryPlayer = null;
        return false;
    }

    public void newPassword(String password){
        if (!Regex.password.regexMatcher(password)) {
            if (!Regex.MINIMUM_LENGTH.regexMatcher(password)) {
                System.out.println("Password should contain at least 8 characters");
            }else {
                System.out.println("Invalid password");
            }
        }else {
            password = hashPassword(password);
            temporaryPlayer.personalInfo.setPassword(password);
            System.out.println("Password updated successfully!");
        }

    }
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public String generatePassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String symbols = "? > < , \" ' ; : \\/ | ] [ } { + = ) ( * & ^ % $ # !";
        String all = upper + lower + digits + symbols;

        Random rand = new Random();
        StringBuilder password = new StringBuilder();
        password.append(upper.charAt(rand.nextInt(upper.length())));
        password.append(lower.charAt(rand.nextInt(lower.length())));
        password.append(digits.charAt(rand.nextInt(digits.length())));
        password.append(symbols.charAt(rand.nextInt(symbols.length())));

        for (int i = 4; i < 9; i++) {
            password.append(all.charAt(rand.nextInt(all.length())));
        }

        List<Character> chars = new ArrayList<>();
        for (char c : password.toString().toCharArray()) chars.add(c);
        Collections.shuffle(chars);

        StringBuilder shuffled = new StringBuilder();
        for (char c : chars) shuffled.append(c);

        return shuffled.toString();
    }
}
