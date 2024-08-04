package br.com.portifolio.ProjetoBebidas.service;

import br.com.portifolio.ProjetoBebidas.model.entities.SecaoEntity;
import br.com.portifolio.ProjetoBebidas.repository.SecaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecaoService {

    @Autowired
    private SecaoRepository repository;

   /*public secaoEntity insert(PedidoDTO pedidoDTO){

        repository.save(secaoEntity);
        return secaoEntity;



    }*/

    public SecaoEntity findById(Long id) {
        Optional<SecaoEntity> optsecao =  repository.findById(id);
        return optsecao.get();
    }
}
