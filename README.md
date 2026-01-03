# 🎧 Spotify API Automation Framework
### CI/CD Enabled | Enterprise-Grade API Test Automation

---

## 📌 Overview

This repository contains a **production-grade API automation framework** for validating **Spotify Playlist APIs**, built using **Java, Rest Assured, TestNG, and Maven**, and fully integrated with **CI/CD pipelines** using **GitHub Actions** and **Jenkins**.

The framework is designed following **real-world automation engineering principles**, with a strong focus on:

- Secure credential handling  
- OAuth 2.0 authentication  
- CI-safe execution  
- Environment-independent design  
- Robust API request and response validation  

---

## 🧰 Technology Stack

| Category | Tools |
|--------|------|
| Language | Java (JDK 11) |
| API Automation | Rest Assured |
| Test Framework | TestNG |
| Build Tool | Maven |
| CI (Cloud) | GitHub Actions |
| CI (Self-Hosted) | Jenkins |
| Authentication | OAuth 2.0 (Spotify) |
| Version Control | Git & GitHub |

---

## 📂 Framework Architecture

```text
Spotify_RestAssured
│
├── src/main/java
│   └── POJO
│       ├── ExternalUrls__1.java
│       ├── ExternalUrls.java
│       ├── Followers.java
│       ├── Owner.java
│       ├── PlayList.java
│       └── Tracks.java
│
├── src/test/java
│   ├── Authmanager
│   │   ├── TokenGeneration.java
│   │   └── TimeCalculator.java
│   │
│   ├── runners
│   │   └── PlayListRunner.java
│   │
│   ├── StepDefinition
│   │   └── PlaylistSteps.java
│   │
│   └── utility
│       ├── DateAndTimeProvider.java
│       ├── PropReader.java
│       └── SpecBuilder.java
│
├── src/test/resources
│   ├── SpotifyFeatures
│   │   └── Playlist.feature
│   └── Config.properties
│
├── pom.xml
├── testng.xml
├── Jenkinsfile
└── README.md
```

---

## 🧩 Core Framework Components

### 🔹 POJO Layer (`src/main/java/POJO`)
- Models Spotify API request and response payloads
- Uses Jackson annotations for JSON serialization and deserialization
- Maintains a clean separation between API contracts and test logic
- Improves maintainability and shields tests from API schema changes

- Example Classes:
  - `PlayList.java`
  - `Owner.java`
  - `Tracks.java`

### 🔹 Authentication Manager (`Authmanager`)
- Implements OAuth 2.0 Refresh Token flow
- Dynamically generates access tokens during runtime
- Prevents token expiry issues in CI pipelines

- Key Classes:
  - `TokenGenerartion.java – Generates access token`
  - `TimeCalculator.java – Manages token validity window`

### 🔹 Spec Builder (`SpecBuilder`)
- Centralized request/response specification
  
- Defines:
  - `Base URI`
  - `Headers`
  - `Authentication`
  - `Logging`
    
- Eliminates duplication across API calls

### 🔹 Property Management (`PropReader`)
- Reads configuration dynamically
- Supports CI-friendly environment variables
- Avoids hardcoding secrets inside the codebase

### 🔹 BDD Layer (`Feature Files`)
- Written in Gherkin syntax
- Improves readability and stakeholder understanding

- Example  Scenario:
  - `Step 1: Create Spotify Playlist`
  - `Step 2: Given user has valid access token`
  - `Step 3: When user creates a playlist`
  - `Step 4: Then playlist should be created successfully`

### 🔹 Step Definitions (`PlaylistSteps`)
- Maps Gherkin steps to RestAssured logic
- Contains request execution and assertions
- Keeps business flow separate from implementation

### 🔹 Test Runner (`PlayListRunner`)
- Executes feature files using TestNG
- Controls test execution flow
- CI-compatible runner design

---

## 🔐 Secure Configuration Strategy

### Local Execution
- Uses Config.properties for non-sensitive values
- Secrets are excluded via .gitignore

### CI Execution
- Secrets are injected at runtime via:
  - `GitHub Secrets`
  - `Jenkins Credentials`
    
- No credentials are stored in source code.

---

## 🚀 CI/CD Integration

### ✔ GitHub Actions
- Triggered on push and pull request
- Executes: mvn clean test
- Runs on Linux runners
- Ensures fast feedback per commit

### ✔ Jenkins Pipeline
- Declarative pipeline using Jenkinsfile
- Tooling:
  - `JDK 11`
  - `Maven`
- Secure credential binding
- Suitable for enterprise CI environments
- Pipeline Stages: Checkout → Build → Test

---

## 🛡 Engineering Best Practices Implemented

- OAuth token lifecycle handling
- CI safe configuration management
- POJO based API modeling
- BDD driven automation
- Environment independent execution
- Clean separation of concerns
- Defensive API validation
