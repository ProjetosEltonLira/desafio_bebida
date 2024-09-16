package br.com.portifolio.ProjetoBebidas.controllers;


import br.com.portifolio.ProjetoBebidas.model.domain.Bebida;
import br.com.portifolio.ProjetoBebidas.model.dto.BebidaDTO;
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

    @PostMapping
    public ResponseEntity<BebidaDTO> insert(@RequestBody BebidaDTO bebidaDTO){
        BebidaEntity bebidaEntity = service.inserir(bebidaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bebidaEntity.getId())
                .toUri();

        BebidaDTO bebidaDTOCriado = toBebidaDTO(bebidaEntity);
        return ResponseEntity.created(uri).body(bebidaDTOCriado);
    }



    @GetMapping
    public ResponseEntity<List<BebidaDTO>> findAll() {
        List<BebidaEntity> list = service.findAll();

        // Mapeia a lista de entidades para uma lista de DTOs
        List<BebidaDTO> dtoList = list.stream()
                .map(this::toBebidaDTO)
                .toList();

        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping (value = "/{id}") // Isso indica que permite receber um valor na URL
    public ResponseEntity<BebidaDTO> findById(@PathVariable Long id) { // para o Spring aceitar esse valor vindo da URL precisa colocar a notação @PathVariable
        BebidaEntity bebidaEntity = service.findById(id);
        BebidaDTO bebidaDtoCriado = toBebidaDTO(bebidaEntity);
        return ResponseEntity.ok().body(bebidaDtoCriado);
    }


    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<BebidaDTO> update(@PathVariable Long id,
                                       @RequestBody BebidaDTO bebidaDTO,
                                       @RequestHeader String texto){

        System.out.println("header" + texto);
        service.update(id,bebidaDTO);
        return ResponseEntity.ok(bebidaDTO);
    }

    private BebidaDTO toBebidaDTO(BebidaEntity bebidaEntity) {
        return new BebidaDTO(
                bebidaEntity.getId(),
                bebidaEntity.getNome(),
                bebidaEntity.getTipoBebida().getId()
        );
    }
}