<img src="https://freesvg.org/img/1459857238.png" width=50% height=50%>

# LabU Reserve

[![CI](https://github.com/ez4encelol/LabU-Reserve/actions/workflows/ci.yml/badge.svg)](https://github.com/ez4encelol/LabU-Reserve/actions/workflows/ci.yml)

LabU Reserve is a university laboratory equipment booking and management system which allows students, researchers, faculty, and lab managers to reserve equipment, manage usage, and automate operational policies such as approvals, deposits, and arrival tracking.

## Tech Stack

- **Java** 26 (compiled with `--release 11`)
- **Maven** manages the project's configuration
- **Swing/AWT** desktop GUI 
- **Hibernate/JPA** with **Flyway** migrations
- **PostgreSQL** (local dev + production)
- **JUnit 5** testing (48 tests: 40 unit + 8 integration)
- **JaCoCo**/**PIT** automated and mutation testing
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

<img width="561" height="628" alt="login" src="https://github.com/user-attachments/assets/d6de2a8b-9297-4b40-b371-70be211255f9" />
<img width="1364" height="406" alt="user" src="https://github.com/user-attachments/assets/f404b33d-6c09-47b8-b0f3-109c0aea5225" />
<img width="758" height="407" alt="manager" src="https://github.com/user-attachments/assets/56c018d1-35b4-4424-bf95-4f88d323016c" />
