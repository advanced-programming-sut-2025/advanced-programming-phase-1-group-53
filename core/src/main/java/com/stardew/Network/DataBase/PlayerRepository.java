package com.stardew.Network.DataBase;

import com.stardew.Enums.Gender;
import com.stardew.Models.Game.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerRepository {
    private final Connection connection;

    public PlayerRepository(Connection connection) {
        this.connection = connection;
    }

    public Player findByUsername(String username) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE name = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String genderStr = rs.getString("gender");
                Gender gender = Gender.valueOf(genderStr.toUpperCase());
                Player user = new Player(
                    rs.getString("name"),
                    rs.getString("nickname"),
                    rs.getString("hashed_password"),
                    rs.getString("email"),
                    gender);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Player player) {
        try {
                PreparedStatement insertStmt = connection.prepareStatement(
                    "INSERT INTO users (name, nickname, hashed_password, email, gender) VALUES (?, ?, ?, ?, ?)");
                insertStmt.setString(1, player.personalInfo.getName());
                insertStmt.setString(2, player.personalInfo.getNickname());
                insertStmt.setString(3, player.personalInfo.getPassword());
                insertStmt.setString(4, player.personalInfo.getEmail());
                insertStmt.setString(5, player.personalInfo.getGender().name());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
