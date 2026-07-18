package com.edwart.rpgdatabase;

public class Main {

    public static void main(String[] args) {

        DatabaseInitializer.initializeDatabase();

        DatabaseManager.getPlayers();

        // DatabaseManager.addItemToInventory(1, 2, 5);

        DatabaseManager.showInventory(1);

        DatabaseManager.consumeItem(1, 2);

        DatabaseManager.showInventory(1);
    }
}