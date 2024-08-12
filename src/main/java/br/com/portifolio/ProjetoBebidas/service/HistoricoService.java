package br.com.portifolio.ProjetoBebidas.service;

import br.com.portifolio.ProjetoBebidas.model.dto.PedidoDTO;
import br.com.portifolio.ProjetoBebidas.model.entities.HistoricoEntity;
import br.com.portifolio.ProjetoBebidas.repository.HistoricoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository repository;

    public void inserir(PedidoDTO pedido) {
        repository.save(instanciarHistorico(pedido));
    }

    public HistoricoEntity instanciarHistorico(@NotNull PedidoDTO pedidoDto){
        return new HistoricoEntity(
                pedidoDto.getSecaoId(),
                pedidoDto.getBebidaId(),
                pedidoDto.getQuantidade(),
                pedidoDto.getTipoPedido(),
                pedidoDto.getSolicitante());

    }

    public List<HistoricoEntity> findAll() {
        return repository.findAll();
    }

    public boolean findByIdSescao(Long idSecao) {
        return repository.existsById(idSecao);
    }
}
