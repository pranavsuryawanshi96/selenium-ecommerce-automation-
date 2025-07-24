# Selenium E-Commerce Automation

This project contains automation scripts written in Java using **Selenium WebDriver** and **TestNG** to test core functionalities of an e-commerce platform.

## 🧪 Features Tested

- Click on **SHOP NOW** and verify product listing
- Scroll to products dynamically
- Extract and validate **listing price vs detail page price**
- Use **Explicit Waits** for stable automation
- Hover over menus using **Actions class**
- Click and verify **Cart (1)** element using `data-testid`
- Validate **Subtotal** in cart
- Handle **Dynamic Locators** with partial text (like `"Cart"`)

---

## 🔧 Tech Stack

- Java (JDK 21)
- Selenium WebDriver (v4.33.0)
- TestNG
- ChromeDriver
- Maven (for dependency management)

---

## 📁 Project Structure

.
├── pom.xml
├── README.md
└── src
    └── test
        └── java
            └── rohanTest
                └── StoreTest.java
