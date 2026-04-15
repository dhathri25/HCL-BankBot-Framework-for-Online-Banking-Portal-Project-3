# BankBot – Selenium-Java Test Automation Framework
### HCL-GUVI Hackathon | Problem Statement 3

---

## 📌 Project Overview
This project is a robust, production-grade automation framework developed for the **Guru99 Online Banking Portal**. It follows the **Page Object Model (POM)** design pattern to ensure scalability, reusability, and clean code separation.

---

## 🛠️ Tech Stack & Dependencies
* **Language:** Java 11
* **Automation:** Selenium WebDriver 4.21.0
* **Test Runner:** TestNG 7.9.0
* **Driver Management:** WebDriverManager
* **Reporting:** ExtentReports 5.1.1
* **Build Tool:** Maven

---

## 🏗️ Framework Architecture
The framework is built with a clear separation of concerns to meet all hackathon business rules:

* **`BasePage`**: Contains reusable synchronization methods using `WebDriverWait` (**Strictly zero `Thread.sleep()`**).
* **`BaseTest`**: Handles browser lifecycle (`@BeforeMethod`/`@AfterMethod`) and reads configurations.
* **`Page Classes`**: Encapsulates locators and actions for Login, Customer, Account, and Fund Transfer pages.
* **`Test Classes`**: Execution logic calling POM methods. Includes **Data-Driven Testing** via `@DataProvider`.
* **`TestListener`**: Implements `ITestListener` for automatic **Screenshot Capture** on failure.
* **`Config Management`**: Uses `config.properties` and `ConfigReader` to eliminate hardcoded values.

---

## 🚀 Key Features Implemented

| Requirement | Implementation Detail |
| :--- | :--- |
| **POM Design** | 100% decoupling of Test and Page layers. |
| **Wait Strategy** | Strictly uses **Explicit Waits** (`WebDriverWait`). No `Thread.sleep()`. |
| **Data Driven** | `@DataProvider` implemented for User Authentication scenarios. |
| **Automation Coverage** | End-to-end flows for Customer creation, Account linking, and Fund transfers. |
| **Error Handling** | Validation for field-level errors and dynamic alert handling. |
| **Reporting** | Rich HTML reports with failure screenshots via ExtentReports. |

---

## 📁 Project Structure
```text
bankbot-framework/
├── src/test/java/
│   ├── base/           # BasePage and BaseTest setup
│   ├── pages/          # Page Object classes (LoginPage, NewCustomerPage, etc.)
│   ├── tests/          # TestNG Test classes
│   └── utils/          # ConfigReader, ExtentManager, ScreenshotUtil, TestListener
├── src/test/resources/
│   └── config.properties # Environment variables (Browser, URL, Timeout)
├── screenshots/         # Auto-generated failure evidence
├── reports/             # ExtentReport HTML output
├── testng.xml           # Suite execution runner
└── pom.xml              # Maven dependencies