# 🎮 RPG Backend System

A Java backend learning project built to explore object-oriented programming, JDBC, MariaDB, and relational database design through an RPG-inspired application.

Instead of using generic examples like employees and departments, this project simulates the backend of a role-playing game by managing players, items, inventories, consumables, and player progression.

---

## 🚀 Technologies

- Java
- Maven
- JDBC
- MariaDB
- dotenv-java

## 🛠️ Development Tools

- IntelliJ IDEA
- DBeaver
- XAMPP
- phpMyAdmin

---

# ✨ Features

## Database

- Automatic database creation
- Automatic table creation
- Optional seed data
- Environment variable configuration (.env)

## Player System

- Player CRUD operations
- Experience system
- Level progression
- Learning points
- Strength training
- HP and Mana management

## Item System

- Weapon support
- Consumable support
- Item values
- Item descriptions
- Generic item types using enums

## Inventory System

- Add items
- Stackable quantities
- Remove items
- Automatic inventory cleanup
- Inventory display using SQL JOINs

## Consumable System

- Generic consumable implementation
- HP restoration
- Mana restoration
- Automatic inventory update after consumption
- Maximum HP/Mana validation

## Database Concepts

- Prepared Statements
- SQL CRUD operations
- SQL JOINs
- Primary Keys
- Foreign Keys
- Relational database design

---

# ⚙️ Configuration

Create a `.env` file in the project root.

```text
DB_SERVER_URL=jdbc:mariadb://localhost:3306
DB_URL=jdbc:mariadb://localhost:3306/game_db
DB_USER=root
DB_PASSWORD=
```

---

# ▶️ Running

1. Clone the repository.
2. Start MariaDB.
3. Configure the `.env` file.
4. Run `Main.java`.

The application automatically:

- creates the database
- creates all tables
- optionally inserts sample data

---

# 🗄️ Database Schema

```
Players
    │
Inventory
    │
Items
```

### Players

- Name
- Level
- Strength
- Current HP
- Max HP
- Current Mana
- Max Mana
- Experience
- Learning Points
- Armor

### Items

- Name
- Type
- Damage
- Healing
- Mana Restore
- Description
- Value

### Inventory

- Player
- Item
- Quantity

---

# 📚 Concepts Practiced

- Object-Oriented Programming
- Repository Pattern
- Service Layer
- JDBC
- MariaDB
- SQL CRUD
- SQL JOINs
- Prepared Statements
- Environment Variables
- Relational Database Design
- Database Initialization
- Enums
- Business Logic Separation

---

# 🏗️ Project Structure

```
src
│
├── model
├── repository
├── service
├── database
├── enums
└── Main.java
```

---

# 🔮 Roadmap

- Equipment System
- Enemy System
- Combat System
- Loot System
- Merchant System
- Quest System
- NPC System
- Character Selection
- Save Game System

---

# 🎯 Goal

The purpose of this project is to learn how Java backend applications communicate with relational databases while applying clean architecture principles and building increasingly complex gameplay systems.