package com.example.java21.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * PUBLIC_INTERFACE
 * OpenAPI configuration:
 * - Populates metadata.
 * - Configures Servers so Swagger UI generates HTTPS URLs and correct host/port.
 * - Derives the scheme/host/port from X-Forwarded-* headers when available, with a fixed
 *   preview fallback domain to prevent mixed-content in the preview environment.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Optional explicit preview URL for the environment. If not provided, a default is used.
     * This is used as a fallback server when a request context isn't available (e.g., on app start)
     * to ensure Swagger UI doesn't emit http://localhost links in hosted previews.
     */
    @Value("${app.preview.base-url:https://vscode-internal-25043-beta.beta01.cloud.kavia.ai:3003}")
    private String previewBaseUrl;

    // PUBLIC_INTERFACE
    @Bean
    public OpenAPI customOpenAPI() {
        /** Configure basic API metadata and server list. */
        OpenAPI api = new OpenAPI()
            .info(new Info()
                .title("Java 21 Backend API")
                .description("Spring Boot 3.3.x API with OpenAPI, CRUD for Questions/Answers, and health endpoints")
                .version("v0.0.1")
                .contact(new Contact().name("API Support"))
            )
            .externalDocs(new ExternalDocumentation()
                .description("Swagger UI")
                .url("/docs")
            );

        // Configure servers: try to build from current request (X-Forwarded-*), else use previewBaseUrl.
        List<Server> servers = new ArrayList<>();
        String derived = deriveServerUrlFromRequest();
        if (derived != null && !derived.isBlank()) {
            servers.add(new Server().url(derived).description("Derived from reverse proxy (X-Forwarded-*)"));
        }
        // Ensure a deterministic https server exists to avoid mixed content in previews.
        servers.add(new Server().url(previewBaseUrl).description("Preview environment (HTTPS)"));
        api.setServers(servers);

        return api;
    }

    /**
     * Attempt to derive an absolute server URL from the current HTTP request,
     * honoring standard reverse proxy headers.
     */
    private String deriveServerUrlFromRequest() {
        try {
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs == null) {
                return null;
            }
            HttpServletRequest req = attrs.getRequest();
            if (req == null) {
                return null;
            }
            // Standard headers used by proxies/load balancers
            String forwardedProto = headerOrNull(req, "X-Forwarded-Proto");
            String forwardedHost = headerOrNull(req, "X-Forwarded-Host");
            String forwardedPort = headerOrNull(req, "X-Forwarded-Port");
            String hostHeader = headerOrNull(req, "Host");

            String scheme = forwardedProto != null ? forwardedProto : req.getScheme();
            String hostPort = forwardedHost != null ? forwardedHost : hostHeader;

            // If a separate forwarded port is provided and not already in host header, append it.
            if (hostPort != null && forwardedPort != null && !hostPort.contains(":")) {
                hostPort = hostPort + ":" + forwardedPort;
            }

            if (scheme == null || hostPort == null) {
                return null;
            }
            // Always use root base path; app does not use a servlet context path.
            return scheme + "://" + hostPort;
        } catch (Exception ignored) {
            return null;
        }
    }

    private static String headerOrNull(HttpServletRequest req, String name) {
        String v = req.getHeader(name);
        return (v == null || v.isBlank()) ? null : v;
        }
}
