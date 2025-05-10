package Models.Game;

import Enums.ItemType;
import Models.DateAndTime;
import Models.Items.Item;
import Models.Product;
import Models.Weather;

import java.util.ArrayList;

public class Game {
    private int numOfPlayers;
    private int numOfTurn;
    public final ArrayList<Player> players = new ArrayList<>();
    public final DateAndTime dateAndTime = new DateAndTime();
    public final Weather weather = new Weather();
    public final ArrayList<Product> sellAbleProducts = new ArrayList<>();//sellable By Players



    public Player getCurrentPlayer(){
        return players.get(numOfTurn);
    }

    public Item getItemByItemType(ItemType itemType){

    }

    public Product getProductByItemType(ItemType itemType){
        for(Product product : sellAbleProducts){
            if(product.)
        }
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        numOfPlayers = numOfPlayers;
    }

    public int getNumOfTurn() {
        return numOfTurn;
    }

    public void setNumOfTurn(int numOfTurn) {
        numOfTurn = numOfTurn;
    }

    Game(){

    }
    public Player getPlayer(int playerIndex){
        if(playerIndex > players.size() || playerIndex < 0){
            return null;
        }
        return players.get(playerIndex);
    }

    public static Player whichPlayersFarm(int x, int y){
        return null;
    }

    public static boolean isInFarm(int x, int y){
        return false;
    }
    public boolean isInCave(int x, int y){
        return false;
    }
    public boolean isInField(int x, int y){
        return false;
    }
}
