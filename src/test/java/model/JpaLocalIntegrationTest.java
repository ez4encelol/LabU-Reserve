package model;

/**
 * Integration test using <strong>local PostgreSQL</strong> at
 * {@code jdbc:postgresql://localhost:5432/labureserve}.
 *
 * <p>No Docker or Testcontainers required — for local development when
 * Docker Desktop is not ready. Uses the datasource configured in
 * {@code src/main/resources/application.properties}.</p>
 *
 * <p>For CI / Docker environments, use {@link JpaIntegrationTest}
 * (Testcontainers PostgreSQL 16).</p>
 */
class JpaLocalIntegrationTest extends AbstractJpaTest {
    // All test methods inherited from AbstractJpaTest.
    // Uses application.properties datasource (local PostgreSQL).
}
