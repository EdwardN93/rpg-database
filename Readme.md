# 🎮 RPG Database Manager

A small Java project created to learn SQL, JDBC, MariaDB, and relational database concepts by building an RPG-inspired database system.

Instead of using traditional examples such as employees and departments, this project explores database concepts through players, items, and inventories.

## 🚀 Technologies

- Java
- Maven
- JDBC
- MariaDB
- XAMPP
- phpMyAdmin

## ✨ Features

- Connect Java to a MariaDB database using JDBC
- Create database tables from Java
- Perform CRUD operations on players
- Create and manage RPG items
- Add items to a player's inventory
- Track item quantities
- Display player inventories using SQL JOINs
- Persistent data storage with MariaDB

## 🗄️ Database Structure

The project currently uses three main tables:

### Players

Stores player statistics such as:

- Player name
- Level
- Strength
- Maximum HP
- Maximum Mana
- Learning Points
- Experience
- Armor

### Items

Stores RPG items with properties such as:

- Item name
- Type
- Damage
- Healing

Example items:

- Rusty Sword
- Healing Potion

### Inventory

Connects players and items using foreign keys.

Each inventory entry stores:

- Player ID
- Item ID
- Quantity

Relationship:

Players → Inventory ← Items

## 📚 Concepts Practiced

This project is primarily a learning project focused on:

- SQL CRUD operations
- JDBC
- PreparedStatement
- Statement
- ResultSet
- Primary Keys
- Foreign Keys
- AUTO_INCREMENT
- DEFAULT values
- SQL JOINs
- Relational database design
- Java database connection management

## 🔮 Planned Features

- Consume inventory items
- Update item quantities
- Player current HP
- Healing system
- SQL transactions with COMMIT and ROLLBACK
- Prevent healing above maximum HP
- More item types
- Inventory management improvements

## 🎯 Project Goal

The goal of this project is to learn SQL and relational databases through a practical RPG-inspired system while understanding how Java applications communicate with a database using JDBC.