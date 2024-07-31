package br.com.portifolio.ProjetoBebidas.service;

import br.com.portifolio.ProjetoBebidas.model.entities.Secao;
import br.com.portifolio.ProjetoBebidas.repository.SecaoRepository;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecaoService {

    @Autowired
    private SecaoRepository repository;

    public Secao insert(Secao secao){
        repository.save(secao);
        return secao;
    }

    public Secao findById(Long id) {
        Secao secao =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return secao;
    }
}
