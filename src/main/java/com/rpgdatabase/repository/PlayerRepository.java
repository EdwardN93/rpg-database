package com.rpgdatabase.repository;

import com.rpgdatabase.DatabaseConnection;

import java.sql.*;

public class PlayerRepository {

    public static void addPlayer(int strength) {

        String sql = """
            INSERT INTO players (strength)
            VALUES (?)
            """;

        try (
                Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, strength);
            stmt.executeUpdate();

            System.out.println("Player added");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void showPlayers() {
        String sql = """
                    SELECT id, player_name, level, strength 
                    FROM players;
                """;

        try(
                Connection conn = DatabaseConnection.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ){
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("player_name");
                int strength = rs.getInt("strength");
                int level = rs.getInt("level");

                System.out.println(
                        id + " | "
                                + name + " | "
                                + level + " | "
                                + strength
                );
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updatePlayer(int id, int strength) {
        String sql = """
                    UPDATE players
                    SET strength = ?
                    WHERE id = ?
                """;
        try(
                Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1, strength);
            stmt.setInt(2, id);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Player updated.");
            } else {
                System.out.println("Player not found.");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void deletePlayer(int id) {
        String sql = """
                DELETE FROM players
                WHERE ID = ?
                """;

        try(
                Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Player deleted.");
            } else {
                System.out.println("Player not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
