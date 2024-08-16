package br.com.portifolio.ProjetoBebidas.controller;

import br.com.portifolio.ProjetoBebidas.controllers.TipoBebidaController;
import br.com.portifolio.ProjetoBebidas.handler.ResourceExceptionHandler;
import br.com.portifolio.ProjetoBebidas.model.dto.TipoBebidaDTO;
import br.com.portifolio.ProjetoBebidas.repository.TipoBebidaRepository;
import br.com.portifolio.ProjetoBebidas.service.TipoBebidaService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.data.util.ReflectionUtils.setField;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.doThrow;


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

        tipoBebidaDTO = new TipoBebidaDTO(1, "Refrigerante", 2.0);
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
    @DisplayName("Inserir um tipo bebida deve retornar statusBadRequest e tipoBebidaDTO")
    public void inserirTipoBebidaSemDescricaoComNúmeroCapcidadeNegativo() throws Exception {

        tipoBebidaDTO = new TipoBebidaDTO(1, "", -1.0);
        when(tipoBebidaService.inserir(any())).thenReturn(this.tipoBebidaDTO);

        mockMvc.perform(post("/tipobebida")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tipoBebidaDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("¨$.error").value("Campo(s) com preenchimento inválido"));
//                .andExpect(jsonPath("$.errors[0].campo").value("Valor da capacidade não pode ser zero ou negativo."))
  //              .andExpect(jsonPath("$.errors[0].mensagem").value("CAPACIDADE"))
    //            .andExpect(jsonPath("$.errors[0].parametro").value(-1.0))
      //          .andExpect(jsonPath("$.errors[1].campo").value("Nome da bebida não pode ser vazio ou preenchido com espaços"))
        //        .andExpect(jsonPath("$.errors[1].mensagem").value("DESCRICAO"))
          //      .andExpect(jsonPath("$.errors[1].parametro").value(""));

    }
}

