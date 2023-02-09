package bcp.cuenta.recaudadora.Service.Chaski;

import bcp.cuenta.recaudadora.Entity.secondary.Anexo;
import bcp.cuenta.recaudadora.Repository.secondary.AnexoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnexoService {

    @Autowired
    AnexoDAO anexoDAO;

    public Optional<Anexo> getAnexo(String id){
        return this.anexoDAO.findById(id);
    }
}
