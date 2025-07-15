package com.stardew.GameLogic.Models.Abilities;

import com.stardew.GameLogic.Enums.ItemType;
import com.stardew.GameLogic.Enums.ShopNames;
import com.stardew.GameLogic.Enums.TileKind;
import com.stardew.GameLogic.Enums.ToolLevel;
import com.stardew.GameLogic.Models.Game.App;
import com.stardew.GameLogic.Models.Items.Animal;
import com.stardew.GameLogic.Models.Items.Buildings.Shop;
import com.stardew.GameLogic.Models.Items.Foragings.*;
import com.stardew.GameLogic.Models.Items.Item;
import com.stardew.GameLogic.Models.Items.Tool;
import com.stardew.GameLogic.Models.Items.WateringCan;
import com.stardew.GameLogic.Models.MessageManager;
import com.stardew.GameLogic.Models.Result;

public class Activity {
    public void equipTool(ItemType itemType){
        for(Item item : App.getGame().getCurrentPlayer().backpack.getItems().keySet()){
            if(item.getItemType().equals(itemType)) {
                App.getGame().getCurrentPlayer().backpack.setItemInHand(item);
                return;
            }
        }
        MessageManager.getMessage(Result.failure("No such tool in inventory."));
    }

    public void showCurrentTool(){
        if(App.getGame().getCurrentPlayer().backpack.getItemInHand() == null)
            MessageManager.getMessage(Result.failure("You don't have any tool currently."));
        else if(!(App.getGame().getCurrentPlayer().backpack.getItemInHand() instanceof Tool))
            MessageManager.getMessage(Result.failure("You handle some other item than a tool."));
        else
            MessageManager.getMessage(Result.success("Item in hand : " + App.getGame().getCurrentPlayer().backpack.getItemInHand().getItemType().name()));
    }

    public void showAvailableTools(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Item item : App.getGame().getCurrentPlayer().backpack.getTools().keySet()) {
            if(App.getGame().getCurrentPlayer().backpack.getTools().get(item) == 0)
                continue;
            stringBuilder.append(item.getItemType().name() + ", ");
        }
        if(App.getGame().getCurrentPlayer().backpack.getTools().isEmpty())
            MessageManager.getMessage(Result.failure("No tool available in the backpack."));
        else
            MessageManager.getMessage(Result.success("Available tools : \n" + stringBuilder.toString()));
    }

    public void upgradeTool(ItemType itemType){
        if(!(App.getGame().getItemByItemType(itemType) instanceof Tool)){
            MessageManager.getMessage(Result.failure("You could only upgrade a tool"));
        }
        Tool tool = (Tool) App.getGame().getItemByItemType(itemType);
        if(tool.getItemType().equals(ItemType.Scythe) || tool.getItemType().equals(ItemType.Shear) ||
                tool.getItemType().equals(ItemType.FishingPole) || tool.getItemType().equals(ItemType.MilkPail) ){
            MessageManager.getMessage(Result.failure("These items are not upgrade able."));
            return;
        }
        ItemType itemType1 = switch (tool.getLevel()){
            case normal -> ItemType.CopperBar;
            case copper -> ItemType.IronBar;
            case iron -> ItemType.GoldBar;
            case gold -> ItemType.IridiumBar;
            default -> ItemType.IridiumBar;
        };
        if(!App.getGame().getCurrentPlayer().backpack.areItemsAvailable(App.getGame().getItemByItemType(itemType1), 5)){
            MessageManager.getMessage(Result.failure("Insufficient material."));
        }

        int price ;
        if(itemType.equals(ItemType.Trashcan)){
            price = switch (tool.getLevel()) {
                case normal -> 1000;
                case copper -> 2500;
                case iron -> 5000;
                case gold -> 12500;
                default -> 0;
            };
        }
        else {
            price = switch (tool.getLevel()) {
                case normal -> 2000;
                case copper -> 5000;
                case iron -> 10000;
                case gold -> 15000;
                default -> 0;
            };
        }

        if(!App.getGame().getCurrentPlayer().personalInfo.hasEnoughGold(price)){
            MessageManager.getMessage(Result.failure("Not enough gold."));
            return;
        }
        if(App.getGame().getGameMap().findBuilding(App.getGame().getCurrentPlayer().position.getX(),
                App.getGame().getCurrentPlayer().position.getY()) instanceof Shop shop){
            if(!shop.getShopName().equals(ShopNames.Blacksmith)){
                MessageManager.getMessage(Result.failure("You must be inside Blacksmith."));
                return;
            }
            App.getGame().getCurrentPlayer().personalInfo.updateGold(-price);
            ToolLevel toolLevel = switch (tool.getLevel()){
                case normal -> ToolLevel.copper;
                case copper -> ToolLevel.iron;
                case iron -> ToolLevel.gold;
                case gold -> ToolLevel.iridium;
                case iridium -> ToolLevel.iridium;
                case bamboo -> null;
                case fiberglass -> null;
            };
            tool.setLevel(toolLevel);
        }
        else
            MessageManager.getMessage(Result.failure("You must be in a shop."));
    }

    public void useTool(int x, int y){
        switch (App.getGame().getCurrentPlayer().backpack.getItemInHand().getItemType()){
            case Hoe:
                useHoe(x, y);
                break;
            case Axe:
                useAxe(x, y);
                break;
            case Pickaxe:
                usePickAxe(x, y);
                break;
            case WateringCan:
                useWateringCan(x, y);
                break;
            case Scythe:
                useScythe(x, y);
                break;
            case Shear:
                useShear(x, y);
                break;
            case MilkPail:
                useMilkPail(x, y);
                break;
            default:

        }
    }

    private void useWateringCan(int x, int y){
        int energy = ((Tool) App.getGame().getCurrentPlayer().backpack.getItemInHand()).getEnergyConsumed();
        if(!(App.getGame().getCurrentPlayer().energy.getEnergy() > energy)){
            MessageManager.getMessage(Result.failure("Not enough energy to continue."));
            return;
        }
        App.getGame().getCurrentPlayer().energy.updateEnergy(-energy);

        Item item = App.getGame().findTile(x, y).getItem();

        if(item instanceof Tree tree){
            tree.setNotWateredDays(0);
            if(App.getGame().getCurrentPlayer().backpack.getItemInHand() instanceof WateringCan wateringCan){
                wateringCan.setCurrentWaterLevel(wateringCan.getCurrentWaterLevel()-1);
            }
        }

        if(item instanceof PlantAbleCrop plantAbleCrop){
            plantAbleCrop.setNotWateredDays(0);
            if(App.getGame().getCurrentPlayer().backpack.getItemInHand() instanceof WateringCan wateringCan){
                wateringCan.setCurrentWaterLevel(wateringCan.getCurrentWaterLevel()-1);
            }
        }
    }

    private void useScythe(int x, int y){
        int energy = ((Tool) App.getGame().getCurrentPlayer().backpack.getItemInHand()).getEnergyConsumed();
        if(!(App.getGame().getCurrentPlayer().energy.getEnergy() > energy)){
            MessageManager.getMessage(Result.failure("Not enough energy to continue."));
            return;
        }
        App.getGame().getCurrentPlayer().energy.updateEnergy(-energy);
        if(App.getGame().findTile(x, y).getItem() != null){
            MessageManager.getMessage(Result.failure("The tile already has some item."));
            return;
        }

        Item item = App.getGame().findTile(x, y). getItem();

        if(item instanceof Tree tree){
            if(tree.isReadyForHarvest())
                App.getGame().getCurrentPlayer().abilities.normalFarming.harvest(x, y);
        }
    }

    private void useMilkPail(int x, int y){
        int energy = ((Tool) App.getGame().getCurrentPlayer().backpack.getItemInHand()).getEnergyConsumed();
        if(!(App.getGame().getCurrentPlayer().energy.getEnergy() > energy)){
            MessageManager.getMessage(Result.failure("Not enough energy to continue."));
            return;
        }
        Animal animal1 = null;
        for(Animal animal : App.getGame().getCurrentPlayer().backpack.getAnimals()){
            if(animal.getPosition().isHere(x, y)) {
                animal1 = animal;
                break;
            }
        }

        if(animal1 == null){
            MessageManager.getMessage(Result.failure("No animal around."));
            return;
        }
        if(animal1.getItemType().equals(ItemType.Goat) || animal1.getItemType().equals(ItemType.Cow))
            animal1.collectProducts();
    }

    private void useShear(int x, int y){
        int energy = ((Tool) App.getGame().getCurrentPlayer().backpack.getItemInHand()).getEnergyConsumed();
        if(!(App.getGame().getCurrentPlayer().energy.getEnergy() > energy)){
            MessageManager.getMessage(Result.failure("Not enough energy to continue."));
            return;
        }
        App.getGame().getCurrentPlayer().energy.updateEnergy(-energy);

        Animal animal1 = null;
        for(Animal animal : App.getGame().getCurrentPlayer().backpack.getAnimals()){
            if(animal.getPosition().isHere(x, y)) {
                animal1 = animal;
                break;
            }
        }
        if(animal1 == null){
            MessageManager.getMessage(Result.failure("No animal around."));
            return;
        }
        if(animal1.getItemType().equals(ItemType.Sheep) || animal1.getItemType().equals(ItemType.Rabbit))
            animal1.collectProducts();
    }

    private void useAxe(int x, int y){
        int energy = ((Tool) App.getGame().getCurrentPlayer().backpack.getItemInHand()).getEnergyConsumed();
        if(!(App.getGame().getCurrentPlayer().energy.getEnergy() > energy)){
            MessageManager.getMessage(Result.failure("Not enough energy to continue."));
            return;
        }
        App.getGame().getCurrentPlayer().energy.updateEnergy(-energy);

        Item item = App.getGame().findTile(x, y).getItem();
        if(item instanceof Tree || item instanceof ForagingTree){
            App.getGame().findTile(x, y).setItem(null);
            App.getGame().getCurrentPlayer().backpack.addItem(ForagingMineral.Wood, 5);
            if(item instanceof Tree tree){
                if(tree.getItemType().equals(ItemType.MapleTree)){
                    App.getGame().getCurrentPlayer().backpack.addItem(Fruit.MapleSyrup, 1);
                }
                if(tree.getItemType().equals(ItemType.MysticTree)){
                    App.getGame().getCurrentPlayer().backpack.addItem(Fruit.MysticSyrup, 1);
                }
            }
        }

        if(item.getItemType().equals(ItemType.Wood)){
            App.getGame().findTile(x, y).setItem(null);
            App.getGame().getCurrentPlayer().backpack.addItem(ForagingMineral.Wood, 1);
        }
    }

    private void usePickAxe(int x, int y){
        int energy = ((Tool) App.getGame().getCurrentPlayer().backpack.getItemInHand()).getEnergyConsumed();
        if(!(App.getGame().getCurrentPlayer().energy.getEnergy() > energy)){
            MessageManager.getMessage(Result.failure("Not enough energy to continue."));
            return;
        }
        App.getGame().getCurrentPlayer().energy.updateEnergy(-energy);
        if(App.getGame().findTile(x, y).getItem() != null){
            MessageManager.getMessage(Result.failure("The tile already has some item."));
            return;
        }


        if(App.getGame().findTile(x, y).getTileKind().equals(TileKind.plowed)){
            App.getGame().findTile(x, y).setTileKind(TileKind.empty);
        }
        if(App.getGame().findTile(x, y).getItem() != null &&
                App.getGame().findTile(x, y).getItem() instanceof ForagingMineral foragingMineral) {
            if(foragingMineral.getItemType().name().contains("Ore")){
                if(foragingMineral.getItemType().ordinal() - ItemType.CopperOre.ordinal() >
                        (((Tool) App.getGame().getCurrentPlayer().backpack.getItemInHand()).getLevel().getLevel()-1)){
                    MessageManager.getMessage(Result.failure("Not enough level to mine the mineral."));
                    return;
                }

            }
            else if(((Tool) App.getGame().getCurrentPlayer().backpack.getItemInHand()).getLevel().getLevel() < 3){
                MessageManager.getMessage(Result.failure("Not enough level to mine the mineral."));
                return;
            }
            App.getGame().getCurrentPlayer().backpack.addItem(App.getGame().
                    getItemByItemType(foragingMineral.getItemType()));
        }
        App.getGame().findTile(x, y).setItem(null);
    }

    private void useHoe(int x, int y){
        int energy = ((Tool) App.getGame().getCurrentPlayer().backpack.getItemInHand()).getEnergyConsumed();
        if(!(App.getGame().getCurrentPlayer().energy.getEnergy() > energy)){
            MessageManager.getMessage(Result.failure("Not enough energy to continue."));
            return;
        }
        App.getGame().getCurrentPlayer().energy.updateEnergy(-energy);
        if(App.getGame().findTile(x, y).getItem() != null){
            MessageManager.getMessage(Result.failure("The tile already has some item."));
            return;
        }

        if(!App.getGame().findTile(x, y).getTileKind().equals(TileKind.grass) &&
                !App.getGame().findTile(x, y).getTileKind().equals(TileKind.empty)){
            MessageManager.getMessage(Result.failure("The tile must be empty or grass."));
            return;
        }

        App.getGame().findTile(x, y).setTileKind(TileKind.plowed);
        MessageManager.getMessage(Result.success("The tile was plowed successfully."));
    }

    public void placeItem(ItemType itemType, int x, int y){
        if(App.getGame().findTile(x, y).getItem() != null){
            MessageManager.getMessage(Result.failure("The tile already has some item."));
            return;
        }
        if(App.getGame().getCurrentPlayer().backpack.areItemsAvailable(App.getGame().getItemByItemType(itemType), 1)){
            App.getGame().getCurrentPlayer().backpack.getItems().compute(App.getGame().getItemByItemType(itemType),
                    (k, v) -> (v-1));
            App.getGame().findTile(x, y).setItem(App.getGame().getItemByItemType(itemType).clone());
            MessageManager.getMessage(Result.success(itemType.name() + " was placed successfully at " +x +", " + y));
        }
        else
            MessageManager.getMessage(Result.failure("The product is not available."));
    }

    public void addItem(ItemType itemType, int quantity){
        if(App.getGame().getCurrentPlayer().backpack.isInventoryFull()) {
            MessageManager.getMessage(Result.failure("Inventory is full."));
            return;
        }
        App.getGame().getCurrentPlayer().backpack.addItem(App.getGame().getItemByItemType(itemType), quantity);
    }

}
