package Controllers;

import Enums.Gender;
import Enums.Menu;
import Enums.Regex;
import Models.Game.App;
import Models.Game.Player;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SignUpMenuController {
    private final List<Player> players = App.getInstance().getPlayers();
    Player newPlayer;

    public void register(String username, String password, String confirmPassword, String nickname,
                         String email, String gender) {
        if (!Regex.username.regexMatcher(username)) {
            System.out.println("Invalid username. Username can only contain letters, numbers and -");
            return;
        }
        if (isUsernameTaken(username)) {
            System.out.println("Username already exists. Please try again.");
            return;
        }
        if (!Regex.email.regexMatcher(email)){
            String[] parts = email.split("@");
            if (parts.length != 2) {
                System.out.println("You can use only one '@'.");
                return;
            }
            String localPart = parts[0];
            String domain = parts[1];
            if (Regex.EMAIL_USERNAME_VALID.regexMatcher(localPart) && Regex.NO_DOUBLE_DOTS.regexMatcher(localPart)) {
                System.out.println("Invalid email username.");
                return;
            }else {
                if (Regex.DOMAIN_VALID.regexMatcher(domain) && Regex.NO_DOUBLE_DOTS.regexMatcher(domain)) {
                    System.out.println("Invalid email domain.");
                    return;
                }else {
                    System.out.println("Invalid email.");
                    return;
                }
            }
        }
        if (password.equals("accidentally") && confirmPassword.equals("accidentally")) {
            password = generatePassword();
        }
        else {
            if (confirmPassword != null && !password.equals(confirmPassword)) {
                System.out.println("Confirmed password is not valid.");
                return;
            }
            if (!Regex.password.regexMatcher(password)) {
                if (!Regex.MINIMUM_LENGTH.regexMatcher(password)) {
                    System.out.println("Password should contain at least 8 characters");
                    return;
                } else {
                    System.out.println("Invalid password");
                    return;
                }
            }
        }

        String hashedPassword = hashPassword(password);
        newPlayer = new Player(username, nickname, hashedPassword, email, Gender.getGender(gender));
        finalizeRegistration();
        System.out.println("Registered successfully!");
        listOfQuestions();
    }

    public void finalizeRegistration() {
        players.add(newPlayer);
        App.setCurrentPlayer(newPlayer);
        System.out.println("User data saved successfully.");
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

    private void listOfQuestions() {
        final ArrayList<String> questions = new ArrayList<>();
        questions.add("9 + 0 =");
        questions.add("10 - 6/2 =");
        questions.add("2 * 3 =");
        for (String question : questions) {
            System.out.println(question);
        }
    }
    public void handleQuestions(String Index, String answer, String confirmAnswer){
        int questionIndex;
        int ans;
        int confirmAns;
        try {
            questionIndex = Integer.parseInt(Index);
            ans = Integer.parseInt(answer);
            confirmAns = Integer.parseInt(confirmAnswer);
        } catch (NumberFormatException e) {
            System.out.println("Invalid question number.");
            return;
        }
        if (!answer.equals(confirmAnswer)) {
            System.out.println("Confirmed answer is not valid.");
            return;
        }
        else {
            switch (questionIndex){
                case 1:
                    if (ans != 9) {
                        System.out.println("Answer is not valid.");
                        return;
                    }
                    newPlayer.personalInfo.setSecurityQuestion("9 + 0 =");
                    newPlayer.personalInfo.setSecurityAnswer(9);
                    break;
                case 2:
                    if (ans != 7) {
                        System.out.println("Answer is not valid.");
                        return;
                    }
                    newPlayer.personalInfo.setSecurityQuestion("10 - 6/2 =");
                    newPlayer.personalInfo.setSecurityAnswer(7);
                    break;
                case 3:
                    if (ans != 6) {
                        System.out.println("Answer is not valid.");
                        return;
                    }
                    newPlayer.personalInfo.setSecurityQuestion("2 * 3 =");
                    newPlayer.personalInfo.setSecurityAnswer(6);
                    break;
                default:
                    System.out.println("Invalid question number.");
                    return;
            }
            System.out.println("Security question saved successfully.\nRedirecting to login menu...");
            App.setCurrentMenu(Menu.loginRegisterMenu);
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
