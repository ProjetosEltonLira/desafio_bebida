package br.com.portifolio.ProjetoBebidas.service;


import br.com.portifolio.ProjetoBebidas.model.dto.BebidaDTO;
import br.com.portifolio.ProjetoBebidas.model.entities.BebidaEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.TipoBebidaEntity;
import br.com.portifolio.ProjetoBebidas.repository.BebidaRepository;
import br.com.portifolio.ProjetoBebidas.service.exceptions.DataBaseException;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@DisplayName("BebidaServiceTest")
public class BebidaServiceTest {

    @InjectMocks //cria uma instância igual a que uso quando a aplicação executa.
    private BebidaService bebidaService;

    @Mock
    private BebidaRepository bebidaRepository;

    @Mock
    private TipoBebidaService tipoBebidaService;

    BebidaDTO bebidaDTO;
    BebidaEntity bebidaEntity;
    TipoBebidaEntity tipoBebidaEntity;

    @BeforeEach // executa antes dos testes
    public void configuracaoInicial() {

        bebidaDTO = new BebidaDTO(1,"51 uma boa escolha",2);
        tipoBebidaEntity = new TipoBebidaEntity(2, "ALCOOLICA", 500D);
        bebidaEntity = new BebidaEntity(1L, "51 uma boa escolha", tipoBebidaEntity);

    }

    @Test
    @DisplayName("Deve inserir uma bebida")
    public void testeInserirBebida(){

        // Simula o retorno de tipoBebidaService e do repository.save()
        when(tipoBebidaService.findById(any())).thenReturn(tipoBebidaEntity);
        when(bebidaRepository.save(any())).thenReturn(bebidaEntity);

        //Chama o método
        BebidaEntity resultado = bebidaService.inserir(bebidaDTO);

        // Verifica se o método save foi chamado e se o resultado está correto
        verify(bebidaRepository, times(1)).save(any(BebidaEntity.class));
        Assertions.assertEquals(bebidaEntity, resultado);
    }

    //Teste para o método delete (deve deletar com sucesso)
    @Test
    void delete_PodeDeletarBebida_QuandoIdExiste() {
        Long id = 1L;

        // Chama o método
        bebidaService.delete(id);

        // Verifica se o método deleteById foi chamado
        verify(bebidaRepository, times(1)).deleteById(id);
    }

    // Teste para o método delete (deve lançar ResourceNotFoundException)
    @Test
    void delete_PodeLancarExcecaoResourceNotFoundException_QuandoIdNaoExiste() {
        Long id = 1L;

        // Simula o lançamento da exceção EmptyResultDataAccessException
        doThrow(EmptyResultDataAccessException.class).when(bebidaRepository).deleteById(id);

        // Verifica se a exceção ResourceNotFoundException é lançada
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            bebidaService.delete(id);
        });

        // Verifica se o método deleteById foi chamado
        verify(bebidaRepository, times(1)).deleteById(id);
    }

    // Teste para o método delete (deve lançar DataBaseException)
    @Test
    void delete_PodeLancarExcecaoDataBaseException_WhenDataIntegrityViolationOccurs() {
        Long id = 1L;

        // Simula o lançamento da exceção DataIntegrityViolationException
        doThrow(DataIntegrityViolationException.class).when(bebidaRepository).deleteById(id);

        // Verifica se a exceção DataBaseException é lançada
        Assertions.assertThrows(DataBaseException.class, () -> {
            bebidaService.delete(id);
        });

        // Verifica se o método deleteById foi chamado
        verify(bebidaRepository, times(1)).deleteById(id);
    }

    // Teste para o método findById (deve retornar a bebida)
    @Test
    void findById_PodeRetornarBebida_QuandoIdExiste() {
        Long id = 1L;

        // Simula o retorno de findById()
        when(bebidaRepository.findById(id)).thenReturn(Optional.of(bebidaEntity));

        // Chama o método
        BebidaEntity result = bebidaService.findById(id);

        // Verifica o resultado
        Assertions.assertEquals(bebidaEntity, result);
        verify(bebidaRepository, times(1)).findById(id);
    }

    // Teste para o método findById (deve lançar ResourceNotFoundException)
    @Test
    void findById_PodeLancarExcecaoResourceNotFoundException_QuandoIdNaoExiste() {
        Long id = 1L;

        // Simula o retorno de findById() como vazio
        when(bebidaRepository.findById(id)).thenReturn(Optional.empty());

        // Verifica se a exceção ResourceNotFoundException é lançada
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            bebidaService.findById(id);
        });

        // Verifica se o método findById foi chamado
        verify(bebidaRepository, times(1)).findById(id);
    }

    // Teste para o método findAll (deve retornar a lista de bebidas)
    @Test
    void findAll_PodeRetornarUmaListaDeBebidas() {

        List<BebidaEntity> ListaBebidaEntities = Arrays.asList(
                new BebidaEntity(1L, "Coca-Cola", tipoBebidaEntity),
                new BebidaEntity(2L, "Pepsi", tipoBebidaEntity)
        );
        // Simula o retorno do método findAll()
        when(bebidaRepository.findAll()).thenReturn(ListaBebidaEntities);

        // Chama o método
        List<BebidaEntity> result = bebidaService.findAll();

        // Verifica o resultado
        Assertions.assertEquals(ListaBebidaEntities, result);
        verify(bebidaRepository, times(1)).findAll();
    }

    // Teste para o método update (deve atualizar a bebida com sucesso)
    @Test
    void update_PodeAtualizarBebida_QuandoIdExiste() {
        Long id = 1L;

        // Simula o retorno de findById e tipoBebidaService
        when(bebidaRepository.findById(id)).thenReturn(Optional.of(bebidaEntity));
        when(tipoBebidaService.findById(bebidaDTO.getTipoBebidaId())).thenReturn(tipoBebidaEntity);

        // Chama o método update
        bebidaService.update(id, bebidaDTO);

        // Verifica se o método save foi chamado com a entidade atualizada
        verify(bebidaRepository, times(1)).save(any(BebidaEntity.class));
    }

    // Teste para o método update (deve lançar ResourceNotFoundException)
    @Test
    void update_PodeLancarExcecaoResourceNotFoundException_QuandoIdNaoExiste() {
        Long id = 1L;

        // Simula o retorno de findById como vazio
        when(bebidaRepository.findById(id)).thenReturn(Optional.empty());

        // Verifica se a exceção ResourceNotFoundException é lançada
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            bebidaService.update(id, bebidaDTO);
        });

        // Verifica se o método save não foi chamado
        verify(bebidaRepository, times(0)).save(any(BebidaEntity.class));
    }


}
