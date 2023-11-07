package br.com.apipixv3.apipixv3.service;

import br.com.apipixv3.apipixv3.domain.ChavePix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChaveServiceImplementTest {


    @Test
    void isCpfValido() {

        String tipo = "cpf";
        String valor = "04937499696";
        ChaveServiceImplement serviceImplement = new ChaveServiceImplement();
        boolean cpf = serviceImplement.isCpfValido(tipo,valor);
        Assertions.assertEquals(true, cpf);

    }

    @Test
    void isEmailValido() {

        String tipo = "email";
        String valor = "euru@.com";
        ChaveServiceImplement serviceImplement = new ChaveServiceImplement();
        boolean email = serviceImplement.isEmailValido(tipo, valor);
        Assertions.assertEquals(true, email);

    }

    @Test
    void isCnpjValido() {

        String tipo = "cnpj";
        String valor = "68463273000106";
        ChaveServiceImplement serviceImplement = new ChaveServiceImplement();
        boolean cnpj = serviceImplement.isCnpjValido(valor,tipo);
        Assertions.assertEquals(true, cnpj);

    }

    @Test
    void isCelularValido() {
    }

    @Test
    void isChaveAleatoriaValido() {
    }

    @Test
    void desativarChave() {
        ChavePix chave = new ChavePix();
        chave.setAtivo(true);

        ChaveServiceImplement serviceImplement = new ChaveServiceImplement();
        serviceImplement.desativarChave(chave);

        assertFalse(chave.isAtivo());
    }

}