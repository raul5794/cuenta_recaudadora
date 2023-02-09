package bcp.cuenta.recaudadora.Repository.secondary;

import bcp.cuenta.recaudadora.Entity.secondary.Volante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolanteDAO extends JpaRepository<Volante,String> {
}
