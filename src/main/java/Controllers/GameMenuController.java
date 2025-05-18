package Controllers;

import Enums.ItemType;
import Enums.MapsNames;
import Enums.Menu;
import Enums.WeatherType;
import Models.*;
import Models.Game.App;
import Models.Game.Game;
import Models.Game.Player;
import Models.Items.Foragings.ForagingSeed;
import Models.Items.Item;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameMenuController {
    int[] dx = {0, 1, 0, -1, 1, 1, -1, -1};
    int[] dy = {1, 0, -1, 0, -1, 1, 1, -1};
    public void gameLoop() {
        App.getGame().getGameMap().generateRandomThings();
        updateGame();
    }

    public void updateGame(){
        App.getGame().dateAndTime.timeCheat(1);
        for(Player player : App.getGame().getPlayers()){
            App.getGame().setNumOfTurn(App.getGame().getPlayers().indexOf(player));
            player.backpack.update();
        }

        App.getGame().weather.update();
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
        System.out.println("khosh oomadid");
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
    public void weather(){
        MessageManager.getMessage(Result.success(App.getGame().weather.getWeather().name()));
    }


    public void nextTurn() {
        App.getGame().goToNextPlayer();
        MapsNames mapsNames = App.getGame().getCurrentPlayer().getCurrentMap();
        Tile[][] map = MapsNames.findMapByMapsName(mapsNames, App.getCurrentPlayer());
        if (map == null) {
            System.out.println("bug");
            return;
        }
        App.getGame().setCurrentMap(map);
    }

    public void date(){
        App.getGame().dateAndTime.showDate();
    }

    public void time(){
        App.getGame().dateAndTime.showTime();
    }

    public void dateTime(){
        App.getGame().dateAndTime.showDateAndTime();
    }

    public void dayOfTheWeek(){
        App.getGame().dateAndTime.showDay();
    }

    public void advanceTime(int hour){
        App.getGame().dateAndTime.timeCheat(hour);
    }

    public void advanceDate(int day){
        App.getGame().dateAndTime.timeCheat(day*24);
    }

    public void season(){
        App.getGame().dateAndTime.getSeason();
    }

    public void thor(int x, int y){
        App.getGame().weather.thundering(x, y);
    }


    public void weatherForecast(){
        App.getGame().weather.weatherForecast();
    }

    public void setWeather(String weather){
        for(WeatherType weatherType : WeatherType.values()){
            if(weatherType.name().equals(weather)){
                App.getGame().weather.setWeather(weatherType);
            }
        }
    }

    public void weatherSet(String weather) {
        for(WeatherType weatherType : WeatherType.values()){
            if(weatherType.name().equals(weather))
                App.getGame().weather.setWeather(weatherType);

        }
    }

    public void buildGreenHouse() {
        App.getCurrentPlayer().getFarm().getGreenHouse().letsBuildGreenhouse();
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

    public void showEnergy(){
        App.getGame().getCurrentPlayer().energy.showEnergy();
    }

    public void setEnergy(int energy){
        App.getGame().getCurrentPlayer().energy.setEnergy(energy);
    }

    public void setEnergyUnlimited(){
        App.getGame().getCurrentPlayer().energy.setUnlimitedEnergy();
    }

    public void showInventory(){
        App.getGame().getCurrentPlayer().backpack.showInventory();
    }


    public void inventoryTrash(String name, int number){
        if(getItemByName(name) != null)
            App.getGame().getCurrentPlayer().backpack.useTrashCan(getItemByName(name), number);
    }

    public void equipTool(String tool){
        if(getItemByName(tool) != null)
            App.getGame().getCurrentPlayer().activity.equipTool(getItemByName(tool));
    }

    public void showCurrentTools(){
        App.getGame().getCurrentPlayer().activity.showCurrentTool();
    }

    public void showAvailableTools(){
        App.getGame().getCurrentPlayer().activity.showAvailableTools();
    }

    public void upgradeTool(String name){
        if(getItemByName(name) != null)
            App.getGame().getCurrentPlayer().activity.upgradeTool(getItemByName(name));
    }

    public void useTool(String direction){
        int dir = switch (direction){
            case "W" -> 0;
            case "D" -> 1;
            case "S" -> 2;
            case "A" ->3;
            case "Q" ->4;
            case "E" ->5;
            case "Z" -> 6;
            case "X" ->7;
            default -> -1;
        };
        if(dir == -1)
            return;

        App.getGame().getCurrentPlayer().activity.useTool(App.getGame().getCurrentPlayer().position
                .getX()+dx[dir], App.getGame().getCurrentPlayer().position
                .getY()+dy[dir]);
    }

    public void craftInfo(String name){
        if(getItemByName(name) != null)
            App.getGame().getCurrentPlayer().abilities.normalFarming.showCraftInfo(getItemByName(name));
    }

    public void plant(String seed, String direction){
        int dir = switch (direction){
            case "W" -> 0;
            case "D" -> 1;
            case "S" -> 2;
            case "A" ->3;
            case "Q" ->4;
            case "E" ->5;
            case "Z" -> 6;
            case "X" ->7;
            default -> -1;
        };
        if(dir == -1)
            return;


        if(getItemByName(seed) != null) {
            if(getItemByName(seed).equals(ItemType.MixedSeed)){
                ForagingSeed foragingSeed = (ForagingSeed) App.getGame().getItemByItemType(getItemByName(seed));
                foragingSeed = foragingSeed.randomiseMixedSeed();
                App.getGame().getCurrentPlayer().abilities.normalFarming.plant(foragingSeed.getItemType(),
                        App.getGame().getCurrentPlayer().position
                                .getX() + dx[dir], App.getGame().getCurrentPlayer().position
                                .getY() + dy[dir]);
            }
            else {
                App.getGame().getCurrentPlayer().abilities.normalFarming.plant(getItemByName(seed),
                        App.getGame().getCurrentPlayer().position
                                .getX() + dx[dir], App.getGame().getCurrentPlayer().position
                                .getY() + dy[dir]);
            }
        }
    }

    public void showPlant(int x, int y){
        App.getGame().getCurrentPlayer().abilities.normalFarming.showPlants(x, y);
    }

    public void fertilize(String name, String direction){
        int dir = switch (direction){
            case "W" -> 0;
            case "D" -> 1;
            case "S" -> 2;
            case "A" ->3;
            case "Q" ->4;
            case "E" ->5;
            case "Z" -> 6;
            case "X" ->7;
            default -> -1;
        };
        if(dir == -1)
            return;

        App.getGame().getCurrentPlayer().abilities.normalFarming.fertilize(getItemByName(name),
                App.getGame().getCurrentPlayer().position
                        .getX()+dx[dir], App.getGame().getCurrentPlayer().position
                        .getY()+dy[dir]);
    }

    public void howMuchWater(){
        App.getGame().getCurrentPlayer().backpack.howMuchWater();
    }

    public void craftingShowRecipes(){
        App.getGame().getCurrentPlayer().abilities.crafting.showCraftingRecipes(true);
    }

    public void craft(String name){
        if(getItemByName(name) != null)
            App.getGame().getCurrentPlayer().abilities.crafting.craft(getItemByName(name));
    }

    public void placeItem(String name, String direction){
        if(getItemByName(name) == null)
            return;
        int dir = switch (direction){
            case "W" -> 0;
            case "D" -> 1;
            case "S" -> 2;
            case "A" ->3;
            case "Q" ->4;
            case "E" ->5;
            case "Z" -> 6;
            case "X" ->7;
            default -> -1;
        };
        if(dir == -1)
            return;
        App.getGame().getCurrentPlayer().activity.placeItem(getItemByName(name), App.getGame().getCurrentPlayer().position
                .getX()+dx[dir], App.getGame().getCurrentPlayer().position
                .getY()+dy[dir]);
    }

    public void addItem(String name, int count){
        if(getItemByName(name) != null && (App.getGame().getItemByItemType(getItemByName(name))!= null))
            App.getGame().getCurrentPlayer().backpack.addItem(App.getGame().getItemByItemType(getItemByName(name))
                    , count);
    }

    public void refrigerator(boolean pick, String item){
        if(getItemByName(item) != null){
            if(pick)
                App.getGame().getCurrentPlayer().abilities.cooking.pickItemFromRef(getItemByName(item));
            else
                App.getGame().getCurrentPlayer().abilities.cooking.putItemInRef(getItemByName(item));
        }
    }

    public void showCookingRecipes(){
        App.getGame().getCurrentPlayer().abilities.cooking.showCookingRecipes();
    }

    public void prepareFood(String name){
        if(getItemByName(name) != null)
            App.getGame().getCurrentPlayer().abilities.cooking.prepare(getItemByName(name));
    }

    public void eat(String name){
        if(getItemByName(name) != null)
            App.getGame().getCurrentPlayer().abilities.cooking.eat(getItemByName(name));
    }

    public void build(){

    }

    public void buyAnimal(String animal, String name){
        if(getItemByName(animal) != null)
            App.getGame().getCurrentPlayer().abilities.shopping.purchase(getItemByName(animal), name);
    }

    public void pet(String name){
        App.getGame().getCurrentPlayer().abilities.dairyFarming.pet(name);
    }


    public void setFriendship(String name, int amount){
        App.getGame().getCurrentPlayer().abilities.dairyFarming.cheatSetFriendship(name, amount);
    }

    public void animals(){
        App.getGame().getCurrentPlayer().abilities.dairyFarming.animalsShowDetails();
    }

    public void shepherdAnimal(String name, int x, int y){
        App.getGame().getCurrentPlayer().abilities.dairyFarming.shepherdAnimal(name, x, y);
    }

    public  void feed(String name){
        App.getGame().getCurrentPlayer().abilities.dairyFarming.feed(name);
    }

    public void produces(){
        App.getGame().getCurrentPlayer().abilities.dairyFarming.showNotCollectedProducts();
    }

    public void collectProduce(String name){
        App.getGame().getCurrentPlayer().abilities.dairyFarming.collectProduct(name);
    }

    public void sellAnimal(String name){
        App.getGame().getCurrentPlayer().abilities.dairyFarming.sellAnimal(name);
    }

    public void fishing(String pole){
        if(getItemByName(pole) != null){
            App.getGame().getCurrentPlayer().backpack.setItemInHand(App.getGame().getItemByItemType(getItemByName(pole)));
            App.getGame().getCurrentPlayer().abilities.fishing.fishing();
        }
    }

    public void artisanUse(String artisan, List<ItemType> items){
        if(getItemByName(artisan) != null){
            App.getGame().getCurrentPlayer().abilities.crafting.artisanUse(getItemByName(artisan), items);
        }
    }

    public void artisanGet(String name){
        if(getItemByName(name) != null){
            App.getGame().getCurrentPlayer().abilities.crafting.artisanGet(getItemByName(name));
        }
    }


    public void showAvailableProducts(){
        App.getGame().getCurrentPlayer().abilities.shopping.showAvailableProducts();
    }
    public void showAllProducts(){
        App.getGame().getCurrentPlayer().abilities.shopping.showAllProducts();
    }

    public void Purchase(String name, int count){
        if(getItemByName(name) != null){
            App.getGame().getCurrentPlayer().abilities.shopping.purchase(getItemByName(name), count);
        }
    }

    public void addDollars(int count){
        App.getGame().getCurrentPlayer().personalInfo.updateGold(count);
    }

    public void sell(String name, int count){
        if(getItemByName(name) != null){
            App.getGame().getCurrentPlayer().abilities.shopping.sell(getItemByName(name), count);
        }
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

    private ItemType getItemByName(String name){
        for(Item item : App.getGame().getAllItemsInTheGame()){
            if(item.getItemType().name().equals(name))
                return item.getItemType();
        }
        MessageManager.getMessage(Result.failure("No item with such name."));
        return null;
    }
}
