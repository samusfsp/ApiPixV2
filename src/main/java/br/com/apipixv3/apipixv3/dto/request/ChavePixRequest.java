package br.com.apipixv3.apipixv3.dto.request;


import br.com.apipixv3.apipixv3.domain.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Service
public class ChavePixRequest {
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private UUID id;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String tipo;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String valor;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String dataEhora;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Cliente cliente;
    @JsonIgnore
    private boolean ativo = true;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String menssagem = "Chave cadastrada com sucesso";


}
