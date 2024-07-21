package br.com.portifolio.ProjetoBebidas.entities;

import br.com.portifolio.ProjetoBebidas.Model.entities.Bebida;
import br.com.portifolio.ProjetoBebidas.Model.entities.Sessao;
import br.com.portifolio.ProjetoBebidas.exception.ExceptionError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.portifolio.ProjetoBebidas.Enum.TipoBebida.*;


public class SessaoTest {

    Bebida bebida;
    Sessao sessaoAlcoolica ;
    Sessao sessaoNaoAlcolico;

    @BeforeEach
    public void configuracaoInicial() {

        sessaoAlcoolica = new Sessao(1, 500, 0,ALCOOLICA);
        sessaoNaoAlcolico = new Sessao(1, 400, 0,SEM_ALCOOL);
    }
    @Test
    public void testeSessaoToString() {
        Assertions.assertEquals(this.sessaoAlcoolica.toString(), "Sessao{id:1 Capacidade:500.0 VolumeAtual:0.0 Tipo:ALCOOLICA ListaBebida:[]}");
    }

    @Test
    public void testeErroAdicionarUmaBebidaSemAlcoolEmUmaSessaoDeBebibasAlcoolicas() throws ExceptionError {
        bebida = new Bebida(SEM_ALCOOL,"SUCO DE UVA",100);
        Throwable exception = Assertions.assertThrows(ExceptionError.class, () -> this.sessaoAlcoolica.adicionarBebida(bebida));
        Assertions.assertEquals(exception.getMessage(), "A sessão ALCOOLICA só pode receber bebidas do mesmo tipo");
    }


    @Test
    public void testeErroAdicionarUmaBebidaAlcoolicaEmUmaSessaoDeBebibasSemAlcool() throws ExceptionError {
        bebida = new Bebida(ALCOOLICA,"CACHACA",100);
        Throwable exception = Assertions.assertThrows(ExceptionError.class, () -> this.sessaoNaoAlcolico.adicionarBebida(bebida));
        Assertions.assertEquals(exception.getMessage(), "A sessão SEM_ALCOOL só pode receber bebidas do mesmo tipo");
    }

    @Test //Teste para validar que não pode ser inserir bebida que exceda a capacidade permitida.
    public void testeConsultarLimiteSessaoIndisponível() throws ExceptionError {
        bebida = new Bebida(ALCOOLICA,"CACHACA",600);
        Throwable exception = Assertions.assertThrows(ExceptionError.class, () -> this.sessaoAlcoolica.adicionarBebida(bebida));
        Assertions.assertEquals(exception.getMessage(), "A sessao possui 500.0 litros disponíveis, não é possível inserir 600.0 litros da bebida");
    }

    @Test //Teste para validar que não pode ser inserir bebida que exceda a capacidade permitida.
    public void testeInserirValorNegativoDeBebida() throws ExceptionError {
        bebida = new Bebida(ALCOOLICA,"CACHACA",-1);
        Throwable exception = Assertions.assertThrows(ExceptionError.class, () -> this.sessaoAlcoolica.adicionarBebida(bebida));
        Assertions.assertEquals(exception.getMessage(), "Não é possível inserir um valor igual ou inferior a 0 litros de bebida na sessao");
    }


    @Test //Adicionou uma bebida.
    public void testeInserirUmaBebidaNaSessao() throws ExceptionError {
        bebida = new Bebida(ALCOOLICA,"CACHACA",500);
        this.sessaoAlcoolica.adicionarBebida(bebida);
        Assertions.assertEquals(this.sessaoAlcoolica.toString(), "Sessao{" +
                "id:1 Capacidade:500.0 VolumeAtual:500.0 Tipo:ALCOOLICA " +
                     "ListaBebida:[Nome:CACHACA Tipo:ALCOOLICA Qtde:500.0]}");

    }

    @Test //Inserir mais de uma bebida na sessao.
    public void testeInserirMaisDeUmaBebidaNaSessao() throws ExceptionError {
        sessaoNaoAlcolico.adicionarBebida(new Bebida(SEM_ALCOOL,"SUCO DE UVA",200));
        sessaoNaoAlcolico.adicionarBebida(new Bebida(SEM_ALCOOL,"SUCO DE LARANJA",100));
        Assertions.assertEquals(this.sessaoNaoAlcolico.toString(),
                "Sessao{id:1 Capacidade:400.0 VolumeAtual:300.0 Tipo:SEM_ALCOOL " +
                                       "ListaBebida:[Nome:SUCO DE UVA Tipo:SEM_ALCOOL Qtde:200.0, " +
                                                "Nome:SUCO DE LARANJA Tipo:SEM_ALCOOL Qtde:100.0]}");
    }
}
