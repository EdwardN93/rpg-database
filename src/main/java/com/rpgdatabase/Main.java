package com.rpgdatabase;

import com.rpgdatabase.repository.InventoryRepository;
import com.rpgdatabase.repository.ItemRepository;
import com.rpgdatabase.repository.PlayerRepository;
import com.rpgdatabase.services.PlayerService;

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
            System.out.println("4. Add new item");
            System.out.println("5. Gain experience");
            System.out.println("0. Exit");

            System.out.print("Enter option: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    System.out.println();
                    InventoryRepository.showInventory(1);
                    break;

                case 2:
                    System.out.println();
                    PlayerRepository.showPlayers();
                    break;

                case 3:
                    System.out.println();
                    InventoryRepository.consumeHealingPotion(1, 2);
                    break;

                case 4:
                    System.out.println();
                    addItem(sc);
                    break;

                case 5:
                    System.out.println();

                    System.out.print("Player ID: ");
                    int playerId = Integer.parseInt(sc.nextLine());

                    System.out.print("Experience gained: ");
                    int gainedExperience = Integer.parseInt(sc.nextLine());

                    PlayerService.gainExperience(
                            playerId,
                            gainedExperience
                    );
                    break;

                case 0:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Please enter a valid option.");
            }
        }
    }

    public static void addItem(Scanner sc) {

        System.out.println("Name of the item: ");
        String itemName = sc.nextLine();

        System.out.println("Write the type of item: 'CONSUMABLE' or 'WEAPON'");
        String itemType = sc.nextLine().toUpperCase();

        int healing;
        int damage;

        if (itemType.equals("CONSUMABLE")) {

            System.out.println("How much healing does item give: ");
            healing = Integer.parseInt(sc.nextLine());
            damage = 0;

        } else if (itemType.equals("WEAPON")) {

            System.out.println("How much damage does item have: ");
            damage = Integer.parseInt(sc.nextLine());
            healing = 0;

        } else {

            System.out.println("Invalid item type.");
            return;
        }

        System.out.println("Item's description: ");
        String itemDescription = sc.nextLine();

        System.out.println("Item's value: ");
        int itemValue = Integer.parseInt(sc.nextLine());

        ItemRepository.addItem(itemName, itemType, damage, healing, itemDescription, itemValue);
    }
}