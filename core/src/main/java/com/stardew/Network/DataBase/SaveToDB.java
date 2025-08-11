package com.stardew.Network.DataBase;

import com.stardew.Models.Game.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveToDB {
    private final Connection connection;

    public SaveToDB(Connection connection) {
        this.connection = connection;
    }

    public boolean savePlayerInfo(Player player) {
        String sql = "INSERT INTO users (name, email, password_hash, gender, created_at, nickname, updated_at) VALUES (?, ?, ?, ?, NOW(), ?, NOW())";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, player.personalInfo.getName());
            stmt.setString(2, player.personalInfo.getEmail());
            stmt.setString(3, player.personalInfo.getPassword());
            stmt.setString(4, player.personalInfo.getGender().toString());
            stmt.setString(6, player.personalInfo.getNickname());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateLastLogin(String name) {
        String sql = "UPDATE users SET last_login = NOW() WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
