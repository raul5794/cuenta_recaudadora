package bcp.cuenta.recaudadora.Service.Chaski;

import bcp.cuenta.recaudadora.Entity.secondary.Volante;
import bcp.cuenta.recaudadora.Repository.secondary.VolanteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VolanteService {

    @Autowired
    VolanteDAO volanteDAO;

    public Optional<Volante> getVolante(String id){
        return this.volanteDAO.findById(id);
    }

}
