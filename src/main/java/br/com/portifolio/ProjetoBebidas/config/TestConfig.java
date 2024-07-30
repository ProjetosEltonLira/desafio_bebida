package br.com.portifolio.ProjetoBebidas.config;

import br.com.portifolio.ProjetoBebidas.model.Bebida;
import br.com.portifolio.ProjetoBebidas.model.Enum.TipoBebida;
import br.com.portifolio.ProjetoBebidas.model.entities.BebidaEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.SecaoBebidaEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.SecaoEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.TipoBebidaEntity;
import br.com.portifolio.ProjetoBebidas.repository.BebidaRepository;
import br.com.portifolio.ProjetoBebidas.repository.SecaoBebidaRepository;
import br.com.portifolio.ProjetoBebidas.repository.SecaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//Essa classe server para implementar o database seeding (Inserir dados no banco de dados).
@Configuration //representa uma configuração
@Profile("test") //Deve ser igual ao nome que está no arquivo application.properties spring.profiles.active=test
public class TestConfig implements CommandLineRunner {

    @Autowired //O spring resolve a dependencia entre o TesteConfig e o UserRepository
    private BebidaRepository bebidaRepository;

    @Autowired //O spring resolve a dependencia entre o TesteConfig e o UserRepository
    private SecaoRepository secaoRepository;

    @Autowired //O spring resolve a dependencia entre o TesteConfig e o UserRepository
    private SecaoBebidaRepository secaoBebidaRepository;

    //CommandLineRunner em tempo de execução, roda o comando abaixo.
    @Override
    public void run(String... args) {

        TipoBebidaEntity tipoBebidaAlcoolica = new TipoBebidaEntity(TipoBebida.ALCOOLICA.getCodigo());
        TipoBebidaEntity tipoBebidaSemAlcool = new TipoBebidaEntity(TipoBebida.SEM_ALCOOL.getCodigo());

        //Optional<Sessao> optSessao =  sessaoRepository.findById(2l);
        //System.out.println(secaoRepository.findById(2l));
        //Bebida bebida = new Bebida();

        BebidaEntity bebida = new BebidaEntity("CACHAÇA", tipoBebidaAlcoolica);
        //bebidaRepository.save(bebida);

        SecaoEntity secao = new SecaoEntity(500.0, 0.0);
        //secaoRepository.save(secao);

        SecaoBebidaEntity secaoBebida = new SecaoBebidaEntity(secao, bebida, 100);
        secaoBebidaRepository.save(secaoBebida);
    }
}


