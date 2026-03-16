# 🏦 BankServlet – Java Servlet Based Banking System

![Java](https://img.shields.io/badge/Java-17+-orange)
![Servlet](https://img.shields.io/badge/Servlet-Jakarta-blue)
![Database](https://img.shields.io/badge/Database-MySQL-green)
![Server](https://img.shields.io/badge/Server-Apache%20Tomcat-red)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

A **Full Stack Online Banking Web Application** built using **Java Servlets, JDBC, MySQL, HTML, CSS, and JavaScript**.
This project simulates a **mini banking system** where users can manage their accounts and administrators can manage user transactions.

This project demonstrates:

* Java Servlet Architecture
* Session Management
* JDBC Database Integration
* REST-style APIs with Fetch
* Full Stack Web Development

---

# 📸 Application Screenshots

## 🏠 Home Page

![Home](screenshots/home.png)

## 🔐 Login Page

![Login](screenshots/login.png)

## 📊 User Dashboard

![Dashboard](screenshots/dashboard.png)

## 👨‍💼 Admin Panel

![Admin](screenshots/admin.png)

*(Add screenshots in `/screenshots` folder to display them here.)*

---

# ⚙️ Technologies Used

### Backend

* Java Servlets
* JDBC
* Apache Tomcat

### Frontend

* HTML5
* CSS3
* JavaScript (Fetch API)

### Database

* MySQL

### Development Tools

* Eclipse / IntelliJ
* Git & GitHub

---

# 🏗 System Architecture

```
                 ┌────────────────────┐
                 │      Web Browser   │
                 │  HTML / CSS / JS   │
                 └─────────┬──────────┘
                           │ HTTP Request
                           ▼
                 ┌────────────────────┐
                 │   Apache Tomcat    │
                 │   Java Servlets    │
                 └─────────┬──────────┘
                           │ JDBC
                           ▼
                 ┌────────────────────┐
                 │      MySQL DB      │
                 │      bankproj      │
                 └────────────────────┘
```

---

# 📂 Project Structure

```
BankServlet
│
├── BankProjectDucat
│   ├── src
│   │   └── com.ducat
│   │       ├── LoginServlet.java
│   │       ├── SignupServlet.java
│   │       ├── LogoutServlet.java
│   │       ├── DepositServlet.java
│   │       ├── TransferServlet.java
│   │       ├── GetProfileServlet.java
│   │       ├── ProfileUpdateServlet.java
│   │       ├── AdminActionServlet.java
│   │       ├── ContactusServlet.java
│   │       └── DBConnection.java
│   │
│   ├── webapp
│   │   ├── index.html
│   │   ├── login.html
│   │   ├── signup.html
│   │   ├── dashboard.html
│   │   ├── deposit.html
│   │   ├── transfer.html
│   │   ├── transactions.html
│   │   ├── profile.html
│   │   ├── admin.html
│   │   └── contact.html
│
├── database
│   └── schema.sql
│
├── screenshots
│   ├── home.png
│   ├── login.png
│   ├── dashboard.png
│   └── admin.png
│
├── README.md
└── .gitignore
```

---

# ✨ Features

## 👤 User Features

* Account Registration
* Secure Login & Logout
* Deposit Money
* Transfer Money
* View Transactions
* Update Profile
* Contact / Feedback Form

## 🛠 Admin Features

* Admin Login
* View All Users
* Add Money
* Deduct Money
* Monitor Transactions

---

# 🗄 Database Configuration

Database Details:

```
Database Name: bankproj
Username: root
Password: 223362
```

### DBConnection.java Example

```java
String url = "jdbc:mysql://localhost:3306/bankproj";
String username = "root";
String password = "223362";
Connection con = DriverManager.getConnection(url, username, password);
```

---

# 🗃 Database Schema

### User Table

```sql
CREATE TABLE usertable (
    accountnumber VARCHAR(20) PRIMARY KEY,
    fullname VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    username VARCHAR(50) UNIQUE,
    password VARCHAR(100),
    accountType VARCHAR(20)
);
```

---

### Account Table

```sql
CREATE TABLE account (
    accountnumber VARCHAR(20) PRIMARY KEY,
    balance DECIMAL(12,2) DEFAULT 0
);
```

---

### Transactions Table

```sql
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    accountnumber VARCHAR(20),
    type VARCHAR(20),
    amount DECIMAL(12,2),
    description VARCHAR(255),
    transactionDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

### Feedback Table

```sql
CREATE TABLE sbi_feedback (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fullname VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    subject VARCHAR(200),
    message TEXT
);
```

---

# 🚀 Installation Guide

## 1️⃣ Clone Repository

```bash
git clone https://github.com/Kartik-2233/BankServlet.git
```

---

## 2️⃣ Import Project

Import project into:

* Eclipse
* IntelliJ IDEA
* NetBeans

Configure **Apache Tomcat Server**.

---

## 3️⃣ Setup Database

1. Install **MySQL**
2. Create database

```sql
CREATE DATABASE bankproj;
```

3. Run schema.sql file.

---

## 4️⃣ Run Application

Start Tomcat and open:

```
http://localhost:8082/BankServlet
```

---

# 🔐 Default Admin Login

```
Username: Admin
Password: Admin123
```

---

# 📈 Future Improvements

* Password Encryption (BCrypt)
* OTP Authentication
* REST API Architecture
* Spring Boot Migration
* React Frontend
* Payment Gateway Integration

---

# 👨‍💻 Author

**Kartikey Shrivastava**

Java Full Stack Developer
Cybersecurity Enthusiast

GitHub
https://github.com/Kartik-2233

LinkedIn
https://linkedin.com/in/kartik2233

---

# ⭐ Support

If you like this project, please give it a **⭐ on GitHub**!
