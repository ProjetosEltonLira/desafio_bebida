package br.com.portifolio.ProjetoBebidas.controller;

import br.com.portifolio.ProjetoBebidas.controllers.TipoBebidaController;
import br.com.portifolio.ProjetoBebidas.model.domain.Bebida;
import br.com.portifolio.ProjetoBebidas.model.domain.BebidaSecao;
import br.com.portifolio.ProjetoBebidas.model.domain.Secao;
import br.com.portifolio.ProjetoBebidas.model.domain.TipoBebida;
import br.com.portifolio.ProjetoBebidas.model.dto.TipoBebidaDTO;
import br.com.portifolio.ProjetoBebidas.service.BebidaSecaoService;
import br.com.portifolio.ProjetoBebidas.service.TipoBebidaService;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ExceptionError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@DisplayName("TipoBebidaControllerTest")
public class TipoBebidaTest {


    @InjectMocks //cria uma instância igual a que uso quando a aplicação executa.
    private TipoBebidaController tipoBebidaController;

    @Mock
    private TipoBebidaService tipoBebidaService;

    TipoBebidaDTO tipoBebidaDTO;


    @BeforeEach // executa antes dos testes
    public void configuracaoInicial() {

        tipoBebidaDTO = new TipoBebidaDTO(1,"HEINEKKEN",500D);

    }

        @Test
        @DisplayName("Teste controller Inserir TipoBebida")
        public void testeInserirTipoBebida() {

        when(tipoBebidaService.inserir(any())).thenReturn(tipoBebidaDTO);
        TipoBebidaDTO responseDTO = tipoBebidaController.inserir(tipoBebidaDTO).getBody();
        assertEquals(tipoBebidaDTO.getId(),responseDTO.getId());
        assertEquals(tipoBebidaDTO.getDescricao(),responseDTO.getDescricao());
        assertEquals(tipoBebidaDTO.getCapacidade(),responseDTO.getCapacidade());
        }

}

