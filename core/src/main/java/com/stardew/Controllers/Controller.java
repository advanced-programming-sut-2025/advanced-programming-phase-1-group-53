package com.stardew.Controllers;

import com.stardew.Models.Result;
import com.stardew.Network.Common.Packet.ClientPacket.*;

public abstract class Controller {
    public abstract Result keyUp(KeyUpPacket keyUpPacket);
    public abstract Result keyDown(KeyDownPacket keyDownPacket);
    public abstract Result mouseMove(MouseMovePacket mouseMovePacket);
    public abstract Result touchDown(TouchDownPacket touchDownPacket);
    public abstract Result click(ClickPacket clickPacket);
}
