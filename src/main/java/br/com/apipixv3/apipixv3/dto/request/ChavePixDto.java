package br.com.apipixv3.apipixv3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChavePixDto {

    private String msg = "Chave cadastrada";
    private UUID id;
    private String data;
}
