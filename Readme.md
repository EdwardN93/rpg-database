# 🎮 RPG Database Manager

A Java learning project built to explore **JDBC**, **MariaDB**, and relational database design through an RPG-inspired database system.

Instead of generic examples like employees and departments, this project manages players, items, and inventories.

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

## ✨ Features

- Automatic database and table creation
- Player CRUD operations
- RPG item management
- Inventory system
- SQL JOINs
- Primary & Foreign Keys
- Seed data for testing
- Database configuration using `.env`

---

## ⚙️ Configuration

Create a `.env` file in the project root:

```text
DB_SERVER_URL=jdbc:mariadb://localhost:3306
DB_URL=jdbc:mariadb://localhost:3306/game_db
DB_USER=root
DB_PASSWORD=
```

---

## ▶️ Running

1. Clone the repository.
2. Start MariaDB.
3. Configure the `.env` file.
4. Run `Main.java`.

The application will automatically:

- Create the `game_db` database
- Create all required tables
- Optionally insert sample RPG data

---

## 🗄️ Database

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
- HP / Mana
- Experience
- Armor

### Items
- Name
- Type
- Damage
- Healing
- Description
- Value

### Inventory
- Player
- Item
- Quantity

---

## 📚 Concepts Practiced

- JDBC
- SQL CRUD
- SQL JOINs
- PreparedStatement
- Relational database design
- Primary & Foreign Keys
- Database initialization
- Environment variables

---

## 🏗️ Project Structure

```
Main
│
├── DatabaseConnection
├── DatabaseInitializer
└── DatabaseManager
```

---

## 🔮 Future Improvements

- SQL Transactions
- Quest system
- Monster system
- Repository pattern
- Improved inventory management

---

## 🎯 Goal

Learn how Java applications communicate with relational databases using JDBC while building a small RPG-inspired backend project.