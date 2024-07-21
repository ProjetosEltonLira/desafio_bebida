package br.com.portifolio.ProjetoBebidas.entities;

import br.com.portifolio.ProjetoBebidas.Enum.TipoBebida;
import br.com.portifolio.ProjetoBebidas.Model.entities.Bebida;
import br.com.portifolio.ProjetoBebidas.Model.entities.Estoque;
import br.com.portifolio.ProjetoBebidas.Model.entities.Sessao;
import br.com.portifolio.ProjetoBebidas.exception.ExceptionError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.portifolio.ProjetoBebidas.Enum.TipoBebida.*;

public class TestEstoque {

    Estoque estoque = new Estoque();
    Sessao sessaoAlcoolica;
    Sessao sessaoNaoAlcolico;

    @BeforeEach // executa antes dos testes
    public void configuracaoInicial() throws ExceptionError {

        sessaoAlcoolica = new Sessao(1, 500, 0,ALCOOLICA);
        sessaoAlcoolica.adicionarBebida(new Bebida(ALCOOLICA,"CACHACA",100));
        sessaoNaoAlcolico = new Sessao(2, 400, 0,SEM_ALCOOL);
        sessaoNaoAlcolico.adicionarBebida(new Bebida(SEM_ALCOOL,"SUCO DE UVA",200));
        sessaoNaoAlcolico.adicionarBebida(new Bebida(SEM_ALCOOL,"SUCO DE LARANJA",100));
        estoque.adicionarSessao(sessaoAlcoolica);
        estoque.adicionarSessao(sessaoNaoAlcolico);

    }
    @Test
    public void testeEstoqueToString() {
        Assertions.assertEquals(estoque.toString(), "" +
                "Estoque[" +
                "Sessao{id:1 Capacidade:500.0 VolumeAtual:100.0 Tipo:ALCOOLICA " +
                                     "ListaBebida:[Nome:CACHACA Tipo:ALCOOLICA Qtde:100.0]}, " +
                "Sessao{id:2 Capacidade:400.0 VolumeAtual:300.0 Tipo: SEM ALCOOL " +
                                     "ListaBebida:[Nome:SUCO DE UVA Tipo:SEM ALCOOL Qtde:200.0, " +
                                              "Nome:SUCO DE LARANJA Tipo:SEM ALCOOL Qtde:100.0]}]}");
    }

    @Test
    public void testeAdicionarSessao(){
        Assertions.assertTrue(estoque.checarDisponibilidadeSessao());
    }

    @Test
    public void testeAdicionarSessaoExcedente() throws ExceptionError {
        estoque.adicionarSessao(sessaoAlcoolica);
        estoque.adicionarSessao(sessaoAlcoolica);
        estoque.adicionarSessao(sessaoAlcoolica);
        estoque.adicionarSessao(sessaoAlcoolica);
        Assertions.assertFalse(estoque.checarDisponibilidadeSessao());
    }

    @Test
    public void testeRemoverUmaSessaoExistente(){
        Assertions.assertTrue(estoque.removerSessao(1));
    }

    @Test
    public void testeRemoverUmaSessaoInexistente(){
        Assertions.assertFalse(estoque.removerSessao(6));
    }
}


