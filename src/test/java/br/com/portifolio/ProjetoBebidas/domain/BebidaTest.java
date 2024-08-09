package br.com.portifolio.ProjetoBebidas.domain;

import br.com.portifolio.ProjetoBebidas.model.domain.Bebida;
import br.com.portifolio.ProjetoBebidas.model.domain.TipoBebida;
import br.com.portifolio.ProjetoBebidas.model.entities.BebidaEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BebidaTest {

    private Bebida bebida;
    private Bebida bebida2 = new Bebida();
    private TipoBebida tipoBebida;

    @BeforeEach // executa antes dos testes
    public void configuracaoInicial() {
      tipoBebida = new TipoBebida(1,"ALCOOLICA",500D);
      bebida = new Bebida(1,"CACHACA",tipoBebida);
      bebida2.setId(1);
      bebida2.setNome("ALOOLICA");
      bebida2.setTipoBebida(tipoBebida);

    }

    @Test //o método aqui, sempre será void e não recebe parametros
    public void testeToString() {
        assertEquals(this.bebida.toString(), "Bebida{id=1, nome='CACHACA', tipoBebidam=TipoBebida{id=1, descricao='ALCOOLICA', capacidade=500.0, bebida=null}}");
    }
}
