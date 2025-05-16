package Models.Game;

import Enums.Gender;
import Enums.Menu;

import java.util.ArrayList;
import java.util.List;

public class App {
    private static App app = null;
    private Menu menu;
    private static Game game;
    private final ArrayList<Player> players = new ArrayList<>();

    private App(){
        menu = Menu.gameMenu;
    }

    public void signupGame(){
        players.add(new Player("Ali", "1234", "bdzvhvzd", Gender.MALE));
        players.add(new Player("aaaa", "1234", "bdzvhvzd", Gender.FEMALE));
        players.add(new Player("adfsvdsv", "1234", "bdzvhvzd", Gender.MALE));
        players.add(new Player("wesgege", "1234", "bdzvhvzd", Gender.MALE));
    }

    public void newGame(){
        game = new Game(List.of(players.get(0), players.get(1), players.get(2), players.get(3)));
    }

    public static App getInstance(){
        if(app == null){
            app = new App();
        }
        return app;
    }



    public static Game getGame(){
        return app.game;
    }

    private void setGame(Game game){
        this.game = game;
    }

    private void displayMessage(String message){

    }
}
