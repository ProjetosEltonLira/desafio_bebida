package br.com.portifolio.ProjetoBebidas.service;

import br.com.portifolio.ProjetoBebidas.model.entities.BebidaSecao;
import br.com.portifolio.ProjetoBebidas.repository.SecaoBebidaRepository;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecaoBebidaService {

    @Autowired
    private SecaoBebidaRepository repository;

    public BebidaSecao insert(BebidaSecao secaoBebidaEntity){
        repository.save(secaoBebidaEntity);
        return secaoBebidaEntity;
    }

    public BebidaSecao findById(Long id) {
        BebidaSecao secaoBebidaEntity =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return secaoBebidaEntity;
    }
}
