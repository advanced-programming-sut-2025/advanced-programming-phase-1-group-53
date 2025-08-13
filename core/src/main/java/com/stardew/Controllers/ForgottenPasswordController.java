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

public class ForgottenPasswordController {
    public boolean changePassword(String username, String newPassword) {
        FileHandle userFile = Gdx.files.local("profiles/" + username + ".json");
        if (!userFile.exists()) return false;
        String json = userFile.readString();
        Gson gson = new Gson();
        Type playerType = new TypeToken<PersonalInfo>() {}.getType();
        PersonalInfo pI = gson.fromJson(json, playerType);
        if (!isValidPassword(newPassword)) {
            return false;
        }
        System.out.println("hello there");
        String hashedPassword = hashPassword(newPassword);
        pI.setPassword(hashedPassword);
        // Update current player if needed
        Player currentPlayer = App.getCurrentPlayer();
        if (currentPlayer != null && currentPlayer.personalInfo.getName().equalsIgnoreCase(username)) {
            currentPlayer.personalInfo.setPassword(hashedPassword);
        }
        // Save updated info
        String updatedJson = gson.toJson(pI);
        userFile.writeString(updatedJson, false);
        return true;
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
    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
            password.matches(".*[A-Z].*") &&
            password.matches(".*[0-9].*") &&
            password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
    }
}
