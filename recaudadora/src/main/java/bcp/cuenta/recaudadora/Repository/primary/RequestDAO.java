package bcp.cuenta.recaudadora.Repository.primary;

import bcp.cuenta.recaudadora.Entity.primary.Generic.GenericRequest;
import bcp.cuenta.recaudadora.Entity.primary.Generic.PrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestDAO<T extends GenericRequest> extends JpaRepository<T, PrimaryKey>{


}
