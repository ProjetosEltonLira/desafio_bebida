package br.com.portifolio.ProjetoBebidas.service;

import br.com.portifolio.ProjetoBebidas.model.dto.PedidoDto;
import br.com.portifolio.ProjetoBebidas.model.entities.HistoricoEntity;
import br.com.portifolio.ProjetoBebidas.repository.HistoricoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository repository;

    public HistoricoEntity insert(PedidoDto pedidoDto) {
        return repository.save(instanciarHistorico(pedidoDto));
    }

    public HistoricoEntity instanciarHistorico(@NotNull PedidoDto pedidoDto){
        return new HistoricoEntity(
                pedidoDto.getSecaoId(),
                pedidoDto.getBebidaId(),
                pedidoDto.getQuantidade(),
                pedidoDto.getTipoPedido(),
                pedidoDto.getSolicitante());
    }
}
