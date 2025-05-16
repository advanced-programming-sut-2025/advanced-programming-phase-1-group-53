import Enums.ItemType;
import Models.Game.App;
import Models.Game.Game;
import Models.Items.Item;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        App app = App.getInstance();
        app.signupGame();
        app.newGame();
        Game game = App.getGame();

        if(game == null)
            System.out.println("ll");
        game.getCurrentPlayer().backpack.addItem(game.getItemByItemType(ItemType.Stone), 25);
        game.getCurrentPlayer().backpack.addItem(game.getItemByItemType(ItemType.Coal), 100);
        game.getCurrentPlayer().backpack.addItem(game.getItemByItemType(ItemType.IronOre), 30);
        game.getCurrentPlayer().backpack.addItem(game.getItemByItemType(ItemType.Wood), 500);
        game.getCurrentPlayer().backpack.showInventory();
        game.getCurrentPlayer().abilities.crafting.artisanUse(ItemType.Furnace, List.of(ItemType.IronOre, ItemType.Coal));
        game.getCurrentPlayer().backpack.showInventory();
        int energy = (int)game.getCurrentPlayer().moveTo(10, 5);
        game.getCurrentPlayer().applyMovementCost(energy, 1, 7);
        game.getGameMap().generateRandomThings();
        game.getGameMap().generateRandomThings();
        game.getGameMap().generateRandomThings();
        game.printMap();
        System.out.println(game.getCurrentPlayer().energy.getEnergy());
    }
}