package br.com.apipixv3.apipixv3.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;

@Service
public class ClienteServiceImpl {

    public boolean tipoContaValido(String tipoConta) {
        if (tipoConta.equalsIgnoreCase("corrente") ||
                (tipoConta.equalsIgnoreCase("poupança"))) {
            return true;
        }
        throw new IllegalArgumentException("O tipo de conta deve ser corrente ou poupança");
    }

    public boolean numeroAgencia(String numeroAgencia) {
        if (numeroAgencia.matches("\\d{4}")) {
            return true;
        }
        throw new IllegalArgumentException
                ("message = \"O número da agencia deve conter exatamente 4 dígitos numéricos");
    }

    public boolean numeroConta(String numeroConta) {
        if (numeroConta.matches("\\d{6,8}")) {
            return true;
        }
        throw new IllegalArgumentException
                ("message = \"O número da conta deve conter ntre 6 e 8 dígitos numéricos");
    }

    public boolean nomeCorrentista(String nomeCorrentista) {
        if (!nomeCorrentista.isEmpty()) {
            return true;
        }
        throw new IllegalArgumentException
                ("O none é obrigatorio");
    }

    public boolean tipoPessoa(String tipoPessoa) {
        if (tipoPessoa.equalsIgnoreCase("fisica") ||
                (tipoPessoa.equalsIgnoreCase("juridica"))) {
            return true;
        }
        throw new IllegalArgumentException
                ("Deve obrigatoriamente ser Fisica ou Juridica");
    }

    public List<?> recuperarTodos(List<?> lista) {
        if (lista.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        return lista;
    }



    }



