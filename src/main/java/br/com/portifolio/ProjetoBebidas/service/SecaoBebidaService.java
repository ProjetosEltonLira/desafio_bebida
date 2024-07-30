package br.com.portifolio.ProjetoBebidas.service;

import br.com.portifolio.ProjetoBebidas.model.entities.SecaoBebidaEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.SecaoEntity;
import br.com.portifolio.ProjetoBebidas.repository.SecaoBebidaRepository;
import br.com.portifolio.ProjetoBebidas.repository.SecaoRepository;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecaoBebidaService {

    @Autowired
    private SecaoBebidaRepository repository;

    public SecaoBebidaEntity insert(SecaoBebidaEntity secaoBebidaEntity){
        repository.save(secaoBebidaEntity);
        return secaoBebidaEntity;
    }

    public SecaoBebidaEntity findById(Long id) {
        SecaoBebidaEntity secaoBebidaEntity =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return secaoBebidaEntity;
    }
}
