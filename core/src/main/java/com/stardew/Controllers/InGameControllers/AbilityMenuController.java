package com.stardew.Controllers.InGameControllers;

import com.stardew.Models.Result;
import com.stardew.Network.Common.Packet.ClientPacket.KeyboardPackets.*;

public class AbilityMenuController extends Controller {
//    public Result keyDown(int keycode) {
//        System.out.println(keycode);
//        if(keycode == Input.Keys.ESCAPE){
//            Main.main.setScreen(GameMenu.getInstance());
//            return true;
//        }
//        if(keycode == Input.Keys.M){
//            Main.main.setScreen(MapMenu.getInstance());
//            return true;
//        }
//        return false;
//    }
    // TODO
    public static final String MENU_NAME = "AbilityMenu";

    @Override
    public Result keyUp(KeyUpPacket keyUpPacket) {

        return new Result(true, "");
    }

    @Override
    public Result keyDown(KeyDownPacket keyDownPacket) {

        return new Result(true, "");
    }

    @Override
    public Result mouseMove(MouseMovePacket mouseMovePacket) {

        return new Result(true, "");
    }

    @Override
    public Result touchDown(TouchDownPacket touchDownPacket) {

        return new Result(true, "");
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
