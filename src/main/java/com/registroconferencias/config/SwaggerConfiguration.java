package com.registroconferencias.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Plataforma de Registro para Conferencias Online.",
                summary = "Prueba inicial del booster de Metaphorce.",
                version = "SNAPSHOT - 0.0.1",
                contact = @Contact(
                        name = "Saúl Ramírez",
                        url = "https://www.linkedin.com/in/sayul-ramirez/"
                ))
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@Configuration
public class SwaggerConfiguration {
}
