package br.com.portifolio.ProjetoBebidas.controller;


import br.com.portifolio.ProjetoBebidas.controllers.BebidaController;
import br.com.portifolio.ProjetoBebidas.model.dto.request.BebidaDTO;
import br.com.portifolio.ProjetoBebidas.model.entities.BebidaEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.TipoBebidaEntity;
import br.com.portifolio.ProjetoBebidas.service.BebidaService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("TipoBebidaControllerTest")
public class BebidaControllerTest {

    @InjectMocks
    BebidaController bebidaController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BebidaService bebidaService;

        BebidaDTO bebidaDTO ;
    BebidaEntity bebidaEntity ;
    BebidaEntity bebidaEntity2;
    List<BebidaEntity> bebidaList;

    @BeforeEach
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(bebidaController).alwaysDo(print()).build();

        bebidaDTO = new BebidaDTO(1L, "Coca Cola", 1L);
        bebidaEntity = new BebidaEntity(1L, "Coca Cola", new TipoBebidaEntity(1, "Refrigerante",400D));
        bebidaEntity2 = new BebidaEntity(2L, "Pepsi", new TipoBebidaEntity(1, "Refrigerante", 400D));
        bebidaList = new ArrayList<>();
        bebidaList.add(bebidaEntity);
        bebidaList.add(bebidaEntity2);

    }

    @Test
    public void testInsert() throws Exception {

        when(bebidaService.inserir(any(BebidaDTO.class))).thenReturn(bebidaEntity);

        mockMvc.perform(post("/bebida")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bebidaDTO)))
                .andExpect(status().isCreated()) //status 201
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Coca Cola"))
                .andExpect(jsonPath("$.tipoBebidaId").value(1));
    }

    @Test
    public void testFindAll() throws Exception {

        when(bebidaService.findAll()).thenReturn(bebidaList);

        mockMvc.perform(get("/bebida")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Coca Cola"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nome").value("Pepsi"));
    }

    @Test
    public void testFindById() throws Exception {

        when(bebidaService.findById(1L)).thenReturn(bebidaEntity);

        mockMvc.perform(get("/bebida/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Coca Cola"))
                .andExpect(jsonPath("$.tipoBebidaId").value(1));
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(bebidaService).delete(1L);

        mockMvc.perform(delete("/bebida/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdate() throws Exception {

        doNothing().when(bebidaService).update(eq(1L),any(BebidaDTO.class));

        mockMvc.perform(put("/bebida/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("texto", "someHeader")
                        .content(new ObjectMapper().writeValueAsString(bebidaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Coca Cola"));
    }
}

