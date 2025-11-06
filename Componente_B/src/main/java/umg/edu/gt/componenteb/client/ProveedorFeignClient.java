package umg.edu.gt.componenteb.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import umg.edu.gt.componenteb.dto.ProveedorResponseDTO;

import java.util.List;
import java.util.Map;

/**
 * Feign Client para consumir la API de Proveedores del Componente A
 */
@FeignClient(
    name = "proveedor-service",
    url = "${componente-a.url}",
    path = "/api/proveedores"
)
public interface ProveedorFeignClient {
    
    @GetMapping
    List<ProveedorResponseDTO> obtenerTodosLosProveedores();
    
    @GetMapping("/{id}")
    ProveedorResponseDTO obtenerProveedorPorId(@PathVariable("id") Long id);
    
    @GetMapping("/codigo/{codigo}")
    ProveedorResponseDTO obtenerProveedorPorCodigo(@PathVariable("codigo") String codigo);
    
    @GetMapping("/validacion/existe/{codigo}")
    Map<String, Object> validarExistenciaProveedor(@PathVariable("codigo") String codigo);
}
