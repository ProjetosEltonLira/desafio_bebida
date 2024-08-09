package br.com.portifolio.ProjetoBebidas.controllers;


import br.com.portifolio.ProjetoBebidas.model.entities.BebidaEntity;
import br.com.portifolio.ProjetoBebidas.service.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping (value = "/bebida")
public class BebidaController {

    @Autowired
    private BebidaService service;

    /*@PostMapping
    public ResponseEntity<BebidaEntity> insert(@RequestBody BebidaEntity bebidaEntityEntry){
        BebidaEntity bebidaEntity = service.insert(bebidaEntityEntry);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bebidaEntity.getId())
                .toUri();
        return ResponseEntity.created(uri).body(bebidaEntity);
    }

    @GetMapping
    public ResponseEntity<List<BebidaEntity>> findAll() {
        List<BebidaEntity> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping (value = "/{id}") // Isso indica que permite receber um valor na URL
    public ResponseEntity<BebidaEntity> findById(@PathVariable Long id) { // para o Spring aceitar esse valor vindo da URL precisa colocar a notação @PathVariable
        BebidaEntity bebidaEntity = service.findById(id);
        return ResponseEntity.ok().body(bebidaEntity);
    }*/


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