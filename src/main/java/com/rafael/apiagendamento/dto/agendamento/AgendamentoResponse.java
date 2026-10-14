package com.rafael.apiagendamento.dto.agendamento;

import com.rafael.apiagendamento.dto.client.ClientResponse;
import com.rafael.apiagendamento.dto.profissional.ProfissionalResponse;
import com.rafael.apiagendamento.dto.servico.ServicoResponse;
import com.rafael.apiagendamento.model.Profissional;
import com.rafael.apiagendamento.model.Status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record AgendamentoResponse(
        UUID id,
        ClientResponse cliente,
        ProfissionalResponse profissional,
        ServicoResponse servico,
        LocalDate data,
        LocalTime horaInicio,
        LocalTime horaFim,
        Status status

) {
}
