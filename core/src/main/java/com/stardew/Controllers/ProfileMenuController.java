//package com.stardew.Controllers;
//
//import com.stardew.Enums.Regex;
//import com.stardew.Models.Game.App;
//import com.stardew.Models.Game.Player;
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.List;
//
//public class ProfileMenuController {
//    private final List<Player> players = App.getInstance().getPlayers();
//    private Player currentPlayer;
//
//    public Player getCurrentPlayer() {
//        return currentPlayer;
//    }
//
//    public void setCurrentPlayer(Player currentPlayer) {
//        this.currentPlayer = currentPlayer;
//    }
//
//    private Player findPlayerByUsername(String username) {
//        for (Player p : players) {
//            if (p.personalInfo.getName().equalsIgnoreCase(username)) return p;
//        }
//        return null;
//    }
//
//    public void changeUsername(String newUsername) {
//        currentPlayer = App.getCurrentPlayer();
//        if (currentPlayer == null) {
//            System.out.println("No player is currently logged in.");
//            return;
//        }
//        if (!Regex.username.regexMatcher(newUsername)) {
//            System.out.println("Invalid username format.");;
//        }else {
//            if (findPlayerByUsername(newUsername) != null) {
//                System.out.println("Username already taken.");;
//            }else {
//                currentPlayer.personalInfo.setName(newUsername);
//                App.getInstance().setPlayers(players);
//                System.out.println("Username updated successfully.");
//            }
//        }
//    }
//    public void changeNickname(String newNickname) {
//        currentPlayer = App.getCurrentPlayer();
//        if (currentPlayer == null) {
//            System.out.println("No player is currently logged in.");
//            return;
//        }
//        if (currentPlayer.personalInfo.getNickname().equalsIgnoreCase(newNickname)){
//            System.out.println("NickName already taken.");
//        }else {
//            currentPlayer.personalInfo.setNickname(newNickname);
//            App.getInstance().setPlayers(players);
//            System.out.println("Nickname updated successfully.");
//        }
//    }
//    public void changeEmail(String newEmail) {
//        currentPlayer = App.getCurrentPlayer();
//        if (currentPlayer == null) {
//            System.out.println("No player is currently logged in.");
//            return;
//        }
//        if (!Regex.email.regexMatcher(newEmail)) {
//            System.out.println("Invalid email format.");
//        }else {
//            if (currentPlayer.personalInfo.getEmail().equalsIgnoreCase(newEmail)) {
//                System.out.println("Email already taken.");
//            }else{
//                currentPlayer.personalInfo.setEmail(newEmail);
//                App.getInstance().setPlayers(players);
//                System.out.println("Email updated successfully.");
//            }
//        }
//
//    }
//    public void changePassword(String newPassword, String oldPassword) {
//        currentPlayer = App.getCurrentPlayer();
//        if (currentPlayer == null) {
//            System.out.println("No player is currently logged in.");
//            return;
//        }
//        String hashedOld = hashPassword(oldPassword);
//        if (!hashedOld.equals(currentPlayer.personalInfo.getPassword())) {
//            System.out.println("Current password is incorrect.");
//        }else {
//            if (!Regex.password.regexMatcher(newPassword)) {
//                System.out.println("New password is invalid.");
//            }else {
//                currentPlayer.personalInfo.setPassword(hashPassword(newPassword));
//                App.getInstance().setPlayers(players);
//                System.out.println("Password updated successfully.");
//            }
//        }
//    }
//
//    private String hashPassword(String password) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] encodedHash = digest.digest(password.getBytes());
//            StringBuilder hexString = new StringBuilder();
//            for (byte b : encodedHash) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) hexString.append('0');
//                hexString.append(hex);
//            }
//            return hexString.toString();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public void showUserInfo() {
//        currentPlayer = App.getCurrentPlayer();
//        if (currentPlayer == null) {
//            System.out.println("aval bia dakhel");
//            return;
//        }
//        System.out.println(String.format("Username: %s\nNickname: %s\nEmail: %s\nGender: %s", currentPlayer.personalInfo.getName(),
//                currentPlayer.personalInfo.getNickname(), currentPlayer.personalInfo.getEmail(), currentPlayer.personalInfo.getGender().toString()));
//    }
//}
