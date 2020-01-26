package org.agoncal.fascicle.quarkus.book.infrastructure;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Collections;
import java.util.Map;

public class Database implements QuarkusTestResourceLifecycleManager {

  private static final PostgreSQLContainer DATABASE = new PostgreSQLContainer<>("postgres:12.1")
    .withDatabaseName("books_database")
    .withUsername("book")
    .withPassword("book")
    .withExposedPorts(5432);

  @Override
  public Map<String, String> start() {
    DATABASE.start();
    return Collections.singletonMap("quarkus.datasource.url", DATABASE.getJdbcUrl());
  }

  @Override
  public void stop() {
    DATABASE.stop();
  }
}
