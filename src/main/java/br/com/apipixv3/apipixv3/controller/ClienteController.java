package br.com.apipixv3.apipixv3.controller;


import br.com.apipixv3.apipixv3.domain.Cliente;
import br.com.apipixv3.apipixv3.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    public ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.cadastrar(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Cliente>> recuperarTodosClientes() {
        List<Cliente> clientes = clienteService.recuperarTodos();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> recuperarClientePorId(@PathVariable("id") Long id) {
        Optional<Cliente> clienteOptional = clienteService.recuperarClientePorId(id);
        return clienteOptional.map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }




}
