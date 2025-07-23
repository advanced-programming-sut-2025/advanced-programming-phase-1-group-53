package com.stardew.Controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stardew.Enums.Gender;
import com.stardew.Enums.Regex;
import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Views.LoginRegisterMenu;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SignUpMenuController {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String PROFILE_DIR = "profiles/";
    private final List<Player> players = App.getInstance().getPlayers();
    Player newPlayer;
    Game main;
    String message;

    public String register(String username, String password, String confirmPassword,
                           String nickname, String email, String gender, Game main) {
        this.main = main;

        if (!Regex.username.regexMatcher(username)) {
            return "Invalid username. Username can only contain letters, numbers and -";
        }
        if (isUsernameTaken(username)) {
            return "Username already exists. Please try again.";
        }

        if (!Regex.email.regexMatcher(email)) {
            return "Invalid email.";
        }

        if (password.equals("accidentally") && confirmPassword.equals("accidentally")) {
            password = generatePassword();
        } else {
            if (!isValidPassword(password)) {
                return "Password must be at least 8 chars, include upper case, number, special char.";
            }

            FileHandle profileFile = Gdx.files.local(PROFILE_DIR + username + ".json");
            if (profileFile.exists()) {
                return "Username already exists.";
            }
        }



        String hashedPassword = hashPassword(password);
        newPlayer = new Player(username, nickname, hashedPassword, email, Gender.getGender(gender));
        return finalizeRegistration(username, nickname, hashedPassword, email, Gender.getGender(gender));
    }


    public String finalizeRegistration(String username, String nickname, String hashedPassword, String email, Gender gender) {
        FileHandle profileFile = Gdx.files.local(PROFILE_DIR + username + ".json");
        newPlayer = new Player(username, nickname, hashedPassword, email, gender);

        if (profileFile.exists()) {
            return "Username already exists.";
        }
        if (!Gdx.files.local(PROFILE_DIR).exists()) {
            Gdx.files.local(PROFILE_DIR).file().mkdirs();
        }
        profileFile.writeString(gson.toJson(newPlayer.personalInfo), false);
        players.add(newPlayer);
        App.setCurrentPlayer(newPlayer);
        return "User data saved successfully.";
    }

    private boolean isUsernameTaken(String username) {
        for (Player p : players) {
            if (p.personalInfo.getName().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
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

    public static class SecurityQuestion {
        public final int index;
        public final String question;
        public SecurityQuestion(int index, String question) {
            this.index = index;
            this.question = question;
        }
    }

    public SecurityQuestion getRandomQuestionWithIndex() {
        List<String> questions = new ArrayList<>();
        questions.add("9 + 0 ="); // index 0
        questions.add("10 - 6/2 ="); // index 1
        questions.add("2 * 3 ="); // index 2
        int randomIndex = new Random().nextInt(questions.size());
        return new SecurityQuestion(randomIndex, questions.get(randomIndex));
    }

    public boolean handleQuestions(String Index, String answer, String confirmAnswer, Player newPlayer) {
        int questionIndex;
        int ans;
        int confirmAns;
        message = "";

        try {
            questionIndex = Integer.parseInt(Index);
            ans = Integer.parseInt(answer);
            confirmAns = Integer.parseInt(confirmAnswer);
        } catch (NumberFormatException e) {
            message = "Invalid question number.";
            return false;
        }
        if (!answer.equals(confirmAnswer)) {
            message = "Confirmed answer is not valid.";
            return false;
        }
        else {
            switch (questionIndex){
                case 1:
                    if (ans != 9) {
                        message = "Answer is not valid.";
                        return false;
                    }
                    newPlayer.personalInfo.setSecurityQuestion("9 + 0 =");
                    newPlayer.personalInfo.setSecurityAnswer(9);
                    break;
                case 2:
                    if (ans != 7) {
                        message = "Answer is not valid.";
                        return false;
                    }
                    newPlayer.personalInfo.setSecurityQuestion("10 - 6/2 =");
                    newPlayer.personalInfo.setSecurityAnswer(7);
                    break;
                case 3:
                    if (ans != 6) {
                        message = "Answer is not valid.";
                        return false;
                    }
                    newPlayer.personalInfo.setSecurityQuestion("2 * 3 =");
                    newPlayer.personalInfo.setSecurityAnswer(6);
                    break;
                default:
                    message = "Invalid question number.";
                    return false;
            }
            message = Index;
            return true;
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

    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
            password.matches(".*[A-Z].*") &&
            password.matches(".*[0-9].*") &&
            password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
    }
}
