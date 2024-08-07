package br.com.portifolio.ProjetoBebidas.domain;

import br.com.portifolio.ProjetoBebidas.model.Enum.TipoBebidaEnum;
import br.com.portifolio.ProjetoBebidas.model.domain.Bebida;
import br.com.portifolio.ProjetoBebidas.model.domain.BebidaSecao;
import br.com.portifolio.ProjetoBebidas.model.domain.Secao;
import br.com.portifolio.ProjetoBebidas.model.dto.PedidoDto;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ExceptionError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BebidaSecaoTest {

    Bebida bebidaAlcoolica;
    Bebida bebidaSemAlcool;
    Secao secao;
    BebidaSecao bebidaSecaoAlcoolica;
    BebidaSecao bebidaSecaoSemAlcool;
    PedidoDto pedidoDto;

    @BeforeEach // executa antes dos testes
    public void configuracaoInicial() {


       bebidaAlcoolica = new Bebida(1,"CACHACA", TipoBebidaEnum.ALCOOLICA);
       bebidaSemAlcool = new Bebida(3,"AGUA", TipoBebidaEnum.SEM_ALCOOL);
       secao = new Secao(1,500.0);
       bebidaSecaoAlcoolica = new BebidaSecao(bebidaAlcoolica,secao,100.0);
       bebidaSecaoSemAlcool = new BebidaSecao(bebidaSemAlcool,secao,100.0);
    }

    @Test
    @DisplayName("Não pode inserir bebida de um tipo diferente do que a seção permite")
    public void testevalidarTipoBebidaInvalido() {
        Throwable exception = Assertions.assertThrows(ExceptionError.class,
                () -> this.bebidaSecaoSemAlcool.validarTipoBebida(bebidaAlcoolica.getTipoBebida()));

        String mensagemEsperada =  "A sessão ALCOOLICA só pode receber bebidas do mesmo tipo";
        String mensagemAtual = exception.getMessage();
        assertTrue(mensagemAtual.contains(mensagemEsperada));

    }

    @Test
    @DisplayName("Validar a entrada de bebida do mesmo tipo da seção")
    public void testevalidarTipoBebidavalido() {

        TipoBebidaEnum mensagemEsperada =  bebidaSemAlcool.getTipoBebida();
        TipoBebidaEnum mensagemAtual = bebidaSecaoSemAlcool.getBebida().getTipoBebida();
        assertEquals(mensagemAtual, mensagemEsperada);
    }

    @Test
    @DisplayName("Calcular valor a ser adicionado na seção, o valor do pedido + valor existente na secao")
    public void testeCalcularValorDaEntradaMaisValorExistenteNaSecao() {
        pedidoDto = new PedidoDto(1, 1, 100.0, "ENTRADA", "ELTON");
        bebidaSecaoAlcoolica.calcularQuantidadeBebida(pedidoDto,100D);
        assertEquals(bebidaSecaoAlcoolica.getQuantidade(),200D);
    }
    @Test
    @DisplayName("Calcular valor a ser adicionado na seção, o valor do pedido + 0 valor existente na seção")
    public void testeCalcularValorDaEntradaSemValorExistenteNaSecao() {

        pedidoDto = new PedidoDto(1, 1, 100.0, "ENTRADA", "ELTON");
        bebidaSecaoAlcoolica.calcularQuantidadeBebida(pedidoDto,0.0);
        assertEquals(bebidaSecaoAlcoolica.getQuantidade(),100D);
    }

    @Test
    @DisplayName("Calcular valor a ser SUBTRAIDO DA seção, retirar tudo da secao")
    public void testeCalcularValorASerSubtraidoDaSecao() {
        pedidoDto = new PedidoDto(1, 1, 100.0, "SAIDA", "ELTON");
        bebidaSecaoAlcoolica.calcularQuantidadeBebida(pedidoDto,100D);
        assertEquals(bebidaSecaoAlcoolica.getQuantidade(),0D);
    }

    @Test
    @DisplayName("Calcular valor a ser SUBTRAIDO DA seção, deixar uma sobra na secao")
    public void teste2CalcularValorASerSubtraidoDaSecao() {
        pedidoDto = new PedidoDto(1, 1, 70D, "SAIDA", "ELTON");
        bebidaSecaoAlcoolica.calcularQuantidadeBebida(pedidoDto,100D);
        assertEquals(bebidaSecaoAlcoolica.getQuantidade(),30D);
    }

    @Test
    @DisplayName("Valor a ser retirado da Secao é superior ao valor disponível na secao")
    public void testeCalcularValorDeSaidaSemValorExistenteNaSecao() {

        pedidoDto = new PedidoDto(1, 1, 100.0, "SAIDA", "ELTON");
        Throwable exception = Assertions.assertThrows(ExceptionError.class,() -> bebidaSecaoAlcoolica.calcularQuantidadeBebida(pedidoDto,0.0));

        String mensagemEsperada =  "Não é possível retirar mais bebidas do que existe na secao, consulte a quantidade de bebida disponível nessa secao.";
        String mensagemAtual = exception.getMessage();
        assertTrue(mensagemAtual.contains(mensagemEsperada));

    }

    @Test
    @DisplayName("tipo de pedido invalido")
    public void testeValidarTipoPedido() {

        pedidoDto = new PedidoDto(1, 1, 100.0, "SALIDA", "ELTON");
        Exception exception = Assertions.assertThrows(ExceptionError.class,() -> bebidaSecaoAlcoolica.calcularQuantidadeBebida(pedidoDto,100.0));

        String mensagemEsperada =  "Tipo de pedido inválido, informar: 'ENTRADA' ou 'SAIDA'";
        String mensagemAtual = exception.getMessage();
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }








}
