package com.stardew.Network.DataBase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stardew.Models.Game.Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameRepository {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void saveGame(Game game) throws SQLException {
        String gameJson;
        try {
            gameJson = mapper.writeValueAsString(game);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing game", e);
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS games (" +
                "id SERIAL PRIMARY KEY, " +
                "data TEXT)");

            PreparedStatement ps = conn.prepareStatement("INSERT INTO games (data) VALUES (?)");
            ps.setString(1, gameJson);
            ps.executeUpdate();
        }
    }

    public static Game loadLastGame() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT data FROM games ORDER BY id DESC LIMIT 1"
            );

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String gameJson = rs.getString("data");
                try {
                    return mapper.readValue(gameJson, Game.class);
                } catch (Exception e) {
                    throw new RuntimeException("Error deserializing game JSON", e);
                }
            }
        }
        return null;
    }
}
