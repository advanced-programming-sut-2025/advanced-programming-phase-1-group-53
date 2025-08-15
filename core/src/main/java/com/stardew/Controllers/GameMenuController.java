package com.stardew.Controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.stardew.Enums.*;
import com.stardew.Main;
import com.stardew.Models.*;
import com.stardew.Models.Abilities.Abilities;
import com.stardew.Models.Abilities.Activity;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Game;
import com.stardew.Models.Game.Player;
import com.stardew.Models.Items.Animal;
import com.stardew.Models.Items.Buildings.Building;
import com.stardew.Models.Items.Buildings.Shop;
import com.stardew.Models.Items.CoopAndBarn;
import com.stardew.Models.Items.CraftAbleAndArtisan.Artisan;
import com.stardew.Models.Items.Foragings.ForagingMineral;
import com.stardew.Models.Items.Foragings.ForagingSeed;
import com.stardew.Models.Items.Foragings.ForagingTree;
import com.stardew.Models.Items.Foragings.Tree;
import com.stardew.Models.Items.Item;
import com.stardew.Models.Items.ShippingBin;
import com.stardew.Models.NPC.NPC;
import com.stardew.Views.GameMenu;
import com.stardew.Views.TabMenus.*;

import java.util.*;

public class GameMenuController {
    private static Game game ;
    public static MovementController mvc = new MovementController();
    private static int printStartX = 0;
    private static int printStartY = 0;
    private boolean hideEnergyBar = false;
    public Activity activity = new Activity();
    public Abilities abilities = new Abilities();



    public static int getPrintStartX() {
        return printStartX;
    }

    public static int getPrintStartY() {
        return printStartY;
    }

    public GameMenuController(){
        game = App.getGame();
        mvc = new MovementController();
        mvc.setGame(App.getGame());
        System.out.println(App.getInstance());
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game gamee) {
        game = gamee;
    }

    int[] dx = {0, 1, 0, -1, 1, 1, -1, -1};
    int[] dy = {1, 0, -1, 0, -1, 1, 1, -1};
    public void gameLoop() {
        App.getGame().getGameMap().generateRandomThings(0);
    }

    public void updateGame(float delta, boolean isCheat){
        App.getGame().dateAndTime.timeCheat(DateAndTime.convertRealSecondToGameMinute(delta));
        while (App.getGame().dateAndTime.getDiff()>0){
            App.getGame().dateAndTime.updateTime();
        }
        GameMenu.getInstance().updateScreen(delta);
        App.getMyPlayer().update(delta);
        abilities.normalFarming.update(delta);
        ShippingBin.ShippingBin.update(delta);

        if(game.dateAndTime.isADayPassed()){
            //System.out.println("gold :"+App.getMyPlayer().personalInfo.getGold());
            //regenerate
            for(Vector2 v2 : game.getGameMap().getReGenerateQue().keySet()){
                ItemType itemType = game.getGameMap().getReGenerateQue().get(v2);
                Item item = App.getGame().getItemByItemType(itemType).clone();
                App.getGame().getGameMap().getTiles()[(int) v2.y][(int) v2.x].setItem(item);
                item.getPosition().setY((int) v2.y);
                item.getPosition().setX((int) v2.x);
                if(item instanceof ForagingMineral && !itemType.equals(ItemType.Wood) && !itemType.equals(ItemType.Stone)){
                    App.getGame().getGameMap().getTiles()[(int) v2.y][(int) v2.x].setTileKind(TileKind.foragingMineral);
                }
                else{
                    App.getGame().getGameMap().getTiles()[(int) v2.y][(int) v2.x].setTileKind(TileKind.foraging);
                }
            }
            game.getGameMap().getReGenerateQue().clear();

            //choose weather
        }

        for(Player player : App.getGame().getPlayers()){
            player.update(delta);
        }

        game.weather.update(delta);

        Animal.updateSprites(delta);

        for(Tile[] tt : game.getGameMap().getTiles()){
            for(Tile t : tt){
                t.update(delta);
            }
        }

        if(game.dateAndTime.isADayPassed()){
            game.dateAndTime.setADayPassed(false);
        }
    }

    public ArrayList<Sprite> getSprites(){
        ArrayList<Tile> tilesInRegion = new ArrayList<>(); //for printing items on tiles
        isInPrintRegion(null);
        ArrayList<Sprite> sprites = new ArrayList<>();
        //print tiles
        for(Tile[] tt : game.getGameMap().getTiles()){
            for(Tile t : tt){
                Sprite s = t.getSprite();
                s.setX(GameMap.getTilePrintSize() *t.getPosition().getX());
                s.setY(GameMap.getTilePrintSize() *t.getPosition().getY());
                if(isInPrintRegion(s)){
                    if(GameMenu.getInstance().isShowFullTiles() && t.getItem() != null){
                        s.setColor(0, 0, 0, 1);
                    }
                    s.setX(GameMap.getTilePrintSize() *t.getPosition().getX() - printStartX);
                    s.setY(GameMap.getTilePrintSize()*t.getPosition().getY() - printStartY);
                    sprites.add(s);
                    tilesInRegion.add(t);
                }
            }
        }

        //print structures
        for(Player p : App.getGame().players){
            for(Building b : p.getFarm().getBuildings()){
                if (isInPrintRegion(b.fixSpriteCoordinatesForPrint())) {
                    sprites.add(b.getSprite());
                }
            }
        }

        //print items on tiles
        for(Tile t : tilesInRegion){
            if(t.getItem() == null)
                continue;
            if(t.getItem() instanceof CoopAndBarn || t.getItem() instanceof Artisan){
                Sprite s = t.getItem().getSprite();
                sprites.add(s);
                continue;
            }
            if(t.getItem().getClass() != ForagingTree.class && t.getItem().getClass() != ForagingMineral.class&&
            t.getItem().getClass() != Tree.class && !(t.getItem() instanceof ShippingBin) &&
                !(t.getItem() instanceof Artisan))
                continue;
            if(isInPrintRegion(t.getItem().getSprite())){
                Sprite s = t.getItem().getSprite();
                s.setPosition(s.getX() - printStartX, s.getY() - printStartY);
                sprites.add(s);
            }
        }


        //print shops
        for (Shop shop : Shop.shops) {
            if (isInPrintRegion(shop.fixSpriteCoordinatesForPrint())) {
                sprites.add(shop.getSprite());
            }
        }
        if(!hideEnergyBar){
            for(int i = 0; i< App.getMyPlayer().energy.getSprite().length; i++){
                Sprite s = App.getMyPlayer().energy.getSprite()[i];
                if(i==0){
                    Sprite ss = App.getMyPlayer().foodBuff.getSprite();
                    ss.setPosition(s.getX(), s.getY() + s.getHeight() + 30);
                    if(ss.getTexture() != null){
                        sprites.add(ss);
                    }
                }
                sprites.add(s);
            }
        }

        for(CoopAndBarn coop : App.getMyPlayer().backpack.getCoopsAndBarns()){
            for(Animal animal : coop.getOutAnimals()){
                Sprite s = animal.getSprite();
                if(isInPrintRegion(s)){
                    sprites.add(s);
                }
            }
        }


        for(Sprite s : Animal.getAnimalSprites().keySet()){
            sprites.add(s);
        }

        for(NPC npc : NPC.allNPCs){
            Sprite s = npc.getSprite();
            if(isInPrintRegion(s)){
                sprites.add(npc.fixForPrint());
                if(npc.isDialogueReady()){
                    sprites.addAll(npc.dialogueSprites());
                }
            }
        }


        for(Player player : App.getGame().players){
            sprites.add(player.getSprite());
        }
        if(App.getMyPlayer().backpack.getItemInHand()!= null){
            Sprite s = App.getMyPlayer().backpack.getItemInHand().getSprite();
            if(GameMenu.getInstance().isSetToolToMouse()) {
                s.setPosition(GameMenu.getInstance().getMouseX(), GameMenu.getInstance().getMouseY());
                s.setColor(0.3f, 0.3f, 0.3f, 1);
            }
            else
                s.setPosition((float) (App.getMyPlayer().getSprite().getX()+App.getMyPlayer().getSprite().getWidth()*0.7),
                (float) (App.getMyPlayer().getSprite().getY()+ App.getMyPlayer().getSprite().getHeight()*0.37));
            sprites.add(s);
        }
        //System.out.println(App.getMyPlayer().position.getX()+" "+App.getMyPlayer().position.getY());
        return sprites;
    }


    public static boolean isInPrintRegion(Sprite sprite){
        int x = App.getMyPlayer().position.getX();
        int y = App.getMyPlayer().position.getY();
        int margin = 20;
        int startPrintX = (x/GameMenu.getScreenWidth())*GameMenu.getScreenWidth() -margin;
        int startPrintY = (y/GameMenu.getScreenHeight())*GameMenu.getScreenHeight() -margin;
        printStartX = startPrintX + margin;
        printStartY = startPrintY + margin;
        if(sprite == null)
            return false;
        if(coordinateCollision(startPrintX, GameMenu.getScreenWidth() +margin, sprite.getX(), sprite.getWidth()) &&
            coordinateCollision(startPrintY, GameMenu.getScreenHeight()+margin, sprite.getY(), sprite.getHeight())){
            return true;
        }
        return false;
    }

    public static boolean coordinateCollision(float x1, float h1, float x2, float h2){
        if((x1 + h1 < x2) || (x2 + h2 < x1))
            return false;
        return true;
    }

    public void exitGame() {
        App.setCurrentPlayer(App.getGame().players.get(0));
//        App.setCurrentMenu(Menu.mainMenu);
    }

    public static void newGame(String username0, String username1, String username2, String username3) {
        System.out.println(username0+" "+username1+" "+username2+" "+username3);
        Player me = App.getInstance().findPlayerByUsername(username0);
        Player player1 = App.getInstance().findPlayerByUsername(username1);
        Player player2 = App.getInstance().findPlayerByUsername(username2);
        Player player3 = App.getInstance().findPlayerByUsername(username3);
        if (player1 == null && player2 == null && player3 == null) {
            System.out.println("One or more players not found.");
        }

        else if (player2 == null && player3 == null) {
            App.getInstance().setGame(new Game(List.of(me, player1)));
            System.out.println("ll");
            System.out.println("khosh oomadid");
        }


        else if (player3 == null) {
            System.out.println("3");
            App.getInstance().setGame(new Game(List.of(me , player1, player2)));
            System.out.println("ll");
            App.getGame().getGameMap().generateRandomThings(App.getGame().getPlayers(), 4);
            Player.initializePlayerRelations(App.getGame().players);
            System.out.println("khosh oomadid");
        }
        else {
            App.getInstance().setGame(new Game(List.of(me , player1, player2, player3)));
            System.out.println("ll");
            App.getGame().getGameMap().generateRandomThings(App.getGame().getPlayers(), 4);
            Player.initializePlayerRelations(App.getGame().players);
            System.out.println("khosh oomadid");
        }
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
        Tile[][] map = MapsNames.findMapByMapsName(mapsNames, App.getMyPlayer());
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
        TimeCheatMenu.setCheatHours(hour);
        TimeCheatMenu.setIsCheatActivate(true);
        System.out.println("jsdkj");
    }

    public void advanceDate(int day){
        TimeCheatMenu.setCheatHours(24*day);
        TimeCheatMenu.setIsCheatActivate(true);
    }

    public void season(){
        System.out.println(App.getGame().dateAndTime.getSeason());
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
        App.getMyPlayer().getFarm().getGreenHouse().letsBuildGreenhouse();
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

    public boolean isHideEnergyBar() {
        return hideEnergyBar;
    }

    public void setHideEnergyBar(boolean hideEnergyBar) {
        this.hideEnergyBar = hideEnergyBar;
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

    public void fertilize(int x, int y, Item item){
        App.getGame().getCurrentPlayer().abilities.normalFarming.fertilize(item.getItemType(), x, y);
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
//        NPC.talk(npc, player);
    }

//    public Result keyDown(int keycode) {
//
//
//    }

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
