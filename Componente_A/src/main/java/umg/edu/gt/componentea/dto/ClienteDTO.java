package umg.edu.gt.componentea.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @NotBlank(message = "El NIT es obligatorio")
    private String nit;
    
    @Email(message = "Email inválido")
    private String email;
    
    private String telefono;
    private String direccion;
}
