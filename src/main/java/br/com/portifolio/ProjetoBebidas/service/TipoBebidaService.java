package br.com.portifolio.ProjetoBebidas.service;


import br.com.portifolio.ProjetoBebidas.model.dto.TipoBebidaDTO;
import br.com.portifolio.ProjetoBebidas.model.entities.TipoBebidaEntity;
import br.com.portifolio.ProjetoBebidas.repository.TipoBebidaRepository;
import br.com.portifolio.ProjetoBebidas.service.exceptions.DataBaseException;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoBebidaService {

    @Autowired
    private TipoBebidaRepository repository;

    @Autowired
    private TipoBebidaService tipoPedidoService;

    public TipoBebidaEntity insert(TipoBebidaDTO TipoBebidaDto){

        TipoBebidaEntity TipoBebida = instanciarTipoBebidaEntity(TipoBebidaDto);
        repository.save(TipoBebida);
        return TipoBebida;
    }

    private TipoBebidaEntity instanciarTipoBebidaEntity(TipoBebidaDTO tipoBebidaDto) {
        return new TipoBebidaEntity(tipoBebidaDto.getId(),
                   tipoBebidaDto.getDescricao(),
                   tipoBebidaDto.getCapacidade());
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

    public TipoBebidaEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<TipoBebidaEntity> findAll() {
        return repository.findAll();
    }
}
