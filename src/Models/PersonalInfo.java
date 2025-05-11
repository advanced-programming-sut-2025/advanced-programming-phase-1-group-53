package Models;

import Enums.Gender;

public class PersonalInfo {
    private String email;
    private String name;
    private String password;
    private String coupleEmail;
    private Gender gender;
    private int gold;

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
}
