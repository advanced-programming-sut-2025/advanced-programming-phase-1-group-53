package Models;

import Models.Items.Item;

import java.util.ArrayList;

public class NPC {
    private final String name;
    private final ArrayList<Item> favoriteItems;
    private final ArrayList<Request> requests;

    private NPC(String name, ArrayList<Item> favoriteItems, ArrayList<Request> requests) {
        this.name = name;
        this.favoriteItems = favoriteItems;
        this.requests = requests;
    }

    public static NPC Sebastian("Sebastian", new ArrayList<Item>(), new ArrayList<Request>());
    public static NPC Abigail("Abigail", new ArrayList<Item>(), new ArrayList<Request>());
    public static NPC Harvey("Harvey", new ArrayList<Item>(), new ArrayList<Request>());
    public static NPC Lia("Lia", new ArrayList<Item>(), new ArrayList<Request>());
    public static NPC Robin("Robin", new ArrayList<Item>(), new ArrayList<Request>);
}
