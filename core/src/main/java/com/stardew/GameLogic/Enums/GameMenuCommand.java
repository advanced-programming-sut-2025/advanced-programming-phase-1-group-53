package com.stardew.GameLogic.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommand implements Command {
    exit("^exit$"),
    showCurrentMenu("^show current menu$"),
    enterMenu("^enter menu -a (?<menu>.+?)$"),
    exitGame("^exit game$"),
    newGame("^new game (?<username1>.+?) (?<username2>.+?) (?<username3>.+?)$"),
    loadGame("^load game (?<index>.+?)$"),
    selectMap("^select map (?<mapNumber>.+?)$"),
    nextTurn("^next turn$"),
    time("^time$"),
    date("^date$"),
    dateTime("^datetime$"),
    dayOfTheWeek("^day of the week$"),
    advanceTime("^advance time (?<time>.+?)h$"),
    advanceDate("^advance date (?<date>.+?)d$"),
    season("^season$"),
    thor("^thor -l (?<x>.+?) (?<y>.+?)$"),
    weather("^weather$"),
    weatherForeCast("^weather forecast$"),
    weatherSet("^weather set (?<weather>.+?)$"),
    buildGreenHouse("^build greenhouse$"),
    walk("^walk -l (?<x>.+?) (?<y>.+?)$"),
    printMap("^print map$"),
    helpReadingMap("^help reading map$"),
    showEnergy("^show energy$"),
    setEnergy("^set energy -v (?<energy>.+?)$"),
    setEnergyUnlimited("^set energy unlimited$"),
    showInventory("^show inventory$"),
    InventoryTrash("^inventory trash -i (?<name>.+?) -n (?<number>.+?)$"),
    equipTools("^tools equip (?<name>.+?)$"),
    showCurrentTool("^show current tool$"),
    showAvailableTools("^show available tools$"),
    upgradeTool("^upgrade tool (?<name>.+?)$"),
    useTool("^use tool -d (?<direction>.+?)$"),
    craftInfo("^craft info -n (?<name>.+?)$"),
    plant("^plant seed -s (?<seedName>.+?) -d (?<direction>.+?)$"),
    showPlant("^show plant -l (?<x>.+?) (?<y>.+?)$"),
    fertilize("^fertilize -f (?<fertilizer>.+?) -d (?<direction>.+?)$"),
    howMuchWater("^how much water$"),
    showCraftingRecipes("^show crafting recipes$"),
    craftCraftings("^craft -n (?<name>.+?)$"),
    placeItem("^place item -n (?<ItemName>.+?) -d (?<direction>.+?)$"),
    addItem("^add item -n (?<ItemName>.+?) -c (?<count>.+?)$"),
    refigratoratorPut("^refigratorator put -n (?<ItemName>.+?)$"),
    refigratoratorPick("^refigratorator pick -n (?<ItemName>.+?)$"),
    showCookingRecipes("^show cookinfg recipes$"),
    prepareFood("^prepare food (?<recipeName>.+?)$"),
    eat("^eat (?<foodName>.+?)$"),
    build("^build -a (?<buildingName>.+?) -l (?<x>.+?) (?<y>.+?)$"),
    buyAnimal("^buy animal -a (?<animal>.+?) -n (?<animalName>.+?)$"),
    pet("^pet -n (?<animalName>.+?)$"),
    setFreindship("^set friendship -n (?<animalName>.+?) -v (?<value>.+?)$"),
    Animals("^animals$"),
    shepherdAnimals("^shepherd animals -n (?<animalName>.+?) -l (?<x>.+?) (?<y>.+?)$"),
    feedHay("^feed hay -n (?<animalName>.+?)$"),
    produces("^produces$"),
    collectProduces("^collect produces -n (?<animalName>.+?)$"),
    sellAnimal("^sell animal -n (?<animalName>.+?)$"),
    fishing("^fishing -p (?<fishingPole>.+?)$"),
    artisanUse("^artisan use (?<artisanName>.+?) (?<itemName>.+?)$"),
    artisanGet("^artisan get (?<artisanName>.+?)$"),
    showAllProducts("^show all products$"),
    showAllAvailableProducts("^show all available products$"),
    purchase("^purchase (?<productName>.+?) -n (?<count>.+?)"),
    addDollars("^add dollars -n (?<count>.+?)$"),
    sell("^sell (?<productName>.+?) -n (?<count>.+?)$"),
    friendships("^friendships$"),
    talk("^talk -u (?<username>.+?) -m (?<message>.+?)$"),
    talkHistory("^talk history -u (?<username>.+?)$"),
    gift("^gift -u (?<username>.+?) -i (?<itemName>.+?) -a (?<amount>.+?)$"),
    giftList("^gift list$"),
    giftHistory("^gift history -u (?<username>.+?)$"),
    hug("^hug -u (?<username>.+?)$"),
    flower("^flower -u (?<username>.+?)$"),
    askMarriage("^ask marriage -u (?<username>.+?) -r (?<ring>.+?)$"),
    meetNPC("^meet npc -n (?<npcName>.+?)$"),
    giftNPC("^gift npc -n (?<npcName>.+?) -i (?<itemName>.+?)$"),
    friendshipNPCList("^friendship npc list$"),
    questsList("^quests list (?<npcName>.+?)$"),
    questFinish("^quest finish (?<npcName>.+?) (?<index>.+?)$");
    private final String pattern;

    GameMenuCommand(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
