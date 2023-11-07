package br.com.apipixv3.apipixv3.repository;


import br.com.apipixv3.apipixv3.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
