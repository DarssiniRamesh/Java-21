package com.example.java21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PUBLIC_INTERFACE
 * Java 21 Spring Boot 3.3.x application entrypoint.
 * Starts the embedded server and registers controllers.
 */
@SpringBootApplication
public class Java21BackendApplication {

    // PUBLIC_INTERFACE
    public static void main(String[] args) {
        /** Application entrypoint starting Spring Boot. */
        SpringApplication.run(Java21BackendApplication.class, args);
    }
}
