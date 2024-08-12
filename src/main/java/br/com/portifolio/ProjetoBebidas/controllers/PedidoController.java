package br.com.portifolio.ProjetoBebidas.controllers;


import br.com.portifolio.ProjetoBebidas.model.dto.PedidoDTO;
import br.com.portifolio.ProjetoBebidas.model.dto.PedidoResponseDTO;
import br.com.portifolio.ProjetoBebidas.service.BebidaSecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/gerenciar")
public class PedidoController {

    @Autowired
    private BebidaSecaoService service;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> insert(@RequestBody PedidoDTO pedidoDTO) {
        PedidoResponseDTO responseDTO = service.inserirPedido(pedidoDTO);
        return ResponseEntity.ok(responseDTO);
    }
}