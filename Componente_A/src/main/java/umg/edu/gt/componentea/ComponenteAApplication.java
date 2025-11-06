package umg.edu.gt.componentea;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Componente A - API de Gestión",
        version = "1.0.0",
        description = "API REST para gestión de clientes y proveedores",
        contact = @Contact(
            name = "UMG - Examen Final",
            email = "contacto@umg.edu.gt"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8081", description = "Servidor Local")
    }
)
public class ComponenteAApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComponenteAApplication.class, args);
    }
}
