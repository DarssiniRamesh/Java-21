# Java-21

Java 21 Spring Boot 3.3.x backend with OpenAPI, CRUD for Questions/Answers, and H2 (dev)/PostgreSQL (prod) profiles.

Features:
- Java 21 toolchain with Spring Boot 3.3.x
- CRUD for Questions and Answers
- Pagination for listing questions
- Jakarta Validation for request DTOs
- Exception handling with consistent error responses
- OpenAPI docs at /openapi.json and Swagger UI at /docs
- Health endpoints:
  - GET /health returns {"status":"ok"}
  - GET /health/db performs a DB ping (SELECT 1)
  - Actuator: /actuator/health, /actuator/info
- CORS enabled (permissive for dev)
- Profiles:
  - dev: H2 in-memory database (PostgreSQL compatibility mode)
  - prod: PostgreSQL

Build:
- Ensure wrapper is executable:
  - chmod +x mvnw
- Build:
  - ./mvnw -q -DskipTests package

Run:
- Dev (H2):
  - SPRING_PROFILES_ACTIVE=dev SERVER_PORT=3001 ./mvnw spring-boot:run
- Prod (PostgreSQL):
  - SPRING_PROFILES_ACTIVE=prod \
    SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/modernrepo" \
    SPRING_DATASOURCE_USERNAME=postgres \
    SPRING_DATASOURCE_PASSWORD=postgres \
    SERVER_PORT=3001 \
    ./mvnw spring-boot:run

API Highlights:
- GET /questions?page=0&size=10
- GET /questions/{id}?includeAnswers=true
- POST /questions { "title": "...", "content": "..." }
- PUT /questions/{id} { "title": "...", "content": "..." }
- DELETE /questions/{id}
- GET /questions/{id}/answers
- POST /questions/{id}/answers { "content": "..." }
- DELETE /questions/{id}/answers/{answerId}

Config:
- Server binds to 0.0.0.0:3001 by default (override via SERVER_ADDRESS/SERVER_PORT).
- Env var overrides for datasource and JPA DDL behavior.
- See .env.example for common environment variables.

Notes:
- Ensure JDK 21 is available. If using Maven Toolchains, set ~/.m2/toolchains.xml for version 21.
- This project uses Hibernate 6 (managed by Boot 3.3.x) and the PostgreSQL JDBC driver 42.7.4.
