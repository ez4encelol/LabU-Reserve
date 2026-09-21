package model;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Integration test using Testcontainers PostgreSQL 16.
 *
 * <p>Testcontainers spins up an isolated PostgreSQL container for each
 * test run, proving database portability with the production stack.
 * Tests are <strong>skipped</strong> automatically if Docker is unavailable
 * ({@code disabledWithoutDocker = true}).</p>
 *
 * <p>For local development without Docker, use
 * {@link JpaLocalIntegrationTest} instead.</p>
 *
 * <p>CI (GitHub Actions) has Docker pre-installed, so all 4 tests
 * run there. See {@code .github/workflows/ci.yml}.</p>
 */
@Testcontainers(disabledWithoutDocker = true)
class JpaIntegrationTest extends AbstractJpaTest {

    @Container
    static final PostgreSQLContainer<?> postgres =
        new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("labureserve")
            .withUsername("labureserve")
            .withPassword("labureserve");

    /**
     * Override datasource and Hibernate properties to point at the
     * Testcontainers PostgreSQL container instead of application.properties.
     */
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.driver-class-name", postgres::getDriverClassName);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.flyway.url", postgres::getJdbcUrl);
        registry.add("spring.jpa.properties.hibernate.dialect",
            () -> "org.hibernate.dialect.PostgreSQLDialect");
    }
}
