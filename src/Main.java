import Enums.ItemType;
import Models.Game.App;
import Models.Game.Game;
import Models.Items.Item;
import Models.NPC;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
        int energy = game.getCurrentPlayer().moveTo(2, 2);
        game.getCurrentPlayer().applyMovementCost(energy, 2, 2);
        game.printMap();
        scanner.nextLine();
        game.getCurrentPlayer().applyMovementCost(5, 5, 0);
        System.out.println(game.getCurrentPlayer().position.getX() + " " + game.getCurrentPlayer().position.getY());
        game.getGameMap().generateRandomThings();
        game.getGameMap().generateRandomThings();
        game.getGameMap().generateRandomThings();
        game.printMap();
        game.mapHelper();
        System.out.println(game.getCurrentPlayer().energy.getEnergy());
        NPC.showRequests(NPC.Sebastian);
    }
}