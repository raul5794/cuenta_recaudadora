package bcp.cuenta.recaudadora.Service;

import bcp.cuenta.recaudadora.Entity.primary.Generic.GenericResponse;
import bcp.cuenta.recaudadora.Entity.primary.Generic.PrimaryKey;
import bcp.cuenta.recaudadora.Repository.primary.ResponseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponseService {

    @Autowired
    public ResponseDAO responseDAO;

    public <T extends GenericResponse> Optional getResponse(PrimaryKey pk){
        return this.responseDAO.findById(pk);
    }

    public <T extends GenericResponse> void saveResponse(T t){
        this.responseDAO.save(t);
    }


}
