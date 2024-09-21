package br.com.portifolio.ProjetoBebidas.controllers;

import br.com.portifolio.ProjetoBebidas.model.dto.request.TipoBebidaDTO;
import br.com.portifolio.ProjetoBebidas.model.entities.TipoBebidaEntity;
import br.com.portifolio.ProjetoBebidas.service.TipoBebidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping (value = "/tipobebida")
public class TipoBebidaController {

    @Autowired
    private TipoBebidaService service;

    @PostMapping
    public ResponseEntity<TipoBebidaDTO> inserir(@Valid @RequestBody TipoBebidaDTO tipoBebidaDTO) {
        TipoBebidaDTO responseDTO = service.inserir(tipoBebidaDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<TipoBebidaDTO> findById(@PathVariable Long id) {
        TipoBebidaDTO tipoBebidaDTO = service.pesquisarPorId(id);
        return ResponseEntity.ok(tipoBebidaDTO);
    }

    @GetMapping
    public ResponseEntity<List<TipoBebidaEntity>> findAll() {
        List<TipoBebidaEntity> tipoBebidaEntity  = service.findAll();
        return ResponseEntity.ok(tipoBebidaEntity);
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping (value = "/{id}")
    public ResponseEntity<TipoBebidaDTO> update(@PathVariable Long id,@RequestBody TipoBebidaDTO tipoBebidaDTO){
        service.update(id,tipoBebidaDTO);
        return ResponseEntity.ok(tipoBebidaDTO);
    }

}