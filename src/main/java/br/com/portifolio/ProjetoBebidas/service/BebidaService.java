package br.com.portifolio.ProjetoBebidas.service;

import br.com.portifolio.ProjetoBebidas.model.dto.request.BebidaDTO;
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

    public BebidaEntity inserir(BebidaDTO bebidaDto){

        TipoBebidaEntity tipoBebidaEntity = tipoBebidaService.findById(bebidaDto.getTipoBebidaId());
        BebidaEntity bebidaEntity = toBebidaEntity(bebidaDto,tipoBebidaEntity);

        return repository.save(bebidaEntity);
    }

    private BebidaEntity toBebidaEntity(BebidaDTO bebidaDto, TipoBebidaEntity tipoBebidaEntity) {
        return new BebidaEntity(
                bebidaDto.getId(),
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

    public void update(Long id, BebidaDTO bebidaDto) {
        // Verifica se a bebida com o ID fornecido existe. Se não, lança uma exceção.
        BebidaEntity bebidaExistente = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        // Busca o tipo de bebida associado, lançando exceção se o ID não existir
        TipoBebidaEntity tipoBebidaExistente = tipoBebidaService.findById(bebidaDto.getTipoBebidaId());

        // Atualiza a entidade existente com os novos valores do DTO
        BebidaEntity bebidaAtualizada = updateBebidaEntity(bebidaExistente, tipoBebidaExistente, bebidaDto);

        // Salva a entidade atualizada
        repository.save(bebidaAtualizada);
    }

    private BebidaEntity updateBebidaEntity(BebidaEntity bebidaExistente, TipoBebidaEntity tipoBebidaExistente,BebidaDTO bebidaDto ) {
        bebidaExistente.setNome(bebidaDto.getNome());
        bebidaExistente.setTipoBebida(tipoBebidaExistente);
        return bebidaExistente;
    }
}
