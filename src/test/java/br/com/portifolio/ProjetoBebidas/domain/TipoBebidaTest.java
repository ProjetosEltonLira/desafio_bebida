package br.com.portifolio.ProjetoBebidas.domain;

import br.com.portifolio.ProjetoBebidas.model.domain.Bebida;
import br.com.portifolio.ProjetoBebidas.model.domain.BebidaSecao;
import br.com.portifolio.ProjetoBebidas.model.domain.Secao;
import br.com.portifolio.ProjetoBebidas.model.domain.TipoBebida;
import br.com.portifolio.ProjetoBebidas.model.dto.PedidoDto;
import br.com.portifolio.ProjetoBebidas.model.entities.BebidaEntity;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ExceptionError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TipoBebidaTest {

    Bebida bebidaAlcoolica;
    Bebida bebidaAlcoolica2;
    Bebida bebidaSemAlcool;
    Secao secao;
    BebidaSecao bebidaSecaoAlcoolica;
    BebidaSecao bebidaSecaoAlcoolica2;
    BebidaSecao bebidaSecaoSemAlcool;
    TipoBebida tipoBebidaAlcoolica;
    TipoBebida tipoBebidaSemAlcool;

    @BeforeEach // executa antes dos testes
    public void configuracaoInicial() {

        tipoBebidaAlcoolica = new TipoBebida(2, "ALCOOLICA", 500D);
        tipoBebidaSemAlcool = new TipoBebida(1, "SEM_ALCOOL", 400D);
        bebidaAlcoolica = new Bebida(1, "CACHACA", tipoBebidaAlcoolica);
        bebidaSemAlcool = new Bebida(3, "AGUA", tipoBebidaSemAlcool);
        secao = new Secao(1);
        bebidaSecaoAlcoolica = new BebidaSecao(bebidaAlcoolica, secao, 100.0);
        bebidaSecaoSemAlcool = new BebidaSecao(bebidaSemAlcool, secao, 100.0);
    }
        @Test
        @DisplayName("Não pode inserir bebida de um tipo diferente do que a seção permite")
        public void testevalidarTipoBebidaInvalido() {
            Throwable exception = Assertions.assertThrows(ExceptionError.class,
                    () -> this.bebidaSecaoAlcoolica.validarTipoBebida(tipoBebidaSemAlcool));

            String mensagemEsperada =  "A sessão SEM_ALCOOL só pode receber bebidas do mesmo tipo";
            String mensagemAtual = exception.getMessage();
            assertTrue(mensagemAtual.contains(mensagemEsperada));
        }

}

