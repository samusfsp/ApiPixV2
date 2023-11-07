package br.com.apipixv3.apipixv3.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
//    @Pattern(regexp = "^(corrente|poupança)$", message = "O tipo de conta deve ser 'corrente' ou 'poupança'")
    private String tipoConta;



    @Column(nullable = false)
//    @Pattern(regexp = "\\d{4}", message = "O número da agência deve conter exatamente 4 dígitos")
    private String numeroAgencia;


    @Column(nullable = false)
//    @Pattern(regexp = "\\d{6,8}", message = "O número da conta deve conter entre 6 e 8 dígitos numéricos")
    private String numeroConta;


//    @NotEmpty(message = "O nome é obrigatório")
//    @Column(nullable = false)
    private String nomeCorrentista;

//    @Column(nullable = false)
//    @Pattern(regexp = "^(fisica|juridica)$", message = "O tipo de pessoa deve ser 'fisica' ou 'juridica'")
    private String tipoPessoa;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<ChavePix> chavesPix;
}
