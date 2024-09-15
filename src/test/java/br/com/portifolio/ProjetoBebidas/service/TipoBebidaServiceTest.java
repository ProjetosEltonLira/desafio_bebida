package br.com.portifolio.ProjetoBebidas.service;

import br.com.portifolio.ProjetoBebidas.model.domain.Bebida;
import br.com.portifolio.ProjetoBebidas.model.domain.Secao;
import br.com.portifolio.ProjetoBebidas.model.dto.PedidoDTO;
import br.com.portifolio.ProjetoBebidas.model.dto.PedidoResponseDTO;
import br.com.portifolio.ProjetoBebidas.model.dto.TipoBebidaDTO;
import br.com.portifolio.ProjetoBebidas.model.entities.BebidaEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.BebidaSecaoEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.SecaoEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.TipoBebidaEntity;
import br.com.portifolio.ProjetoBebidas.repository.SecaoBebidaRepository;
import br.com.portifolio.ProjetoBebidas.repository.TipoBebidaRepository;
import br.com.portifolio.ProjetoBebidas.service.exceptions.DataBaseException;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ExceptionError;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@DisplayName("TipoBebidaServiceTest")
public class TipoBebidaServiceTest {

    @InjectMocks //cria uma instância igual a que uso quando a aplicação executa.
    private TipoBebidaService tipoBebidaService;

    @Mock
    private TipoBebidaRepository tipoBebidaRepository;

    TipoBebidaEntity tipoBebidaEntity;
    TipoBebidaDTO tipoBebidaDTO;

    @BeforeEach // executa antes dos testes
    public void configuracaoInicial() {

        tipoBebidaDTO = new TipoBebidaDTO(1, "ALCOOLICA", 500D);
        tipoBebidaEntity = new TipoBebidaEntity(1, "ALCOOLICA", 500D);
    }

    @Test
    @DisplayName("Teste Inserir tipo bebida")
    public void testeInserirPedidoBebida(){

       when(tipoBebidaRepository.save(any())).thenReturn(tipoBebidaEntity);
       TipoBebidaDTO retornoTipoBebidaDto = tipoBebidaService.inserir(tipoBebidaDTO);

       assertEquals(retornoTipoBebidaDto.getId(),tipoBebidaDTO.getId());
       assertEquals(retornoTipoBebidaDto.getDescricao(),tipoBebidaDTO.getDescricao());
       assertEquals(retornoTipoBebidaDto.getCapacidade(),tipoBebidaDTO.getCapacidade());
    }

    @Test
    @DisplayName("Testar a exceção de registro não encontrado")
    public void delete_ResourceNotFoundException() {
        Long id = 1L;
        // Simula o comportamento do repositório lançando a exceção EmptyResultDataAccessException
        doThrow(EmptyResultDataAccessException.class).when(tipoBebidaRepository).deleteById(id);

        // Verifica se a exceção ResourceNotFoundException é lançada
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            tipoBebidaService.delete(id);
        });

        // Verifica se o método deleteById foi chamado com o ID correto
        Mockito.verify(tipoBebidaRepository).deleteById(id);
    }

    @Test
    @DisplayName("Teste para deletar um registro não encontrado")
    public void delete_DataBaseException() {
        Long id = 1L;
        // Simula o comportamento do repositório lançando a exceção DataIntegrityViolationException
        doThrow(DataIntegrityViolationException.class).when(tipoBebidaRepository).deleteById(id);

        // Verifica se a exceção DataBaseException é lançada
        Assertions.assertThrows(DataBaseException.class, () -> {
            tipoBebidaService.delete(id);
        });

        // Verifica se o método deleteById foi chamado com o ID correto
        Mockito.verify(tipoBebidaRepository).deleteById(id);
    }

    @Test
    @DisplayName("Teste pesquisa de registro")
    public void pesquisarPorId() {
        Long id = 1L;

        when(tipoBebidaRepository.findById(any())).thenReturn(Optional.ofNullable(tipoBebidaEntity));

        TipoBebidaDTO retornoTipoBebidaDto = tipoBebidaService.pesquisarPorId(id);
        assertEquals(retornoTipoBebidaDto.getId(),tipoBebidaEntity.getId());
        assertEquals(retornoTipoBebidaDto.getDescricao(),tipoBebidaEntity.getDescricao());
        assertEquals(retornoTipoBebidaDto.getCapacidade(),tipoBebidaEntity.getCapacidade());
    }

    @Test
    @DisplayName("Testar a exceção de registro não encontrado")
    public void findById_ResourceNotFoundException() {
        Long id = 1L;

        // Simula o comportamento do repositório lançando a exceção EmptyResultDataAccessException
        doThrow(ResourceNotFoundException.class).when(tipoBebidaRepository).findById(id);

        // Verifica se a exceção ResourceNotFoundException é lançada
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            tipoBebidaService.pesquisarPorId(id);
        });

        // Verifica se o método deleteById foi chamado com o ID correto
        Mockito.verify(tipoBebidaRepository).findById(id);
    }

    @Test
    public void findAll_ShouldReturnListOfTipoBebidaEntities() {
        // Cria uma lista simulada de TipoBebidaEntity
        List<TipoBebidaEntity> listBebidaEntities = Arrays.asList(
                new TipoBebidaEntity(1, "Coca-Cola", 350D),
                new TipoBebidaEntity(2, "Pepsi", 300D)
        );

        // Simula o comportamento do repositório ao chamar o método findAll()
        when(tipoBebidaRepository.findAll()).thenReturn(listBebidaEntities);

        // Chama o método findAll() da classe de serviço
        List<TipoBebidaEntity> result = tipoBebidaRepository.findAll();

        // Verifica se o resultado é igual à lista simulada
        assertEquals(listBebidaEntities, result);

        // Verifica se o método findAll() do repositório foi chamado
        Mockito.verify(tipoBebidaRepository).findAll();
    }


    @Test
    @DisplayName("Testar a atualização de registros do Tipo de bebida")
    public void Update_TipoBebidaService() {
        Long id = 2L;
        tipoBebidaDTO = new TipoBebidaDTO(2, "ALCOOLICA", 500D);

        // Simula o comportamento do repositório lançando a exceção EmptyResultDataAccessException
        when(tipoBebidaRepository.existsById(id)).thenReturn(true);
        // Chama o método de update
        tipoBebidaService.update(id, tipoBebidaDTO);

        // Verifica se o método save foi chamado com a entidade correta
        verify(tipoBebidaRepository, times(1)).save(Mockito.any(TipoBebidaEntity.class));
    }

    @Test
    @DisplayName("Não Atualizar, registro não encontrado")
    public void update_ResourceNotFoundException() {
        Long id = 1L;

        // Simula o comportamento do repositório lançando a exceção EmptyResultDataAccessException
        doThrow(ResourceNotFoundException.class).when(tipoBebidaRepository).existsById(id);
        // Verifica se a exceção ResourceNotFoundException é lançada
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            tipoBebidaService.update(id,tipoBebidaDTO);
        });
        // Verifica que o método save não foi chamado
        verify(tipoBebidaRepository, times(0)).save(Mockito.any(TipoBebidaEntity.class));
    }
}
