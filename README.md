# OCBC BE Java Junior Take Home Test

This repository contains solutions for the Backend Java Junior take-home test.

---

## 📌 Overview

The solutions cover a mix of:

* Java (Algorithm & Data Structures)
* SQL Queries
* Object-Oriented Programming (OOP)
* Spring Boot (REST API Validation)

Each problem is implemented in a separate file as required.

---

## 📂 Project Structure

```
ocbc-be-java-junior-test/
├── README.md
├── java/
│   ├── 01_TotalGoals.java
│   ├── 04_MinimumCostMST.java
│   ├── 05_DemolitionScore.java
│   ├── 06_MinimumPenalty.java
│   ├── 07_VehicleDemo.java
│   └── 09_MinYearsToSurpass.java
├── sql/
│   ├── 02_PerformanceRating.sql
│   ├── 03_CustomerConsumption.sql
│   └── 10_NegativeBalanceReport.sql
└── spring-product-validator/
    ├── pom.xml
    └── src/main/java/com/example/productvalidator/
        ├── ProductValidatorApplication.java
        ├── controller/ProductController.java
        ├── model/Product.java
        ├── validator/ProductValidator.java
        └── exception/GlobalExceptionHandler.java
```

---

## 🧠 Problem Breakdown

### Java (Algorithm & Logic)

| No | File                      | Description                            |
| -- | ------------------------- | -------------------------------------- |
| 1  | 01_TotalGoals.java        | REST API pagination & data aggregation |
| 4  | 04_MinimumCostMST.java    | Graph & Minimum Spanning Tree          |
| 5  | 05_DemolitionScore.java   | Recursion & Dynamic Programming        |
| 6  | 06_MinimumPenalty.java    | Greedy algorithm using Priority Queue  |
| 7  | 07_VehicleDemo.java       | OOP (Inheritance & Abstraction)        |
| 9  | 09_MinYearsToSurpass.java | Simulation problem                     |

---

### SQL

| No | File                         | Description                        |
| -- | ---------------------------- | ---------------------------------- |
| 2  | 02_PerformanceRating.sql     | CASE WHEN & string formatting      |
| 3  | 03_CustomerConsumption.sql   | Aggregation, JOIN, Window Function |
| 10 | 10_NegativeBalanceReport.sql | JOIN, GROUP BY, filtering          |

---

### Spring Boot

| No | Description                                           |
| -- | ----------------------------------------------------- |
| 8  | Product validation using custom Validator in REST API |

**Features:**

* Custom validation rules
* Error handling with ordered messages
* REST endpoint: `POST /products`

---

## 🚀 How to Run

### 1. Java Files

Compile & run (example):

```
cd java
javac 09_MinYearsToSurpass.java
java MinYearsToSurpass
```

---

### 2. SQL Files

Run queries using:

* MySQL
* PostgreSQL
* Any SQL client

---

### 3. Spring Boot Application

Run application:

```
cd spring-product-validator
mvn spring-boot:run
```

---

### Test API

**Endpoint:**

```
POST http://localhost:8080/products
```

**Sample Request:**

```json
{
  "sku": "SKU-98765432",
  "productName": "Wireless Mouse",
  "quantityInStock": 150,
  "price": 29.99,
  "category": "Electronics"
}
```

---

## ⚠️ Notes

* Each problem is implemented in a separate file as required
* Code focuses on clarity, correctness, and best practices
* Edge cases are handled where applicable
* SQL queries follow standard ANSI SQL

---
