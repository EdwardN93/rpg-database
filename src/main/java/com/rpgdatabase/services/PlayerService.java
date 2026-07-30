package com.rpgdatabase.services;

import com.rpgdatabase.enums.Attribute;
import com.rpgdatabase.enums.ItemType;
import com.rpgdatabase.model.Item;
import com.rpgdatabase.model.Player;
import com.rpgdatabase.repository.InventoryRepository;
import com.rpgdatabase.repository.ItemRepository;
import com.rpgdatabase.repository.PlayerRepository;

public class PlayerService {

    public static void gainExperience(
            int playerId,
            int gainedExperience
    ) {
        Player player =
                PlayerRepository.getPlayerById(playerId);

        if (player == null) {
            System.out.println("Player not found.");
            return;
        }

        if (gainedExperience <= 0) {
            System.out.println(
                    "Experience must be greater than 0."
            );
            return;
        }

        player.setExperience(
                player.getExperience() + gainedExperience
        );

        checkLevelUp(player);

        boolean updated =
                PlayerRepository.updatePlayer(player);

        if (!updated) {
            System.out.println(
                    "Player progress could not be updated."
            );
            return;
        }

        System.out.println(
                player.getPlayerName()
                        + " gained "
                        + gainedExperience
                        + " experience."
        );

        System.out.println(
                "Total experience: "
                        + player.getExperience()
        );

        System.out.println(
                "Current level: "
                        + player.getLevel()
        );

        System.out.println(
                "Experience needed for next level: "
                        + player.getExperienceRequired()
        );
    }

    private static int experienceRequiredForLevel(int level) {
        return 250 * level * (level + 1);
    }

    private static void checkLevelUp(Player player) {

        while (player.getExperience()
                >= experienceRequiredForLevel(player.getLevel() + 1)) {

            levelUp(player);
        }
    }

    private static void levelUp(Player player) {

        player.setLevel(
                player.getLevel() + 1
        );

        player.setMaxHp(
                player.getMaxHp() + 12
        );

        player.setLearningPoints(
                player.getLearningPoints() + 10
        );

        player.setExperienceRequired(
                experienceRequiredForLevel(player.getLevel() + 1)
        );

        System.out.println();
        System.out.println("LEVEL UP!");
        System.out.println("New level: " + player.getLevel());
        System.out.println("Maximum HP: " + player.getMaxHp());
        System.out.println("Learning points: " + player.getLearningPoints());
        System.out.println(
                "Experience required: "
                        + player.getExperienceRequired()
        );
        System.out.println();
    }

    public static void showPlayerStats(int playerId) {

        Player player = PlayerRepository.getPlayerById(playerId);

        if (player == null) {
            System.out.println("Player not found.");
            return;
        }

        System.out.println();
        System.out.println("=============== PLAYER STATS ===============");
        System.out.println();
        System.out.printf("%-18s %s%n", "Name:", player.getPlayerName());
        System.out.printf("%-18s %d%n", "Level:", player.getLevel());

        System.out.printf(
                "%-18s %d / %d%n",
                "Experience:",
                player.getExperience(),
                player.getExperienceRequired()
        );

        System.out.printf("%-18s %d%n", "Learning Points:", player.getLearningPoints());

        System.out.println();
        System.out.println("---------------- ATTRIBUTES ----------------");
        System.out.println();
        System.out.printf("%-18s %d / %d%n",
                "HP:",
                player.getCurrentHp(),
                player.getMaxHp());

        System.out.printf("%-18s %d / %d%n",
                "Mana:",
                player.getCurrentMana(),
                player.getMaxMana());

        System.out.printf("%-18s %d%n", "Strength:", player.getStrength());
        System.out.printf("%-18s %d%n", "Armor:", player.getArmor());

        System.out.println();
        System.out.println("------------------ SKILLS ------------------");

        // ---------------- EQUIPMENT ----------------
        // Weapon:
        // Armor:
        // Damage:
        // Protection:

        // FUTURE IMPLEMENTATIONS
        // System.out.printf("%-18s %d%n", "Dexterity:", player.getDexterity());
        // System.out.printf("%-18s %s%n", "Faction:", player.getFaction());
        // System.out.printf("%-18s %d%n", "Circle of Magic:", player.getCircleOfMagic());
        // System.out.printf("%-18s %d%%%n", "One-Handed:", player.getOneHanded());
        // System.out.printf("%-18s %d%%%n", "Two-Handed:", player.getTwoHanded());
        // System.out.printf("%-18s %d%%%n", "Bow:", player.getBowSkill());
        // System.out.printf("%-18s %d%%%n", "Crossbow:", player.getCrossbowSkill());
        // System.out.printf("%-18s %s%n", "Smithing:", player.getSmithing());
        // System.out.printf("%-18s %s%n", "Alchemy:", player.getAlchemy());

        System.out.println();
        System.out.println("============================================");
    }

    public static void trainAttribute(
            int playerId,
            Attribute attribute,
            int points
    ) {

        Player player = PlayerRepository.getPlayerById(playerId);

        if (player == null) {
            System.out.println("Player not found.");
            return;
        }

        if (points <= 0) {
            System.out.println("Points must be greater than 0.");
            return;
        }

        if (player.getLearningPoints() < points) {
            System.out.println("Not enough learning points.");
            return;
        }

        switch (attribute) {

            case STRENGTH:
                player.setStrength(
                        player.getStrength() + points
                );
                break;

//            TO IMPLEMENT DEXTERITY
//            case DEXTERITY:
//                player.setDexterity(
//                        player.getDexterity() + points
//                );
//                break;

            case MAX_MANA:
                player.setMaxMana(
                        player.getMaxMana() + points
                );
                break;

            default:
                System.out.println("Invalid attribute.");
                return;
        }

        player.setLearningPoints(
                player.getLearningPoints() - points
        );

        boolean updated = PlayerRepository.updatePlayer(player);

        if (!updated) {
            System.out.println("Training could not be saved.");
            return;
        }

        System.out.println(
                player.getPlayerName()
                        + " increased "
                        + attribute
                        + " by "
                        + points
                        + "."
        );

        System.out.println(
                "Learning Points remaining: "
                        + player.getLearningPoints()
        );
    }

    public static void trainStrength(
            int id,
            int points
    ) {
        trainAttribute(id, Attribute.STRENGTH, points);
    }

    public static void trainMaxMana(
            int id,
            int points
    ) {
        trainAttribute(id, Attribute.MAX_MANA, points);
    }

    public static void useConsumable(int playerId, int itemId) {

        Player player = PlayerRepository.getPlayerById(playerId);
        Item item = ItemRepository.getItemById(itemId);

        if (player == null) {
            System.out.println("Player not found.");
            return;
        }

        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        if (item.getType() != ItemType.CONSUMABLE) {
            System.out.println("This item is not consumable.");
            return;
        }

        int quantity =
                InventoryRepository.getItemQuantity(playerId, itemId);

        if (quantity <= 0) {
            System.out.println("Player does not have this item.");
            return;
        }

        int oldHp = player.getCurrentHp();
        int oldMana = player.getCurrentMana();

        int newHp = Math.min(
                oldHp + item.getHealing(),
                player.getMaxHp()
        );

        int newMana = Math.min(
                oldMana + item.getManaRestore(),
                player.getMaxMana()
        );

        if (newHp == oldHp && newMana == oldMana) {
            System.out.println("This consumable has no effect right now.");
            return;
        }

        int restoredHp = newHp - oldHp;
        int restoredMana = newMana - oldMana;

        player.setCurrentHp(newHp);
        player.setCurrentMana(newMana);

        PlayerRepository.updatePlayer(player);
        InventoryRepository.removeOneItem(playerId, itemId);
        InventoryRepository.deleteEmptyInventoryEntry(playerId, itemId);

        System.out.println(
                player.getPlayerName()
                        + " used "
                        + item.getItemName()
                        + ". Restored "
                        + restoredHp
                        + " HP and "
                        + restoredMana
                        + " mana."
        );

        System.out.println(
                "Current HP: "
                        + player.getCurrentHp()
                        + "/"
                        + player.getMaxHp()
        );

        System.out.println(
                "Current mana: "
                        + player.getCurrentMana()
                        + "/"
                        + player.getMaxMana()
        );
    }
}