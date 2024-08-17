package br.com.portifolio.ProjetoBebidas.controller;

import br.com.portifolio.ProjetoBebidas.controllers.TipoBebidaController;
import br.com.portifolio.ProjetoBebidas.handler.ResourceExceptionHandler;
import br.com.portifolio.ProjetoBebidas.model.dto.TipoBebidaDTO;
import br.com.portifolio.ProjetoBebidas.model.entities.TipoBebidaEntity;
import br.com.portifolio.ProjetoBebidas.repository.TipoBebidaRepository;
import br.com.portifolio.ProjetoBebidas.service.TipoBebidaService;

import br.com.portifolio.ProjetoBebidas.service.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@ExtendWith(MockitoExtension.class)
@DisplayName("TipoBebidaControllerTest")
public class TipoBebidaControllerTest {

    @InjectMocks
    private TipoBebidaController controller;

    @Mock
    private TipoBebidaService tipoBebidaService;

    private MockMvc mockMvc; //Usado apenas para testar o controller

    private TipoBebidaDTO tipoBebidaDTO;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {

        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .alwaysDo(print())
                .build();

        tipoBebidaDTO = new TipoBebidaDTO(1, "Refrigerante", 200.0);
    }

    @Test
    @DisplayName("Inserir um tipo bebida deve retornar statusOk e tipoBebidaDTO")
    public void inserirTipoBebida() throws Exception {

        when(tipoBebidaService.inserir(any())).thenReturn(this.tipoBebidaDTO);

        mockMvc.perform(post("/tipobebida")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tipoBebidaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descricao").value("Refrigerante"))
                .andExpect(jsonPath("$.capacidade").value(2.0));
    }

    @Test
    @DisplayName("Falha ao Inserir uma capacidade Negativa")
    public void FalhaAoinserirCapacidadeNegativaNoTipoBebida() throws Exception {

        tipoBebidaDTO = new TipoBebidaDTO(1, "TESTE BEBIDA", -1.0);

        mockMvc.perform(post("/tipobebida")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tipoBebidaDTO)))
                .andExpect(status().isBadRequest());
                //.andExpect(jsonPath("¨$.error").value("Campo(s) com preenchimento inválido"));
//                .andExpect(jsonPath("$.errors[0].campo").value("Valor da capacidade não pode ser zero ou negativo."))
        //              .andExpect(jsonPath("$.errors[0].mensagem").value("CAPACIDADE"))
        //            .andExpect(jsonPath("$.errors[0].parametro").value(-1.0))
        //          .andExpect(jsonPath("$.errors[1].campo").value("Nome da bebida não pode ser vazio ou preenchido com espaços"))
        //        .andExpect(jsonPath("$.errors[1].mensagem").value("DESCRICAO"))
        //      .andExpect(jsonPath("$.errors[1].parametro").value(""));

    }

    @Test
    @DisplayName("Falha ao Inserir uma bebida sem descricao")
    public void FalhaAoinserirUmTipoBebidaSemDescricao() throws Exception {

        tipoBebidaDTO = new TipoBebidaDTO(1, " ", 500D);
        mockMvc.perform(post("/tipobebida")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tipoBebidaDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Buscar Tipo Bebida por Id")
    public void BuscarPorId() throws Exception {

        tipoBebidaDTO = new TipoBebidaDTO(2, "Refrigerante", 200.0);
        when(tipoBebidaService.pesquisarPorId(any())).thenReturn(tipoBebidaDTO);

        mockMvc.perform(get("/tipobebida/2"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(2))
                        .andExpect(jsonPath("$.descricao").value("Refrigerante"))
                        .andExpect(jsonPath("$.capacidade").value(200.0));

        verify(tipoBebidaService).pesquisarPorId(any());

    }

    @Test
    @DisplayName("Falha na Buscar Tipo Bebida por Id")
    public void FalhaBuscarPorId() throws Exception {

        //when(tipoBebidaService.pesquisarPorId(any())).thenThrow(new RuntimeException());

       // mockMvc.perform(get("/tipobebida/100"))
       //         .andExpect(MockMvcResultMatchers.status().isNotFound());
    //.andExpect(status().isNotFound())
                        //.andExpect(content().string("Registro não encontrado. Id:100"));

    }


    @Test
    @DisplayName("Selecionar todos tipos de bebidas existentes")
    public void BuscarTodosTiposBebidas() throws Exception {

        TipoBebidaEntity tipoBebidaEntity = new TipoBebidaEntity(1,"bebida teste",500.0);
        TipoBebidaEntity tipoBebidaEntity2 = new TipoBebidaEntity(2,"bebida teste2",400.0);
        List<TipoBebidaEntity> list = new ArrayList<>();
        list.add(tipoBebidaEntity);
        list.add(tipoBebidaEntity2);

        when(tipoBebidaService.findAll()).thenReturn(list);

        mockMvc.perform(get("/tipobebida/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].descricao").value("bebida teste"))
                .andExpect(jsonPath("$[0].capacidade").value(500.0))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].descricao").value("bebida teste2"))
                .andExpect(jsonPath("$[1].capacidade").value(400.0));

        verify(tipoBebidaService).findAll();

    }
}
