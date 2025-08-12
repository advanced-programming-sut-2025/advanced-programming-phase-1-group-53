package com.stardew.Controllers;

import com.badlogic.gdx.Input;
import com.stardew.Main;
import com.stardew.Models.Result;
import com.stardew.Network.Common.Packet.ClientPacket.*;
import com.stardew.Views.GameMenu;
import com.stardew.Views.TabMenus.MapMenu;

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

    }

    @Override
    public Result keyDown(KeyDownPacket keyDownPacket) {

    }

    @Override
    public Result mouseMove(MouseMovePacket mouseMovePacket) {

    }

    @Override
    public Result touchDown(TouchDownPacket touchDownPacket) {

    }

    @Override
    public Result click(ClickPacket clickPacket) {

    }
}
