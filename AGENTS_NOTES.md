# Migration/Modernization Notes for Future Agents

- Profiles:
  - dev: H2 in-memory (PostgreSQL mode)
  - prod: PostgreSQL (env overrides via SPRING_DATASOURCE_* and SPRING_JPA_HIBERNATE_DDL_AUTO)
- Server binding:
  - Defaults SERVER_ADDRESS=0.0.0.0 and SERVER_PORT=3001
- OpenAPI:
  - /openapi.json and /docs via springdoc-openapi-starter-webmvc-ui
- Health:
  - /health (simple)
  - /health/db (DB ping)
  - /actuator/health
- CRUD:
  - Questions and Answers under /questions and nested /answers
- Validation:
  - Jakarta Validation annotations on DTOs and entities
- CORS:
  - Permissive; tighten for production as needed
