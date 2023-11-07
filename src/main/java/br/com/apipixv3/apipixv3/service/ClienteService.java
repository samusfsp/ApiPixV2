package br.com.apipixv3.apipixv3.service;


import br.com.apipixv3.apipixv3.domain.Cliente;
import br.com.apipixv3.apipixv3.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ClienteServiceImpl serviceImp;


    public Cliente cadastrar(Cliente cliente) {
        serviceImp.tipoContaValido(cliente.getTipoConta());
        serviceImp.numeroAgencia(cliente.getNumeroAgencia());
        serviceImp.numeroConta(cliente.getNumeroConta());
        serviceImp.nomeCorrentista(cliente.getNomeCorrentista());
        serviceImp.tipoPessoa(cliente.getTipoPessoa());
        return repository.save(cliente);
    }

    public List<Cliente> recuperarTodos() {
        List<Cliente> clientes = repository.findAll();
        serviceImp.recuperarTodos(clientes);
        return clientes;
    }

    public Optional<Cliente> recuperarClientePorId(Long id) {
        Optional<Cliente> clienteOptional = repository.findById(id);
        if (clienteOptional.isEmpty()) {
            throw new IllegalArgumentException("Cliente Não encontrado");
        }
        return clienteOptional;
    }


}
