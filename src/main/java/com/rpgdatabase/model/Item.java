package com.rpgdatabase.model;

import com.rpgdatabase.enums.ItemType;

public class Item {

    private int id;
    private String itemName;
    private ItemType type;
    private int damage;
    private int healing;
    private int manaRestore;
    private String itemDescription;
    private int itemValue;

    public Item (
            int id,
            String itemName,
            ItemType type,
            int damage,
            int healing,
            int manaRestore,
            String itemDescription,
            int itemValue
    ){
        this.id = id;
        this.itemName = itemName;
        this.type = type;
        this.damage = damage;
        this.healing = healing;
        this.manaRestore = manaRestore;
        this.itemDescription = itemDescription;
        this.itemValue = itemValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealing() {
        return healing;
    }

    public void setHealing(int healing) {
        this.healing = healing;
    }

    public int getManaRestore() { return manaRestore; }

    public void setManaRestore(int manaRestore) { this.manaRestore = manaRestore ;}

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getItemValue() {
        return itemValue;
    }

    public void setItemValue(int itemValue) {
        this.itemValue = itemValue;
    }



}
