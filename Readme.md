# 🎮 RPG Database Manager

A Java learning project built to explore SQL, JDBC, MariaDB, and relational database concepts through an RPG-inspired database system.

Instead of using traditional database examples such as employees and departments, this project explores database concepts through players, items, and inventories.

The application can automatically create its database structure and populate it with sample RPG data for development and testing.

## 🚀 Technologies

- Java
- Maven
- JDBC
- MariaDB
- XAMPP
- phpMyAdmin

## ✨ Features

- Connect Java to MariaDB using JDBC
- Automatically create the `game_db` database
- Automatically create the required database tables
- Perform CRUD operations on players
- Create and manage RPG items
- Add items to a player's inventory
- Track item quantities
- Consume inventory items
- Display player inventories using SQL JOINs
- Use Primary Keys and Foreign Keys to create relationships between tables
- Persistent data storage with MariaDB
- Optional seed data for development and testing

## 🗄️ Database Structure

The project currently uses three main tables:

### Players

Stores player statistics such as:

- Player name
- Level
- Strength
- Current HP
- Maximum HP
- Current Mana
- Maximum Mana
- Learning Points
- Experience
- Armor

### Items

Stores RPG items and their properties:

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

```text
Players ──── Inventory ──── Items
    │                         │
    └── player_id      item_id ┘
```

This allows a player to own multiple items while keeping the item information stored separately.

## ⚙️ Database Initialization

The application includes a `DatabaseInitializer` class responsible for setting up the database.

When the application starts, the following method can be called:

```java
DatabaseInitializer.initializeDatabase();
```

The initialization process runs in the following order:

```text
Create game_db if it does not exist
        ↓
Create players table
        ↓
Create items table
        ↓
Create inventory table
        ↓
Optional seed data
```

The `inventory` table is created last because it contains foreign keys referencing the `players` and `items` tables.

The database and tables use `IF NOT EXISTS`, allowing the initialization process to safely check whether they already exist.

## 🚀 Quick Start

1. Clone the repository.

2. Make sure MariaDB is installed and running.

   XAMPP can be used to start the MariaDB/MySQL server and phpMyAdmin can be used to inspect the database.

3. Configure the database credentials used by the application.

   The default development configuration currently uses:

```text
Host: localhost
Port: 3306
Database: game_db
User: root
Password: empty
```

4. Run the application.

The application will automatically create:

```text
game_db
├── players
├── items
└── inventory
```

5. If `seedData()` is enabled, sample RPG data will also be inserted.

## 🌱 Seed Data

For development and testing purposes, the project includes a simple `seedData()` method inside `DatabaseInitializer`.

The seed method can create initial test data such as:

```text
Player:
└── Nameless Hero

Items:
├── Rusty Sword
└── Healing Potion

Inventory:
├── Rusty Sword ×1
└── Healing Potion ×5
```

This makes it possible to quickly test the application after creating a fresh database.

### ⚠️ Important

The current seed implementation is intentionally simple and does **not** check whether the data already exists.

Running `seedData()` multiple times will create duplicate entries.

After running the application for the first time, comment out the `seedData()` call inside `initializeDatabase()`:

```java
public static void initializeDatabase() {

    createDatabase();
    createPlayerTable();
    createItemTable();
    createInventoryTable();

    // seedData();
}
```

The database initialization methods can continue running because the database and tables are created using `IF NOT EXISTS`.

The seed method should only be enabled when initializing a fresh database or when sample test data is needed.

A future improvement will make the seed process automatically detect existing data.

## 🎒 Inventory System

The inventory system connects players and items using foreign keys.

For example:

```text
Nameless Hero
│
├── Rusty Sword ×1
└── Healing Potion ×5
```

Internally, the `inventory` table stores IDs:

```text
player_id | item_id | quantity
1         | 1       | 1
1         | 2       | 5
```

SQL JOINs are used to combine information from the `players`, `inventory`, and `items` tables and display readable results.

Example:

```text
Nameless Hero | Rusty Sword    | 1
Nameless Hero | Healing Potion | 5
```

## 🧪 Consuming Items

Inventory items can be consumed by updating their quantity.

For example:

```text
Healing Potion ×5
        ↓
Consume Item
        ↓
Healing Potion ×4
```

The current implementation prevents the quantity from being reduced when it is already `0`.

Future development will connect consumable items to player statistics.

For example, consuming a Healing Potion will:

```text
Healing Potion quantity: 5 → 4
Player current HP:        15 → 35
```

These operations will eventually be executed using an SQL transaction to ensure that both operations either succeed together or are rolled back together.

## 📚 Concepts Practiced

This project is primarily a learning project focused on:

- SQL CRUD operations
- JDBC
- MariaDB database connections
- `Connection`
- `Statement`
- `PreparedStatement`
- `ResultSet`
- Primary Keys
- Foreign Keys
- `AUTO_INCREMENT`
- `DEFAULT` values
- SQL JOINs
- SQL UPDATE operations
- Relational database design
- Database initialization from Java
- Persistent application data
- Java database connection management

## 🏗️ Current Project Structure

The project currently separates database responsibilities into several classes:

```text
com.rpgdatabase
│
├── Main
│
├── DatabaseConnection
│   └── Handles connections to MariaDB
│
├── DatabaseInitializer
│   ├── Creates the database
│   ├── Creates the players table
│   ├── Creates the items table
│   ├── Creates the inventory table
│   └── Provides optional seed data
│
└── DatabaseManager
    ├── Player CRUD operations
    ├── Item operations
    └── Inventory operations
```

As the project grows, `DatabaseManager` may be refactored into separate repository classes such as:

```text
PlayerRepository
ItemRepository
InventoryRepository
```

## 🔮 Planned Features

- Healing Potion increases player HP
- Prevent healing above maximum HP
- SQL transactions using COMMIT and ROLLBACK
- Automatic rollback if an operation fails
- Improved seed data without duplicate entries
- Remove inventory entries when quantity reaches zero
- More item types
- Weapons and damage system
- Improved inventory management
- Refactor database operations into repositories
- Improved database configuration and credential management

## 🎯 Project Goal

The goal of this project is to learn SQL and relational databases through a practical RPG-inspired system while understanding how Java applications communicate with a database using JDBC.

The project is built incrementally, starting with basic CRUD operations and gradually introducing relational database concepts such as foreign keys, JOINs, persistent inventories, database initialization, and transactions.