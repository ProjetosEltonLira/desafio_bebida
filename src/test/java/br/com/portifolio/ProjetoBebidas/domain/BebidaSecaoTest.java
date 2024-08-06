package br.com.portifolio.ProjetoBebidas.domain;

import br.com.portifolio.ProjetoBebidas.model.Enum.TipoBebidaEnum;
import br.com.portifolio.ProjetoBebidas.model.domain.Bebida;
import br.com.portifolio.ProjetoBebidas.model.domain.BebidaSecao;
import br.com.portifolio.ProjetoBebidas.model.domain.Secao;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ExceptionError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BebidaSecaoTest {

    Bebida bebidaAlcoolica;
    Bebida bebidaSemAlcool;
    Secao secao;
    BebidaSecao bebidaSecaoAlcoolica;
    BebidaSecao bebidaSecaoSemAlcool;

    @BeforeEach // executa antes dos testes
    public void configuracaoInicial() {
       bebidaAlcoolica = new Bebida(1,"CACHACA", TipoBebidaEnum.ALCOOLICA);
       bebidaSemAlcool = new Bebida(3,"AGUA", TipoBebidaEnum.SEM_ALCOOL);
       secao = new Secao(1,500.0);
       bebidaSecaoAlcoolica = new BebidaSecao(bebidaAlcoolica,secao,100.0);
       bebidaSecaoSemAlcool = new BebidaSecao(bebidaSemAlcool,secao,100.0);
    }

    @Test
    public void testevalidarTipoBebidaInvalido() {
        Throwable exception = Assertions.assertThrows(ExceptionError.class,
                () -> this.bebidaSecaoSemAlcool.validarTipoBebida(bebidaAlcoolica.getTipoBebida()));
        Assertions.assertEquals(exception.getMessage(), "A sessão ALCOOLICA só pode receber bebidas do mesmo tipo");
    }

    @Test
    public void testevalidarTipoBebidavalido() {
       bebidaSecaoSemAlcool.validarTipoBebida(bebidaSemAlcool.getTipoBebida());
       Assertions.assertEquals(bebidaSecaoSemAlcool.getBebida().getTipoBebida(),bebidaSemAlcool.getTipoBebida());
    }





}
