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
- Dynamically generates access tokens
- Prevents token expiry in CI runs

### 🔹 Spec Builder (`SpecBuilder`)
- Centralized request/response specification
- Defines base URI, headers, authentication, logging

### 🔹 Property Management (`PropReader`)
- Reads configuration dynamically
- Supports CI-friendly environment variables

### 🔹 BDD Layer (Feature Files)
- Written using Gherkin syntax

### 🔹 Step Definitions (`PlaylistSteps`)
- Maps Gherkin steps to Rest Assured logic

### 🔹 Test Runner (`PlayListRunner`)
- Executes feature files using TestNG

---

## 🔐 Secure Configuration Strategy

### Local Execution
- Uses Config.properties for non-sensitive values
- Secrets excluded via .gitignore

### CI Execution
- Secrets injected using GitHub Secrets and Jenkins Credentials

---

## 🚀 CI/CD Integration

### ✔ GitHub Actions
- Triggered on push and pull request
- Executes: mvn clean test

### ✔ Jenkins Pipeline
- Declarative pipeline using Jenkinsfile
- Stages: Checkout → Build → Test

---

## 🛡 Engineering Best Practices Implemented

- OAuth token lifecycle management
- CI-safe configuration
- POJO-based API modeling
- BDD-driven automation
- Environment-independent execution
