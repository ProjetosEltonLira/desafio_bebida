package br.com.portifolio.ProjetoBebidas.controllers;


import br.com.portifolio.ProjetoBebidas.model.dto.PedidoDto;
import br.com.portifolio.ProjetoBebidas.model.dto.PedidoResponseDto;
import br.com.portifolio.ProjetoBebidas.service.BebidaSecaoService;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ExceptionError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping (value = "/gerenciar")
public class PedidoController {

    @Autowired
    private BebidaSecaoService service;

    @PostMapping
    public ResponseEntity<PedidoResponseDto> insert(@RequestBody PedidoDto pedidoDTO) {
        PedidoResponseDto responseDTO = service.inserirPedido(pedidoDTO);
        return ResponseEntity.ok(responseDTO);
    }
}