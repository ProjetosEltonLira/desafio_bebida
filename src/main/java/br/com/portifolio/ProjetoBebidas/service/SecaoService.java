package br.com.portifolio.ProjetoBebidas.service;

import br.com.portifolio.ProjetoBebidas.model.Enum.TipoBebidaEnum;
import br.com.portifolio.ProjetoBebidas.model.entities.SecaoEntity;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ResourceNotFoundException;
import br.com.portifolio.ProjetoBebidas.repository.SecaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecaoService {

    @Autowired
    private SecaoRepository repository;

    /*public secaoEntity insert(PedidoDTO pedidoDTO){
        repository.save(secaoEntity);
        return secaoEntity;
    }*/

    public SecaoEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public SecaoEntity inserir(SecaoEntity secaoEntity) {
         return repository.save(secaoEntity);
    }

    public SecaoEntity AtualizarCapacidadeSecao(SecaoEntity secaoEntity, TipoBebidaEnum tipoBebida) {
        return repository.save(ajustarCapacidadeSecao(secaoEntity,tipoBebida));
    }


    public SecaoEntity ajustarCapacidadeSecao (SecaoEntity secaoEntity,TipoBebidaEnum tipoBebida){
        if (tipoBebida.equals(TipoBebidaEnum.ALCOOLICA))
            secaoEntity.setCapacidade(500.0);
        else { //Sem alcool
            secaoEntity.setCapacidade(400.0);
        }
        return secaoEntity;
    }
}
