package br.com.portifolio.ProjetoBebidas.entities;

import br.com.portifolio.ProjetoBebidas.Enum.TipoBebida;
import br.com.portifolio.ProjetoBebidas.Model.entities.Bebida;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BebidaTest {

    private Bebida bebida;

    @BeforeEach // executa antes dos testes
    public void configuracaoInicial() {
        //this.bebida = new Bebida(TipoDeBebida.ALCOOLICA,"CACHACA",100);
      this.bebida = new Bebida("CACHACA", TipoBebida.ALCOOLICA,100);
    }

    @Test //o método aqui, sempre será void e não recebe parametros
    public void testeToString() {
        //Assertions.assertEquals(this.bebida.toString(), "Nome:CACHACA Tipo:ALCOOLICA Qtde:100.0");
    }

}
