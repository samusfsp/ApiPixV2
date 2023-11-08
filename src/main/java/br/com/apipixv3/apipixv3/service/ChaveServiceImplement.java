package br.com.apipixv3.apipixv3.service;


import br.com.apipixv3.apipixv3.domain.ChavePix;
import org.springframework.stereotype.Service;

@Service
public class ChaveServiceImplement {





    public boolean isCpfValido(String tipo, String valor) {
        if (!"cpf".equalsIgnoreCase(tipo)){
            return false;
        }
        // Verifica se contém apenas números
        if (!valor.matches("[0-9]+")) {
            return false;
        }

        if (valor.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (valor.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula os dígitos verificadores
        int[] digitos = new int[11];
        for (int i = 0; i < 11; i++) {
            digitos[i] = Integer.parseInt(valor.substring(i, i + 1));
        }

        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += digitos[i] * (10 - i);
        }

        int resto1 = soma1 % 11;
        int digito1 = (resto1 < 2) ? 0 : 11 - resto1;

        if (digitos[9] != digito1) {
            return false;
        }

        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            soma2 += digitos[i] * (11 - i);
        }

        int resto2 = soma2 % 11;
        int digito2 = (resto2 < 2) ? 0 : 11 - resto2;

        if (digitos[10] != digito2) {
            return false;
        }

        return true;
    }

    public boolean isEmailValido(String tipo, String valor) {
        if (!"email".equalsIgnoreCase(tipo)){
            return false;
        }
        else if (valor != null && !valor.isEmpty()) {
             return  valor.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        }
        return true;
    }

    public boolean isCnpjValido(String valor, String tipo) {
        if (!"cnpj".equalsIgnoreCase(tipo)){
            return false;
        }
        if (!valor.matches("\\d{14}")) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (valor.matches("(\\d)\\1{13}")) {
            return false;
        }

        int[] digitos = new int[14];
        for (int i = 0; i < 14; i++) {
            digitos[i] = Integer.parseInt(valor.substring(i, i + 1));
        }

        // Cálculo do primeiro dígito verificador
        int soma = 0;
        int peso = 2;

        for (int i = 11; i >= 0; i--) {
            soma += digitos[i] * peso;
            peso++;
            if (peso > 9) {
                peso = 2;
            }
        }

        int resto = soma % 11;
        int digito1 = resto < 2 ? 0 : 11 - resto;

        if (digitos[12] != digito1) {
            return false;
        }

        // Cálculo do segundo dígito verificador
        soma = 0;
        peso = 2;

        for (int i = 12; i >= 0; i--) {
            soma += digitos[i] * peso;
            peso++;
            if (peso > 9) {
                peso = 2;
            }
        }

        resto = soma % 11;
        int digito2 = resto < 2 ? 0 : 11 - resto;

        return digitos[13] == digito2;
    }

    public boolean isCelularValido(String valor) {
        // Verifica se o celular possui exatamente 13 dígitos (código do país, DDD e número)
        if (valor.length() != 14) {
            return false;
        }

        // Verifica se o restante dos dígitos (DDD e número) é composto apenas por números
        String numeros = valor.substring(4);
        if (!numeros.matches("\\d+")) {
            return false;
        }


        return true;
    }

    public boolean isChaveAleatoriaValido(String valor) {
        if (valor.length() == 36) {
            return true;
        }
        if (valor.matches("[a-zA-Z0-9]+")) {
            return false;
        }

        return true;
    }

    public void desativarChave(ChavePix ativo) {
        ativo.setAtivo(false);
    }
}
