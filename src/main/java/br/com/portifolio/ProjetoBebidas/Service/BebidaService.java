package br.com.portifolio.ProjetoBebidas.Service;

import br.com.portifolio.ProjetoBebidas.Model.entities.Bebida;
import br.com.portifolio.ProjetoBebidas.Repository.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class BebidaService {


    @Autowired
    private BebidaRepository repository;

    public Bebida insert(Bebida bebida){
        repository.save(bebida);
        return bebida;
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new RuntimeException();

            //throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){ //Exceção da minha camada de serviços.
            throw new RuntimeException();
            //throw new DataBaseException(e.getMessage());
        }
    }


    public Bebida findById(Long id) {
        //return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

}
