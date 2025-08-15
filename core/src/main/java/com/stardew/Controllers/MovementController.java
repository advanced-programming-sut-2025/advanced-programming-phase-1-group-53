package com.stardew.Controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.stardew.Enums.ItemType;
import com.stardew.Enums.TileKind;
import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Game;
import com.stardew.Models.GameMap;
import com.stardew.Models.Items.Buildings.Shop;
import com.stardew.Models.Items.CoopAndBarn;
import com.stardew.Models.Items.ShippingBin;
import com.stardew.Models.MessageManager;
import com.stardew.Models.Result;
import com.stardew.Views.GameMenu;
import com.stardew.Views.TabMenus.OceanMenu;

public class MovementController {
    private Game game;

    Vector2 v2;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean canPlayerMove(int direction){
        int x = 0;
        int y = 0;
        if(direction == 2) y = App.ADVANCE_OF_EACH_STEP;
        if(direction == 0) y = -App.ADVANCE_OF_EACH_STEP;
        if(direction == 1) x = App.ADVANCE_OF_EACH_STEP;
        if(direction == 3) x = -App.ADVANCE_OF_EACH_STEP;
        v2 = GameMap.getPositionByCoordinates(App.getCurrentPlayer().position.getX() + x,
            App.getCurrentPlayer().position.getY() + y);
        if(!App.getGame().getGameMap().areInBound(App.getCurrentPlayer().position.getX() + x,
            App.getCurrentPlayer().position.getY() + y))
            return false;
        if(App.getGame().getGameMap().getTileByPixelCoordinate(App.getCurrentPlayer().position.getX() + x,
            App.getCurrentPlayer().position.getY() + y).getTileKind().equals(TileKind.house)){
            GameMenu.getInstance().setGoingInHouse(true);
        }
        if(App.getGame().getGameMap().getTileByPixelCoordinate(App.getCurrentPlayer().position.getX() + x,
            App.getCurrentPlayer().position.getY() + y).getTileKind().equals(TileKind.coop)){
        }
        if(App.getGame().getGameMap().getTileByPixelCoordinate(App.getCurrentPlayer().position.getX() + x,
            App.getCurrentPlayer().position.getY() + y).getTileKind().equals(TileKind.shop)){
            Shop shop = Shop.getShopByPosition((int)v2.x, (int)v2. y);
            if(shop != null){
//                shop.setUpShopMenu();            //TODO uncomment
            }
        }if(App.getGame().getGameMap().getTileByPixelCoordinate(App.getCurrentPlayer().position.getX() + x,
            App.getCurrentPlayer().position.getY() + y).getTileKind().equals(TileKind.shippingBin)){
//            ShippingBin.ShippingBin.setUpSellingMenu();
            //TODO uncomment
        }
        if(App.getGame().getGameMap().getTileByPixelCoordinate(App.getCurrentPlayer().position.getX() + x,
            App.getCurrentPlayer().position.getY() + y).getTileKind().equals(TileKind.coop)){
                ((CoopAndBarn)(App.getGame().getGameMap().getTileByPixelCoordinate(App.getCurrentPlayer().position.getX() + x,
                    App.getCurrentPlayer().position.getY() + y).getItem())).setUpCoopMenu();
        }
        if(App.getGame().getGameMap().getTileByPixelCoordinate(App.getCurrentPlayer().position.getX() + x,
            App.getCurrentPlayer().position.getY() + y).getTileKind().equals(TileKind.lake)){
            try{
                if(App.getCurrentPlayer().backpack.getItemInHand().getItemType().equals(ItemType.FishingPole)) {
                    OceanMenu.getInstance().setPlayer(App.getCurrentPlayer());
                    OceanMenu.getInstance().setChanged(true);
                    Main.main.setScreen(OceanMenu.getInstance());
                }
                else {
                    MessageManager.getMessage(Result.success("you must handle a fishing rod to enter the lake."));
                }
            }
            catch (Exception e){
                MessageManager.getMessage(Result.success("you must handle a fishing rod to enter the lake."));
            }
        }
        return App.getGame().getGameMap().getTileByPixelCoordinate(App.getCurrentPlayer().position.getX() + x,
            App.getCurrentPlayer().position.getY() + y).getTileKind().isWalkable();
    }

    public void movePlayer(int keyPressed){
        try {
            if (keyPressed == Input.Keys.W) {
                App.getCurrentPlayer().setDirection(2);
                App.getCurrentPlayer().setIdle(false);
            }
            if (keyPressed == Input.Keys.S) {
                App.getCurrentPlayer().setDirection(0);
                App.getCurrentPlayer().setIdle(false);
            }
            if (keyPressed == Input.Keys.A) {
                App.getCurrentPlayer().setDirection(3);
                App.getCurrentPlayer().setIdle(false);
            }
            if (keyPressed == Input.Keys.D) {
                App.getCurrentPlayer().setDirection(1);
                App.getCurrentPlayer().setIdle(false);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stopMoving(int keyPressed){
        App.getCurrentPlayer().setIdle(true);
    }

    public Vector2 getV2() {
        return v2;
    }
}
