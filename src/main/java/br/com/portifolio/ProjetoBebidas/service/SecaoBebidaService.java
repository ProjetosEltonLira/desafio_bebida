package br.com.portifolio.ProjetoBebidas.service;

import br.com.portifolio.ProjetoBebidas.model.Enum.TipoBebidaEnum;
import br.com.portifolio.ProjetoBebidas.model.domain.Bebida;
import br.com.portifolio.ProjetoBebidas.model.domain.BebidaSecao;
import br.com.portifolio.ProjetoBebidas.model.domain.Secao;
import br.com.portifolio.ProjetoBebidas.model.dto.PedidoDto;
import br.com.portifolio.ProjetoBebidas.model.dto.PedidoResponseDto;
import br.com.portifolio.ProjetoBebidas.model.entities.BebidaEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.BebidaSecaoEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.SecaoEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.TipoBebidaEntity;
import br.com.portifolio.ProjetoBebidas.repository.BebidaRepository;
import br.com.portifolio.ProjetoBebidas.repository.SecaoBebidaRepository;
import br.com.portifolio.ProjetoBebidas.repository.SecaoRepository;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecaoBebidaService {

    @Autowired
    private SecaoBebidaRepository secaoBebidaRepository;

    @Autowired
    private SecaoRepository secaoRepository;

    @Autowired
    private BebidaRepository bebidaRepository;

    public PedidoResponseDto insert(PedidoDto pedidoDto){

        Optional<SecaoEntity> optSecao = secaoRepository.findById(pedidoDto.getSecaoId());
        optSecao.orElseThrow(() -> new ResourceNotFoundException(pedidoDto.getSecaoId()));
        Secao secao = instanciarSecao(optSecao);

        Optional<BebidaEntity> optBebida = bebidaRepository.findById(pedidoDto.getBebidaId());
        optBebida.orElseThrow(() -> new ResourceNotFoundException(pedidoDto.getBebidaId()));
        Bebida bebida = instanciarBebida(optBebida);

        BebidaSecao bebidaSecao = new BebidaSecao(bebida,secao, pedidoDto.getQuantidade());




        return null;
        /**
        1 - Buscar no banco se a seção existe, se não, retornar erro.
        2 - recuperar dado da seção
        3 - buscar no banco se a bebida existe, se não, retornar erro.
        4 - recuperar dados da bebida.
        5 - através da seção e bebida, instanciar a bebidaSecao.
        6 - Validar se a bebida pode ser inserida na secao, aplicar as regras aqui.
        7 - instanciar a BebidaSeção
        7 - Persistir no banco de dados a bebidaSecao.
        8 -
        **/
    }

    public Secao instanciarSecao (Optional<SecaoEntity> opt){
          return new Secao(
                opt.get().getId(),
                opt.get().getCapacidade(),
                opt.get().getVolumeAtual());
    }

    public Bebida instanciarBebida (Optional<BebidaEntity> opt){

        return new Bebida(
                opt.get().getId(),
                opt.get().getNome(),
                TipoBebidaEnum.valueOf(opt.get().getTipoBebida().getDescricao()));
    }


    /*public BebidaSecaoEntity findById(Long id) {
        BebidaSecaoEntity secaoBebidaEntity =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return secaoBebidaEntity;
    }*/
}
