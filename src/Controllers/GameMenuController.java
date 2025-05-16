package Controllers;

import Models.Farm;
import Models.GameMap;
import Models.Position;
import Models.Game.Player;

public class GameMenuController {
    public void createNewGame() {
        createGameMap();
    }
    private void createGameMap() {
        //GameMap gameMap = new GameMap();
    }
    private void createFarm(Position position, Player player) {
        Farm farm = new Farm(position, player);
    }
}
