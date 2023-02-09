package bcp.cuenta.recaudadora.Service;

import bcp.cuenta.recaudadora.Entity.primary.Generic.GenericRequest;
import bcp.cuenta.recaudadora.Entity.primary.Generic.PrimaryKey;
import bcp.cuenta.recaudadora.Repository.primary.RequestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    public RequestDAO requestDAO;

    public <T extends GenericRequest> Optional  getRequest(PrimaryKey pk){
      return this.requestDAO.findById(pk);
    }

    public <T extends GenericRequest> void saveRequest(T t){
          this.requestDAO.save(t);
    }
}
