package umg.edu.gt.componenteb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorResponseDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private String nit;
    private String email;
    private String telefono;
    private String direccion;
    private String categoria;
    private Boolean activo;
}
