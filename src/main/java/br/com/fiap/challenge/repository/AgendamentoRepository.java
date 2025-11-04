package br.com.fiap.challenge.repository;

import br.com.fiap.challenge.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
