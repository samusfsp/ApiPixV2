package br.com.apipixv3.apipixv3.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
//@Validated
@Table(name = "ChavePix")

public class ChavePix {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Type(type = "uuid-char")
    private UUID id;


//    @Id
//    @Column(name = "id")
//    @Type(type  = "uuid-char")
//    private UUID id;

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private UUID id;

    @NotEmpty(message = "O tipo da chave Pix é obrigatório")
    private String tipo;

    @Column(nullable = false)
    @NotEmpty(message = "O valor da chave Pix é obrigatório")
    private String valor;


    private boolean ativo = true;


    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dataEhora = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;


}
