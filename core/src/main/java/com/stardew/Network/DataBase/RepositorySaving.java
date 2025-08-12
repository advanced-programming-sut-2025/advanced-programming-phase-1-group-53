package com.stardew.Network.DataBase;

import com.stardew.Models.Game.Player;

import java.sql.Connection;
import java.sql.SQLException;

public interface RepositorySaving{
    default void savePlayerInfo(Player player) throws SQLException {
        Connection con = DatabaseConnection.getConnection();
        PlayerRepository playerRepo = new PlayerRepository(con);
        playerRepo.save(player);
    }
}
