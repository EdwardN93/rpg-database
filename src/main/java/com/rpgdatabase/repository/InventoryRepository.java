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

    public static int getItemQuantity(int playerId, int itemId){

        String sql = """
                SELECT quantity
                FROM inventory
                WHERE player_id = ?
                  AND item_id = ?
                """;
        try(
                Connection con = DatabaseConnection.connect();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, playerId);
            ps.setInt(2, itemId);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return rs.getInt("quantity");
                }
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public static void deleteEmptyInventoryEntry(int playerId, int itemId){

        String sql = """
                DELETE FROM inventory
                WHERE player_id = ?
                  AND item_id = ?
                  AND quantity <= 0
                """;
        try(
                Connection con = DatabaseConnection.connect();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, playerId);
            ps.setInt(2, itemId);

            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void removeOneItem(int playerId, int itemId){

        String sql = """
                UPDATE inventory
                SET quantity = quantity - 1
                WHERE player_id = ?
                  AND item_id = ?
                  AND quantity > 0
                """;

        try(
                Connection con = DatabaseConnection.connect();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, playerId);
            ps.setInt(2, itemId);

            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
