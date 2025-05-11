package Controllers;

import Models.Farm;
import Models.GameMap;
import Models.Player;
import Models.Position;

public class GameMenuController {
    public void createNewGame() {
        createGameMap();
    }
    private void createGameMap() {
        GameMap gameMap = new GameMap();
    }
    private void createFarm(Position position, Player player) {
        Farm farm = new Farm(position, player);
    }
}
