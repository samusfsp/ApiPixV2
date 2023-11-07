package br.com.apipixv3.apipixv3.validation;


import br.com.apipixv3.apipixv3.domain.ChavePix;
import br.com.apipixv3.apipixv3.dto.request.chavePixRequestConverter;
import br.com.apipixv3.apipixv3.repository.ChavePixRepository;
import br.com.apipixv3.apipixv3.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LimiteChaveValidation {

    @Autowired
    private ChavePixRepository repository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private chavePixRequestConverter converter;

    private static final int MAXIMO_FISICA = 5;
    private static final int MAXIMO_JURIDICA = 20;

    public void limiteChaveValidation(ChavePix chavePix) {

        String tipo = chavePix.getCliente().getTipoPessoa();
        Long idCliente = chavePix.getCliente().getId();

        List<ChavePix> chavesCliente = repository.findByClienteId(idCliente);

        if (clienteRepository.findById(idCliente).isEmpty()) {
            throw new IllegalArgumentException("Cliente não cadastrado.");
        }

        if (tipo.equalsIgnoreCase("fisica") && chavesCliente.size() >= MAXIMO_FISICA) {
            throw new IllegalArgumentException("Limite máximo de cadastros atingido para o cliente.");
        }

        if (tipo.equalsIgnoreCase("juridica") && chavesCliente.size() >= MAXIMO_JURIDICA) {
            throw new IllegalArgumentException("Limite máximo de cadastros atingido para o cliente.");
        }

        if (!tipo.equalsIgnoreCase("fisica") && !tipo.equalsIgnoreCase("juridica")) {
            throw new IllegalArgumentException("Tipo de pessoa inválido.");
        }
    }
}
