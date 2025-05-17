package Models;

import Enums.ItemType;
import Enums.Season;
import Models.Game.App;
import Models.Game.Player;
import Models.Items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class NPC {
    private final String name;
    private final ArrayList<Item> favoriteItems;
    private final ArrayList<Request> requests;
    private final HashMap<Season, String> seasonalDialogue;
    private final Position position;

    // Example village tile locations (must be asphalt tiles inside the village area)
    // Adjust these coordinates as needed to fit your village layout
    public static final Position SEBASTIAN_POSITION = new Position(31, 31, 1, 1);
    public static final Position ABIGAIL_POSITION   = new Position(35, 32, 1, 1);
    public static final Position HARVEY_POSITION    = new Position(25, 34, 1, 1);
    public static final Position LIA_POSITION       = new Position(28, 36, 1, 1);
    public static final Position ROBIN_POSITION     = new Position(30, 37, 1, 1);

    public NPC(String name, ArrayList<Item> favoriteItems, ArrayList<Request> requests, HashMap<Season, String> seasonalDialogue, Position position) {
        this.name = name;
        this.favoriteItems = favoriteItems;
        this.requests = requests;
        this.seasonalDialogue = seasonalDialogue;
        this.position = position;
    }

    public static final NPC Sebastian = new NPC(
            "Sebastian",
            new ArrayList<>(Arrays.asList(
                    App.getGame().getItemByItemType(ItemType.SheepWool),
                    App.getGame().getItemByItemType(ItemType.PumpkinPie),
                    App.getGame().getItemByItemType(ItemType.Pizza)
            )),
            new ArrayList<>(Arrays.asList(
                    new Request(50, App.getGame().getItemByItemType(ItemType.IronBar), 2, App.getGame().getItemByItemType(ItemType.Diamond), 0),
                    new Request(1, App.getGame().getItemByItemType(ItemType.PumpkinPie), 0, null, 5000),
                    new Request(150, App.getGame().getItemByItemType(ItemType.Stone), 50, App.getGame().getItemByItemType(ItemType.Quartz), 0)
            )),
            new HashMap<Season, String>() {{
                put(Season.SPRING, "Spring is a good time to ride my bike around the valley.");
                put(Season.SUMMER, "I like the summer nights. The cool air and the sound of frogs.");
                put(Season.FALL, "Fall is my favorite season. The rain is calming.");
                put(Season.WINTER, "Winter is perfect for staying inside and working on my projects.");
            }},
            SEBASTIAN_POSITION
    );

    public static final NPC Abigail = new NPC(
            "Abigail",
            new ArrayList<>(Arrays.asList(
                    App.getGame().getItemByItemType(ItemType.Stone),
                    App.getGame().getItemByItemType(ItemType.IronOre),
                    App.getGame().getItemByItemType(ItemType.Coffee)
            )),
            new ArrayList<>(Arrays.asList(
                    new Request(1, App.getGame().getItemByItemType(ItemType.GoldBar), 0, null, 10000),
                    new Request(1, App.getGame().getItemByItemType(ItemType.Pumpkin), 0, null, 5000),
                    new Request(1, App.getGame().getItemByItemType(ItemType.Pizza), 0, null, 5000),
                    new Request(1, App.getGame().getItemByItemType(ItemType.Coffee), 0, null, 5000)
            )),
            new HashMap<Season, String>() {{
                put(Season.SPRING, "Spring always makes me want to go on an adventure!");
                put(Season.SUMMER, "Summer is perfect for exploring the caves.");
                put(Season.FALL, "I love the mysterious feeling of fall.");
                put(Season.WINTER, "Winter is a good time to practice my flute indoors.");
            }},
            ABIGAIL_POSITION
    );

    public static final NPC Harvey = new NPC(
            "Harvey",
            new ArrayList<>(Arrays.asList(
                    App.getGame().getItemByItemType(ItemType.Coffee),
                    App.getGame().getItemByItemType(ItemType.Parsnip),
                    App.getGame().getItemByItemType(ItemType.Salad)
            )),
            new ArrayList<>(Arrays.asList(
                    new Request(2, App.getGame().getItemByItemType(ItemType.Coffee), 1, App.getGame().getItemByItemType(ItemType.Salad), 2000),
                    new Request(5, App.getGame().getItemByItemType(ItemType.Parsnip), 0, null, 1000)
            )),
            new HashMap<Season, String>() {{
                put(Season.SPRING, "Spring pollen can be tough for allergies. Take care!");
                put(Season.SUMMER, "Remember to stay hydrated in the summer heat.");
                put(Season.FALL, "Fall is a busy time for checkups.");
                put(Season.WINTER, "Be careful not to catch a cold this winter.");
            }},
            HARVEY_POSITION
    );

    public static final NPC Lia = new NPC(
            "Lia",
            new ArrayList<>(Arrays.asList(
                    App.getGame().getItemByItemType(ItemType.Salad),
                    App.getGame().getItemByItemType(ItemType.FruitSalad),
                    App.getGame().getItemByItemType(ItemType.Wood)
            )),
            new ArrayList<>(Arrays.asList(
                    new Request(10, App.getGame().getItemByItemType(ItemType.Wood), 1, App.getGame().getItemByItemType(ItemType.FruitSalad), 500),
                    new Request(1, App.getGame().getItemByItemType(ItemType.Salad), 0, null, 1000)
            )),
            new HashMap<Season, String>() {{
                put(Season.SPRING, "Spring inspires me to create new sculptures.");
                put(Season.SUMMER, "I love gathering wild berries in the summer.");
                put(Season.FALL, "The colors of fall are so beautiful for painting.");
                put(Season.WINTER, "Winter is a quiet time to work on my art indoors.");
            }},
            LIA_POSITION
    );

    public static final NPC Robin = new NPC(
            "Robin",
            new ArrayList<>(Arrays.asList(
                    App.getGame().getItemByItemType(ItemType.Wood),
                    App.getGame().getItemByItemType(ItemType.Stone),
                    App.getGame().getItemByItemType(ItemType.Wine)
            )),
            new ArrayList<>(Arrays.asList(
                    new Request(100, App.getGame().getItemByItemType(ItemType.Wood), 0, null, 2000),
                    new Request(50, App.getGame().getItemByItemType(ItemType.Stone), 0, null, 1000),
                    new Request(10, App.getGame().getItemByItemType(ItemType.Wine), 0, null, 3000)
            )),
            new HashMap<Season, String>() {{
                put(Season.SPRING, "Spring is the best time to start new construction projects.");
                put(Season.SUMMER, "Summer keeps me busy with repairs around the valley.");
                put(Season.FALL, "Fall is a good time to stock up on building materials.");
                put(Season.WINTER, "Winter slows down my work, but I enjoy the break.");
            }},
            ROBIN_POSITION
    );

    public String getName() {
        return name;
    }

    public ArrayList<Item> getFavoriteItems() {
        return favoriteItems;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public String getDialogue() {
        return seasonalDialogue != null ? seasonalDialogue.getOrDefault(App.getGame().dateAndTime.getSeason(), "") : "";
    }

    public Position getPosition() {
        return position;
    }

    public static void talk(NPC npc, Player player) {
        player.changeNPCsFriendship(20, npc);
        System.out.println(npc.getDialogue());
    }

    public static NPC findNPCsByName(String name) {
        for (NPC npc : new NPC[]{Sebastian, Abigail, Harvey, Lia, Robin}) {
            if (npc.getName().equalsIgnoreCase(name)) {
                return npc;
            }
        }
        return null; // NPC not found
    }

    public static void gift(NPC npc, Player player, Item item) {
        if (player.backpack.areItemsAvailable(item, 1)) {
            if (npc.getFavoriteItems().contains(item)) {
                System.out.println("You gave " + npc.getName() + " a kheili khoob item " + ". They loved it!");
                player.changeNPCsFriendship(50, npc);
            } else {
                System.out.println("You gave " + npc.getName() + " a na kheili khoob item" + ". They didn't like it.");
                player.changeNPCsFriendship(20, npc);
            }
        }
        else {
            System.out.println("badbakht");
        }
        player.backpack.getItems().compute(item, (k, v) ->(v-1));
    }

    public static void doRequest(Player player, NPC npc, int requestNumber) {
        int friendShipLevel = player.calculateNPCsFriendship(npc);
        if (requestNumber > friendShipLevel || requestNumber > 2) {
            System.out.println("we are moving to soon");
            return;
        }
        Request request = npc.getRequests().get(requestNumber);
        if (request == null) {
            System.out.println("dige dire");
            return;
        }
        if (!player.backpack.areItemsAvailable(request.giveAwayItem(), request.numberOfGiveAwayItem())) {
            System.out.println("faghiri");
            return;
        }
        player.backpack.getItems().compute(request.giveAwayItem(), (k, v) ->(v-request.numberOfGiveAwayItem()));
        player.backpack.addItem(request.giveAwayItem(), request.numberOfRewardItem());
        player.personalInfo.updateGold(request.goldReward());
        player.NPCsFriendship.put(npc, player.NPCsFriendship.get(npc) + 50);
        npc.getRequests().remove(requestNumber);
    }

    public static void showRequests(NPC npc) {
        for (Request request : npc.getRequests()) {
            System.out.println(request.toString());
        }
    }
}
