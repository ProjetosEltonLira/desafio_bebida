package br.com.portifolio.ProjetoBebidas.service;

import br.com.portifolio.ProjetoBebidas.model.entities.BebidaEntity;
import br.com.portifolio.ProjetoBebidas.repository.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class BebidaService {


    @Autowired
    private BebidaRepository repository;

    public BebidaEntity insert(BebidaEntity bebidaEntity){
        repository.save(bebidaEntity);
        return bebidaEntity;
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


    public BebidaEntity findById(Long id) {
        //return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

}