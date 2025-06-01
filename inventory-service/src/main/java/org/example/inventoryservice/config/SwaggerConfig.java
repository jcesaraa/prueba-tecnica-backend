package org.example.inventoryservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Configura la información básica de la documentación OpenAPI/Swagger
        return new OpenAPI()
                .info(new Info()
                        .title("Inventory Service API")
                        .version("1.0")
                        .description("API para gestión de inventario"));
    }
}
