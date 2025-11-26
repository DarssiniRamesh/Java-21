# Migration/Modernization Notes for Future Agents

- Profiles:
  - dev: H2 in-memory (PostgreSQL mode)
  - prod: PostgreSQL (env overrides via SPRING_DATASOURCE_* and SPRING_JPA_HIBERNATE_DDL_AUTO)

- Startup notes:
  - No references to /home/kavia/workspace/code-generation/postgres-db_workspace/postgres-db/db_visualizer exist in this project. Backend starts independently.
  - Current environment does not support Java release 21 for compilation; Maven compiler set to release=17 to allow startup and verification. Revert to 21 when environment supports it.

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
