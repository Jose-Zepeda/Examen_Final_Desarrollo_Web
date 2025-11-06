package umg.edu.gt.componentea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umg.edu.gt.componentea.dto.ProveedorDTO;
import umg.edu.gt.componentea.model.Proveedor;
import umg.edu.gt.componentea.repository.ProveedorRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProveedorService {
    
    private final ProveedorRepository proveedorRepository;
    
    @Transactional
    public Proveedor crearProveedor(ProveedorDTO dto) {
        Proveedor proveedor = new Proveedor();
        proveedor.setCodigo(generarCodigo("PROV"));
        proveedor.setNombre(dto.getNombre());
        proveedor.setNit(dto.getNit());
        proveedor.setEmail(dto.getEmail());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setDireccion(dto.getDireccion());
        proveedor.setCategoria(dto.getCategoria());
        proveedor.setActivo(true);
        
        return proveedorRepository.save(proveedor);
    }
    
    @Transactional(readOnly = true)
    public List<Proveedor> obtenerTodos() {
        return proveedorRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Proveedor obtenerPorId(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
    }
    
    @Transactional(readOnly = true)
    public Proveedor obtenerPorCodigo(String codigo) {
        return proveedorRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con código: " + codigo));
    }
    
    @Transactional(readOnly = true)
    public boolean existePorCodigo(String codigo) {
        return proveedorRepository.existsByCodigo(codigo);
    }
    
    @Transactional
    public Proveedor actualizarProveedor(Long id, ProveedorDTO dto) {
        Proveedor proveedor = obtenerPorId(id);
        proveedor.setNombre(dto.getNombre());
        proveedor.setNit(dto.getNit());
        proveedor.setEmail(dto.getEmail());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setDireccion(dto.getDireccion());
        proveedor.setCategoria(dto.getCategoria());
        
        return proveedorRepository.save(proveedor);
    }
    
    @Transactional
    public void eliminarProveedor(Long id) {
        Proveedor proveedor = obtenerPorId(id);
        proveedorRepository.delete(proveedor);
    }
    
    private String generarCodigo(String prefijo) {
        return prefijo + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
