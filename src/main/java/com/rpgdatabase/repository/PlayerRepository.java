package com.rpgdatabase.repository;

import com.rpgdatabase.model.Player;
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

    public static Player getPlayerById(int playerId) {

        String sql = """
        SELECT id,
               player_name,
               level,
               strength,
               current_hp,
               max_hp,
               current_mana,
               max_mana,
               learning_points,
               experience_required,
               experience,
               armor
        FROM players
        WHERE id = ?
        """;

        try (
                Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, playerId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return new Player(
                            rs.getInt("id"),
                            rs.getString("player_name"),
                            rs.getInt("level"),
                            rs.getInt("strength"),
                            rs.getInt("current_hp"),
                            rs.getInt("max_hp"),
                            rs.getInt("current_mana"),
                            rs.getInt("max_mana"),
                            rs.getInt("learning_points"),
                            rs.getInt("experience"),
                            rs.getInt("experience_required"),
                            rs.getInt("armor")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void showPlayerStats(int playerId) {

        Player player = getPlayerById(playerId);

        if (player == null) {
            System.out.println("Player not found.");
            return;
        }

        System.out.println();
        System.out.println("=============== PLAYER STATS ===============");
        System.out.println();
        System.out.printf("%-18s %s%n", "Name:", player.getPlayerName());
        System.out.printf("%-18s %d%n", "Level:", player.getLevel());

        System.out.printf(
                "%-18s %d / %d%n",
                "Experience:",
                player.getExperience(),
                player.getExperienceRequired()
        );

        System.out.printf("%-18s %d%n", "Learning Points:", player.getLearningPoints());

        System.out.println();
        System.out.println("---------------- ATTRIBUTES ----------------");

        System.out.printf("%-18s %d / %d%n",
                "HP:",
                player.getCurrentHp(),
                player.getMaxHp());

        System.out.printf("%-18s %d / %d%n",
                "Mana:",
                player.getCurrentMana(),
                player.getMaxMana());

        System.out.printf("%-18s %d%n", "Strength:", player.getStrength());
        System.out.printf("%-18s %d%n", "Armor:", player.getArmor());

        System.out.println();
        System.out.println("------------------ SKILLS ------------------");

        // FUTURE IMPLEMENTATIONS
        // System.out.printf("%-18s %d%n", "Dexterity:", player.getDexterity());
        // System.out.printf("%-18s %s%n", "Faction:", player.getFaction());
        // System.out.printf("%-18s %d%n", "Circle of Magic:", player.getCircleOfMagic());
        // System.out.printf("%-18s %d%%%n", "One-Handed:", player.getOneHanded());
        // System.out.printf("%-18s %d%%%n", "Two-Handed:", player.getTwoHanded());
        // System.out.printf("%-18s %d%%%n", "Bow:", player.getBowSkill());
        // System.out.printf("%-18s %d%%%n", "Crossbow:", player.getCrossbowSkill());
        // System.out.printf("%-18s %s%n", "Smithing:", player.getSmithing());
        // System.out.printf("%-18s %s%n", "Alchemy:", player.getAlchemy());

        System.out.println();
        System.out.println("============================================");
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

                System.out.println("--------------------------------------------");
                System.out.println("Player Id       : " + id);
                System.out.println("Name            : " + name);
                System.out.println("Strength        : " + strength);
                System.out.println("Level           : " + level);

            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean updatePlayer(Player player) {

        String sql = """
        UPDATE players
        SET player_name = ?,
            level = ?,
            strength = ?,
            current_hp = ?,
            max_hp = ?,
            current_mana = ?,
            max_mana = ?,
            learning_points = ?,
            experience = ?,
            experience_required = ?,
            armor = ?
        WHERE id = ?
        """;

        try (
                Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, player.getPlayerName());
            pstmt.setInt(2, player.getLevel());
            pstmt.setInt(3, player.getStrength());
            pstmt.setInt(4, player.getCurrentHp());
            pstmt.setInt(5, player.getMaxHp());
            pstmt.setInt(6, player.getCurrentMana());
            pstmt.setInt(7, player.getMaxMana());
            pstmt.setInt(8, player.getLearningPoints());
            pstmt.setInt(9, player.getExperience());
            pstmt.setInt(10, player.getExperienceRequired());
            pstmt.setInt(11, player.getArmor());
            pstmt.setInt(12, player.getId());

            int rowsUpdated = pstmt.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

    public static boolean updateExperience(
            int playerId,
            int experience
    ) {
        String sql = """
        UPDATE players
        SET experience = ?
        WHERE id = ?
        """;

        try (
                Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, experience);
            pstmt.setInt(2, playerId);

            int rowsUpdated = pstmt.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
