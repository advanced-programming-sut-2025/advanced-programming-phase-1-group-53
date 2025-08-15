package com.stardew.Controllers.InGameControllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.stardew.Controllers.GameMenuController;
import com.stardew.Enums.ItemType;
import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Models.GameMap;
import com.stardew.Models.Items.Animal;
import com.stardew.Models.Items.Tool;
import com.stardew.Models.MessageManager;
import com.stardew.Models.Result;
import com.stardew.Network.Common.Packet.ClientPacket.KeyboardPackets.*;
import com.stardew.Views.GameMenu;
import com.stardew.Views.TabMenus.CookingMenu;
import com.stardew.Views.TabMenus.CraftingMenu;
import com.stardew.Views.TabMenus.InventoryMenu;
import com.stardew.Views.TabMenus.MapMenu;

import static com.stardew.Views.GameMenu.angleBetweenPoints;

public class AbilityMenuController extends Controller {

    public static final String MENU_NAME = "AbilityMenu";

    @Override
    public Result keyUp(KeyUpPacket keyUpPacket) {
         int keycode=keyUpPacket.keycode;
        Vector2 v = GameMap.getPositionByCoordinates((int) (App.getCurrentPlayer().position.getX()),
            (int) (App.getCurrentPlayer().position.getY()));
        int x = (int) (v.x+App.getCurrentPlayer().getDirectionVector().x);
        int y = (int)(v.y + App.getCurrentPlayer().getDirectionVector().y);
        if(keycode == Input.Keys.W || keycode == Input.Keys.A ||keycode == Input.Keys.S ||
            keycode == Input.Keys.D){
            GameMenuController.mvc.stopMoving(keycode);
            return new Result(true, "");
        }
        if(keycode == Input.Keys.Q){
            if(App.getCurrentPlayer().backpack.getItemInHand() instanceof Tool){
                ((Tool) App.getCurrentPlayer().backpack.getItemInHand()).setHoldInPlace(false);
            }
            return new Result(true, "");

        }

        return new Result(true, "");
    }

    @Override
    public Result keyDown(KeyDownPacket keyDownPacket) {
        System.out.println(App.getCurrentPlayer().personalInfo.getName()+"awwwaw"+
            App.getCurrentPlayer());
        System.out.println(App.getGame().players.get(0)+"mm"+App.getGame().players.get(1));
        int keycode = keyDownPacket.keycode;

        Vector2 v = GameMap.getPositionByCoordinates((int) (App.getCurrentPlayer().position.getX()),
                (int) (App.getCurrentPlayer().position.getY()));
        int x = (int) (v.x+App.getCurrentPlayer().getDirectionVector().x);
        int y = (int)(v.y + App.getCurrentPlayer().getDirectionVector().y);
        if(keycode == Input.Keys.W || keycode == Input.Keys.A ||keycode == Input.Keys.S ||
                keycode == Input.Keys.D){
            GameMenuController.mvc.movePlayer(keycode);
            return new Result(true, "MovePlayer");

        }
        if(keycode == Input.Keys.ESCAPE){
            Main.main.setScreen(InventoryMenu.getInstance());
            return new Result(true, "Inventory Menu");
        }
        if(keycode == Input.Keys.T){
            System.out.println("t");
            GameMenu.getInstance().triggerThunder();
        }
//        if(keycode == Input.Keys.H){
//            setHideEnergyBar(!isHideEnergyBar());
//        }
        if(keycode == Input.Keys.Q){
            GameMenu.getInstance().useItem(x, y,
                    App.getGame().getItemByItemType(App.getCurrentPlayer().backpack.getItemInHand().getItemType()));
        }
        if (keycode == Input.Keys.Y) {
            GameMenu.getInstance().getController().abilities.normalFarming.plant(ItemType.PomegranateSapling, x, y);
        }
        if (keycode == Input.Keys.Z) {
            GameMenu.getInstance().getController().abilities.cooking.showCookingRecipes();
        }
        // TODO fix this if
//        if(keycode == Input.Keys.K){
//            Main.main.setScreen(CheatMenuController.getInstance());
//        }
//        if(keycode == Input.Keys.C){
//            Main.main.setScreen(CookingMenu.getInstance());
//        }
//        if(keycode == Input.Keys.B){
//            Main.main.setScreen(CraftingMenu.getInstance());
//            return new Result(true, "Crafting Menu");
//        }
        if(keycode == Input.Keys.O){
            GameMenu.getInstance().setSHOW_TILE_DETAILS(!GameMenu.getInstance().isSHOW_TILE_DETAILS());
            if(!GameMenu.getInstance().isSHOW_TILE_DETAILS())
                MessageManager.setShowTileDetailButton(null, 0, 0);
        }
        if(keycode == Input.Keys.P){
            for(Animal animal : App.getCurrentPlayer().backpack.getAnimals()){
                animal.pet();
            }
        }
        return new Result(false, "non of your business");
    }

    @Override
    public Result mouseMove(MouseMovePacket mouseMovePacket) {

        return new Result(true, "");
    }

    @Override
    public Result touchDown(TouchDownPacket touchDownPacket) {
        float mouseX = touchDownPacket.screenX;
        float mouseY = GameMenu.getScreenHeight()-touchDownPacket.screenY;
        int button = touchDownPacket.button;

        Vector2 v = GameMap.getPositionByCoordinates((int) (App.getCurrentPlayer().position.getX()),
            (int) (App.getCurrentPlayer().position.getY()));
        int x = (int) (v.x+App.getCurrentPlayer().getDirectionVector().x);
        int y = (int)(v.y + App.getCurrentPlayer().getDirectionVector().y);

        if(button == Input.Buttons.LEFT){
            float degree = angleBetweenPoints(App.getCurrentPlayer().position.getX(), App.getCurrentPlayer().position.getY(),
                mouseX, mouseY);
            if(degree>=45 && degree< 135){
                App.getCurrentPlayer().setDirection(2);
            }
            else if(degree>=135 && degree< 225){
                App.getCurrentPlayer().setDirection(3);
            }
            else if(degree>=225 && degree< 315){
                App.getCurrentPlayer().setDirection(0);
            }
            else if(degree>=315 || degree< 45){
                App.getCurrentPlayer().setDirection(1);
            }

            if(false){
                v = GameMap.getPositionByCoordinates((int)mouseX,
                    (int) mouseY);
                x = (int) (v.x);
                y = (int)(v.y);
                GameMenu.getInstance().useItem(x, y, App.getGame().getItemByItemType(App.getCurrentPlayer().backpack.getItemInHand().getItemType()));
            }

            else if(App.getCurrentPlayer().backpack.getItemInHand() != null)
                GameMenu.getInstance().useItem(x, y, App.getGame().getItemByItemType(App.getCurrentPlayer().backpack.getItemInHand().getItemType()));
            return new Result(true, "Left click handled");
        }
        return new Result(true, "Right click can't be handled");
    }

    @Override
    public Result click(ClickPacket clickPacket) {
        TextButtonType type = clickPacket.textButtonType;
        switch (type) {
            case sleep:
                // TODO: Handle sleep button click
                break;

            case name:
                // TODO: Handle name input or change
                break;

            case refrigerator:
                // TODO: Open refrigerator menu
                break;

            case feed:
                // TODO: Feed an animal or character
                break;

            case submit:
                // TODO: Submit current form/action
                break;

            case cancel:
                // TODO: Cancel current action
                break;

            case back:
                // TODO: Go back to previous menu
                break;

            case purchase:
                // TODO: Purchase selected item
                break;

            case enter_cheat_code:
                // TODO: Open cheat code input dialog
                break;

            case move_out:
                // TODO: Handle moving out logic
                break;

            case collect:
                // TODO: Collect items or rewards
                break;

            case sell:
                // TODO: Sell selected item(s)
                break;

            case next_page:
                // TODO: Go to next page in pagination
                break;

            case previous_page:
                // TODO: Go to previous page in pagination
                break;

            default:
                // Optional: Handle unknown button types
                break;
        }

        return new Result(true, "");
    }

}
