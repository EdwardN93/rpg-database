package com.rpgdatabase.services;

import com.rpgdatabase.enums.Attribute;
import com.rpgdatabase.model.Player;
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
                        + experienceForNextLevel(
                        player.getLevel()
                )
        );
    }

    private static int experienceForNextLevel(int level) {
        return 500 * level * (level + 1);
    }

    private static void checkLevelUp(Player player) {

        while (
                player.getExperience()
                        >= experienceForNextLevel(
                        player.getLevel()
                )
        ) {
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

        System.out.println();
        System.out.println("LEVEL UP!");
        System.out.println(
                "New level: "
                        + player.getLevel()
        );
        System.out.println(
                "Maximum HP: "
                        + player.getMaxHp()
        );
        System.out.println(
                "Learning points: "
                        + player.getLearningPoints()
        );
        System.out.println();
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
}