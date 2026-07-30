package com.rpgdatabase.repository;

import com.rpgdatabase.DatabaseConnection;
import com.rpgdatabase.enums.ItemType;
import com.rpgdatabase.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRepository {

    public static Item getItemById(int itemId){
        String sql = """
        SELECT id,
               item_name,
               type,
               damage,
               healing,
               item_description,
               item_value
        FROM items
        WHERE id = ?
        """;

        try(
                Connection con = DatabaseConnection.connect();
                PreparedStatement ps = con.prepareStatement(sql)
        ){
            ps.setInt(1, itemId);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return new Item(
                            rs.getInt("id"),
                            rs.getString("item_name"),
                            ItemType.valueOf(rs.getString("type")),
                            rs.getInt("damage"),
                            rs.getInt("healing"),
                            rs.getString("item_description"),
                            rs.getInt("item_value")
                    );
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void addItem(
            String name,
            ItemType type,
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
            pstmt.setString(2, type.name());
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
