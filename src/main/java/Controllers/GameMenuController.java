package Controllers;

import Enums.ItemType;
import Enums.Menu;
import Models.Friendship;
import Models.Game.App;
import Models.Game.Game;
import Models.Game.Player;
import Models.Items.Item;
import Models.NPC;

import java.util.AbstractList;
import java.util.HashMap;
import java.util.List;

public class GameMenuController {
    public void gameLoop() {

    }

    public void exitGame() {
        App.setCurrentPlayer(App.getGame().players.get(0));
        App.setCurrentMenu(Menu.mainMenu);
    }
    
    public void newGame(String username1, String username2, String username3) {
        Player me = App.getCurrentPlayer();
        Player player1 = App.getInstance().findPlayerByUsername(username1);
        Player player2 = App.getInstance().findPlayerByUsername(username2);
        Player player3 = App.getInstance().findPlayerByUsername(username3);
        if (player1 == null || player2 == null || player3 == null) {
            System.out.println("One or more players not found.");
            return;
        }
        App.getInstance().setGame(new Game(List.of(me,player1, player2, player3)));
        Player.initializePlayerRelations(App.getGame().players);
    }

    public void loadGame(String index) {
        int index1;
        try {
            index1 = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            System.out.println("Invalid index. Please enter a valid integer.");
            return;
        }
        if (index1 < 0 || index1 >= App.getInstance().getGames().size()) {
            System.out.println("Invalid game index.");
            return;
        }
        Game game = App.getInstance().getGames().get(index1);
        App.getInstance().setGame(game);
    }

    public void selectMap(String mapNumber) {

    }

    public void nextTurn() {

    }

    public void time() {

    }

    public void date() {

    }

    public void dateTime() {

    }

    public void dayOfTheWeek() {

    }

    public void advanceTime(String time) {

    }

    public void advanceDate(String date) {

    }

    public void season() {

    }

    public void thor(String x, String y) {

    }

    public void weather() {

    }

    public void weatherForeCast() {

    }

    public void weatherSet(String weather) {

    }

    public void buildGreenHouse() {

    }

    public void walk(String x, String y) {
        int newX, newY;
        try {
            newX = Integer.parseInt(x);
            newY = Integer.parseInt(y);
        } catch (NumberFormatException e) {
            System.out.println("Invalid coordinates. Please enter valid integers.");
            return;
        }
        Player player = App.getGame().getCurrentPlayer();
        int energyCost = player.moveTo(newX, newY);
        player.applyMovementCost(energyCost, newX, newY);
    }

    public void printMap() {
        App.getGame().printMap();
    }

    public void helpReadingMap() {
        App.getGame().mapHelper();
    }

    public void showEnergy() {

    }

    public void setEnergy(String energy) {

    }

    public void setEnergyUnlimited() {

    }

    public void showInventory() {

    }

    public void inventoryTrash(String name, String number) {

    }

    public void equipTools(String name) {

    }

    public void showCurrentTool() {

    }

    public void showAvailableTools() {

    }

    public void upgradeTool(String name) {

    }

    public void useTool(String direction) {

    }

    public void craftInfo(String name) {

    }

    public void plant(String seedName, String direction) {

    }

    public void showPlant(String x, String y) {

    }

    public void fertilize(String fertilizer, String direction) {

    }

    public void howMuchWater() {

    }

    public void showCraftingRecipes() {

    }

    public void craftCraftings(String name) {

    }

    public void placeItem(String ItemName, String direction) {

    }

    public void addItem(String ItemName, String count) {

    }

    public void refigratoratorPut(String ItemName) {

    }

    public void refigratoratorPick(String ItemName) {

    }

    public void showCookingRecipes() {

    }

    public void prepareFood(String recipeName) {

    }

    public void eat(String foodName) {

    }

    public void build(String buildingName, String x, String y) {

    }

    public void buyAnimal(String animal, String animalName) {

    }

    public void pet(String animalName) {

    }

    public void setFreindship(String animalName, String value) {

    }

    public void animals() {

    }

    public void shepherdAnimals(String animalName, String x, String y) {

    }

    public void feedHay(String animalName) {

    }

    public void produces() {

    }

    public void collectProduces(String animalName) {

    }

    public void sellAnimal(String animalName) {

    }

    public void fishing(String fishingPole) {

    }

    public void artisanUse(String artisanName, String itemName) {

    }

    public void artisanGet(String artisanName) {

    }

    public void showAllProducts() {

    }

    public void showAllAvailableProducts() {

    }

    public void purchase(String productName, String count) {

    }

    public void addDollars(String count) {

    }

    public void sell(String productName, String count) {

    }

    public void friendships() {
        Player player = App.getGame().getCurrentPlayer();
        HashMap<Player, Friendship> friendshipHashMap = player.getFriendship();
        for (Player friend : friendshipHashMap.keySet()) {
            System.out.println(friend.personalInfo.getName() + " : " + friendshipHashMap.get(friend).getLevel());
        }
    }

    public void talk(String username, String message) {
        Player me = App.getGame().getCurrentPlayer();
        Player other = App.getGame().findPlayerByName(username);
        if (other == null) {
            System.out.println("Player not found.");
            return;
        }
        Friendship.talk(message, me, other);
    }

    public void talkHistory(String username) {
        Player me = App.getGame().getCurrentPlayer();
        Player other = App.getGame().findPlayerByName(username);
        if (other == null) {
            System.out.println("Player not found.");
            return;
        }
        Friendship.talkHistory(me, other);
    }

    public void gift(String username, String itemName, String amount) {
        int giftAmount;
        try {
            giftAmount = Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid integer.");
            return;
        }
        Player me = App.getGame().getCurrentPlayer();
        Player other = App.getGame().findPlayerByName(username);
        if (other == null) {
            System.out.println("Player not found.");
            return;
        }
        ItemType type = ItemType.findItemType(itemName);
        if (type == null) {
            System.out.println("Item not found.");
            return;
        }
        Item item = App.getGame().getItemByItemType(type);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        Friendship.gifting(me, other, item, giftAmount);
    }

    public void giftList() {
        Player player = App.getGame().getCurrentPlayer();
        AbstractList<Item> gifts = player.gifts;
        for (Item gift : gifts) {
            System.out.println(gift.getItemType().name());
        }
    }

    public void giftHistory(String username) {
        Player player = App.getGame().getCurrentPlayer();
        Player other = App.getGame().findPlayerByName(username);
        if (other == null) {
            System.out.println("Player not found.");
            return;
        }
        System.out.println(player.getGiftHistory().get(other).toString());
    }

    public void hug(String username) {
        Player me = App.getGame().getCurrentPlayer();
        Player other = App.getGame().findPlayerByName(username);
        if (other == null) {
            System.out.println("Player not found.");
            return;
        }
        Friendship.hugging(me, other);
    }

    public void flower(String username) {
        Player me = App.getGame().getCurrentPlayer();
        Player other = App.getGame().findPlayerByName(username);
        if (other == null) {
            System.out.println("Player not found.");
            return;
        }
        Item flower = App.getGame().getItemByItemType(ItemType.Bouquet);
        if (flower == null) {
            System.out.println("Flower not found.");
            return;
        }
        Friendship.bouquetGiving(me, other, flower);
    }

    public void askMarriage(String username, String ring) {
        Player me = App.getGame().getCurrentPlayer();
        Player other = App.getGame().findPlayerByName(username);
        if (other == null) {
            System.out.println("Player not found.");
            return;
        }
        Item weddingRing = App.getGame().getItemByItemType(ItemType.WeddingRing);
        if (weddingRing == null) {
            System.out.println("Wedding ring not found.");
            return;
        }
        Friendship.proposalMade(me, other, weddingRing);
    }

    public void meetNPC(String npcName) {
        NPC npc = NPC.findNPCsByName(npcName);
        if (npc == null) {
            System.out.println("NPC not found.");
            return;
        }
        Player player = App.getGame().getCurrentPlayer();
        NPC.talk(npc, player);
    }

    public void giftNPC(String npcName, String itemName) {
        NPC npc = NPC.findNPCsByName(npcName);
        if (npc == null) {
            System.out.println("NPC not found.");
            return;
        }
        ItemType type = ItemType.findItemType(itemName);
        if (type == null) {
            System.out.println("Item not found.");
            return;
        }
        Item item = App.getGame().getItemByItemType(type);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        Player player = App.getGame().getCurrentPlayer();
        NPC.gift(npc, player, item);
    }

    public void friendshipNPCList() {
        Player player = App.getGame().getCurrentPlayer();
        for (NPC npc : NPC.getAllNPCs()) {
            int friendshipLevel = player.calculateNPCsFriendship(npc);
            System.out.println(npc.getName() + " : " + friendshipLevel);
        }
    }

    public void questsList(String npcName) {
        NPC npc = NPC.findNPCsByName(npcName);
        if (npc == null) {
            System.out.println("NPC not found.");
            return;
        }
        NPC.showRequests(npc);
    }

    public void questFinish(String npcName, String index) {
        NPC npc = NPC.findNPCsByName(npcName);
        if (npc == null) {
            System.out.println("NPC not found.");
            return;
        }
        int questIndex;
        try {
            questIndex = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            System.out.println("Invalid quest index. Please enter a valid integer.");
            return;
        }
        Player me = App.getGame().getCurrentPlayer();
        NPC.doRequest(me, npc, questIndex);
    }
}
