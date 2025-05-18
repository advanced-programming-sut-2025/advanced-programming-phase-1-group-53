package Controllers;

import Enums.Regex;
import Models.Game.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class ProfileMenuController {
    private final String playersFilePath = "players.json";
    private final List<Player> players;
    private final Gson gson;
    private Player currentPlayer = null;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ProfileMenuController() {
        gson = new Gson();
        this.players = loadPlayersFromFile();
    }
    private List<Player> loadPlayersFromFile() {
        File file = new File(playersFilePath);
        if (!file.exists()) return new java.util.ArrayList<>();

        try (FileReader reader = new FileReader(file)) {
            Type playerListType = new TypeToken<List<Player>>() {}.getType();
            List<Player> loaded = gson.fromJson(reader, playerListType);
            return loaded != null ? loaded : new java.util.ArrayList<>();
        } catch (IOException e) {
            return new java.util.ArrayList<>();
        }
    }
    private void savePlayersToFile() {
        try (FileWriter writer = new FileWriter(playersFilePath)) {
            gson.toJson(players, writer);
        } catch (IOException e) {
            System.out.println("Error saving players.");
        }
    }
    private Player findPlayerByUsername(String username) {
        for (Player p : players) {
            if (p.personalInfo.getName().equalsIgnoreCase(username)) return p;
        }
        return null;
    }

    public void changeUsername(String newUsername) {
        if (currentPlayer == null) {
            System.out.println("No player is currently logged in.");
            return;
        }
        if (!Regex.username.regexMatcher(newUsername)) {
            System.out.println("Invalid username format.");;
        }else {
            if (findPlayerByUsername(newUsername) != null) {
                System.out.println("Username already taken.");;
            }else {
                currentPlayer.personalInfo.setName(newUsername);
                savePlayersToFile();
                System.out.println("Username updated successfully.");
            }
        }
    }
    public void changeNickname(String newNickname) {
        if (currentPlayer == null) {
            System.out.println("No player is currently logged in.");
            return;
        }
        if (currentPlayer.personalInfo.getNickname().equalsIgnoreCase(newNickname)){
            System.out.println("NickName already taken.");
        }else {
            currentPlayer.personalInfo.setNickname(newNickname);
            savePlayersToFile();
            System.out.println("Nickname updated successfully.");
        }
    }
    public void changeEmail(String newEmail) {
        if (currentPlayer == null) {
            System.out.println("No player is currently logged in.");
            return;
        }
        if (!Regex.email.regexMatcher(newEmail)) {
            System.out.println("Invalid email format.");
        }else {
            if (currentPlayer.personalInfo.getEmail().equalsIgnoreCase(newEmail)) {
                System.out.println("Email already taken.");
            }else{
                currentPlayer.personalInfo.setEmail(newEmail);
                savePlayersToFile();
                System.out.println("Email updated successfully.");
            }
        }

    }
    public void changePassword(String newPassword, String oldPassword) {
        if (currentPlayer == null) {
            System.out.println("No player is currently logged in.");
            return;
        }
        String hashedOld = hashPassword(oldPassword);
        if (!hashedOld.equals(currentPlayer.personalInfo.getPassword())) {
            System.out.println("Current password is incorrect.");
        }else {
            if (!Regex.password.regexMatcher(newPassword)) {
                System.out.println("New password is invalid.");
            }else {
                currentPlayer.personalInfo.setPassword(hashPassword(newPassword));
                savePlayersToFile();
                System.out.println("Password updated successfully.");
            }
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
    public void showUserInfo() {
        System.out.println(String.format("Username: %s\nNickname: %s\nEmail: %s\nGender: %s", currentPlayer.personalInfo.getName(),
                currentPlayer.personalInfo.getNickname(), currentPlayer.personalInfo.getEmail(), currentPlayer.personalInfo.getGender().toString()));
    }
}
