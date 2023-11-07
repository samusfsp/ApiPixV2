package br.com.apipixv3.apipixv3.dto.response;

import br.com.apipixv3.apipixv3.domain.Cliente;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ChavePixDto {
    private UUID id;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String tipo;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String valor;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String dataEhora;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private boolean ativo = true;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Cliente cliente;


}

