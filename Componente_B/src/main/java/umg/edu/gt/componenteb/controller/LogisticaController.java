package umg.edu.gt.componenteb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umg.edu.gt.componenteb.dto.CalculoTotalRequestDTO;
import umg.edu.gt.componenteb.dto.ClienteResponseDTO;
import umg.edu.gt.componenteb.dto.ProveedorResponseDTO;
import umg.edu.gt.componenteb.service.LogisticaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/logistica")
@RequiredArgsConstructor
@Tag(name = "Logística", description = "API de Logística - Integra Componente C y consume Componente A mediante Feign Client")
public class LogisticaController {
    
    private final LogisticaService logisticaService;
    
    @Operation(
        summary = "Calcular total de productos",
        description = "Calcula el total de una lista de productos con IVA incluido (12%) usando el Componente C"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cálculo realizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PostMapping("/calcular-total")
    public ResponseEntity<Map<String, Object>> calcularTotal(
            @Valid @RequestBody CalculoTotalRequestDTO request) {
        
        double total = logisticaService.calcularTotalProductos(request.getProductos());
        
        Map<String, Object> response = new HashMap<>();
        response.put("productos", request.getProductos());
        response.put("totalConIVA", total);
        response.put("mensaje", "Cálculo realizado usando Componente C");
        
        return ResponseEntity.ok(response);
    }
    
    @Operation(
        summary = "Generar código único",
        description = "Genera un código único para una entidad usando el Componente C"
    )
    @ApiResponse(responseCode = "200", description = "Código generado exitosamente")
    @PostMapping("/generar-codigo/{tipoEntidad}")
    public ResponseEntity<Map<String, String>> generarCodigo(
            @Parameter(description = "Tipo de entidad (ej: CLIENTE, PROVEEDOR, ORDEN)")
            @PathVariable String tipoEntidad) {
        
        String codigo = logisticaService.generarCodigoUnico(tipoEntidad);
        
        Map<String, String> response = new HashMap<>();
        response.put("tipoEntidad", tipoEntidad);
        response.put("codigo", codigo);
        response.put("mensaje", "Código generado usando Componente C");
        
        return ResponseEntity.ok(response);
    }
    
    @Operation(
        summary = "Validar cliente (Integración con Componente A)",
        description = "Valida la existencia de un cliente consumiendo el Componente A mediante Feign Client"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Validación exitosa"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error de comunicación", content = @Content)
    })
    @GetMapping("/validar-cliente/{codigo}")
    public ResponseEntity<Map<String, Object>> validarCliente(
            @Parameter(description = "Código del cliente a validar")
            @PathVariable String codigo) {
        
        Map<String, Object> resultado = logisticaService.validarClienteRemoto(codigo);
        resultado.put("componenteOrigen", "Componente A - via Feign Client");
        
        return ResponseEntity.ok(resultado);
    }
    
    @Operation(
        summary = "Validar proveedor (Integración con Componente A)",
        description = "Valida la existencia de un proveedor consumiendo el Componente A mediante Feign Client"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Validación exitosa"),
        @ApiResponse(responseCode = "404", description = "Proveedor no encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error de comunicación", content = @Content)
    })
    @GetMapping("/validar-proveedor/{codigo}")
    public ResponseEntity<Map<String, Object>> validarProveedor(
            @Parameter(description = "Código del proveedor a validar")
            @PathVariable String codigo) {
        
        Map<String, Object> resultado = logisticaService.validarProveedorRemoto(codigo);
        resultado.put("componenteOrigen", "Componente A - via Feign Client");
        
        return ResponseEntity.ok(resultado);
    }
    
    @Operation(
        summary = "Obtener todos los clientes",
        description = "Obtiene todos los clientes del Componente A usando Feign Client"
    )
    @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida exitosamente")
    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteResponseDTO>> obtenerClientes() {
        return ResponseEntity.ok(logisticaService.obtenerTodosLosClientes());
    }
    
    @Operation(
        summary = "Obtener todos los proveedores",
        description = "Obtiene todos los proveedores del Componente A usando Feign Client"
    )
    @ApiResponse(responseCode = "200", description = "Lista de proveedores obtenida exitosamente")
    @GetMapping("/proveedores")
    public ResponseEntity<List<ProveedorResponseDTO>> obtenerProveedores() {
        return ResponseEntity.ok(logisticaService.obtenerTodosLosProveedores());
    }
    
    @Operation(
        summary = "Obtener cliente por código",
        description = "Obtiene un cliente específico del Componente A por su código usando Feign Client"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
    })
    @GetMapping("/clientes/{codigo}")
    public ResponseEntity<ClienteResponseDTO> obtenerClientePorCodigo(
            @Parameter(description = "Código del cliente")
            @PathVariable String codigo) {
        return ResponseEntity.ok(logisticaService.obtenerClientePorCodigo(codigo));
    }
    
    @Operation(
        summary = "Obtener proveedor por código",
        description = "Obtiene un proveedor específico del Componente A por su código usando Feign Client"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proveedor encontrado"),
        @ApiResponse(responseCode = "404", description = "Proveedor no encontrado", content = @Content)
    })
    @GetMapping("/proveedores/{codigo}")
    public ResponseEntity<ProveedorResponseDTO> obtenerProveedorPorCodigo(
            @Parameter(description = "Código del proveedor")
            @PathVariable String codigo) {
        return ResponseEntity.ok(logisticaService.obtenerProveedorPorCodigo(codigo));
    }
    
    @Operation(
        summary = "Flujo circular de integración",
        description = "Demuestra el flujo circular completo usando el método invocarValidacionRemota del Componente C"
    )
    @ApiResponse(responseCode = "200", description = "Flujo ejecutado exitosamente")
    @GetMapping("/flujo-circular")
    public ResponseEntity<Map<String, String>> flujoCircular(
            @Parameter(description = "ID de la entidad a validar")
            @RequestParam String idEntidad,
            @Parameter(description = "URL base del componente (ej: http://localhost:8081)")
            @RequestParam(defaultValue = "http://localhost:8081") String urlBase) {
        
        String resultado = logisticaService.flujoCircularIntegracion(idEntidad, urlBase);
        
        Map<String, String> response = new HashMap<>();
        response.put("idEntidad", idEntidad);
        response.put("urlBase", urlBase);
        response.put("resultado", resultado);
        response.put("descripcion", "Flujo circular ejecutado usando Componente C (método invocarValidacionRemota)");
        
        return ResponseEntity.ok(response);
    }
}
