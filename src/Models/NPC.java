package Models;

import Models.Items.Item;

import java.util.ArrayList;
import java.util.Arrays;

public class NPC {
    private final String name;
    private final ArrayList<Item> favoriteItems;
    private final ArrayList<Request> requests;

    public NPC(String name, ArrayList<Item> favoriteItems, ArrayList<Request> requests) {
        this.name = name;
        this.favoriteItems = favoriteItems;
        this.requests = requests;
    }

    public static final NPC Sebastian = new NPC(
            "Sebastian",
            new ArrayList<>(Arrays.asList(/* add favorite Item objects here */)),
            new ArrayList<>(Arrays.asList(/* add Request objects here */))
    );

    public static final NPC Abigail = new NPC(
            "Abigail",
            new ArrayList<>(Arrays.asList(/* add favorite Item objects here */)),
            new ArrayList<>(Arrays.asList(/* add Request objects here */))
    );

    public static final NPC Harvey = new NPC(
            "Harvey",
            new ArrayList<>(Arrays.asList(/* add favorite Item objects here */)),
            new ArrayList<>(Arrays.asList(/* add Request objects here */))
    );

    public static final NPC Lia = new NPC(
            "Lia",
            new ArrayList<>(Arrays.asList(/* add favorite Item objects here */)),
            new ArrayList<>(Arrays.asList(/* add Request objects here */))
    );

    public static final NPC Robin = new NPC(
            "Robin",
            new ArrayList<>(Arrays.asList(/* add favorite Item objects here */)),
            new ArrayList<>(Arrays.asList(/* add Request objects here */))
    );
}