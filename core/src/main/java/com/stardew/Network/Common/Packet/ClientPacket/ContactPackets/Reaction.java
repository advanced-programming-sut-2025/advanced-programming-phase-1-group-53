package com.stardew.Network.Common.Packet.ClientPacket.ContactPackets;

import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Models.Result;

public enum Reaction {
    TOU_CANT_EVEN_KISS_MY_ASS("you cant even kiss my ass"),
    NOT_IMPRESSED("not impressed"),
    IS_THAT_ALL("is that all you got?"),
    TOO_WEAK("too weak"),
    NICE_TRY("nice try"),
    PATHETIC("pathetic");

    String reaction;
    Reaction(String reaction) {
        this.reaction = reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public static Result sendReaction(ReactionPacket packet) {
        try {
            Player player = App.getInstance().findPlayerByUsername(packet.receiverUsername);
            player.getReactions().put(packet.reaction, 5.0f);
            return new Result(true, "reaction sent");
        } catch (NullPointerException ex) {
            return new Result(false, "Player not found");
        }
    }
}
