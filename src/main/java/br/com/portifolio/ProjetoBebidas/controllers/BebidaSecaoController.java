package br.com.portifolio.ProjetoBebidas.controllers;


import br.com.portifolio.ProjetoBebidas.model.dto.request.PedidoDTO;
import br.com.portifolio.ProjetoBebidas.model.dto.response.PedidoResponseDTO;
import br.com.portifolio.ProjetoBebidas.service.BebidaSecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/pedidos")
public class BebidaSecaoController {

    @Autowired
    private BebidaSecaoService service;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> insert(@RequestBody PedidoDTO pedidoDTO) {
        PedidoResponseDTO responseDTO = service.inserirPedido(pedidoDTO);
        return ResponseEntity.ok(responseDTO);
    }
}