package bcp.cuenta.recaudadora.Repository.secondary;

import bcp.cuenta.recaudadora.Entity.secondary.Recaudacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecaudacionDAO extends JpaRepository<Recaudacion, Recaudacion.PK> {
}
