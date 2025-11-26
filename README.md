# Java-21

This module contains a Java 21 compatible Spring Boot 3.3.x backend.

What’s included:
- Maven project targeting Java 21
- Dependencies: spring-boot-starter-web, spring-boot-starter-actuator, springdoc-openapi-starter-webmvc-ui
- Health endpoint at GET /health returning {"status":"ok"}
- Swagger UI available at /swagger-ui.html

Build:
- ./mvnw -q -DskipTests package

Run (local):
- ./mvnw spring-boot:run

Note: Do not hardcode ports; preview system maps ports automatically.
