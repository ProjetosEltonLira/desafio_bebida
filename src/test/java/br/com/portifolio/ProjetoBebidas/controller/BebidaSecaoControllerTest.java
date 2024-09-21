package br.com.portifolio.ProjetoBebidas.controller;


import br.com.portifolio.ProjetoBebidas.controllers.BebidaSecaoController;
import br.com.portifolio.ProjetoBebidas.model.dto.request.PedidoDTO;
import br.com.portifolio.ProjetoBebidas.model.dto.response.PedidoResponseDTO;
import br.com.portifolio.ProjetoBebidas.service.BebidaSecaoService;
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



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("PedidoControllerTest")
public class BebidaSecaoControllerTest {

    @InjectMocks
    BebidaSecaoController bebidaSecaoController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BebidaSecaoService bebidaSecaoService;

    PedidoDTO pedidoDto;
    PedidoResponseDTO responseDTO = new PedidoResponseDTO();
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bebidaSecaoController).alwaysDo(print()).build();
        responseDTO.setId(2);
        responseDTO.setSecaoId(2);
        responseDTO.setBebidaId(2);
        responseDTO.setQuantidade(500D);
        responseDTO.setTipoPedido("ENTRADA");
        responseDTO.setSolicitante("Elton");
    }

    @Test
    public void testInsert() throws Exception {
        pedidoDto = new PedidoDTO(2, 2, 500.0, "ENTRADA", "ELTON");

        when(bebidaSecaoService.inserirPedido(any(PedidoDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(responseDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.secaoId").value(2))
                .andExpect(jsonPath("$.bebidaId").value(2))
                .andExpect(jsonPath("$.quantidade").value(500D))
                .andExpect(jsonPath("$.tipoPedido").value("ENTRADA"))
                .andExpect(jsonPath("$.solicitante").value("Elton"));
    }
}

