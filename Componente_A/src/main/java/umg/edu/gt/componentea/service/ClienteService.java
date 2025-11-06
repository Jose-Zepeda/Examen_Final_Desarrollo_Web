package umg.edu.gt.componentea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umg.edu.gt.componentea.dto.ClienteDTO;
import umg.edu.gt.componentea.model.Cliente;
import umg.edu.gt.componentea.repository.ClienteRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    
    @Transactional
    public Cliente crearCliente(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setCodigo(generarCodigo("CLI"));
        cliente.setNombre(dto.getNombre());
        cliente.setNit(dto.getNit());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());
        cliente.setActivo(true);
        
        return clienteRepository.save(cliente);
    }
    
    @Transactional(readOnly = true)
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Cliente obtenerPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }
    
    @Transactional(readOnly = true)
    public Cliente obtenerPorCodigo(String codigo) {
        return clienteRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con código: " + codigo));
    }
    
    @Transactional(readOnly = true)
    public boolean existePorCodigo(String codigo) {
        return clienteRepository.existsByCodigo(codigo);
    }
    
    @Transactional
    public Cliente actualizarCliente(Long id, ClienteDTO dto) {
        Cliente cliente = obtenerPorId(id);
        cliente.setNombre(dto.getNombre());
        cliente.setNit(dto.getNit());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());
        
        return clienteRepository.save(cliente);
    }
    
    @Transactional
    public void eliminarCliente(Long id) {
        Cliente cliente = obtenerPorId(id);
        clienteRepository.delete(cliente);
    }
    
    private String generarCodigo(String prefijo) {
        return prefijo + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
