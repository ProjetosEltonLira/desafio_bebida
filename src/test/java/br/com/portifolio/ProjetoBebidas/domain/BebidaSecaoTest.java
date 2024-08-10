package br.com.portifolio.ProjetoBebidas.domain;

import br.com.portifolio.ProjetoBebidas.model.Enum.TipoBebidaEnum;
import br.com.portifolio.ProjetoBebidas.model.domain.Bebida;
import br.com.portifolio.ProjetoBebidas.model.domain.BebidaSecao;
import br.com.portifolio.ProjetoBebidas.model.domain.Secao;
import br.com.portifolio.ProjetoBebidas.model.domain.TipoBebida;
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
    Bebida bebidaAlcoolica2;
    Bebida bebidaSemAlcool;
    Secao secao;
    BebidaSecao bebidaSecaoAlcoolica;
    BebidaSecao bebidaSecaoAlcoolica2;
    BebidaSecao bebidaSecaoSemAlcool;
    PedidoDto pedidoDto;
    TipoBebida tipoBebidaAlcoolica;
    TipoBebida tipoBebidaSemAlcool;

    @BeforeEach // executa antes dos testes
    public void configuracaoInicial() {

        tipoBebidaAlcoolica = new TipoBebida(2,"ALCOOLICA",500D);
        tipoBebidaSemAlcool = new TipoBebida(1,"SEM_ALCOOL",400D);
        bebidaAlcoolica = new Bebida(1L,"CACHACA",tipoBebidaAlcoolica);
        bebidaAlcoolica2 = new Bebida(2L,"SAQUE",tipoBebidaAlcoolica);
        bebidaSemAlcool = new Bebida(3L,"AGUA",tipoBebidaSemAlcool);
        secao = new Secao(1);
        bebidaSecaoAlcoolica = new BebidaSecao(bebidaAlcoolica,secao,100.0);
        bebidaSecaoAlcoolica2 = new BebidaSecao(bebidaAlcoolica2,secao,100.0);
        bebidaSecaoSemAlcool = new BebidaSecao(bebidaSemAlcool,secao,100.0);
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


    @Test
    @DisplayName("Validar impossibilidade de entrar com valores negativos no pedido")
    public void testeValidarValoresNegativosNoPedido() {

        pedidoDto = new PedidoDto(1, 1, -400D, "ENTRADA", "ELTON");
        Throwable exception = Assertions.assertThrows(ExceptionError.class,() -> bebidaSecaoAlcoolica.calcularQuantidadeBebida(pedidoDto,0.0));

        String mensagemEsperada = "Não é possível inserir um valor igual ou inferior a 0.0 litros de bebida na sessao";
        String mensagemAtual = exception.getMessage();
        assertTrue(mensagemAtual.contains(mensagemEsperada));
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

        pedidoDto = new PedidoDto(1, 1, 100.0, "SALIIDDA", "ELTON");
        Exception exception = Assertions.assertThrows(ExceptionError.class,() -> bebidaSecaoAlcoolica.calcularQuantidadeBebida(pedidoDto,100.0));

        String mensagemEsperada =  "Tipo de pedido inválido, informar: 'ENTRADA' ou 'SAIDA'";
        String mensagemAtual = exception.getMessage();
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }
}
