package com.rafael.apiagendamento.repository;

import com.rafael.apiagendamento.model.Agendamento;
import com.rafael.apiagendamento.model.Profissional;
import com.rafael.apiagendamento.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID>, JpaSpecificationExecutor<Agendamento> {

    List<Agendamento> findByProfissionalAndDataAndStatus(Profissional profissional, LocalDate data, Status status);

    List<Agendamento> findByProfissionalAndDataAndStatusAndIdNot(Profissional profissional, LocalDate data, Status status, UUID id);

}
