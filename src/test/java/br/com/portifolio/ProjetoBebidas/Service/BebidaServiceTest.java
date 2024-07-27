package br.com.portifolio.ProjetoBebidas.Service;

import br.com.portifolio.ProjetoBebidas.AplicationConfigTest;
import br.com.portifolio.ProjetoBebidas.Model.entities.Bebida;
import br.com.portifolio.ProjetoBebidas.Repository.BebidaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


@DisplayName("BebidaServiceTest")
public class BebidaServiceTest extends AplicationConfigTest {

   @MockBean
   private BebidaService bebidaService;

   @MockBean
   private BebidaRepository repository;

   Bebida bebida;

   @BeforeEach // executa antes dos testes
   public void configuracaoInicial() {
       //bebida = new Bebida(TipoDeBebida.ALCOOLICA,"CACHACA",100);

   }

   @Test
   @DisplayName("Deve inserir uma bebida")
   public void testeInserirBebida(){

       //Bebida bebidaInserida = bebidaService.insert(bebida);
       //Assertions.assertEquals(bebidaInserida.toString(), "Volume disponível:100.0 Tipo:SEM_ALCOOL\n" );
   }
}
