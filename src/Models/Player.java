package Models;

import java.util.ArrayList;

public class Player extends Character{
    private Integer gold;
    private Position position;
    private String email;
    private String password;
    private Player couple;
    private int energy;
    private final ArrayList<Farm> farms;
    private Backpack backpack;

    public Player(Backpack backpack, int energy, Player couple, String email, String password, Position position, Integer gold) {
        this.farms = new ArrayList<>();
        this.backpack = backpack;
        this.energy = energy;
        this.couple = null;
        this.email = email;
        this.password = password;
        this.position = position;
        this.gold = gold;
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

    public void passOut() {}
}
