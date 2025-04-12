package Models;

import Enums.NPC;

import java.util.HashMap;

public class Connections {
    private final HashMap<Player, Integer> playerConnections = new HashMap<>();
    private final HashMap<NPC, Integer> NPCConnections = new HashMap<>();

    public HashMap<Player, Integer> getPlayerConnections() {
        return playerConnections;
    }

    public HashMap<NPC, Integer> getNPCConnections() {
        return NPCConnections;
    }

    public int calculateFriendship(Player player) {}

    public int calculateFriendship(NPC npc) {}

    public void gifting(Player player) {}

    public void gifting(NPC npc) {}

    public void talking(Player player) {}

    public void talking(NPC npc) {}

    public void quest(NPC npc) {}
}
