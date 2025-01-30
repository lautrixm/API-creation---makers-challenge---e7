package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic",
        in = SecuritySchemeIn.HEADER
)

public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        Server localhostServer = new Server()
                .url("http://localhost:8080")
                .description("Local server for development");

        Server prodServer = new Server()
                .url("https://api.com")
                .description("Production server");

        Contact contact = new Contact()
                .name("Lautaro")
                .email("lautimartinez17@gmail.com")
                .url("https://github.com/lautrixm");


        Info info = new Info()
                .title("Exercise 7 of makers-challenge API Documentation")
                .version("1.0.0")
                .description("This api is the solution to the problem of exercise 7 of makers-challenge.")
                .termsOfService("https://www.mercadolibre.com.ar/ayuda/terminos-y-condiciones_299")
                .contact(contact);

        return new OpenAPI()
                .info(info)
                .servers(List.of(localhostServer, prodServer));
    }
}
