package com.rafael.apiagendamento.repository;

import com.rafael.apiagendamento.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicoRepository extends JpaRepository<Servico, UUID>{
}
