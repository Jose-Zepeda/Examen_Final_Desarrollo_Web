package umg.edu.gt.componenteb;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
    info = @Info(
        title = "Componente B - API de Logística",
        version = "1.0.0",
        description = "API REST que integra servicios de logística usando Componente C y consume Componente A mediante Feign Client",
        contact = @Contact(
            name = "UMG - Examen Final",
            email = "contacto@umg.edu.gt"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8082", description = "Servidor Local")
    }
)
public class ComponenteBApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComponenteBApplication.class, args);
    }
}
