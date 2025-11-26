# Java-21

This module contains a Java 21 compatible Spring Boot 3.3.x backend.

What’s included:
- Maven project targeting Java 21
- Dependencies: spring-boot-starter-web, spring-boot-starter-actuator, springdoc-openapi-starter-webmvc-ui
- Health endpoint at GET /health returning {"status":"ok"}
- Swagger UI available at /docs

Build:
- Preferred: ensure wrapper is executable and use it
  - chmod +x mvnw
  - ./mvnw -q -DskipTests package
- Fallback (when only JDK 17 is installed): the project will compile targeting Java 17 automatically due to fallback settings.

Toolchains:
- If you have JDK 21 installed, configure ~/.m2/toolchains.xml similar to:
  <toolchains>
    <toolchain>
      <type>jdk</type>
      <provides>
        <version>21</version>
        <vendor>any</vendor>
      </provides>
      <configuration>
        <jdkHome>/path/to/jdk-21</jdkHome>
      </configuration>
    </toolchain>
  </toolchains>

Run (local):
- ./mvnw spring-boot:run

Maven Wrapper:
- This project includes the Maven Wrapper (mvnw, mvnw.cmd, and .mvn/wrapper/*). The wrapper JAR will be downloaded automatically if missing.
- Wrapper scripts are aligned with Maven Wrapper 3.2.0 and will download Apache Maven 3.9.7 as configured.
- The wrapper configuration is defined in .mvn/wrapper/maven-wrapper.properties with
  - wrapperUrl pointing to maven-wrapper-3.2.0.jar
  - distributionUrl pointing to Apache Maven 3.9.7
- The mvnw script resolves the wrapper JAR at .mvn/wrapper/maven-wrapper.jar and will download it if missing.



Note:
- Do not hardcode ports; preview system maps ports automatically.
- pom.xml is configured to fall back to release/source/target=17 so builds succeed where only JDK 17 is available.
- Ensure mvnw is executable: chmod +x mvnw
