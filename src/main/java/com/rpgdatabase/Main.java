package com.rpgdatabase;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DatabaseInitializer.initializeDatabase();

        mainMenu(sc);

        sc.close();
    }

    public static void mainMenu(Scanner sc) {

        while (true) {

            System.out.println();
            System.out.println("=== RPG DATABASE MANAGER ===");
            System.out.println("1. Open inventory");
            System.out.println("2. Show players");
            System.out.println("3. Consume Healing Potion");
            System.out.println("0. Exit");

            System.out.print("Enter option: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    System.out.println();
                    DatabaseManager.showInventory(1);
                    break;

                case 2:
                    System.out.println();
                    DatabaseManager.getPlayers();
                    break;

                case 3:
                    System.out.println();
                    DatabaseManager.consumeHealingPotion(1, 2);
                    break;

                case 0:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Please enter a valid option.");
            }
        }
    }
}