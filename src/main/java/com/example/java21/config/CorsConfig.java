package com.example.java21.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * PUBLIC_INTERFACE
 * Conditionally configures permissive global CORS based on a configuration flag.
 *
 * Behavior:
 * - When app.cors.allow-all (env: ALLOW_ALL_CORS) is true, registers a global CORS mapping that allows:
 *   any origin (allowedOriginPatterns="*"), any method, any header, allowCredentials=false, maxAge=3600.
 * - When false, no global CORS mapping is registered here; use Spring/Security configs or proxy to handle CORS.
 *
 * Configuration:
 * - Property: app.cors.allow-all
 * - Env var: ALLOW_ALL_CORS (default true). Set ALLOW_ALL_CORS=false to disable global allow-all CORS.
 *
 * Note: For production, consider disabling allow-all and explicitly configure allowed origins/headers/methods.
 */
@Configuration
public class CorsConfig {

    @Value("${app.cors.allow-all:true}")
    private boolean allowAllCors;

    // PUBLIC_INTERFACE
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        /**
         * Returns a WebMvcConfigurer that conditionally adds CORS mappings depending on allowAllCors.
         * If disabled, this bean still exists but does not add any mappings, leaving CORS handling up to other layers.
         */
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                if (allowAllCors) {
                    registry.addMapping("/**")
                            .allowedOriginPatterns("*")
                            .allowedMethods("*")
                            .allowedHeaders("*")
                            .allowCredentials(false)
                            .maxAge(3600);
                }
            }
        };
    }
}
