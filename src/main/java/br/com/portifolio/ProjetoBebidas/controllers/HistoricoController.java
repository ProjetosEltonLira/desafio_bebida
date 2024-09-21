package br.com.portifolio.ProjetoBebidas.controllers;

import br.com.portifolio.ProjetoBebidas.model.dto.response.PedidoResponseDTO;
import br.com.portifolio.ProjetoBebidas.model.entities.HistoricoEntity;
import br.com.portifolio.ProjetoBebidas.service.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService service;

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> findAll() {
        List<HistoricoEntity> list  = service.findAll();

        // Mapeia a lista de entidades para uma lista de DTOs
        List<PedidoResponseDTO> dtoList = list.stream()
                .map(this::toPedidoResponseDTO)
                .toList();

        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<PedidoResponseDTO> findById(@PathVariable Long id) {
        HistoricoEntity HistoricoEntity = service.findById(id);
        PedidoResponseDTO pedidoResponseDTO = toPedidoResponseDTO(HistoricoEntity);
        return ResponseEntity.ok(pedidoResponseDTO);
    }



    private PedidoResponseDTO toPedidoResponseDTO(HistoricoEntity historico) {
        return new PedidoResponseDTO(
                historico.getId(),
                historico.getSecaoId(),
                historico.getBebidaId(),
                historico.getQuantidade(),
                historico.getTipoPedido(),
                historico.getSolicitante(),
                historico.getDataSolicitacao());
    }
}