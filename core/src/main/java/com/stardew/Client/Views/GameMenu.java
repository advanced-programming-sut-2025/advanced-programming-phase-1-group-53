package com.stardew.Client.Views;

import com.stardew.Server.Controllers.GameMenuController;
import com.stardew.Server.Controllers.ShareController;
import com.stardew.GameLogic.Enums.GameMenuCommand;
import com.stardew.GameLogic.Enums.ItemType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu {
    private final GameMenuController controller = new GameMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        if ((matcher = GameMenuCommand.exit.getMatcher(input)) != null) {
            ShareController.exit(scanner);
        } else if ((matcher = GameMenuCommand.showCurrentMenu.getMatcher(input)) != null) {
            ShareController.showCurrentMenu();
        } else if ((matcher = GameMenuCommand.enterMenu.getMatcher(input)) != null) {
            ShareController.enterMenu(matcher.group("menu"));
        } else if ((matcher = GameMenuCommand.exitGame.getMatcher(input)) != null) {
            controller.exitGame();
        } else if ((matcher = GameMenuCommand.newGame.getMatcher(input)) != null) {
            controller.newGame(matcher.group("username1"), matcher.group("username2"), matcher.group("username3"));
        } else if ((matcher = GameMenuCommand.loadGame.getMatcher(input)) != null) {
            controller.loadGame(matcher.group("index"));
        } else if ((matcher = GameMenuCommand.selectMap.getMatcher(input)) != null) {
            controller.selectMap(matcher.group("mapNumber"));
        } else if ((matcher = GameMenuCommand.nextTurn.getMatcher(input)) != null) {
            controller.nextTurn();
        } else if ((matcher = GameMenuCommand.time.getMatcher(input)) != null) {
            controller.time();
        } else if ((matcher = GameMenuCommand.date.getMatcher(input)) != null) {
            controller.date();
        } else if ((matcher = GameMenuCommand.dateTime.getMatcher(input)) != null) {
            controller.dateTime();
        } else if ((matcher = GameMenuCommand.dayOfTheWeek.getMatcher(input)) != null) {
            controller.dayOfTheWeek();
        } else if ((matcher = GameMenuCommand.advanceTime.getMatcher(input)) != null) {
            controller.advanceTime(Integer.parseInt(matcher.group("time")));
        } else if ((matcher = GameMenuCommand.advanceDate.getMatcher(input)) != null) {
            controller.advanceDate(Integer.parseInt(matcher.group("date")));
        } else if ((matcher = GameMenuCommand.season.getMatcher(input)) != null) {
            controller.season();
        } else if ((matcher = GameMenuCommand.thor.getMatcher(input)) != null) {
            controller.thor(Integer.parseInt(matcher.group("x")), Integer.parseInt(matcher.group("y")));
        } else if ((matcher = GameMenuCommand.weather.getMatcher(input)) != null) {
            controller.weather();
        } else if ((matcher = GameMenuCommand.weatherForeCast.getMatcher(input)) != null) {
            controller.weatherForecast();
        } else if ((matcher = GameMenuCommand.weatherSet.getMatcher(input)) != null) {
            controller.weatherSet(matcher.group("weather"));
        } else if ((matcher = GameMenuCommand.buildGreenHouse.getMatcher(input)) != null) {
            controller.buildGreenHouse();
        } else if ((matcher = GameMenuCommand.walk.getMatcher(input)) != null) {
            controller.walk(matcher.group("x"), matcher.group("y"));
        } else if ((matcher = GameMenuCommand.printMap.getMatcher(input)) != null) {
            controller.printMap();
        } else if ((matcher = GameMenuCommand.helpReadingMap.getMatcher(input)) != null) {
            controller.helpReadingMap();
        } else if ((matcher = GameMenuCommand.showEnergy.getMatcher(input)) != null) {
            controller.showEnergy();
        } else if ((matcher = GameMenuCommand.setEnergy.getMatcher(input)) != null) {
            controller.setEnergy(Integer.parseInt(matcher.group("energy")));
        } else if ((matcher = GameMenuCommand.setEnergyUnlimited.getMatcher(input)) != null) {
            controller.setEnergyUnlimited();
        } else if ((matcher = GameMenuCommand.showInventory.getMatcher(input)) != null) {
            controller.showInventory();
        } else if ((matcher = GameMenuCommand.InventoryTrash.getMatcher(input)) != null) {
            controller.inventoryTrash(matcher.group("name"), Integer.parseInt(matcher.group("number")));
        } else if ((matcher = GameMenuCommand.equipTools.getMatcher(input)) != null) {
            controller.equipTool(matcher.group("name"));
        } else if ((matcher = GameMenuCommand.showCurrentTool.getMatcher(input)) != null) {
            controller.showCurrentTools();
        } else if ((matcher = GameMenuCommand.showAvailableTools.getMatcher(input)) != null) {
            controller.showAvailableTools();
        } else if ((matcher = GameMenuCommand.upgradeTool.getMatcher(input)) != null) {
            controller.upgradeTool(matcher.group("name"));
        } else if ((matcher = GameMenuCommand.useTool.getMatcher(input)) != null) {
            controller.useTool(matcher.group("direction"));
        } else if ((matcher = GameMenuCommand.craftInfo.getMatcher(input)) != null) {
            controller.craftInfo(matcher.group("name"));
        } else if ((matcher = GameMenuCommand.plant.getMatcher(input)) != null) {
            controller.plant(matcher.group("seedName"), matcher.group("direction"));
        } else if ((matcher = GameMenuCommand.showPlant.getMatcher(input)) != null) {
            controller.showPlant(Integer.parseInt(matcher.group("x")), Integer.parseInt(matcher.group("y")));
        } else if ((matcher = GameMenuCommand.fertilize.getMatcher(input)) != null) {
            controller.fertilize(matcher.group("fertilizer"), matcher.group("direction"));
        } else if ((matcher = GameMenuCommand.howMuchWater.getMatcher(input)) != null) {
            controller.howMuchWater();
        } else if ((matcher = GameMenuCommand.showCraftingRecipes.getMatcher(input)) != null) {
            controller.craftingShowRecipes();
        } else if ((matcher = GameMenuCommand.craftCraftings.getMatcher(input)) != null) {
            controller.craft(matcher.group("name"));
        } else if ((matcher = GameMenuCommand.placeItem.getMatcher(input)) != null) {
            controller.placeItem(matcher.group("ItemName"), matcher.group("direction"));
        } else if ((matcher = GameMenuCommand.addItem.getMatcher(input)) != null) {
            controller.addItem(matcher.group("ItemName"), Integer.parseInt(matcher.group("count")));
        } else if ((matcher = GameMenuCommand.refigratoratorPut.getMatcher(input)) != null) {
            controller.refrigerator(false, matcher.group("ItemName"));
        } else if ((matcher = GameMenuCommand.refigratoratorPick.getMatcher(input)) != null) {
            controller.refrigerator(true, matcher.group("ItemName"));
        } else if ((matcher = GameMenuCommand.showCookingRecipes.getMatcher(input)) != null) {
            controller.showCookingRecipes();
        } else if ((matcher = GameMenuCommand.prepareFood.getMatcher(input)) != null) {
            controller.prepareFood(matcher.group("recipeName"));
        } else if ((matcher = GameMenuCommand.eat.getMatcher(input)) != null) {
            controller.eat(matcher.group("foodName"));
        } else if ((matcher = GameMenuCommand.build.getMatcher(input)) != null) {
            controller.build();
        } else if ((matcher = GameMenuCommand.buyAnimal.getMatcher(input)) != null) {
            controller.buyAnimal(matcher.group("animal"), matcher.group("animalName"));
        } else if ((matcher = GameMenuCommand.pet.getMatcher(input)) != null) {
            controller.pet(matcher.group("animalName"));
        } else if ((matcher = GameMenuCommand.setFreindship.getMatcher(input)) != null) {
            controller.setFriendship(matcher.group("animalName"), Integer.parseInt(matcher.group("value")));
        } else if ((matcher = GameMenuCommand.Animals.getMatcher(input)) != null) {
            controller.animals();
        } else if ((matcher = GameMenuCommand.shepherdAnimals.getMatcher(input)) != null) {
            controller.shepherdAnimal(matcher.group("animalName"), Integer.parseInt(matcher.group("x")), Integer.parseInt(matcher.group("y")));
        } else if ((matcher = GameMenuCommand.feedHay.getMatcher(input)) != null) {
            controller.feed(matcher.group("animalName"));
        } else if ((matcher = GameMenuCommand.produces.getMatcher(input)) != null) {
            controller.produces();
        } else if ((matcher = GameMenuCommand.collectProduces.getMatcher(input)) != null) {
            controller.collectProduce(matcher.group("animalName"));
        } else if ((matcher = GameMenuCommand.sellAnimal.getMatcher(input)) != null) {
            controller.sellAnimal(matcher.group("animalName"));
        } else if ((matcher = GameMenuCommand.fishing.getMatcher(input)) != null) {
            controller.fishing(matcher.group("fishingPole"));
        } else if ((matcher = GameMenuCommand.artisanUse.getMatcher(input)) != null) {
            List<ItemType> types = new ArrayList<>();
            for (ItemType itemType : ItemType.values()) {
                if (itemType.name().equalsIgnoreCase(matcher.group("itemName")))
                    types.add(itemType);
            }
            controller.artisanUse(matcher.group("artisanName"), types);
        } else if ((matcher = GameMenuCommand.artisanGet.getMatcher(input)) != null) {
            controller.artisanGet(matcher.group("artisanName"));
        } else if ((matcher = GameMenuCommand.showAllProducts.getMatcher(input)) != null) {
            controller.showAllProducts();
        } else if ((matcher = GameMenuCommand.showAllAvailableProducts.getMatcher(input)) != null) {
            controller.showAvailableProducts();
        } else if ((matcher = GameMenuCommand.purchase.getMatcher(input)) != null) {
            controller.Purchase(matcher.group("productName"), Integer.parseInt(matcher.group("count")));
        } else if ((matcher = GameMenuCommand.addDollars.getMatcher(input)) != null) {
            controller.addDollars(Integer.parseInt(matcher.group("count")));
        } else if ((matcher = GameMenuCommand.sell.getMatcher(input)) != null) {
            controller.sell(matcher.group("productName"),Integer.parseInt( matcher.group("count")));
        } else if ((matcher = GameMenuCommand.friendships.getMatcher(input)) != null) {
            controller.friendships();
        } else if ((matcher = GameMenuCommand.talk.getMatcher(input)) != null) {
            controller.talk(matcher.group("username"), matcher.group("message"));
        } else if ((matcher = GameMenuCommand.talkHistory.getMatcher(input)) != null) {
            controller.talkHistory(matcher.group("username"));
        } else if ((matcher = GameMenuCommand.gift.getMatcher(input)) != null) {
            controller.gift(matcher.group("username"), matcher.group("itemName"), matcher.group("amount"));
        } else if ((matcher = GameMenuCommand.giftList.getMatcher(input)) != null) {
            controller.giftList();
        } else if ((matcher = GameMenuCommand.giftHistory.getMatcher(input)) != null) {
            controller.giftHistory(matcher.group("username"));
        } else if ((matcher = GameMenuCommand.hug.getMatcher(input)) != null) {
            controller.hug(matcher.group("username"));
        } else if ((matcher = GameMenuCommand.flower.getMatcher(input)) != null) {
            controller.flower(matcher.group("username"));
        } else if ((matcher = GameMenuCommand.askMarriage.getMatcher(input)) != null) {
            controller.askMarriage(matcher.group("username"), matcher.group("ring"));
        } else if ((matcher = GameMenuCommand.meetNPC.getMatcher(input)) != null) {
            controller.meetNPC(matcher.group("npcName"));
        } else if ((matcher = GameMenuCommand.giftNPC.getMatcher(input)) != null) {
            controller.giftNPC(matcher.group("npcName"), matcher.group("itemName"));
        } else if ((matcher = GameMenuCommand.friendshipNPCList.getMatcher(input)) != null) {
            controller.friendshipNPCList();
        } else if ((matcher = GameMenuCommand.questsList.getMatcher(input)) != null) {
            controller.questsList(matcher.group("npcName"));
        } else if ((matcher = GameMenuCommand.questFinish.getMatcher(input)) != null) {
            controller.questFinish(matcher.group("npcName"), matcher.group("index"));
        } else {
            System.out.println("invalid command");
        }
        controller.gameLoop();
    }
}
