package br.com.portifolio.ProjetoBebidas.service;

import br.com.portifolio.ProjetoBebidas.model.domain.Bebida;
import br.com.portifolio.ProjetoBebidas.model.domain.BebidaSecao;
import br.com.portifolio.ProjetoBebidas.model.domain.Secao;
import br.com.portifolio.ProjetoBebidas.model.domain.TipoBebida;
import br.com.portifolio.ProjetoBebidas.model.dto.PedidoDTO;
import br.com.portifolio.ProjetoBebidas.model.dto.PedidoResponseDTO;
import br.com.portifolio.ProjetoBebidas.model.entities.BebidaEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.BebidaSecaoEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.SecaoEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.TipoBebidaEntity;
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

    @Autowired
    private HistoricoService historicoService;

    public PedidoResponseDTO inserirPedido(PedidoDTO pedidoDto) throws ExceptionError {

        SecaoEntity secaoEnt = secaoService.findById(pedidoDto.getSecaoId());
        Secao secaoPedido = instanciarSecao(secaoEnt);

        BebidaEntity bebidaEnt = bebidaService.findById(pedidoDto.getBebidaId());
        Bebida bebidaPedido = instanciarBebida(bebidaEnt);

        BebidaSecaoEntity bebidaSecaoEntity = new BebidaSecaoEntity(bebidaEnt, secaoEnt, pedidoDto.getQuantidade());
        BebidaSecao bebidaSecaoPedido = new BebidaSecao(bebidaPedido, secaoPedido, pedidoDto.getQuantidade());

        //Recupera bebidas na secao do pedido.
        List<BebidaSecaoEntity> listBebidaSecao = findAll().stream().filter(x -> x.getSecao().getId()== pedidoDto.getSecaoId()).toList();

        boolean existeBebidaNaSecao = listBebidaSecao.size() > 0;
        if (existeBebidaNaSecao) {
            Double somaQtdeBebidaNaSecao = listBebidaSecao.stream().mapToDouble(BebidaSecaoEntity::getQuantidade).sum();
            bebidaPedido.getTipoBebida().validarDisponibilidadeArmazenamento(somaQtdeBebidaNaSecao,pedidoDto.getQuantidade());

            TipoBebida tipoBebidaExisteNaSecao =  instanciarTipoBebida(listBebidaSecao.get(0).getBebida().getTipoBebida());
            bebidaSecaoPedido.validarTipoBebida(tipoBebidaExisteNaSecao);
        }

        Double quantidadeExistenteNaSecao = getBebidaEncontradaNaSecao(pedidoDto, listBebidaSecao);
        bebidaSecaoPedido.calcularQuantidadeBebida(pedidoDto,quantidadeExistenteNaSecao);

        bebidaSecaoEntity = secaoBebidaRepository.save(bebidaSecaoEntity);
        historicoService.inserir(pedidoDto);
        return instanciarResponseDto(bebidaSecaoEntity,pedidoDto);
    }



    public Double getBebidaEncontradaNaSecao(PedidoDTO pedidoDto, List<BebidaSecaoEntity> listBebidaSecao) {
        return listBebidaSecao.stream().filter(y -> y.getBebida().getId() == pedidoDto.getBebidaId()).findFirst().get().getQuantidade();
    }

    public List<BebidaSecaoEntity> findAll() {
        return secaoBebidaRepository.findAll();
    }

    public PedidoResponseDTO instanciarResponseDto(BebidaSecaoEntity bebida, PedidoDTO pedido) {
        return new PedidoResponseDTO(
                bebida.getSecao().getId(),
                bebida.getBebida().getId(),
                bebida.getQuantidade(),
                pedido.getTipoPedido(),
                pedido.getSolicitante());
    }

    public Secao instanciarSecao(SecaoEntity secao) {
        return new Secao(
                secao.getId());
    }

    public SecaoEntity instanciarSecaoEntity(Secao secao) {
        return new SecaoEntity(
                secao.getId());
    }
    public Bebida instanciarBebida(BebidaEntity bebida) {
        return new Bebida(
                bebida.getId(),
                bebida.getNome(),
                instanciarTipoBebida(bebida.getTipoBebida()));
    }

    public TipoBebida instanciarTipoBebida(TipoBebidaEntity tipoBebidaEntity) {
        return new TipoBebida(
                tipoBebidaEntity.getId(),
                tipoBebidaEntity.getDescricao(),
                tipoBebidaEntity.getCapacidade());
    }

   /*public BebidaSecao instanciarBebida(BebidaSecaoEntity bs) {
       TipoBebidaEnum tipoBebidaEnum = TipoBebidaEnum.getCodigo(bs.getBebida().getTipoBebida().getId());
       return new BebidaSecao(
                       new Bebida(bs.getBebida().getId(),
                                  bs.getBebida().getNome(),tipoBebidaEnum),
                       new Secao(bs.getSecao().getId(),bs.getQuantidade());
    }*/
    /*
    public BebidaSecao instanciarBebidaSecao(List<BebidaSecaoEntity> bebidaSecaoEntity) {
        BebidaSecao bebidaSecao = new BebidaSecao();
        bebidaSecao.setBebida(bebidaSecaoEntity.getFirst().getBebida());

        return new BebidaSecao(
                (bebidaSecaoEntity.get(0).getBebida()),
                instanciarSecao(bebidaSecaoEntity.get(0).getSecao()),
                bebidaSecaoEntity.get(0).getQuantidade());
    }
    */
}