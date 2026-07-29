package com.rpgdatabase.repository;

import com.rpgdatabase.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryRepository {

    public static void showInventory(int playerId) {

        String sql = """
            SELECT players.player_name,
                   items.item_name,
                   items.item_description,
                   items.item_value,
                   inventory.quantity
            FROM inventory
            JOIN players
                ON inventory.player_id = players.id
            JOIN items
                ON inventory.item_id = items.id
            WHERE players.id = ?
            """;

        try (
                Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, playerId);

            ResultSet result = pstmt.executeQuery();

            while (result.next()) {

                String playerName =
                        result.getString("player_name");

                String itemName =
                        result.getString("item_name");

                int quantity =
                        result.getInt("quantity");

                String itemDescription =
                        result.getString("item_description");

                int itemValue =
                        result.getInt("item_value");

                System.out.println("--------------------------------------------");
                System.out.println("Player      : " + playerName);
                System.out.println("Item        : " + itemName);
                System.out.println("Description : " + itemDescription);
                System.out.println("Quantity    : " + quantity);
                System.out.println("Value       : " + itemValue + " ore");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void addItemToInventory(int playerId, int itemId, int quantity) {
        String sql = """
                    INSERT INTO inventory (
                        player_id,
                        item_id,
                        quantity
                    ) values (?, ?, ?)
                """;

        try(
                Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setInt(1, playerId);
            pstmt.setInt(2, itemId);
            pstmt.setInt(3, quantity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void consumeHealingPotion(int playerId, int itemId) {

        Connection conn = null;

        String getHealingSql = """
            SELECT items.healing
            FROM inventory
            JOIN items ON inventory.item_id = items.id
            WHERE inventory.player_id = ?
            AND inventory.item_id = ?
            AND inventory.quantity > 0
            """;

        String consumePotionSql = """
            UPDATE inventory
            SET quantity = quantity - 1
            WHERE player_id = ?
            AND item_id = ?
            AND quantity > 0
            """;

        String updatePlayerHpSql = """
            UPDATE players
            SET current_hp = LEAST(current_hp + ?, max_hp)
            WHERE id = ?
            """;

        try {

            conn = DatabaseConnection.connect();

            conn.setAutoCommit(false);

            int healing;

            try (PreparedStatement pstmt = conn.prepareStatement(getHealingSql)) {

                pstmt.setInt(1, playerId);
                pstmt.setInt(2, itemId);

                ResultSet result = pstmt.executeQuery();

                if (!result.next()) {
                    throw new SQLException(
                            "Potion not found or quantity is 0."
                    );
                }

                healing = result.getInt("healing");

                if (healing <= 0) {
                    throw new SQLException(
                            "This item cannot heal the player."
                    );
                }
            }

            try (PreparedStatement pstmt = conn.prepareStatement(consumePotionSql)) {

                pstmt.setInt(1, playerId);
                pstmt.setInt(2, itemId);

                int rowsUpdated = pstmt.executeUpdate();

                if (rowsUpdated == 0) {
                    throw new SQLException(
                            "Potion could not be consumed."
                    );
                }
            }

            try (PreparedStatement pstmt = conn.prepareStatement(updatePlayerHpSql)) {

                pstmt.setInt(1, healing);
                pstmt.setInt(2, playerId);

                int rowsUpdated = pstmt.executeUpdate();

                if (rowsUpdated == 0) {
                    throw new SQLException(
                            "Player not found."
                    );
                }
            }

            conn.commit();

            System.out.println(
                    "Potion consumed! Healing: +" + healing
            );

        } catch (SQLException e) {

            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Transaction rolled back.");
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }

            e.printStackTrace();

        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
