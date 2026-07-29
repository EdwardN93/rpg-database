package com.rpgdatabase.repository;

import com.rpgdatabase.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemRepository {

    public static void addItem(
            String name,
            String type,
            int damage,
            int healing,
            String itemDescription,
            int itemValue
    ) {
        String sql = """
            INSERT INTO items (
                item_name,
                type,
                damage,
                healing,
                item_description,
                item_value
            )
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (
                Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, name);
            pstmt.setString(2, type);
            pstmt.setInt(3, damage);
            pstmt.setInt(4, healing);
            pstmt.setString(5, itemDescription);
            pstmt.setInt(6, itemValue);

            pstmt.executeUpdate();

            System.out.println("Item added");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
