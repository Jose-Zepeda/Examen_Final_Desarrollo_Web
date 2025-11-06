package umg.edu.gt.componentea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umg.edu.gt.componentea.model.Proveedor;

import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    Optional<Proveedor> findByCodigo(String codigo);
    Optional<Proveedor> findByNit(String nit);
    boolean existsByCodigo(String codigo);
}
