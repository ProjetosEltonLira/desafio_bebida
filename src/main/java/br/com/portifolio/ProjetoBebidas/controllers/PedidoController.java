package br.com.portifolio.ProjetoBebidas.controllers;


import br.com.portifolio.ProjetoBebidas.model.dto.PedidoDto;
import br.com.portifolio.ProjetoBebidas.model.dto.PedidoResponseDto;
import br.com.portifolio.ProjetoBebidas.service.SecaoBebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping (value = "/cadastrar")
public class PedidoController {

    @Autowired
    private SecaoBebidaService service;


    @PostMapping
    public ResponseEntity<PedidoResponseDto> insert(@RequestBody PedidoDto pedidoDTO){
        PedidoResponseDto responseDTO = service.insert(pedidoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    /*@GetMapping
    public ResponseEntity<List<BebidaEntity>> findAll() {
        List<BebidaEntity> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping (value = "/{id}") // Isso indica que permite receber um valor na URL
    public ResponseEntity<BebidaEntity> findById(@PathVariable Long id) { // para o Spring aceitar esse valor vindo da URL precisa colocar a notação @PathVariable
        BebidaEntity bebidaEntity = service.findById(id);
        return ResponseEntity.ok().body(bebidaEntity);
    }


    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<ResponsePessoaDTO> update(@PathVariable Long id,@RequestBody EntryPointPessoaDTO pessoaDTO){
        ResponsePessoaDTO responsePessoaDTO = service.update(id,pessoaDTO);
        return ResponseEntity.ok().body(responsePessoaDTO);
    }*/
}