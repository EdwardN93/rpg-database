package com.rpgdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void createDatabase() {

        String url = "jdbc:mariadb://localhost:3306/";
        String user = "root";
        String password = "";

        String sql = """
                CREATE DATABASE IF NOT EXISTS game_db
                """;

        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
            System.out.println("Database ready!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createPlayerTable() {

        String sql = """
                CREATE TABLE IF NOT EXISTS players (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    player_name VARCHAR(255) NOT NULL DEFAULT 'Nameless Hero',
                    level INT DEFAULT 0,
                    strength INT DEFAULT 10,
                    current_hp INT DEFAULT 40,
                    max_hp INT DEFAULT 40,
                    current_mana INT DEFAULT 10,
                    max_mana INT DEFAULT 10,
                    learning_points INT DEFAULT 0,
                    experience INT DEFAULT 0,
                    armor INT DEFAULT 0
                )
                """;

        try (
                Connection conn = DatabaseConnection.connect();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
            System.out.println("Players table ready.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createItemTable() {

        String sql = """
                CREATE TABLE IF NOT EXISTS items (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    item_name VARCHAR(255) NOT NULL,
                    type VARCHAR(50) NOT NULL,
                    damage INT DEFAULT 0,
                    healing INT DEFAULT 0
                )
                """;

        try (
                Connection conn = DatabaseConnection.connect();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
            System.out.println("Items table ready.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createInventoryTable() {

        String sql = """
                CREATE TABLE IF NOT EXISTS inventory (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    player_id INT NOT NULL,
                    item_id INT NOT NULL,
                    quantity INT DEFAULT 1,

                    FOREIGN KEY (player_id) REFERENCES players(id),
                    FOREIGN KEY (item_id) REFERENCES items(id)
                )
                """;

        try (
                Connection conn = DatabaseConnection.connect();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
            System.out.println("Inventory table ready.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void seedData() {
        DatabaseManager.addPlayer(10);
        DatabaseManager.addItem("Rusty Sword", "WEAPON", 10, 0);
        DatabaseManager.addItem("Healing Potion", "CONSUMABLE", 0, 25);
        DatabaseManager.addItemToInventory(1, 1, 1);
        DatabaseManager.addItemToInventory(1, 2, 5);
    }

    public static void initializeDatabase() {

        createDatabase();
        createPlayerTable();
        createItemTable();
        createInventoryTable();

        seedData();

        System.out.println("Database initialization complete.");
    }


}