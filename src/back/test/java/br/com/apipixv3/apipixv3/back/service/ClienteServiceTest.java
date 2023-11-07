package br.com.apipixv3.apipixv3.service;

import br.com.apipixv3.apipixv3.domain.Cliente;
import br.com.apipixv3.apipixv3.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setTipoConta("corrente");
        cliente.setNumeroAgencia("3075");
        cliente.setNumeroConta("088421");
        cliente.setNomeCorrentista("Flavio silva");
        cliente.setTipoPessoa("fisica");
        cliente.setChavesPix(new ArrayList<>());
    }

    @Test
    void cadastrar() {
        when(repository.save(cliente)).thenReturn(cliente);
        Cliente clienteCadastrado = service.cadastrar(cliente);
        assertEquals(cliente, clienteCadastrado);
        verify(repository, times(1)).save(cliente);
    }

    @Test
    void recuperarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente());
        when(repository.findAll()).thenReturn(clientes);
        List<Cliente> clientesRecuperados = service.recuperarTodos();
        assertEquals(clientes, clientesRecuperados);
        verify(repository, times(1)).findAll();
    }
    @Test
    void exeption_recuperar_Todos() {
        when(repository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(IllegalArgumentException.class,()
                -> service.recuperarTodos() );
        verify(repository, times(1)).findAll();
    }

    @Test
    void recuperarClientePorId() {
        Long clienteId = cliente.getId();
        when(repository.findById(clienteId)).thenReturn(Optional.of(cliente));
        Optional<Cliente> clienteRecuperado = service.recuperarClientePorId(clienteId);
        assertEquals(cliente.getId(), clienteRecuperado.get().getId());
        verify(repository, times(1)).findById(clienteId);
    }
    @Test
    void exeption_recuperar_ClientePorId() {
        Long clienteId = cliente.getId();
        when(repository.findById(clienteId)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class,()
                ->service.recuperarClientePorId(clienteId) );
        verify(repository, times(1)).findById(clienteId);
    }
}
