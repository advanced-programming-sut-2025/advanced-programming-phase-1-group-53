package Models.Abilities;

import Enums.ItemType;
import Enums.ShopNames;
import Enums.TileKind;
import Enums.ToolLevel;
import Models.Game.App;
import Models.Game.Game;
import Models.Items.Buildings.Shop;
import Models.Items.Foragings.ForagingMineral;
import Models.Items.Item;
import Models.Items.Tool;
import Models.MessageManager;
import Models.Result;

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

                break;
            case Pickaxe:
                usePickAxe(x, y);
                break;
            case WateringCan:

                break;
            case Scythe:

                break;
            case Shear:

                break;
            case MilkPail:

                break;
            default:

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

       // if(App.getGame().findTile(x, y).getItem() instanceof)
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
