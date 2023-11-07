package br.com.apipixv3.apipixv3.service;

import br.com.apipixv3.apipixv3.domain.ChavePix;
import br.com.apipixv3.apipixv3.domain.Cliente;
import br.com.apipixv3.apipixv3.repository.ChavePixRepository;
import br.com.apipixv3.apipixv3.validation.LimiteChaveValidation;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Data
class ChavePixServiceTest {

    @Mock
    private ChavePixRepository chavePixRepository;

    @Mock
    private LimiteChaveValidation validation;

    @Mock
    private ChaveServiceImplement chaveServiceImplement;

    @InjectMocks
    private ChavePixService chavePixService;

    ChavePix chavePix;
    Cliente cliente;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        chavePix = new ChavePix();
        chavePix.setId(UUID
                .fromString("26d32f11-72ff-427a-bf6c-0e1d0ac94cff"));
        chavePix.setTipo("cpf");
        chavePix.setValor("04937499696");
        chavePix.setDataEhora(LocalDateTime.now());
        chavePix.setCliente(cliente);

        cliente = new Cliente();
        cliente.setNomeCorrentista("Flavio silva");
        cliente.setNumeroConta("3075");
        cliente.setNumeroAgencia("088421");
        cliente.setTipoConta("corrente");
        cliente.setTipoPessoa("fisica");
        cliente.setId(1L);

    }

    @Test
    public void esperaUmCpfValido() {
        doNothing().when(validation).limiteChaveValidation(chavePix);
        when(chaveServiceImplement
                .isCpfValido("cpf", "04937499696")).thenReturn(true);
        when(chavePixRepository.save(chavePix)).thenReturn(chavePix);
        chavePixService.cadastrar(chavePix);
        verify(chavePixRepository, times(1)).save(chavePix);
    }


    @Test
    void ESPERArecuperarTodoscOMsUCESSO() {
        List<ChavePix> chaves = new ArrayList<>();
        chaves.add(new ChavePix());
        when(chavePixRepository.findAll()).thenReturn(chaves);
        List<ChavePix> esperado = chavePixService.recuperarTodos();
        verify(chavePixRepository, times(1)).findAll();
        assertEquals(chaves, esperado);
    }

    @Test
    public void esperaLancarExetionAoTentarCadastrarInexistente() {
        when(chavePixRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(IllegalArgumentException.class, ()
                -> chavePixService.recuperarTodos());
        verify(chavePixRepository, times(1)).findAll();
    }

    @Test
    void ESPERArecuperarChavePorIdcOMsUCESSO() {
        UUID id = UUID.randomUUID();
        Optional<ChavePix> chavePixOptional = Optional.of(chavePix);
        when(chavePixRepository.findById(id)).thenReturn(chavePixOptional);
        Optional<ChavePix> esperado = chavePixService.recuperarChavePorId(id);
        verify(chavePixRepository, times(1)).findById(id);
        assertEquals(chavePixOptional, esperado);
    }

    @Test
    public void esperaLancarExeptioAoTentarRecuperarChaveComIdInexistente() {
        UUID id = UUID.randomUUID();
        when(chavePixRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, ()
                -> chavePixService.recuperarChavePorId(id));
        verify(chavePixRepository, times(1)).findById(id);
    }

    @Test
    void recuperarChavesPorIdCliente() {
        Long idCliente = 1L;
        List<ChavePix> chaves = new ArrayList<>();
        chaves.add(new ChavePix());
        when(chavePixRepository.findByClienteId(idCliente)).thenReturn(chaves);
        List<ChavePix> esperado = chavePixService
                .recuperarChavesPorIdCliente(idCliente);
        verify(chavePixRepository, times(1))
                .findByClienteId(idCliente);
        assertEquals(chaves, esperado);

    }

    @Test
    public void esperaLancarExeptionAorecuperarPorIdClienteInexistente() {
        Long idCliente = 1L;
        when(chavePixRepository.findByClienteId(idCliente))
                .thenReturn(new ArrayList<>());
        assertThrows(IllegalArgumentException.class, ()
                -> chavePixService.recuperarChavesPorIdCliente(idCliente));
        verify(chavePixRepository, times(1))
                .findByClienteId(idCliente);
    }

    @Test
    void atualizar() {
        when(chaveServiceImplement.isCpfValido
                ("cpf", "04937499696")).thenReturn(true);
        when(chavePixRepository.save(chavePix)).thenReturn(chavePix);
        ChavePix resultado = chavePixService.atualizar(chavePix);
        verify(chaveServiceImplement, times(1))
                .isCpfValido("cpf", "04937499696");
        verify(chavePixRepository, times(1)).save(chavePix);
        assertEquals(chavePix, resultado);

    }

    @Test
    public void atualizar_CpfInvalido_IllegalArgumentException() {
//        chavePix.setId(UUID.fromString("26d32f11-72ff-427a-bf6c-0e1d0ac94cff"));
//        chavePix.setTipo("cpf");
        chavePix.setValor("12345678901"); // CPF inválido
        when(chaveServiceImplement.isCpfValido("cpf", "12345678901")).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> chavePixService.atualizar(chavePix));
        verify(chaveServiceImplement, times(1)).isCpfValido("cpf", "12345678901");
        verify(chavePixRepository, never()).save(any(ChavePix.class));
    }

    @Test
    void recuperarPorData() {
        LocalDate data = LocalDate.of(2023, 7, 17);
        LocalDateTime dataInicio = data.atTime(LocalTime.MIN);
        LocalDateTime dataFim = data.atTime(LocalTime.MAX);

        // Mock do repositório retornando uma lista de chaves para a data especificada
        List<ChavePix> chaves = new ArrayList<>();
        chaves.add(new ChavePix());
        chaves.add(new ChavePix());
        when(chavePixRepository.findByDataEhoraBetween(dataInicio, dataFim)).thenReturn(chaves);

        // Executa o método a ser testado
        List<ChavePix> resultado = chavePixService.recuperarPorData(data);

        // Verifica se o método findByDataEhoraBetween() foi chamado
        verify(chavePixRepository, times(1)).findByDataEhoraBetween(dataInicio, dataFim);

        // Verifica se o resultado é igual à lista de chaves retornada pelo mock
        assertEquals(chaves, resultado);

    }

    @Test
    void recuperarPornome() {
        String nomeCliente = "Flavio";

        // Mock do repositório retornando uma lista de chaves para o nome do cliente especificado
        List<ChavePix> chaves = new ArrayList<>();
        chaves.add(new ChavePix());
        chaves.add(new ChavePix());
        when(chavePixRepository.findByClienteNomeCorrentistaContainingIgnoreCase(nomeCliente)).thenReturn(chaves);

        // Executa o método a ser testado
        List<ChavePix> resultado = chavePixService.recuperarPornome(nomeCliente);

        // Verifica se o método findByClienteNomeCorrentistaContainingIgnoreCase() foi chamado
        verify(chavePixRepository, times(1)).findByClienteNomeCorrentistaContainingIgnoreCase(nomeCliente);

        // Verifica se o resultado é igual à lista de chaves retornada pelo mock
        assertEquals(chaves, resultado);
    }

    @Test
    public void recuperarPornome_ClienteSemChavesCadastradas_IllegalArgumentExceptionLancada() {
        String nomeCliente = "João";

        // Mock do repositório retornando uma lista vazia para o nome do cliente especificado
        when(chavePixRepository.findByClienteNomeCorrentistaContainingIgnoreCase(nomeCliente)).thenReturn(new ArrayList<>());

        // Executa o método a ser testado e verifica se é lançada uma exceção
        assertThrows(IllegalArgumentException.class, () -> chavePixService.recuperarPornome(nomeCliente));

        // Verifica se o método findByClienteNomeCorrentistaContainingIgnoreCase() foi chamado
        verify(chavePixRepository, times(1)).findByClienteNomeCorrentistaContainingIgnoreCase(nomeCliente);
    }
    @Test
    public void recupeConta_ClienteComChavesCadastradas_ListaRetornada() {
        String numeroConta = "3075";

        // Mock do repositório retornando uma lista de chaves para o número da conta especificado
        List<ChavePix> chaves = new ArrayList<>();
        chaves.add(new ChavePix());
        chaves.add(new ChavePix());
        when(chavePixRepository.findByClienteNumeroConta(numeroConta)).thenReturn(chaves);

        // Executa o método a ser testado
        List<ChavePix> resultado = chavePixService.recupeConta(numeroConta);

        // Verifica se o método findByClienteNumeroConta() foi chamado
        verify(chavePixRepository, times(1)).findByClienteNumeroConta(numeroConta);

        // Verifica se o resultado é igual à lista de chaves retornada pelo mock
        assertEquals(chaves, resultado);
    }

    @Test
    public void recupeConta_ClienteSemChavesCadastradas_IllegalArgumentExceptionLancada() {
        String numeroConta = "1234";

        // Mock do repositório retornando uma lista vazia para o número da conta especificado
        when(chavePixRepository.findByClienteNumeroConta(numeroConta)).thenReturn(new ArrayList<>());

        // Executa o método a ser testado e verifica se é lançada uma exceção
        assertThrows(IllegalArgumentException.class, () -> chavePixService.recupeConta(numeroConta));

        // Verifica se o método findByClienteNumeroConta() foi chamado
        verify(chavePixRepository, times(1)).findByClienteNumeroConta(numeroConta);
    }

}
