package Models.Game;

import Enums.Menu;

public class App {
    private static App app = null;
    private Menu menu;
    private static Game game = new Game();
    private App(){

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
