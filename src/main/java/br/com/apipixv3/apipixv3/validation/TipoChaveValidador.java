package br.com.apipixv3.apipixv3.validation;

import br.com.apipixv3.apipixv3.service.ChaveServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoChaveValidador {

    @Autowired
    ChaveServiceImplement serviceImplement;

    public void validarChave(String tipo, String valorChave) {
        if ("cpf".equalsIgnoreCase(tipo)) {
            if (!serviceImplement.isCpfValido(tipo, valorChave)) {
                throw new IllegalArgumentException("CPF inválido. Verifique os campos.\nchave: " + valorChave);
            }
        } else if ("email".equalsIgnoreCase(tipo)) {
            if (!serviceImplement.isEmailValido(tipo, valorChave)) {
                throw new IllegalArgumentException("Email inválido. Verifique os campos.\nchave: " + valorChave);
            }
        } else if ("cnpj".equalsIgnoreCase(tipo)) {
            if (!serviceImplement.isCnpjValido(valorChave, tipo)) {
                throw new IllegalArgumentException("CNPJ inválido. Verifique os campos.\nchave: " + valorChave);
            }
        } else if ("celular".equalsIgnoreCase(tipo)) {
            if (!serviceImplement.isCelularValido(valorChave)) {
                throw new IllegalArgumentException("Celular inválido. Verifique os campos.\nchave: " + valorChave);
            }
        } else if ("aleatoria".equalsIgnoreCase(tipo)) {
            if (!serviceImplement.isChaveAleatoriaValido(valorChave)) {
                throw new IllegalArgumentException("Chave Aleatória inválida. Verifique os campos.\nchave: " + valorChave);
            }
        } else {
            throw new IllegalArgumentException("Tipo de chave inválido. Verifique os campos.");
        }
    }
}
