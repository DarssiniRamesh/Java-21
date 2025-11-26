# Java-21

This module contains a Java 21 compatible Spring Boot 3.3.x backend.

What’s included:
- Maven project targeting Java 21
- Dependencies: spring-boot-starter-web, spring-boot-starter-actuator, springdoc-openapi-starter-webmvc-ui
- Health endpoint at GET /health returning {"status":"ok"}
- Swagger UI available at /swagger-ui.html

Build:
- Preferred (with JDK 21 available via Maven Toolchains): ./mvnw -q -DskipTests package
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
- This project includes the Maven Wrapper (mvnw, mvnw.cmd, and .mvn/wrapper/*). If mvnw is not executable on your system, run: chmod +x mvnw

Note: Do not hardcode ports; preview system maps ports automatically.
