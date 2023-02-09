package bcp.cuenta.recaudadora.Repository.primary;

import bcp.cuenta.recaudadora.Entity.primary.Generic.GenericResponse;
import bcp.cuenta.recaudadora.Entity.primary.Generic.PrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ResponseDAO<T extends GenericResponse> extends JpaRepository<T, PrimaryKey> {


}
