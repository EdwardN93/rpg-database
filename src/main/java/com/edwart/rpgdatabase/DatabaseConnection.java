package com.edwart.rpgdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    static String url = "jdbc:mariadb://localhost:3306/game_db";
    static String user = "root";
    static String password = "";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}