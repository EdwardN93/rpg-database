package com.rpgdatabase;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final Dotenv dotenv = Dotenv.load();

    private static final String SERVER_URL = dotenv.get("DB_SERVER_URL");
    private static final String DATABASE_URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }

    public static Connection connectToServer() throws SQLException {
        return DriverManager.getConnection(SERVER_URL, USER, PASSWORD);
    }
}