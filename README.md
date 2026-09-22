<img src="https://freesvg.org/img/1459857238.png" width=50% height=50%>

# LabU Reserve

[![CI](https://github.com/ez4encelol/LabU-Reserve/actions/workflows/ci.yml/badge.svg)](https://github.com/ez4encelol/LabU-Reserve/actions/workflows/ci.yml)

LabU Reserve is a university laboratory equipment booking and management system which allows students, researchers, faculty, and lab managers to reserve equipment, manage usage, and automate operational policies such as approvals, deposits, and arrival tracking.

## Tech Stack

- **Java** 26 (compiled with `--release 11`)
- **Maven** build system
- **Swing** desktop UI (dark glassmorphism theme)
- **Hibernate/JPA** with **Flyway** migrations
- **PostgreSQL** (local dev + production)
- **JUnit 5** testing (48 tests: 40 unit + 8 integration)
- **JaCoCo** + **PIT** code quality
- **GitHub Actions** CI/CD

## Getting Started

### Prerequisites
- **Java JDK 26** — [Download Temurin](https://adoptium.net/)
- **PostgreSQL 18.6** (or any PostgreSQL 12+) — [Download](https://www.postgresql.org/download/)
- **Docker Desktop** (only for running Testcontainers integration tests; 40 unit tests work without it)

### 1. Set Up the Database
```bash
psql -U postgres -c "CREATE USER labureserve WITH PASSWORD 'labureserve';"
psql -U postgres -c "CREATE DATABASE labureserve OWNER labureserve;"
psql -U postgres -c "GRANT ALL PRIVILEGES ON DATABASE labureserve TO labureserve;"
```

### 2. Run the Application
```bash
# From source
./mvnw spring-boot:run

# Or build a standalone JAR first
./mvnw clean package -DskipTests
java -jar target/lab-reservation-equipment-system-1.0-SNAPSHOT.jar
```

### 3. Running Tests
```bash
# Full suite (48 tests — requires Docker Desktop for Testcontainers)
./mvnw clean test

# Unit tests only (40 tests — no Docker needed)
./mvnw test -Dtest='!model.JpaIntegrationTest,!model.JpaLocalIntegrationTest'

# CI-compatible mode (44 tests — excludes local PostgreSQL test)
./mvnw clean test -Pci
```

### CI/CD

This project uses [GitHub Actions](https://github.com/ez4encelol/LabU-Reserve/actions) for CI. The workflow runs on every push to `main`:

1. **Build & Test (Ubuntu)** — Full test suite with Testcontainers PostgreSQL
2. **Build & Test (Windows)** — Unit tests only (Testcontainers auto-skipped without Docker)
3. **Package JAR** — Builds the JAR artifact
4. **Coverage & Mutation** — JaCoCo + PIT mutation testing

To trigger a manual run: **Actions tab** → select "CI/CD" → **Run workflow** dropdown → Run workflow.
