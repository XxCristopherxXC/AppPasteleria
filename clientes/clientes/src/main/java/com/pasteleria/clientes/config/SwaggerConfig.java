package com.pasteleria.clientes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API 2025 Clientes de la Pasteleria Mil Sabores")
                        .version("1.0")
                        .description("Documentación de la API para el sistema de gestión de clientes de la Pasteleria Mil Sabores"));
    }
}
