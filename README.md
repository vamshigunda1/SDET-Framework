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
* 🎯 **Programmatic Test Runner** - Run tests via Java main method with system properties
* 🌐 **Multi-Browser Support** - Chrome, Safari, Firefox, Edge with WebDriverManager
* ⚡ **Command-line Flexibility** - Override browser, groups, and suite via `-D` properties

---

## 🏗️ Project Structure

```text
SDET-Framework
├── src/
│   ├── main/java/com/sdet/framework/
│   │   ├── drivers/DriverFactory.java       # Multi-browser WebDriver factory
│   │   ├── utils/ConfigReader.java          # Property-based configuration
│   │   ├── utils/WebDriverUtils.java        # Reusable WebDriver utilities
│   │   ├── core/BaseTest.java               # TestNG base class
│   │   ├── pages/LoginPage.java             # Page Object Models
│   │   ├── pages/DashboardPage.java
│   │   └── api/APIClient.java               # RestAssured API wrapper
│   ├── main/resources/
│   │   ├── config.properties                # Browser, URL, wait configs
│   │   └── log4j2.xml                       # Logging configuration
│   └── test/java/com/sdet/framework/
│       ├── runners/
│       │   ├── TestRunner.java              # Cucumber TestNG runner
│       │   └── ProgrammaticTestNG.java      # Java main runner with properties
│       ├── stepdefinitions/                 # Cucumber step definitions
│       └── tests/ui/ & api/                 # TestNG test classes
│
├── .vscode/launch.json                      # VS Code debug configurations
├── pom.xml                                  # Maven dependencies
├── testng.xml                               # TestNG suite configuration
├── Dockerfile                               # Container image (Java 21 + Maven)
└── README.md
```

---

## 🧩 Tech Stack

| Component        | Technology                  | Version |
| ---------------- | --------------------------- | ------- |
| Language         | Java                        | 21      |
| Build Tool       | Maven                       | 3.8+    |
| UI Automation    | Selenium WebDriver          | 4.26.0  |
| API Automation   | RestAssured                 | 5.4.0   |
| BDD Framework    | Cucumber                    | 7.15.0  |
| Test Runner      | TestNG                      | 7.8.0   |
| Driver Manager   | WebDriverManager            | 5.6.3   |
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

### Standard Maven Commands

#### Run all tests
```bash
mvn clean test
```

#### Run specific test class
```bash
mvn clean test -Dtest=LoginTest
```

#### Run with specific browser
```bash
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=safari
mvn clean test -Dbrowser=edge
```

#### Exclude API tests
```bash
mvn clean test -DexcludedGroups=api
```

#### Run Cucumber tests with tags
```bash
mvn clean test -Dcucumber.filter.tags="@smoke"
```

### Programmatic TestNG Runner

Run tests via Java main method with full control over browser, suite, and groups:

#### Using Maven exec plugin
```bash
# Run with Chrome (default)
mvn exec:java -Dexec.mainClass=com.sdet.framework.runners.ProgrammaticTestNG -Dexec.classpathScope=test

# Run with Safari
mvn exec:java -Dexec.mainClass=com.sdet.framework.runners.ProgrammaticTestNG -Dexec.classpathScope=test -Dbrowser=safari

# Run with Chrome, exclude API tests
mvn exec:java -Dexec.mainClass=com.sdet.framework.runners.ProgrammaticTestNG -Dexec.classpathScope=test -Dbrowser=chrome -DexcludedGroups=api

# Run smoke tests only
mvn exec:java -Dexec.mainClass=com.sdet.framework.runners.ProgrammaticTestNG -Dexec.classpathScope=test -Dgroups=smoke

# Use custom suite file
mvn exec:java -Dexec.mainClass=com.sdet.framework.runners.ProgrammaticTestNG -Dexec.classpathScope=test -DsuiteFile=custom-suite.xml
```

#### Using VS Code (Recommended)

Press **F5** and select from available configurations:
- **Run Tests (Chrome)** - All UI tests with Chrome
- **Run Tests (Safari)** - All UI tests with Safari  
- **Run Smoke Tests (Chrome)** - Only smoke group

#### Programmatic Runner Properties

| Property | Options | Default | Description |
|----------|---------|---------|-------------|
| `-Dbrowser` | chrome, safari, edge, firefox | chrome | Browser to run tests |
| `-DsuiteFile` | path to XML | testng.xml | TestNG suite file |
| `-Dgroups` | comma-separated | none | Include test groups |
| `-DexcludedGroups` | comma-separated | none | Exclude test groups |

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
# Browser Configuration
browser=chrome                          # Default: chrome | Options: chrome, safari, edge, firefox
headless.mode=false

# Application URLs
app.url=https://www.saucedemo.com
api.base.url=https://api.example.com

# Wait Times (seconds)
implicit.wait=10
explicit.wait=15

# Logging
logging.level=INFO

# Reporting
report.path=target/allure-results
```

### Multi-Browser Support

The framework automatically manages browser drivers using **WebDriverManager**:

- **Chrome** - WebDriverManager downloads ChromeDriver automatically
- **Safari** - Uses macOS built-in SafariDriver (enable in Safari Settings → Advanced → Developer)
- **Edge** - WebDriverManager downloads EdgeDriver automatically
- **Firefox** - WebDriverManager downloads GeckoDriver automatically

Override browser via command line:
```bash
mvn clean test -Dbrowser=safari
```

Or in VS Code debug configurations (`.vscode/launch.json`).

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
