package br.com.apipixv3.apipixv3.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        // Configuração do Validator antes de cada teste
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void setId() {
        Cliente cliente = new Cliente();

        // Tenta configurar o ID como null, o que viola a anotação @Id
        cliente.setId(null);

        // Validação das restrições
        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("must not be null", violations.iterator().next().getMessage());
    }

    @Test
    void setTipoConta() {
        Cliente cliente = new Cliente();

        // Tenta configurar o tipoConta com um valor inválido, o que viola a anotação @Pattern
        cliente.setTipoConta("invalido");

        // Validação das restrições
        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("O tipo de conta deve ser 'corrente' ou 'poupança'", violations.iterator().next().getMessage());
    }

    @Test
    void setNumeroAgencia() {
        Cliente cliente = new Cliente();

        // Tenta configurar o numeroAgencia com um valor inválido, o que viola a anotação @Pattern
        cliente.setNumeroAgencia("123");

        // Validação das restrições
        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("O número da agência deve conter exatamente 4 dígitos", violations.iterator().next().getMessage());
    }

    @Test
    void setNumeroConta() {
        Cliente cliente = new Cliente();

        // Tenta configurar o numeroConta com um valor inválido, o que viola a anotação @Pattern
        cliente.setNumeroConta("12345");

        // Validação das restrições
        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("O número da conta deve conter entre 6 e 8 dígitos numéricos", violations.iterator().next().getMessage());
    }

    @Test
    void setNomeCorrentista() {
        Cliente cliente = new Cliente();

        // Tenta configurar o nomeCorrentista como vazio, o que viola a anotação @NotEmpty
        cliente.setNomeCorrentista("");

        // Validação das restrições
        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("O nome é obrigatório", violations.iterator().next().getMessage());
    }

    @Test
    void setTipoPessoa() {
        Cliente cliente = new Cliente();

        // Tenta configurar o tipoPessoa com um valor inválido, o que viola a anotação @Pattern
        cliente.setTipoPessoa("invalido");

        // Validação das restrições
        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("O tipo de pessoa deve ser 'fisica' ou 'juridica'", violations.iterator().next().getMessage());
    }

    // O teste para o setter setChavesPix depende do contexto em que essa lista será utilizada.
    // Pode ser adicionada uma validação específica aqui ou em outros casos de teste relacionados.
}
