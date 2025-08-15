package com.stardew.Models.NPC;

import com.stardew.Enums.ItemType;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Models.Items.Item;
import com.stardew.Models.Position;
import com.stardew.Models.Request;
import com.stardew.Models.Result;
import com.stardew.Network.Server.ChangeDurationPacket;
import com.stardew.Network.Server.ServerApp;

import java.util.ArrayList;
import java.util.Arrays;

public class NPC {
    private final String name;
    private final ArrayList<Item> favoriteItems;
    private final ArrayList<Request> requests;
    private final String personality;
    private final Position position;
    private int direction;


    // Example village tile locations (must be asphalt tiles inside the village area)
    // Adjust these coordinates as needed to fit your village layout
    public static final Position SEBASTIAN_POSITION = new Position(31, 31, 1, 1);
    public static final Position ABIGAIL_POSITION   = new Position(35, 32, 1, 1);
    public static final Position HARVEY_POSITION    = new Position(25, 34, 1, 1);
    public static final Position LIA_POSITION       = new Position(28, 36, 1, 1);
    public static final Position ROBIN_POSITION     = new Position(30, 37, 1, 1);

    public NPC(String name, ArrayList<Item> favoriteItems, ArrayList<Request> requests, String personality, Position position, int duration) {
        this.name = name;
        this.favoriteItems = favoriteItems;
        this.requests = requests;
        this.personality = personality;
        this.position = position;
        this.direction = duration;
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
        "Sebastian is a quiet, introspective young man who spends most of his time in his room, tinkering with computers or riding his motorcycle. He enjoys sarcastic humor, rainy days, and dislikes large crowds.",
        SEBASTIAN_POSITION, 0
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
        "Abigail is adventurous, bold, and a little rebellious. She loves exploring mines, playing the flute, and eating anything unusual. She has a mischievous side and enjoys teasing her friends.",
        ABIGAIL_POSITION, 1
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
        "Harvey is a gentle, slightly anxious town doctor who cares deeply for everyone's health. He’s polite, well-mannered, and enjoys calm conversations over a cup of coffee.",
        HARVEY_POSITION, 2
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
        "Lia is a warm, creative artist who loves sculpting and painting. She’s kind-hearted, enjoys quiet walks in nature, and often finds inspiration in the simplest things.",
        LIA_POSITION, 1
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
        "Robin is an energetic, friendly carpenter who loves building and improving homes. She’s practical, resourceful, and always ready to share a laugh.",
        ROBIN_POSITION, 0
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

    public static ArrayList<NPC> getAllNPCs() {
        return new ArrayList<>(Arrays.asList(Sebastian, Abigail, Harvey, Lia, Robin));
    }

    public String getPersonality() {
        return personality;
    }

    public Position getPosition() {
        return position;
    }

    public static Result generateDialogue(String npcName, String playerUsername) {
        NPC npc = NPC.findNPCsByName(npcName);
        if (npc == null) {
            return new Result(false, "NPC not found");
        }
        Player player = App.getInstance().findPlayerByUsername(playerUsername);
        if (player == null) {
            return new Result(false, "Player not found");
        }
        if (!App.getGame().getGameMap().amINearPlayer(npc)) {
            return new Result(false, "You are not near NPC");
        }
        player.changeNPCsFriendship(20, npc);
        LanguageModelAsync npcModel = new HttpLanguageModel(
            "http://localhost:11434/api/generate", "gemma:2b"
        );
        DialogueGenerator generator = new DialogueGenerator(npcModel);

        StringBuilder allDialogue = new StringBuilder();
        generator.generateNPCDialogueAsync(npc, player)
            .thenAccept(allDialogue::append);
        player.getNPCDialogueHistory(npc).append(allDialogue).append("\\n");
        return new Result(true, allDialogue.toString());
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
        if (!App.getGame().getGameMap().amINearPlayer(npc)) {
            System.out.println("You are not near the NPC.");
            return;
        }
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
            return;
        }
        player.backpack.getItems().compute(item, (k, v) ->(v-1));
    }

    public static void doRequest(Player player, NPC npc, int requestNumber) {
        int friendShipLevel = player.calculateNPCsFriendship(npc);
        if (requestNumber > friendShipLevel || requestNumber > npc.requests.size()) {
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

    public static void changeDirection() {
        if (!App.getMyPlayer().personalInfo.getName().equalsIgnoreCase("SERVER")) {
            return;
        }
        NPC.Sebastian.direction = generateNewDirection();
        NPC.Abigail.direction = generateNewDirection();
        NPC.Harvey.direction = generateNewDirection();
        NPC.Lia.direction = generateNewDirection();
        NPC.Robin.direction = generateNewDirection();
        ServerApp.getInstance().broadcastInGame(new ChangeDurationPacket("SERVER", "SERVER",
            Sebastian.direction, Abigail.direction, Harvey.direction, Lia.direction, Robin.direction));
    }

    private static int generateNewDirection() {
        return (int)(Math.random() * 4);
    }

    public static void changeDuration(ChangeDurationPacket packet) {
        Sebastian.direction = packet.SebastianDuration;
        Abigail.direction = packet.AbigailDuration;
        Harvey.direction = packet.HarveyDuration;
        Lia.direction = packet.LiaDuration;
        Robin.direction = packet.RobinDuration;
    }
}
