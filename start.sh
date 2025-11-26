#!/usr/bin/env sh
# PUBLIC_INTERFACE
# Entrypoint script to start the java-21-backend service without any db_visualizer references.
# Profiles:
#   - dev: H2 in-memory (default)
#   - prod: PostgreSQL (requires SPRING_DATASOURCE_* env vars)
#
# Usage examples:
#   # Dev (H2) on port 3001
#   SPRING_PROFILES_ACTIVE=dev SERVER_PORT=3001 ./start.sh
#
#   # Prod (PostgreSQL) on port 3001
#   SPRING_PROFILES_ACTIVE=prod \
#   SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5000/modernrepo" \
#   SPRING_DATASOURCE_USERNAME="modernuser" \
#   SPRING_DATASOURCE_PASSWORD="modernpass" \
#   SERVER_PORT=3001 \
#   ./start.sh
#
# Database startup (compose-based Postgres):
#   cd ../postgres-db_workspace/postgres-db
#   ./start.sh up
#
# Notes:
# - This script intentionally avoids any legacy paths like:
#   /home/kavia/workspace/code-generation/postgres-db_workspace/postgres-db/db_visualizer
# - The app binds to 0.0.0.0:${SERVER_PORT:-3001} by default.

set -eu

SCRIPT_DIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
cd "$SCRIPT_DIR"

# Ensure wrapper is executable
if [ -f "./mvnw" ] && [ ! -x "./mvnw" ]; then
  chmod +x ./mvnw || true
fi

# Default to dev profile if not set
: "${SPRING_PROFILES_ACTIVE:=dev}"
export SPRING_PROFILES_ACTIVE

# Ensure port defaults
: "${SERVER_PORT:=3001}"
export SERVER_PORT

echo "Starting java-21-backend with profile=${SPRING_PROFILES_ACTIVE} on port ${SERVER_PORT} ..."
echo "If you intend to use PostgreSQL, ensure it is running via:"
echo "  (cd ../postgres-db_workspace/postgres-db && ./start.sh up)"

# Prefer Maven Wrapper, then system mvn, else try an existing jar
if [ -x ./mvnw ] && [ -f .mvn/wrapper/maven-wrapper.jar ]; then
  exec ./mvnw -q -DskipTests spring-boot:run -Dspring-boot.run.profiles="$SPRING_PROFILES_ACTIVE"
elif command -v mvn >/dev/null 2>&1; then
  exec mvn -q -DskipTests spring-boot:run -Dspring-boot.run.profiles="$SPRING_PROFILES_ACTIVE"
elif ls target/*.jar >/dev/null 2>&1; then
  JAR="$(ls target/*.jar | head -n 1)"
  exec java -jar "$JAR" --spring.profiles.active="$SPRING_PROFILES_ACTIVE"
else
  echo "No mvnw, mvn, or jar found, cannot start the Spring Boot application" >&2
  exit 127
fi
