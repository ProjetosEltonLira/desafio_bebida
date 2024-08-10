package br.com.portifolio.ProjetoBebidas.service;

import br.com.portifolio.ProjetoBebidas.model.dto.BebidaDto;
import br.com.portifolio.ProjetoBebidas.model.entities.BebidaEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.TipoBebidaEntity;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ResourceNotFoundException;
import br.com.portifolio.ProjetoBebidas.repository.BebidaRepository;
import br.com.portifolio.ProjetoBebidas.service.exceptions.DataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BebidaService {


    @Autowired
    private BebidaRepository repository;

    @Autowired
    private TipoBebidaService tipoBebidaService;

    public BebidaEntity inserir(BebidaDto bebidaDto){

        TipoBebidaEntity tipoBebidaEntity = tipoBebidaService.findById(bebidaDto.getTipoBebidaId());
        BebidaEntity bebidaEntity = instanciarBebida(bebidaDto,tipoBebidaEntity);

        return repository.save(bebidaEntity);
    }

    private BebidaEntity instanciarBebida(BebidaDto bebidaDto,TipoBebidaEntity tipoBebidaEntity) {
        return new BebidaEntity(bebidaDto.getId(),
                bebidaDto.getNome(),
                tipoBebidaEntity);
    }
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){ //Exceção da minha camada de serviços.
            throw new DataBaseException(e.getMessage());
        }
    }

    public BebidaEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<BebidaEntity> findAll() {
        return repository.findAll();
    }
}
