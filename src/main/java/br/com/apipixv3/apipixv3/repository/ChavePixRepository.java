package br.com.apipixv3.apipixv3.repository;


import br.com.apipixv3.apipixv3.domain.ChavePix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChavePixRepository extends JpaRepository<ChavePix, UUID> {

    List<ChavePix> findByClienteId(Long idCliente);

    List<ChavePix> findByDataEhoraBetween(LocalDateTime dataInicio, LocalDateTime dataFim);


    Optional<ChavePix> findByValor(String valorChave);

    List<ChavePix> findByClienteNomeCorrentistaContainingIgnoreCase(String none);

    List<ChavePix> findByClienteNumeroConta(String conta);

}
