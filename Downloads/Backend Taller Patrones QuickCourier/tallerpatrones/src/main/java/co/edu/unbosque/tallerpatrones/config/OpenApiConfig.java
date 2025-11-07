package co.edu.unbosque.tallerpatrones.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiTallerPatrones() {
        final String bearerScheme = "bearerAuth";
        final String apiKeyScheme = "apiKeyAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("API Taller de Patrones")
                        .description("Backend de pedidos con productos, extras de envío, usuarios y seguridad JWT.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Taller Patrones")
                                .email("soporte@tallerpatrones.com"))
                        .license(new License()
                                .name("Uso académico")
                                .url("https://www.example.com/licencia")))
                .components(new Components()
                        // JWT Bearer
                        .addSecuritySchemes(bearerScheme,
                                new SecurityScheme()
                                        .name(bearerScheme)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))
                        // API Key
                        .addSecuritySchemes(apiKeyScheme,
                                new SecurityScheme()
                                        .name("X-API-Key")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)))

                .addSecurityItem(new SecurityRequirement().addList(bearerScheme));
    }
}

