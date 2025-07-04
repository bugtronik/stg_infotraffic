package com.setrag.stg_infotraffic_api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement; // Importez cette annotation
import io.swagger.v3.oas.annotations.security.SecurityScheme; // Importez cette annotation
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration OpenAPI pour générer la documentation Swagger UI avec les informations sur la sécurité JWT.
 */
@Configuration
@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "Aly MBOUMBA - Infotraffic API Support",
            email = "aly.mboumba@setrag.com"
            //url = "https://setrag.ga"
        ),
        description = "Documentation OpenAPI pour l'API Infotraffic.",
        title = "Infotraffic API - Documentation",
        version = "1.0",
        license = @License(
            name = "Licence API Infotraffic"
            //url = "https://www.infotraffic.com/license"
        )
        //termsOfService = "https://www.infotraffic.com/terms"
    ),
    servers = {
        @Server(
            description = "Environnement de développement local",
            url = "http://localhost:8080"
        ),
        /*
         * @Server(
            description = "Environnement de production (exemple)",
            url = "https://infotraffic.com/api"
        )
         */
    },
    // Déclare un SecurityRequirement global qui s'appliquera à toutes les opérations
    // Sauf si elles sont explicitement ignorées ou ont leur propre SecurityRequirement.
    security = @SecurityRequirement(name = "Bearer Authentication") // 'Bearer Authentication' doit correspondre au nom du SecurityScheme
)
@SecurityScheme( // Définit le schéma de sécurité "Bearer Authentication"
    name = "Bearer Authentication", // Nom de référence pour ce schéma de sécurité
    description = "Authentification JWT avec token Bearer",
    type = SecuritySchemeType.HTTP, // Le type de schéma est HTTP (comme Basic, Digest, etc.)
    bearerFormat = "JWT", // Le format du token est JWT
    scheme = "bearer" // Le schéma HTTP est "bearer" (ex: Authorization: Bearer <token>)
)
public class OpenApiConfig {
    // Cette classe ne nécessite pas de corps de méthode car les configurations sont faites via les annotations.
}
