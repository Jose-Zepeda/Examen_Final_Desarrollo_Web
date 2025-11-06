package umg.edu.gt.componenteb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculoTotalRequestDTO {
    @NotEmpty(message = "La lista de productos no puede estar vacía")
    @Valid
    private List<ProductoDTO> productos;
}
