package com.example.java21.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;

/**
 * PUBLIC_INTERFACE
 * Minimal OpenAPI configuration to populate metadata.
 * Uses springdoc-openapi-starter-webmvc-ui (2.6.0), compatible with Spring Boot 3.3.x.
 */
@org.springframework.context.annotation.Configuration
public class OpenApiConfig {

    // PUBLIC_INTERFACE
    @org.springframework.context.annotation.Bean
    public OpenAPI customOpenAPI() {
        /** Configure basic API metadata. */
        return new OpenAPI()
            .info(new Info()
                .title("Java 21 Backend API")
                .description("Spring Boot 3.3.x API with OpenAPI and health endpoint")
                .version("v0.0.1")
                .contact(new Contact().name("API Support"))
            )
            .externalDocs(new ExternalDocumentation()
                .description("Swagger UI")
                .url("/docs")
            );
    }
}
