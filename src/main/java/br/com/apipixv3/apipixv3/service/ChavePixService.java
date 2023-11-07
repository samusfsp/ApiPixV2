package br.com.apipixv3.apipixv3.service;

import br.com.apipixv3.apipixv3.dto.request.chavePixRequestConverter;
import br.com.apipixv3.apipixv3.domain.ChavePix;
import br.com.apipixv3.apipixv3.repository.ChavePixRepository;
import br.com.apipixv3.apipixv3.repository.ClienteRepository;
import br.com.apipixv3.apipixv3.validation.LimiteChaveValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChavePixService {

    @Autowired
    ChavePixRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    LimiteChaveValidation limiteChaveValidation;
    @Autowired
    private ChaveServiceImplement serviceImplement;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    chavePixRequestConverter converter;


    public ChavePix cadastrar(ChavePix chavePix) {


        limiteChaveValidation.limiteChaveValidation(chavePix);

        String tipoChave = chavePix.getTipo();
        String valorChave = chavePix.getValor();

//        // Chave duplicada
//        Optional<ChavePix> existingChavePix = repository.findByValor(valorChave);
//        if (existingChavePix.isPresent()) {
//            throw new IllegalArgumentException("Chave duplicada. A chave informada já está cadastrada.");
//        }


        if ("cpf".equalsIgnoreCase(tipoChave)) {
            if (!serviceImplement.isCpfValido(tipoChave, valorChave)) {
                throw new IllegalArgumentException("CPF inválido. Verifique os campos.\nchave: " + valorChave);
            }
        } else if ("email".equalsIgnoreCase(tipoChave)) {
            if (!serviceImplement.isEmailValido(tipoChave, valorChave)) {
                throw new IllegalArgumentException("Email inválido. Verifique os campos.\nchave: " + valorChave);
            }
        } else if ("cnpj".equalsIgnoreCase(tipoChave)) {
            if (!serviceImplement.isCnpjValido(valorChave, tipoChave)) {
                throw new IllegalArgumentException("CNPJ inválido. Verifique os campos.\nchave: " + valorChave);
            }
        } else if ("celular".equalsIgnoreCase(tipoChave)) {
            if (!serviceImplement.isCelularValido(valorChave)) {
                throw new IllegalArgumentException("Celular inválido. Verifique os campos.\nchave: " + valorChave);
            }
        } else if ("aleatoria".equalsIgnoreCase(tipoChave)) {
            if (!serviceImplement.isChaveAleatoriaValido(valorChave)) {
                throw new IllegalArgumentException("Chave Aleatória inválida. Verifique os campos.\nchave: " + valorChave);
            }
        } else {
            throw new IllegalArgumentException("Tipo de chave inválido. Verifique os campos.");
        }


        ChavePix save = repository.save(chavePix);

        return save;
    }

    public List<ChavePix> recuperarTodos() {
        List<ChavePix> lista = repository.findAll();
        if (lista.isEmpty()) {
            throw new IllegalArgumentException("Chave não encontrada.");
        }

        return lista;
    }

    public Optional<ChavePix> recuperarChavePorId(UUID id) {
        Optional<ChavePix> chavePixOptional = repository.findById(id);

        if (chavePixOptional.isEmpty()) {
            throw new IllegalArgumentException("Chave não encontrada.");
        }
        return chavePixOptional;
    }

    public List<ChavePix> recuperarChavesPorIdCliente(Long idCliente) {
        List<ChavePix> chavesCliente = repository.findByClienteId(idCliente);

        if (chavesCliente.isEmpty()) {
            throw new IllegalArgumentException("Cliente não possui chaves cadastradas.");
        }
        return chavesCliente;
    }

    public ChavePix atualizar(ChavePix chavePix) {
//        Long idCliente = chavePix.getCliente().getId();

//        List<ChavePix> chavesCliente = repository.findByClienteId(idCliente);

        String tipoChave = chavePix.getTipo();
        String valorChave = chavePix.getValor();

        if ("cpf".equalsIgnoreCase(tipoChave)) {
            if (!serviceImplement.isCpfValido(tipoChave, valorChave)) {
                throw new IllegalArgumentException("CPF inválido. Verifique os campos.");
            }
        } else if ("email".equalsIgnoreCase(tipoChave)) {
            if (!serviceImplement.isEmailValido(tipoChave, valorChave)) {
                throw new IllegalArgumentException("Email inválido. Verifique os campos.");
            }
        } else if ("cnpj".equalsIgnoreCase(tipoChave)) {
            if (!serviceImplement.isCnpjValido(tipoChave,valorChave )) {
                throw new IllegalArgumentException("CNPJ inválido. Verifique os campos.");
            }
        } else if ("celular".equalsIgnoreCase(tipoChave)) {
            if (!serviceImplement.isCelularValido(valorChave)) {
                throw new IllegalArgumentException("Celular inválido. Verifique os campos.");
            }
        } else if ("aleatoria".equalsIgnoreCase(tipoChave)) {
            if (!serviceImplement.isChaveAleatoriaValido(valorChave)) {
                throw new IllegalArgumentException("Chave Aleatória inválida. Verifique os campos.");
            }
        } else {
            throw new IllegalArgumentException("Tipo de chave inválido. Verifique os campos.");
        }

        return repository.save(chavePix);
    }

    public List<ChavePix> recuperarPorData(LocalDate data) {
        LocalDateTime dataInicio = data.atTime(LocalTime.MIN);
        LocalDateTime dataFim = data.atTime(LocalTime.MAX);
        return repository.findByDataEhoraBetween(dataInicio, dataFim);
    }

    public List<ChavePix> recuperarPornome(String none) {
        List<ChavePix> chavesCliente = repository.findByClienteNomeCorrentistaContainingIgnoreCase(none);

        if (chavesCliente.isEmpty()) {
            throw new IllegalArgumentException("Cliente não possui chaves cadastradas.");
        }
        return chavesCliente;
    }


    public List<ChavePix> recupeConta(String conta) {
        List<ChavePix> chavesCliente = repository.findByClienteNumeroConta(conta);

        if (chavesCliente.isEmpty()) {
            throw new IllegalArgumentException("Cliente não possui chaves cadastradas.");
        }
        return chavesCliente;


    }
}
