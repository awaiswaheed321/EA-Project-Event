package edu.miu.cs544.awais.EventManagementService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@OpenAPIDefinition(info = @Info(
        title = "${spring.application.name}",
        description = "${app.description}",
        version = "${app.version}",
        contact = @Contact(name = "Awais Waheed", email = "awaheed@miu.edu")
))
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class EventManagementServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventManagementServiceApplication.class, args);
    }
}
