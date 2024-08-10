package br.com.portifolio.ProjetoBebidas.service;

import br.com.portifolio.ProjetoBebidas.model.Enum.TipoBebidaEnum;
import br.com.portifolio.ProjetoBebidas.model.domain.Bebida;
import br.com.portifolio.ProjetoBebidas.model.domain.Secao;
import br.com.portifolio.ProjetoBebidas.model.dto.PedidoDto;
import br.com.portifolio.ProjetoBebidas.model.dto.PedidoResponseDto;
import br.com.portifolio.ProjetoBebidas.model.entities.BebidaEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.BebidaSecaoEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.SecaoEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.TipoBebidaEntity;
import br.com.portifolio.ProjetoBebidas.repository.SecaoBebidaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@ExtendWith(SpringExtension.class)
@DisplayName("BebidaSecaoServiceTest")
public class BebidaSecaoServiceTest {

    @InjectMocks //cria uma instância igual a que uso quando a aplicação executa.
    private BebidaSecaoService bebidaSecaoService;

    @Mock
    private SecaoService secaoService;

    @Mock
    private BebidaService bebidaService;

    @Mock
    private SecaoBebidaRepository secaoBebidaRepository;

    @Mock
    private HistoricoService historicoService;

    PedidoDto pedidoDto;
    Bebida bebidaAlcoolica;
    Bebida bebidaSemAlcool;
    Secao secao;
    SecaoEntity secaoEntity;
    BebidaEntity bebidaEntity;
    BebidaEntity bebidaEntity2;
    TipoBebidaEntity tipoBebidaEntity;
    BebidaSecaoEntity bebidaSecaoEntity;

    BebidaSecaoEntity bebidaEncontradaNaSecao;
    BebidaSecaoEntity bebidaEncontradaNaSecao2;
    List<BebidaSecaoEntity> listBebidaSecaoEntity = new ArrayList<>();

    @BeforeEach // executa antes dos testes
    public void configuracaoInicial() {

        pedidoDto = new PedidoDto(1, 1, 100.0, "ENTRADA", "ELTON");

        tipoBebidaEntity = new TipoBebidaEntity(1, "ALCOOLICA", 500D);
        bebidaEntity = new BebidaEntity(1L, "CACHACA", tipoBebidaEntity);
        bebidaEntity2 = new BebidaEntity(2L, "CACHACA VEGANA", tipoBebidaEntity);
        secaoEntity = new SecaoEntity(1);
        bebidaSecaoEntity = new BebidaSecaoEntity(bebidaEntity, secaoEntity, 100D);
        bebidaEncontradaNaSecao = new BebidaSecaoEntity(bebidaEntity, secaoEntity, 150D);
        bebidaEncontradaNaSecao2 = new BebidaSecaoEntity(bebidaEntity2, secaoEntity, 50D);

        listBebidaSecaoEntity.add(bebidaEncontradaNaSecao);
        listBebidaSecaoEntity.add(bebidaEncontradaNaSecao2);

    }

   @Test
   @DisplayName("Deve inserir uma bebida")
   public void testeInserirPedidoBebida(){

        when(secaoService.findById(any())).thenReturn(secaoEntity);
        when(bebidaService.findById(any())).thenReturn(bebidaEntity);
        when(secaoBebidaRepository.findAll()).thenReturn(List.of(bebidaSecaoEntity));
        when(secaoBebidaRepository.save(any())).thenReturn(bebidaSecaoEntity);

        PedidoResponseDto pedidoResponseDto = bebidaSecaoService.inserirPedido(pedidoDto);
        assertEquals(pedidoResponseDto.getQuantidade(), pedidoDto.getQuantidade());
        assertEquals(pedidoResponseDto.getBebidaId(), pedidoDto.getBebidaId());
        assertEquals(pedidoResponseDto.getSecaoId(), pedidoDto.getSecaoId());
    }

    @Test
    @DisplayName("Validar a captura da quantidade existente da bebida a ser inserida na secao teste")
    public void testeValidarQuantidadeBebidaNaSecao() {

        assertEquals(bebidaSecaoService.getBebidaEncontradaNaSecao(pedidoDto,listBebidaSecaoEntity),150D);
    }

}
