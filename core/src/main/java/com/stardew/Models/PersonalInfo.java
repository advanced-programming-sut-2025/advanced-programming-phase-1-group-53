package com.stardew.Models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stardew.Enums.Gender;

public class PersonalInfo {
    private String email;
    private String name;
    private String nickname;
    private String password;
    private String coupleEmail = null;
    private Gender gender;
    private int gold = 22222220;
    private String securityQuestion;
    private int securityAnswer;
    private String connectionId;

    public PersonalInfo(String email, String name, String nickname, String password, Gender gender, String connectionId) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.gender = gender;
        this.connectionId = connectionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCoupleEmail() {
        return coupleEmail;
    }

    public void setCoupleEmail(String coupleEmail) {
        this.coupleEmail = coupleEmail;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public int getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(int securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateGold(int a){
        gold += a;
    }

    public boolean hasEnoughGold(int a){
        if( gold >= a)
            return true;
        return false;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public static PersonalInfo fromJson(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, PersonalInfo.class);
    }
}

