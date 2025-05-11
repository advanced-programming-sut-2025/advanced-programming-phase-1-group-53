package Models;

import Models.Game.Player;

public class PlayersInteractions {
    public static void talk(Player currentPlayer, Player targetPlayer, String message) {
        currentPlayer.getConversation().get(targetPlayer).append(message).append("\n");
        currentPlayer.changeFriendship(targetPlayer, 20);
    }
    public static void hug(Player currentPlayer, Player targetPlayer) {
        currentPlayer.changeFriendship(targetPlayer, 60);
    }
    public static void giveFlower(Player currentPlayer, Player targetPlayer) {
        currentPlayer.changeFriendship(targetPlayer, 20);
    }
}
