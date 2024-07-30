package br.com.portifolio.ProjetoBebidas.service;

import br.com.portifolio.ProjetoBebidas.model.entities.SecaoEntity;
import br.com.portifolio.ProjetoBebidas.repository.SecaoRepository;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecaoService {

    @Autowired
    private SecaoRepository repository;

    public SecaoEntity insert(SecaoEntity secaoEntity){
        repository.save(secaoEntity);
        return secaoEntity;
    }

    public SecaoEntity findById(Long id) {
        SecaoEntity secaoEntity =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return secaoEntity;
    }
}
