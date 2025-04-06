package Models;

import Enums.Gender;

import java.util.HashMap;

public abstract class Character {
    protected class move {}
    private String name;
    private String username;
    private HashMap<String, Integer> connections;
    private move[] moves;
    private Gender gender;
}
