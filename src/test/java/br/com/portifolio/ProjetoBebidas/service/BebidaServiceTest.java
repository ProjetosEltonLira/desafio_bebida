package br.com.portifolio.ProjetoBebidas.service;


import br.com.portifolio.ProjetoBebidas.model.dto.BebidaDto;
import br.com.portifolio.ProjetoBebidas.model.entities.BebidaEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.TipoBebidaEntity;
import br.com.portifolio.ProjetoBebidas.repository.BebidaRepository;
import br.com.portifolio.ProjetoBebidas.repository.TipoBebidaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@DisplayName("BebidaServiceTest")
public class BebidaServiceTest {

    @InjectMocks //cria uma instância igual a que uso quando a aplicação executa.
    private BebidaService bebidaSecaoService;


    @Mock
    private BebidaService bebidaService;

    @Mock
    private BebidaRepository bebidaRepository;

    @Mock
    private TipoBebidaRepository tipoBebidaRepository;

    @Mock
    private TipoBebidaService tipoBebidaService;

    BebidaDto bebidaDto;
    BebidaEntity bebidaEntity;
    TipoBebidaEntity tipoBebidaEntity;

    @BeforeEach // executa antes dos testes
    public void configuracaoInicial() {

        bebidaDto = new BebidaDto(1,"51 uma boa escolha",1);
        tipoBebidaEntity = new TipoBebidaEntity(2, "ALCOOLICA", 500D);
        bebidaEntity = new BebidaEntity(1, "51 uma boa escolha", tipoBebidaEntity);
    }

    @Test
    @DisplayName("Deve inserir uma bebida")
    public void testeInserirBebida(){

        when(bebidaService.findById(any())).thenReturn(bebidaEntity);
        when(bebidaRepository.save(any())).thenReturn(bebidaEntity);
        when(tipoBebidaService.findById(any())).thenReturn(tipoBebidaEntity);

        BebidaEntity bebidaEntity = bebidaService.inserir(bebidaDto);
        assertEquals(bebidaDto.getId(), Optional.ofNullable(bebidaEntity.getId()));
        assertEquals(bebidaDto.getNome(), bebidaEntity.getNome());
        assertEquals(bebidaDto.getTipoBebidaId(), bebidaEntity.getTipoBebida().getId());
    }

    @Test
    @DisplayName("Validar a captura da quantidade existente da bebida a ser inserida na secao teste")
    public void testeValidarQuantidadeBebidaNaSecao() {

        //assertEquals(bebidaSecaoService.getBebidaEncontradaNaSecao(pedidoDto,listBebidaSecaoEntity),150D);
    }

}
