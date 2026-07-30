package com.rpgdatabase.model;

public class Player {
    private int id;
    private String playerName;
    private int level;
    private int strength;
    private int currentHp;
    private int maxHp;
    private int currentMana;
    private int maxMana;
    private int learningPoints;
    private int experience;
    private int experienceRequired;
    private int armor;



    public Player(
            int id,
            String playerName,
            int level,
            int strength,
            int currentHp,
            int maxHp,
            int currentMana,
            int maxMana,
            int learningPoints,
            int experience,
            int experienceRequired,
            int armor
    ) {
        this.id = id;
        this.playerName = playerName;
        this.level = level;
        this.strength = strength;
        this.currentHp = currentHp;
        this.maxHp = maxHp;
        this.currentMana = currentMana;
        this.maxMana = maxMana;
        this.learningPoints = learningPoints;
        this.experience = experience;
        this.experienceRequired = experienceRequired;
        this.armor = armor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getLearningPoints() {
        return learningPoints;
    }

    public void setLearningPoints(int learningPoints) {
        this.learningPoints = learningPoints;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getExperienceRequired() {
        return experienceRequired;
    }

    public void setExperienceRequired(int experienceRequired) {
        this.experienceRequired = experienceRequired;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }


    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", level=" + level +
                ", strength=" + strength +
                ", currentHp=" + currentHp +
                ", maxHp=" + maxHp +
                ", currentMana=" + currentMana +
                ", maxMana=" + maxMana +
                ", learningPoints=" + learningPoints +
                ", experience=" + experience +
                ", experienceRequired=" + experienceRequired +
                ", armor=" + armor +
                '}';
    }

}
