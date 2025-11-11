# 🧩 SDET-Framework

> A scalable, agentic-AI assisted Test Automation Framework built in **Java** using **Selenium**, **Cucumber**, and **RestAssured**, integrated with **Docker** and **Harness CI/CD** for intelligent, end-to-end automation.

---

## 🏗️ Overview

`SDET-Framework` demonstrates how to design, build, and scale a production-grade automation architecture ready for enterprise and MAANG-level SDET interviews.
It combines **modern test engineering practices**, **self-healing test execution (Healenium)**, and **agentic AI-assisted automation development** to deliver a maintainable, high-performance QA ecosystem.

---

## 🧠 Key Features

* ✅ Modular framework with `Base`, `Hooks`, `Utils`, and `Drivers` packages
* 🧩 **UI Automation:** Selenium 4 + ThreadLocal WebDriver
* 🌐 **API Automation:** RestAssured with JSON/XML validation
* ⚙️ **BDD Support:** Cucumber integration with dynamic data injection
* 🧰 **Agentic AI Integration:** LLM-assisted test generation with human validation
* 🧾 **Custom HTML Reports:** Captures endpoint, request, and response for every service
* 🐳 **Docker Support:** Containerized test execution
* 🔄 **Harness CI/CD Integration:** Automated pipeline for build + regression
* 🧬 **Self-Healing:** Healenium support for auto-recovery of flaky locators

---

## 🏗️ Architecture

```text
SDET-Framework
│
├── src
│   ├── main/java/com/sdet/framework
│   │   ├── drivers/DriverFactory.java
│   │   ├── utils/ConfigReader.java
│   │   └── core/BaseTest.java
│   └── test/java/com/sdet/tests
│       ├── ui/SampleUiTest.java
│       ├── api/ApiTests.java
│       └── bdd/
│           ├── features/
│           └── stepdefinitions/
│
├── Dockerfile
├── harness-pipeline.yml
├── pom.xml
└── README.md
```

---

## 🧩 Tech Stack

| Layer            | Tools                            |
| ---------------- | -------------------------------- |
| Language         | Java 17                          |
| Build            | Maven                            |
| UI Automation    | Selenium 4                       |
| API Automation   | RestAssured                      |
| BDD              | Cucumber + TestNG                |
| Reporting        | Custom HTML / Allure / Extent    |
| CI/CD            | Harness                          |
| Containerization | Docker                           |
| Healing          | Healenium                        |
| Agentic-AI       | GitHub Copilot Chat / Aide Agent |

---

## ⚡ Badges

![Java](https://img.shields.io/badge/Java-17-blue)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen)
![Docker](https://img.shields.io/badge/Docker-Ready-0db7ed)
![Harness](https://img.shields.io/badge/CI-Harness-orange)
![License](https://img.shields.io/badge/License-MIT-yellow)

---

## 🧪 Getting Started

```bash
# Clone repo
git clone https://github.com/<your-username>/SDET-Framework.git
cd SDET-Framework

# Run tests locally
mvn clean test

# Run in Docker
docker build -t sdet-framework .
docker run sdet-framework
```

---

## 🌟 Agentic-AI Workflow Example

> This framework supports **agentic automation development** — use tools like GitHub Copilot Chat or Aide Agent to generate step definitions, test data templates, or helper methods.
> Every AI-assisted code addition includes human-reviewed commits labeled as:
>
> ```
> chore(ai): generated base test utility (reviewed)
> ```

---

## 📈 Future Enhancements

* Add Playwright-based UI layer
* Integrate BrowserStack / AWS Device Farm
* Extend Healenium recovery reporting
* Enable parallel BDD execution in Docker grid

---

## 👨‍💻 Author

**Vamshi Krishna Gunda**
Automation Engineer | SDET | MAANG Aspirant
🚀 Passionate about building intelligent, self-healing automation systems
📫 [Professional Email]
🔗 [LinkedIn Profile]

---

## 📜 License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.
