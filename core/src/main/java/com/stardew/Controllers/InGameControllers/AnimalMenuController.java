package com.stardew.Controllers.InGameControllers;

import com.stardew.Models.Result;
import com.stardew.Network.Common.Packet.ClientPacket.*;

public class AnimalMenuController extends Controller {
    public static final String MENU_NAME = "AnimalMenu";

    @Override
    public Result keyUp(KeyUpPacket keyUpPacket) { return new Result(true, ""); }

    @Override
    public Result keyDown(KeyDownPacket keyDownPacket) { return new Result(true, ""); }

    @Override
    public Result mouseMove(MouseMovePacket mouseMovePacket) { return new Result(true, ""); }

    @Override
    public Result touchDown(TouchDownPacket touchDownPacket) { return new Result(true, ""); }

    @Override
    public Result click(ClickPacket clickPacket) {
        TextButtonType type = clickPacket.textButtonType;
        switch (type) {
            case sleep: break;
            case name: break;
            case refrigerator: break;
            case feed: break;
            case submit: break;
            case cancel: break;
            case back: break;
            case purchase: break;
            case enter_cheat_code: break;
            case move_out: break;
            case collect: break;
            case sell: break;
            case next_page: break;
            case previous_page: break;
            default: break;
        }
        return new Result(true, "");
    }
}
