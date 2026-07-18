package com.edwart.rpgdatabase;


import java.sql.*;

public class DatabaseManager {

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

    public static void getPlayers() {
        String sql = """
                    SELECT * FROM players;
                """;

        try(
                Connection conn = DatabaseConnection.connect();
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery(sql);
                ){
            while(result.next()){
                int id = result.getInt("id");
                String name = result.getString("player_name");
                int strength = result.getInt("strength");

                System.out.println(id + " | " + name + " | " + strength );
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
            stmt.executeUpdate();

            System.out.println("Player updated");
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

            stmt.executeUpdate();

            System.out.println("Player deleted" );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addItem(String name, String type, int damage, int healing) {
            String sql = """
                    INSERT INTO items (
                        item_name,
                        type,
                        damage,
                        healing
                    ) 
                    values (?, ?, ?, ?)
                    """;

            try(
                    Connection conn = DatabaseConnection.connect();
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    ){
                pstmt.setString(1, name);
                pstmt.setString(2, type);
                pstmt.setInt(3, damage);
                pstmt.setInt(4, healing);

                pstmt.executeUpdate();

                System.out.println("Item added");

            } catch (SQLException e){
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

    public static void showInventory(int playerId) {

        String sql = """
            SELECT players.player_name,
                   items.item_name,
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

                System.out.println(
                        playerName + " | "
                                + itemName + " | "
                                + quantity
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void consumeItem(int playerId, int itemId) {
        String sql = """
            UPDATE inventory
            SET quantity = quantity - 1
            WHERE player_id = ?
            AND item_id = ?
            AND quantity > 0
            """;

        try (
                Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, playerId);
            pstmt.setInt(2, itemId);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Item consumed!");
            } else {
                System.out.println("Item not found or quantity is 0.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
