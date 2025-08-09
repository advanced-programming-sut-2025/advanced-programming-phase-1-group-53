package com.stardew.Enums;

public enum Gender {
    MALE, FEMALE;

    public static Gender getGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            return MALE;
        }
        else if (gender.equalsIgnoreCase("female")) {
            return FEMALE;
        }
        else return null;
    }
}
