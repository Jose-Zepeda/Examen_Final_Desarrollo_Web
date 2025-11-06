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
import umg.edu.gt.componentea.dto.ProveedorDTO;
import umg.edu.gt.componentea.model.Proveedor;
import umg.edu.gt.componentea.service.ProveedorService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
@Tag(name = "Proveedores", description = "API para gestión de proveedores")
public class ProveedorController {
    
    private final ProveedorService proveedorService;
    
    @Operation(summary = "Crear un nuevo proveedor", description = "Crea un nuevo proveedor con código autogenerado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Proveedor creado exitosamente",
                content = @Content(schema = @Schema(implementation = Proveedor.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Proveedor> crearProveedor(@Valid @RequestBody ProveedorDTO dto) {
        Proveedor proveedor = proveedorService.crearProveedor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedor);
    }
    
    @Operation(summary = "Obtener todos los proveedores", description = "Retorna una lista de todos los proveedores registrados")
    @ApiResponse(responseCode = "200", description = "Lista de proveedores obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<Proveedor>> obtenerTodos() {
        return ResponseEntity.ok(proveedorService.obtenerTodos());
    }
    
    @Operation(summary = "Obtener proveedor por ID", description = "Retorna un proveedor específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proveedor encontrado",
                content = @Content(schema = @Schema(implementation = Proveedor.class))),
        @ApiResponse(responseCode = "404", description = "Proveedor no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerPorId(
            @Parameter(description = "ID del proveedor") @PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.obtenerPorId(id));
    }
    
    @Operation(summary = "Obtener proveedor por código", description = "Retorna un proveedor específico por su código único")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proveedor encontrado",
                content = @Content(schema = @Schema(implementation = Proveedor.class))),
        @ApiResponse(responseCode = "404", description = "Proveedor no encontrado", content = @Content)
    })
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Proveedor> obtenerPorCodigo(
            @Parameter(description = "Código único del proveedor") @PathVariable String codigo) {
        return ResponseEntity.ok(proveedorService.obtenerPorCodigo(codigo));
    }
    
    @Operation(summary = "Verificar existencia de proveedor", description = "Verifica si existe un proveedor con el código especificado")
    @ApiResponse(responseCode = "200", description = "Resultado de la verificación")
    @GetMapping("/validacion/existe/{codigo}")
    public ResponseEntity<Map<String, Object>> existeProveedor(
            @Parameter(description = "Código del proveedor a verificar") @PathVariable String codigo) {
        boolean existe = proveedorService.existePorCodigo(codigo);
        return ResponseEntity.ok(Map.of(
            "codigo", codigo,
            "existe", existe,
            "mensaje", existe ? "Proveedor encontrado" : "Proveedor no encontrado"
        ));
    }
    
    @Operation(summary = "Actualizar proveedor", description = "Actualiza los datos de un proveedor existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proveedor actualizado exitosamente",
                content = @Content(schema = @Schema(implementation = Proveedor.class))),
        @ApiResponse(responseCode = "404", description = "Proveedor no encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(
            @Parameter(description = "ID del proveedor") @PathVariable Long id,
            @Valid @RequestBody ProveedorDTO dto) {
        return ResponseEntity.ok(proveedorService.actualizarProveedor(id, dto));
    }
    
    @Operation(summary = "Eliminar proveedor", description = "Elimina un proveedor del sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Proveedor eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Proveedor no encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(
            @Parameter(description = "ID del proveedor") @PathVariable Long id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}
