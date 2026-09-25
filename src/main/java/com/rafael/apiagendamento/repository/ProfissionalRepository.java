package com.rafael.apiagendamento.repository;

import com.rafael.apiagendamento.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfissionalRepository extends JpaRepository<Profissional, UUID> {
}
