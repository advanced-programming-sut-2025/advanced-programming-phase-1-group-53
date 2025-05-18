package Controllers;

import Enums.Menu;
import Enums.Regex;
import Models.Game.App;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LoginRegisterMenuController {
    private final String playersFilePath = "players.json";
    private final String sessionFilePath = "session.json";
    private final Gson gson;
    private List<Player> players;
    private Player temporaryPlayer = null;
    Player player = null;
    public LoginRegisterMenuController() {
        gson = new Gson();
        players = loadPlayersFromFile();
        App.getInstance().setPlayers(players);
    }
    private ArrayList<Player> loadPlayersFromFile() {
        File file = new File(playersFilePath);
        if (!file.exists()) return new ArrayList<>();

        try (FileReader reader = new FileReader(file)) {
            Type type = new TypeToken<ArrayList<Player>>() {}.getType();
            ArrayList<Player> loaded = gson.fromJson(reader, type);
            return loaded != null ? loaded : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void login(String username, String password, boolean stayLoggedIn) {
        for (Player p : players) {
            if (p.personalInfo.getName().equalsIgnoreCase(username)) {
                String hashedPassword = hashPassword(password);
                if (p.personalInfo.getPassword().equals(hashedPassword)) {
                    if (stayLoggedIn) saveSession(p);
                    App.setCurrentPlayer(p);
                    App.setCurrentMenu(Menu.mainMenu);
                    System.out.println("User logged in successfully!");
                } else {
                    player = p;
                    System.out.println("Incorrect password.");
                }
            }
        }
        System.out.println("Username not found.");
    }
    private void saveSession(Player player) {
        try (FileWriter writer = new FileWriter(sessionFilePath)) {
            gson.toJson(player, writer);
        } catch (IOException e) {
            System.out.println("Failed to save session.");
        }
    }
    public void handleForgetPassword(String username) {
        for (Player p : players) {
            if (p.personalInfo.getName().equalsIgnoreCase(username)) {
                temporaryPlayer = p;
            }else {
                System.out.println("Username not found.");
            }
        }
    }
    public boolean handleAnswer(String answer) {
        if (temporaryPlayer == null) {
            System.out.println("No reset in progress. Use 'Forget Password' first.");
            return false;
        } else
            System.out.println(player.personalInfo.getSecurityQuestion());
        if (answer.equalsIgnoreCase(player.personalInfo.getSecurityAnswer())) {
            System.out.println("Please enter your new password.");
            return true;
        }
        temporaryPlayer = null;
        App.setCurrentMenu(Menu.loginRegisterMenu);
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
            player.personalInfo.setPassword(password);
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
