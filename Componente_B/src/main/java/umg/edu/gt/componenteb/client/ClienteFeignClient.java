package umg.edu.gt.componenteb.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import umg.edu.gt.componenteb.dto.ClienteResponseDTO;

import java.util.List;
import java.util.Map;

/**
 * Feign Client para consumir la API de Clientes del Componente A
 */
@FeignClient(
    name = "cliente-service",
    url = "${componente-a.url}",
    path = "/api/clientes"
)
public interface ClienteFeignClient {
    
    @GetMapping
    List<ClienteResponseDTO> obtenerTodosLosClientes();
    
    @GetMapping("/{id}")
    ClienteResponseDTO obtenerClientePorId(@PathVariable("id") Long id);
    
    @GetMapping("/codigo/{codigo}")
    ClienteResponseDTO obtenerClientePorCodigo(@PathVariable("codigo") String codigo);
    
    @GetMapping("/validacion/existe/{codigo}")
    Map<String, Object> validarExistenciaCliente(@PathVariable("codigo") String codigo);
}
