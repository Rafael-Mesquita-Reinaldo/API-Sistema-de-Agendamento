package com.rafael.apiagendamento.repository;

import com.rafael.apiagendamento.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {
}
