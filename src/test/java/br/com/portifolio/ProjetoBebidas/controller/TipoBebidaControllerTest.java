package br.com.portifolio.ProjetoBebidas.controller;

import br.com.portifolio.ProjetoBebidas.controllers.TipoBebidaController;
import br.com.portifolio.ProjetoBebidas.model.dto.request.TipoBebidaDTO;
import br.com.portifolio.ProjetoBebidas.model.entities.TipoBebidaEntity;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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
                .andExpect(jsonPath("$.capacidade").value(200.0));
    }

    @Test
    @DisplayName("Falha ao Inserir uma capacidade Negativa")
    public void FalhaAoinserirCapacidadeNegativaNoTipoBebida() throws Exception {

        tipoBebidaDTO = new TipoBebidaDTO(1, "TESTE BEBIDA", -1.0);

        mockMvc.perform(post("/tipobebida")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tipoBebidaDTO)))
                .andExpect(status().isBadRequest());

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

        tipoBebidaDTO = new TipoBebidaDTO(3, "Refrigerante", 200.0);
        when(tipoBebidaService.pesquisarPorId(any())).thenReturn(tipoBebidaDTO);

        mockMvc.perform(get("/tipobebida/3"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(3))
                        .andExpect(jsonPath("$.descricao").value("Refrigerante"))
                        .andExpect(jsonPath("$.capacidade").value(200.0));

        verify(tipoBebidaService).pesquisarPorId(any());

    }
    @Test
    @DisplayName("Falha na Buscar Tipo Bebida por Id")
    public void FalhaBuscarPorId() throws Exception {

        Long id = 3L;
        when(tipoBebidaService.pesquisarPorId(anyLong())).thenThrow(new ResourceNotFoundException(id));
        try {
            tipoBebidaService.pesquisarPorId(id);
        } catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Registro não encontrado. Id:" + id, ex.getMessage());
        }
    }

    @Test
    @DisplayName("Deletar registro com sucesso")
    public void DeletarRegistroPorId() {
        Long id = 3L;
        doNothing().when(tipoBebidaService).delete(id);
        tipoBebidaService.delete(id);
        verify(tipoBebidaService, times(1)).delete(id);
    }

    @Test
    @DisplayName("Falha ao tentar deletar um registro inexistente")
    public void FalhaDeletarRegistroPorId() {

        Long id = 3L;

        // Configura o mock para lançar a exceção quando o método delete for chamado
        doThrow(new ResourceNotFoundException(id))
                .when(tipoBebidaService).delete(id);
        try {
            // Tenta deletar o registro
            tipoBebidaService.delete(id);
        } catch (Exception ex) {
            // Verifica o tipo da exceção
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            String msgEsperada = "Registro não encontrado. Id:" + id;
            String msgExcecao = ex.getMessage();

            assertEquals(msgEsperada,msgExcecao);
        }
    }


    @Test
    @DisplayName("Selecionar todos tipos de bebidas existentes")
    public void BuscarTodosTiposBebidas() throws Exception {

        List<TipoBebidaEntity> list = Arrays.asList(
                new TipoBebidaEntity(1, "bebida teste", 500.0),
                new TipoBebidaEntity(2, "bebida teste2", 400.0));

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
