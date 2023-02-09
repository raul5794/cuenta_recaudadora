package bcp.cuenta.recaudadora.Repository.secondary;

import bcp.cuenta.recaudadora.Entity.secondary.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnexoDAO extends JpaRepository<Anexo,String> {
}
