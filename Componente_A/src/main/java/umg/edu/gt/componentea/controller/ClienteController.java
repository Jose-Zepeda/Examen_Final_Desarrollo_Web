package umg.edu.gt.componentea.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umg.edu.gt.componentea.dto.ClienteDTO;
import umg.edu.gt.componentea.model.Cliente;
import umg.edu.gt.componentea.service.ClienteService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "API para gestión de clientes")
public class ClienteController {
    
    private final ClienteService clienteService;
    
    @Operation(summary = "Crear un nuevo cliente", description = "Crea un nuevo cliente con código autogenerado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente",
                content = @Content(schema = @Schema(implementation = Cliente.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody ClienteDTO dto) {
        Cliente cliente = clienteService.crearCliente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }
    
    @Operation(summary = "Obtener todos los clientes", description = "Retorna una lista de todos los clientes registrados")
    @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodos() {
        return ResponseEntity.ok(clienteService.obtenerTodos());
    }
    
    @Operation(summary = "Obtener cliente por ID", description = "Retorna un cliente específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                content = @Content(schema = @Schema(implementation = Cliente.class))),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerPorId(
            @Parameter(description = "ID del cliente") @PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerPorId(id));
    }
    
    @Operation(summary = "Obtener cliente por código", description = "Retorna un cliente específico por su código único")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                content = @Content(schema = @Schema(implementation = Cliente.class))),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
    })
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Cliente> obtenerPorCodigo(
            @Parameter(description = "Código único del cliente") @PathVariable String codigo) {
        return ResponseEntity.ok(clienteService.obtenerPorCodigo(codigo));
    }
    
    @Operation(summary = "Verificar existencia de cliente", description = "Verifica si existe un cliente con el código especificado")
    @ApiResponse(responseCode = "200", description = "Resultado de la verificación")
    @GetMapping("/validacion/existe/{codigo}")
    public ResponseEntity<Map<String, Object>> existeCliente(
            @Parameter(description = "Código del cliente a verificar") @PathVariable String codigo) {
        boolean existe = clienteService.existePorCodigo(codigo);
        return ResponseEntity.ok(Map.of(
            "codigo", codigo,
            "existe", existe,
            "mensaje", existe ? "Cliente encontrado" : "Cliente no encontrado"
        ));
    }
    
    @Operation(summary = "Actualizar cliente", description = "Actualiza los datos de un cliente existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente",
                content = @Content(schema = @Schema(implementation = Cliente.class))),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(
            @Parameter(description = "ID del cliente") @PathVariable Long id,
            @Valid @RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, dto));
    }
    
    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente del sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(
            @Parameter(description = "ID del cliente") @PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
