package com.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
    title = "Sistema de Parqueadero API",
    version = "1.0",
    description = "API para gestión de vehículos en parqueadero"
))
public class ParkingSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParkingSystemApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Sistema de Parqueadero API")
                        .version("1.0")
                        .description("API para gestión de vehículos en parqueadero")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
} 