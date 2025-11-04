package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
}
