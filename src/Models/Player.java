package Models;

import Enums.Gender;

import java.util.ArrayList;

public class Player {
    private String name;
    private String username;
    private final Gender gender;
    private Integer gold;
    private Position position;
    private String email;
    private String password;
    private Player couple;
    private int energy;
    private final ArrayList<Farm> farms;
    private Backpack backpack;
    private final Abilities abilities = new Abilities();
    private final Connections connections = new Connections();
    private final Trading myTrading = new Trading(this);
    private final ArrayList<Recipe> recipes = new ArrayList<>();

    public Player(String name, String username, Gender gender, Backpack backpack, int energy, Player couple,
                  String email, String password, Position position, Integer gold) {
        this.name = name;
        this.username = username;
        this.gender = gender;
        this.farms = new ArrayList<>();
        this.backpack = backpack;
        this.energy = energy;
        this.couple = null;
        this.email = email;
        this.password = password;
        this.position = position;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public ArrayList<Farm> getFarms() {
        return farms;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Player getCouple() {
        return couple;
    }

    public void setCouple(Player couple) {
        this.couple = couple;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Abilities getAbilities() {
        return abilities;
    }

    public Connections getConnections() {
        return connections;
    }

    public void passOut() {}
}
