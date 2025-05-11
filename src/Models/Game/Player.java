package Models.Game;

import Enums.Gender;
import Enums.MapsNames;
import Models.*;
import Models.Abilities.Abilities;
import Models.Abilities.Activity;

import java.util.HashMap;

public class Player {
    private Farm farm;
    public final PersonalInfo personalInfo;
    public final Abilities abilities = new Abilities();
    public final Backpack backpack = new Backpack();
    public final Energy energy = new Energy();
    public final Activity activity = new Activity();
    public final Position position = new Position(0, 0, 0, 0);
    private MapsNames currentMap = MapsNames.House;
    private final HashMap<Player, Integer> friendship = new HashMap<>();
    private final HashMap<Player, StringBuilder> conversation = new HashMap<>();

    public Player(String name, String password, String email, Gender gender) {
        this.personalInfo = new PersonalInfo(name, password, email, gender);
        for (Player player : App.getGame().players) {
            this.friendship.put(player, 0);
            this.conversation.put(player, new StringBuilder());
        }
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public MapsNames getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(MapsNames currentMap) {
        this.currentMap = currentMap;
    }

    public void changeFriendship(Player player, int xp) {
        Player currentPlayer = this;
        currentPlayer.friendship.put(player, currentPlayer.friendship.get(player) + xp);
        player.friendship.put(currentPlayer, player.friendship.get(currentPlayer) + xp);
    }

    public int getFriendshipLevel(Player player) {
        int xp = this.friendship.get(player);
        return (xp / 100) - 1;
    }

    public HashMap<Player, StringBuilder> getConversation() {
        return conversation;
    }
}
