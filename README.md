<img src="https://freesvg.org/img/1459857238.png" width=50% height=50%>

# LabU Reserve

[![CI](https://github.com/danial-hermes/lab-reservation-equipment-system/actions/workflows/ci.yml/badge.svg)](https://github.com/danial-hermes/lab-reservation-equipment-system/actions/workflows/ci.yml)

LabU Reserve is a university laboratory equipment booking and management system which allows students, researchers, faculty, and lab managers to reserve equipment, manage usage, and automate operational policies such as approvals, deposits, and arrival tracking.

## Tech Stack

- **Java** 26 (compiled with `--release 11`)
- **Maven** build system
- **Swing** desktop UI (dark glassmorphism theme)
- **Hibernate/JPA** with **Flyway** migrations
- **H2** (dev) + **PostgreSQL** (prod/CI via Testcontainers)
- **JUnit 5** testing (48 tests: 40 unit + 8 integration)
- **JaCoCo** + **PIT** code quality
- **GitHub Actions** CI/CD
