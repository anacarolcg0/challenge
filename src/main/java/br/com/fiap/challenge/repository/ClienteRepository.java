package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
