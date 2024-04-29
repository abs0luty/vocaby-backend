package org.vocaby.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "Vocaby",
            url = "https://vocaby.org"
        ),
        description = "OpenApi documentation for Vocaby's main backend API",
        title = "OpenApi specification - Vocaby",
        version = "1.0"
    ),
    security = @SecurityRequirement(name = "accessToken"),
    servers = {
        @Server(description = "Local environment", url = "http://localhost"),
        @Server(description = "Production environment", url = "https://vocaby.org")
    }
)
@SecurityScheme(
    name = "accessToken",
    type = SecuritySchemeType.HTTP
)
public class OpenApiConfiguration {
}
