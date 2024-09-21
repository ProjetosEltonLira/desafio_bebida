package br.com.portifolio.ProjetoBebidas.service;

import br.com.portifolio.ProjetoBebidas.model.dto.request.PedidoDTO;
import br.com.portifolio.ProjetoBebidas.model.entities.BebidaEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.HistoricoEntity;
import br.com.portifolio.ProjetoBebidas.repository.HistoricoRepository;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository repository;

    public void inserir(PedidoDTO pedido) {
        repository.save(toHistoricoEntity(pedido));
    }

    public HistoricoEntity toHistoricoEntity(@NotNull PedidoDTO pedidoDto){
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

    public HistoricoEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }


}
