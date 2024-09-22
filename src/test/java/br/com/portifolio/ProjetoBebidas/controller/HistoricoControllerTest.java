package br.com.portifolio.ProjetoBebidas.controller;


import br.com.portifolio.ProjetoBebidas.controllers.HistoricoController;
import br.com.portifolio.ProjetoBebidas.model.dto.request.BebidaDTO;
import br.com.portifolio.ProjetoBebidas.model.entities.HistoricoEntity;
import br.com.portifolio.ProjetoBebidas.service.HistoricoService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("TipoBebidaControllerTest")
public class HistoricoControllerTest {

    @InjectMocks
    private HistoricoController controller;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private HistoricoService service;

    List<HistoricoEntity>  historicoList = new ArrayList<>();
    HistoricoEntity historicoEntity = new HistoricoEntity();
    HistoricoEntity historicoEntity2 = new HistoricoEntity();

    @BeforeEach
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();

        historicoEntity.setId(1);
        historicoEntity.setSecaoId(2);
        historicoEntity.setBebidaId(2);
        historicoEntity.setQuantidade(500D);
        historicoEntity.setTipoPedido("ENTRADA");
        historicoEntity.setSolicitante("Elton");
        historicoList.add(historicoEntity);

        historicoEntity2.setId(2);
        historicoEntity2.setSecaoId(3);
        historicoEntity2.setBebidaId(4);
        historicoEntity2.setQuantidade(200D);
        historicoEntity2.setTipoPedido("ENTRADA");
        historicoEntity2.setSolicitante("GABI");
        historicoList.add(historicoEntity2);

    }

    @Test
    @DisplayName("Encontrar todos os registros do histórico")
    public void testFindAll() throws Exception {

        when(service.findAll()).thenReturn(historicoList);

        mockMvc.perform(get("/historico")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].secaoId").value(2))
                .andExpect(jsonPath("$[0].bebidaId").value(2))
                .andExpect(jsonPath("$[0].quantidade").value(500D))
                .andExpect(jsonPath("$[0].tipoPedido").value("ENTRADA"))
                .andExpect(jsonPath("$[0].solicitante").value("Elton"))

                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].secaoId").value(3))
                .andExpect(jsonPath("$[1].bebidaId").value(4))
                .andExpect(jsonPath("$[1].quantidade").value(200D))
                .andExpect(jsonPath("$[1].tipoPedido").value("ENTRADA"))
                .andExpect(jsonPath("$[1].solicitante").value("GABI"));
    }

    @Test
    @DisplayName("Encontrar todos os registros do histórico")
    public void testFindById() throws Exception {

        HistoricoEntity historico = new HistoricoEntity(1L, 1L, 5.0, "Compra", "John Doe");

        // Simula a resposta do service.findById()
        when(service.findById(1L)).thenReturn(historico);

        // Executar a requisição GET com o ID e verificar a resposta
        mockMvc.perform(get("/historico/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.secaoId").value(1L))
                .andExpect(jsonPath("$.bebidaId").value(1L))
                .andExpect(jsonPath("$.quantidade").value(5.0))
                .andExpect(jsonPath("$.tipoPedido").value("Compra"))
                .andExpect(jsonPath("$.solicitante").value("John Doe"));
    }
}

