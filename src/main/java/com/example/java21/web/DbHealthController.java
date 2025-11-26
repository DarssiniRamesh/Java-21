package com.example.java21.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * PUBLIC_INTERFACE
 * Controller providing an explicit DB connectivity check.
 */
@RestController
@Tag(name = "Health", description = "Health check endpoints")
public class DbHealthController {

    private final JdbcTemplate jdbcTemplate;

    public DbHealthController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "Database ping", description = "Executes a simple query to verify DB connectivity.")
    @GetMapping(path = "/health/db")
    public ResponseEntity<Map<String, Object>> dbHealth() {
        Integer one = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        return ResponseEntity.ok(Map.of("database", "UP", "result", one));
    }
}
