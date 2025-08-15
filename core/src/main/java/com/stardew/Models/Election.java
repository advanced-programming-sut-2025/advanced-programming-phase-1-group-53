package com.stardew.Models;

import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Network.Common.Packet.ClientPacket.ElectionPackets.ElectionType;
import com.stardew.Network.Common.Packet.ClientPacket.ElectionPackets.FinalizeElectionPacket;
import com.stardew.Network.Common.Packet.ClientPacket.ElectionPackets.StartVotingPacket;
import com.stardew.Network.Common.Packet.ClientPacket.ElectionPackets.VotePacket;
import com.stardew.Network.Common.Packet.ServerPacket.ServerGeneralRespondPacket;
import com.stardew.Network.Server.ServerApp;

import java.util.ArrayList;

public class Election {
    private final ElectionType type;
    private final String username;
    private final ArrayList<Boolean> votes = new ArrayList<>();
    public Election(ElectionType type, String username) {
        this.type = type;
        this.username = username;
    }
    public ElectionType getType() {
        return type;
    }

    public ArrayList<Boolean> getVotes() {
        return votes;
    }

    public String getUsername() {
        return username;
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
            return new Result(true, "Election wins");
        }
        else return new Result(true, "Election lost");
    }

    public static Result finishElection(FinalizeElectionPacket packet) {
        Election election = App.getGame().getElection();
        if (election == null) {
            return new Result(false, "Election is not even started.");
        }
        if (election.getVotes().size() != App.getGame().getPlayers().size()) {
            return new Result(false, "Election is not finished.");
        }
        Result result = election.getElectionResult();
        if (!result.success()) {
            return result;
        }
        applyElectionResult(packet, result);
        return result;
    }

    public static void applyElectionResult(FinalizeElectionPacket packet, Result result) {
        Election election = App.getGame().getElection();
        if (packet.electionType.equals(ElectionType.REMOVE_PLAYER)) {
            if (result.message().equalsIgnoreCase("election lost")) {
                return;
            }
            else if (result.message().equalsIgnoreCase("election wins")) {
                Player player = App.getInstance().findPlayerByUsername(election.getUsername());
                if (player != null) {
                    App.getGame().getPlayers().remove(player);
                }
            }
        } else if (packet.electionType.equals(ElectionType.TERMINATE_GAME)) {
            if (result.message().equalsIgnoreCase("election lost")) {
                return;
            }
            else if (result.message().equalsIgnoreCase("election wins")) {
                // TODO fatemeh
            }
        }
    }

    public static ServerGeneralRespondPacket createElection(StartVotingPacket packet) {
        if (App.getGame().getElection() != null) {
            return new ServerGeneralRespondPacket(new Result(false, "an Election is running."), packet);
        }
        startElection(packet);
        return new ServerGeneralRespondPacket(new Result(true, "Election finished"), packet);
    }

    public static void startElection(StartVotingPacket packet) {
        if (App.getGame().getElection() == null) {
            App.getGame().setElection(new Election(packet.electionType, packet.username));
        }
    }

    public static Result voteRequest(VotePacket packet) {
        if (App.getGame().getElection() == null) {
            return new Result(false, "no Election");
        }
        vote(packet);
        return new Result(true, "Election voted");
    }

    public static void vote(VotePacket packet) {
        if (App.getGame().getElection() == null) {
            return;
        }
        App.getGame().getElection().getVotes().add(packet.vote);
    }
}
