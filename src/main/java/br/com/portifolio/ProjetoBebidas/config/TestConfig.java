package br.com.portifolio.ProjetoBebidas.config;

import br.com.portifolio.ProjetoBebidas.repository.BebidaRepository;
import br.com.portifolio.ProjetoBebidas.repository.HistoricoRepository;
import br.com.portifolio.ProjetoBebidas.repository.SecaoBebidaRepository;
import br.com.portifolio.ProjetoBebidas.repository.SecaoRepository;
import br.com.portifolio.ProjetoBebidas.service.BebidaSecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//Essa classe server para implementar o database seeding (Inserir dados no banco de dados).
@Configuration //representa uma configuração
@Profile("test") //Deve ser igual ao nome que está no arquivo application.properties spring.profiles.active=test
public class TestConfig implements CommandLineRunner {

    @Autowired //O spring resolve a dependencia entre o TesteConfig e o Repository
    private BebidaRepository bebidaRepository;

    @Autowired //O spring resolve a dependencia entre o TesteConfig e o Repository
    private SecaoRepository secaoRepository;

    @Autowired //O spring resolve a dependencia entre o TesteConfig e o Repository
    private SecaoBebidaRepository secaoBebidaRepository;

    @Autowired //O spring resolve a dependencia entre o TesteConfig e o Repository
    private HistoricoRepository historicoRepository;

    @Autowired //O spring resolve a dependencia entre o TesteConfig e o Service
    private BebidaSecaoService bebidaSecaoService;

    //CommandLineRunner em tempo de execução, roda o comando abaixo.
    @Override
    public void run(String... args) {


        //TipoBebida tipoBebida = new TipoBebida();
        //tipoBebida.setId(TipoBebidaEnum.ALCOOLICA.getCodigo());

                //Optional<Sessao> optSessao =  sessaoRepository.findById(2l);
        //System.out.println(secaoRepository.findById(2l));
        //Bebida bebida = new Bebida();

        //Bebida bebida = new Bebida(1,"CAXAAÇA", tipoBebida);
        //bebidaRepository.save(bebida);

        //Secao secao = new Secao(1,400.0, 300.0);
        //secaoRepository.save(secao);

        //BebidaSecao bebidaSecao = new BebidaSecao(bebida,secao,100.0);
        //secaoBebidaRepository.save(bebidaSecao);
        //se já existir registro, o sistema faz um select de dados.
        //secaoBebidaRepository.save(new BebidaSecao(bebida,secao,10.0));
        //
        //secaoBebidaRepository.save(new BebidaSecao(bebida,secao,100.0));

        //HistoricoEntity historico = new HistoricoEntity(1,1,100.0,"ENTRADA","ELTON");
        //historicoRepository.save(historico);
        //System.out.println(historico);


        //PedidoDto pedidoDto = new PedidoDto(1,3,100.0,"ENTRADA","ELTON");
        //bebidaSecaoService.inserirPedido(pedidoDto);


    }
}


