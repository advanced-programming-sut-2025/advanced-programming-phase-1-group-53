package Enums;

import java.util.ArrayList;
import java.util.HashMap;

public enum NPC {
    Sebastian("Sebastian", null, null, null, null),
    Abigail("Abigail", null, null, null, null),
    Harvey("Harvey", null, null, null, null),
    Lia("Lia", null, null, null, null),
    Robin("Robin", null, null, null, null),
    Clint(),
    Morris(),
    Pierre(),
    Willie(),
    Marnie(),
    Gus();

    private final String name;
    private final ArrayList<Product> favorites;
    private final HashMap<Product, Integer> requests;
    private final HashMap<Product, Integer> rewards;
    private final ArrayList<Quest> quests;

    NPC(String name, ArrayList<Product> favorites, HashMap<Product, Integer> requests, HashMap<Product, Integer> rewards, ArrayList<Quest> quests) {
        this.name = name;
        this.favorites = favorites;
        this.requests = requests;
        this.rewards = rewards;
        this.quests = quests;
    }

    public NPC findNPCByName(String name){
        if(NPC.Sebastian.name.equals(name))
            return NPC.Sebastian;
        if(NPC.Abigail.name.equals(name))
            return NPC.Abigail;
        if(NPC.Harvey.name.equals(name))
            return NPC.Harvey;
        if(NPC.Lia.name.equals(name))
            return NPC.Lia;
        if(NPC.Robin.name.equals(name))
            return NPC.Robin;
        return null;
    }
}
