package com.stardew.Network.DataBase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.Gson;
import com.stardew.Models.Game.Player;
import com.stardew.Models.Items.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {
    private static final Gson gson = new Gson();
    public void savePlayer(Player player) throws SQLException {
        ObjectMapper mapper = new ObjectMapper();
        String playerJson;
        try {
            playerJson = mapper.writeValueAsString(player);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing player", e);
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS players (" +
                "id SERIAL PRIMARY KEY, " +
                "data TEXT)");

            PreparedStatement ps = conn.prepareStatement("INSERT INTO players (data) VALUES (?)");
            ps.setString(1, playerJson);
            ps.executeUpdate();
        }
    }

}
