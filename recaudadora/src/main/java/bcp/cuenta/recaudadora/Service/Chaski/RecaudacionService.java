package bcp.cuenta.recaudadora.Service.Chaski;

import bcp.cuenta.recaudadora.Entity.secondary.Recaudacion;
import bcp.cuenta.recaudadora.Repository.secondary.RecaudacionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecaudacionService {
    @Autowired
    private RecaudacionDAO recaudacionDAO;

    public void saveRecaudacion(Recaudacion recaudacion){
        this.recaudacionDAO.save(recaudacion);
    }

    public void deleteRecaudacion(Recaudacion.PK pk){
        this.recaudacionDAO.deleteById(pk);
    }
}
