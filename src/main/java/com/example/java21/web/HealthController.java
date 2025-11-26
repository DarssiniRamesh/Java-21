package com.example.java21.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;

/**
 * PUBLIC_INTERFACE
 * HealthController exposes a simple health endpoint.
 */
@RestController
@Tag(name = "Health", description = "Health check endpoints")
public class HealthController {

    // PUBLIC_INTERFACE
    @Operation(
        summary = "Health check",
        description = "Returns a simple status payload indicating the service is healthy."
    )
    @GetMapping(path = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> health() {
        /** Returns {"status":"ok"} for simple liveness checks. */
        return ResponseEntity.ok(Map.of("status", "ok"));
    }
}
