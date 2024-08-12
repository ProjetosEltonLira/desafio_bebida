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

    public TipoBebidaDTO inserir(TipoBebidaDTO TipoBebidaDto){

        TipoBebidaEntity TipoBebidaEntity = mapearTipoBebidaEntity(TipoBebidaDto);
        repository.save(TipoBebidaEntity);
        return mapearTipoBebidaDTO(TipoBebidaEntity);
    }

    private TipoBebidaEntity mapearTipoBebidaEntity(TipoBebidaDTO tipoBebidaDto) {
        return new TipoBebidaEntity(tipoBebidaDto.getId(),
                   tipoBebidaDto.getDescricao(),
                   tipoBebidaDto.getCapacidade());
    }

    private TipoBebidaDTO mapearTipoBebidaDTO(TipoBebidaEntity tipoBebidaEntity) {
        return new TipoBebidaDTO(tipoBebidaEntity.getId(),
                tipoBebidaEntity.getDescricao(),
                tipoBebidaEntity.getCapacidade());
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

    public TipoBebidaDTO pesquisarPorId(Long id) {
       TipoBebidaEntity tipoBebidaEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
       return mapearTipoBebidaDTO(tipoBebidaEntity);
    }

    public List<TipoBebidaEntity> findAll() {
        return repository.findAll();
    }

    public void update(Long id, TipoBebidaDTO tipoBebidaDTO) {
        if(repository.existsById(id)){
            TipoBebidaEntity tipoBebidaEntity = new TipoBebidaEntity(
                    tipoBebidaDTO.getId(),
                    tipoBebidaDTO.getDescricao(),
                    tipoBebidaDTO.getCapacidade());
            repository.save(tipoBebidaEntity);

        }else {
            throw new ResourceNotFoundException(tipoBebidaDTO.getId());
        }
    }
}
