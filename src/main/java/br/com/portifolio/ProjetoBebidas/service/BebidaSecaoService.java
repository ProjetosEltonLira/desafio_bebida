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
import br.com.portifolio.ProjetoBebidas.service.exceptions.ExceptionError;
import br.com.portifolio.ProjetoBebidas.repository.SecaoBebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BebidaSecaoService {

    @Autowired
    private SecaoBebidaRepository secaoBebidaRepository;

    @Autowired
    private SecaoService secaoService;

    @Autowired
    private BebidaService bebidaService;

    public  PedidoResponseDto inserirPedido(PedidoDto pedidoDto) throws ExceptionError {

        SecaoEntity secaoEnt = secaoService.findById(pedidoDto.getSecaoId());
        Secao secaoPedido = instanciarSecao(secaoEnt);

        BebidaEntity bebidaEnt = bebidaService.findById(pedidoDto.getBebidaId());
        Bebida bebidaPedido = instanciarBebida(bebidaEnt);

        BebidaSecaoEntity bebidaSecaoEntity = new BebidaSecaoEntity(bebidaEnt, secaoEnt, pedidoDto.getQuantidade());
        BebidaSecao bebidaSecaoPedido = new BebidaSecao(bebidaPedido, secaoPedido, pedidoDto.getQuantidade());

        //Recupera bebidas que estão na secao do pedido.
        List<BebidaSecaoEntity> listBebidaSecao = findAll().stream().filter(x -> x.getSecao().getId() == pedidoDto.getSecaoId()).toList();

        boolean existeBebidaNaSecao = listBebidaSecao.size() > 0;
        if (!existeBebidaNaSecao){
            secaoService.AtualizarCapacidadeSecao(secaoEnt,TipoBebidaEnum.getCodigo(bebidaPedido.getTipoBebida().getCodigo()));
        }
        bebidaSecaoPedido.validarQtdeBebidaSolicitada();

        if (existeBebidaNaSecao) {
            Double somaQtdeBebidaNaSecao = listBebidaSecao.stream().mapToDouble(BebidaSecaoEntity::getQuantidade).sum();
            secaoPedido.validarDisponibilidadeArmazenamento(somaQtdeBebidaNaSecao,pedidoDto.getQuantidade());

            TipoBebidaEnum tipoBebidaExisteNaSecao = TipoBebidaEnum.getCodigo(listBebidaSecao.get(0).getBebida().getId());
            bebidaSecaoPedido.validarTipoBebida(tipoBebidaExisteNaSecao);
        }

        bebidaSecaoEntity = secaoBebidaRepository.save(bebidaSecaoEntity);


        System.out.println(bebidaSecaoEntity);
        return instanciarResponseDto(bebidaSecaoEntity,pedidoDto);
    }

    public List<BebidaSecaoEntity> findAll() {
        return secaoBebidaRepository.findAll();
    }

    public PedidoResponseDto instanciarResponseDto(BebidaSecaoEntity bebida,PedidoDto pedido) {
        return new PedidoResponseDto(
                bebida.getSecao().getId(),
                bebida.getBebida().getId(),
                bebida.getQuantidade(),
                pedido.getTipoPedido(),
                pedido.getSolicitante());
    }


    public Secao instanciarSecao(SecaoEntity secao) {
        return new Secao(
                secao.getId(),
                secao.getCapacidade());
    }

    public Bebida instanciarBebida(BebidaEntity bebida) {
        return new Bebida(
                bebida.getId(),
                bebida.getNome(),
                TipoBebidaEnum.valueOf(bebida.getTipoBebida().getDescricao()));
    }

    public BebidaSecao instanciarBebidaSecao(BebidaSecaoEntity bebida) {
        return new BebidaSecao(
                instanciarBebida(bebida.getBebida()),
                instanciarSecao(bebida.getSecao()),
                bebida.getQuantidade());
    }
}