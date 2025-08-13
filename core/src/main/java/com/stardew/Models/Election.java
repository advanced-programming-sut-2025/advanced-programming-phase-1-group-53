package com.stardew.Models;

import com.stardew.Models.Game.App;
import com.stardew.Network.Common.Packet.ClientPacket.ElectionType;

import java.util.ArrayList;
import java.util.HashMap;

public class Election {
    private final ElectionType type;
    private final ArrayList<Boolean> votes = new ArrayList<>();
    public Election(ElectionType type) {
        this.type = type;
    }
    public ElectionType getType() {
        return type;
    }

    public ArrayList<Boolean> getVotes() {
        return votes;
    }

    public Result getElectionResult() {
        if (votes.size() != App.getGame().getPlayers().size()) {
            return new Result(false, "Election is not finished.");
        }
        int yesVotes = 0;
        int noVotes = 0;
        for (Boolean vote : votes) {
            if (vote) {
                yesVotes++;
            }
            else {
                noVotes++;
            }
        }
        if (yesVotes > noVotes) {
            return new Result(false, "Election wins");
        }
        else return new Result(true, "Election lost");
    }
}
