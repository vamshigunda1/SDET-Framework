# 🧩 SDET-Framework

> A scalable, production-grade Test Automation Framework built in **Java** using **Selenium 4**, **Cucumber**, **TestNG**, and **RestAssured**, integrated with **Docker** for intelligent, end-to-end automation.

---

## 🏗️ Overview

`SDET-Framework` demonstrates how to design, build, and scale a production-grade automation architecture combining modern test engineering practices, BDD patterns, and comprehensive test coverage for both UI and API layers.

---

## 🧠 Key Features

* ✅ **Modular Architecture** - Organized packages for drivers, utils, core, pages, api, step definitions
* 🧩 **UI Automation** - Selenium 4 with Page Object Model and ThreadLocal WebDriver
* 🌐 **API Automation** - RestAssured with request/response logging and assertions
* ⚙️ **BDD Support** - Cucumber 7 with Gherkin feature files and TestNG integration
* 🧾 **Comprehensive Reporting** - Allure Reports with detailed execution logs
* 🐳 **Docker Support** - Containerized test execution
* 📊 **Log4j Integration** - Detailed logging for debugging and auditing
* 🔒 **Thread-Safe** - ThreadLocal WebDriver for parallel execution

---

## 🏗️ Project Structure

```text
SDET-Framework
├── src/
│   ├── main/java/com/sdet/framework/
│   │   ├── drivers/DriverFactory.java
│   │   ├── utils/ConfigReader.java & WebDriverUtils.java
│   │   ├── core/BaseTest.java
│   │   ├── pages/LoginPage.java & DashboardPage.java
│   │   └── api/APIClient.java
│   ├── main/resources/
│   │   ├── config.properties
│   │   └── log4j2.xml
│   └── test/java/com/sdet/framework/
│       ├── runners/TestRunner.java
│       ├── stepdefinitions/
│       └── tests/ui/ & api/
│
├── pom.xml
├── testng.xml
├── Dockerfile
└── README.md
```

---

## 🧩 Tech Stack

| Component        | Technology                  | Version |
| ---------------- | --------------------------- | ------- |
| Language         | Java                        | 21      |
| Build Tool       | Maven                       | 3.8+    |
| UI Automation    | Selenium WebDriver          | 4.15.0  |
| API Automation   | RestAssured                 | 5.4.0   |
| BDD Framework    | Cucumber                    | 7.15.0  |
| Test Runner      | TestNG                      | 7.8.0   |
| Logging          | Log4j2                      | 2.21.1  |
| Reporting        | Allure                      | 2.25.0  |
| Containerization | Docker                      | Latest  |

---

## 📋 Prerequisites

* **Java 21** or higher
* **Maven 3.8.0** or higher
* **Git**
* **Docker** (optional)

---

## 🚀 Quick Start

```bash
git clone https://github.com/vamshigunda1/SDET-Framework.git
cd SDET-Framework
mvn clean install
mvn clean test
```

---

## 📝 Running Tests

### Run all tests
```bash
mvn clean test
```

### Run specific test class
```bash
mvn clean test -Dtest=LoginTest
```

### Run Cucumber tests with tags
```bash
mvn clean test -Dcucumber.filter.tags="@smoke"
```

### View Allure reports
```bash
mvn allure:serve
```

---

## 🐳 Docker

```bash
docker build -t sdet-framework:latest .
docker run sdet-framework:latest
```

---

## 🔧 Configuration

Edit `src/main/resources/config.properties`:

```properties
browser=chrome
app.url=https://www.saucedemo.com
api.base.url=https://api.example.com
implicit.wait=10
explicit.wait=15
```

---

## 📚 Resources

- [Selenium Docs](https://www.selenium.dev/)
- [Cucumber Docs](https://cucumber.io/)
- [RestAssured Docs](https://rest-assured.io/)
- [TestNG Docs](https://testng.org/)

---

## 📜 License

MIT License - see LICENSE file for details

---

## 👨‍💻 Author

**Vamshi Krishna Gunda**  
SDET | QA Automation Engineer  
🚀 Building scalable automation frameworks
