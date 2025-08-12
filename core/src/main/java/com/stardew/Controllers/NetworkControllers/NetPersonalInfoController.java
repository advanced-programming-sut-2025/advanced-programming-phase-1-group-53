package com.stardew.Controllers.NetworkControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Models.PersonalInfo;

import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class NetPersonalInfoController {
    public boolean changePassword(String username, String newPassword) {
        FileHandle userFile = Gdx.files.local("profiles/" + username + ".json");
        if (!userFile.exists()) return false;
        String json = userFile.readString();
        Gson gson = new Gson();
        Type playerType = new TypeToken<PersonalInfo>() {}.getType();
        PersonalInfo pI = gson.fromJson(json, playerType);
        if (newPassword.equalsIgnoreCase("accidentally")) {
            newPassword = generatePassword();
            System.out.println("Generated password: " + newPassword);
        }
        else if (!isValidPassword(newPassword)) {
            return false;
        }
        String hashedPassword = hashPassword(newPassword);
        pI.setPassword(hashedPassword);
        Player currentPlayer = App.getCurrentPlayer();
        if (currentPlayer != null && currentPlayer.personalInfo.getName().equalsIgnoreCase(username)) {
            currentPlayer.personalInfo.setPassword(hashedPassword);
        }
        String updatedJson = gson.toJson(pI);
        userFile.writeString(updatedJson, false);
        return true;
    }
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
            password.matches(".*[A-Z].*") &&
            password.matches(".*[0-9].*") &&
            password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
    }

    public String generatePassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String symbols = "?><,\"';:\\/|][}{+=)(*&^%$#!";
        String all = upper + lower + digits + symbols;
        java.util.Random rand = new java.util.Random();
        StringBuilder password = new StringBuilder();
        password.append(upper.charAt(rand.nextInt(upper.length())));
        password.append(lower.charAt(rand.nextInt(lower.length())));
        password.append(digits.charAt(rand.nextInt(digits.length())));
        password.append(symbols.charAt(rand.nextInt(symbols.length())));
        for (int i = 4; i < 9; i++) {
            password.append(all.charAt(rand.nextInt(all.length())));
        }
        java.util.List<Character> chars = new java.util.ArrayList<>();
        for (char c : password.toString().toCharArray()) chars.add(c);
        java.util.Collections.shuffle(chars);
        StringBuilder shuffled = new StringBuilder();
        for (char c : chars) shuffled.append(c);
        return shuffled.toString();
    }
}
