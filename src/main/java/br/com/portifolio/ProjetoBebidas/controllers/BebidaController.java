package br.com.portifolio.ProjetoBebidas.controllers;


import br.com.portifolio.ProjetoBebidas.Model.entities.Bebida;
import br.com.portifolio.ProjetoBebidas.Service.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping (value = "/Bebida")
public class BebidaController {

    @Autowired
    private BebidaService service;

    /*@GetMapping
    public ResponseEntity<List<ResponsePessoaDTO>> findAll(){
        List<ResponsePessoaDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping (value = "/{id}")// Isso indica que permite receber um valor na URL
    public ResponseEntity<ResponsePessoaDTO> findById(@PathVariable Long id){
        ResponsePessoaDTO responsePessoaDTO = service.findById(id);
        return ResponseEntity.ok().body(responsePessoaDTO);
    }*/


    @PostMapping
    public ResponseEntity<Bebida> insert(@RequestBody Bebida bebidaEntry){
        Bebida bebida = service.insert(bebidaEntry);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bebida.getId())
                .toUri();
        return ResponseEntity.created(uri).body(bebida);
    }

    /*@DeleteMapping (value = "/{id}")
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