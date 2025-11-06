package umg.edu.gt.componenteb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import umg.edu.gt.componenteb.client.ClienteFeignClient;
import umg.edu.gt.componenteb.client.ProveedorFeignClient;
import umg.edu.gt.componenteb.dto.ClienteResponseDTO;
import umg.edu.gt.componenteb.dto.ProductoDTO;
import umg.edu.gt.componenteb.dto.ProveedorResponseDTO;
import umg.edu.gt.logistica.models.Producto;
import umg.edu.gt.logistica.utils.CalculosLogistica;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Servicio de Logística que integra el Componente C y consume el Componente A mediante Feign Client
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LogisticaService {
    
    private final CalculosLogistica calculosLogistica = new CalculosLogistica();
    private final ClienteFeignClient clienteFeignClient;
    private final ProveedorFeignClient proveedorFeignClient;
    
    /**
     * Calcula el total de una lista de productos usando el Componente C
     */
    public double calcularTotalProductos(List<ProductoDTO> productosDTO) {
        log.info("Calculando total para {} productos", productosDTO.size());
        
        List<Producto> productos = productosDTO.stream()
            .map(dto -> new Producto(dto.getNombre(), dto.getPrecioUnitario(), dto.getCantidad()))
            .collect(Collectors.toList());
        
        double total = calculosLogistica.calcularTotal(productos);
        log.info("Total calculado: {}", total);
        
        return total;
    }
    
    /**
     * Genera un código único para una entidad usando el Componente C
     */
    public String generarCodigoUnico(String tipoEntidad) {
        log.info("Generando código único para: {}", tipoEntidad);
        String codigo = calculosLogistica.generarCodigoUnico(tipoEntidad);
        log.info("Código generado: {}", codigo);
        return codigo;
    }
    
    /**
     * Valida la existencia de un cliente llamando al Componente A
     * Implementa el flujo circular usando el Componente C
     */
    public Map<String, Object> validarClienteRemoto(String codigo) {
        log.info("Validando cliente con código: {}", codigo);
        
        try {
            // Usa Feign Client para consumir el Componente A
            Map<String, Object> resultado = clienteFeignClient.validarExistenciaCliente(codigo);
            log.info("Validación exitosa: {}", resultado);
            return resultado;
        } catch (Exception e) {
            log.error("Error al validar cliente: {}", e.getMessage());
            throw new RuntimeException("Error al validar cliente: " + e.getMessage());
        }
    }
    
    /**
     * Valida la existencia de un proveedor llamando al Componente A
     */
    public Map<String, Object> validarProveedorRemoto(String codigo) {
        log.info("Validando proveedor con código: {}", codigo);
        
        try {
            Map<String, Object> resultado = proveedorFeignClient.validarExistenciaProveedor(codigo);
            log.info("Validación exitosa: {}", resultado);
            return resultado;
        } catch (Exception e) {
            log.error("Error al validar proveedor: {}", e.getMessage());
            throw new RuntimeException("Error al validar proveedor: " + e.getMessage());
        }
    }
    
    /**
     * Obtiene todos los clientes del Componente A usando Feign
     */
    public List<ClienteResponseDTO> obtenerTodosLosClientes() {
        log.info("Obteniendo todos los clientes del Componente A");
        return clienteFeignClient.obtenerTodosLosClientes();
    }
    
    /**
     * Obtiene todos los proveedores del Componente A usando Feign
     */
    public List<ProveedorResponseDTO> obtenerTodosLosProveedores() {
        log.info("Obteniendo todos los proveedores del Componente A");
        return proveedorFeignClient.obtenerTodosLosProveedores();
    }
    
    /**
     * Obtiene un cliente por código usando Feign
     */
    public ClienteResponseDTO obtenerClientePorCodigo(String codigo) {
        log.info("Obteniendo cliente con código: {}", codigo);
        return clienteFeignClient.obtenerClientePorCodigo(codigo);
    }
    
    /**
     * Obtiene un proveedor por código usando Feign
     */
    public ProveedorResponseDTO obtenerProveedorPorCodigo(String codigo) {
        log.info("Obteniendo proveedor con código: {}", codigo);
        return proveedorFeignClient.obtenerProveedorPorCodigo(codigo);
    }
    
    /**
     * Implementa el flujo circular completo usando el método del Componente C
     */
    public String flujoCircularIntegracion(String idEntidad, String urlBase) {
        log.info("Ejecutando flujo circular para entidad: {}", idEntidad);
        String resultado = calculosLogistica.invocarValidacionRemota(idEntidad, urlBase);
        log.info("Resultado del flujo circular: {}", resultado);
        return resultado;
    }
}
