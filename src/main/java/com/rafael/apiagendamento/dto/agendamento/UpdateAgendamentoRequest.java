package com.rafael.apiagendamento.dto.agendamento;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record UpdateAgendamentoRequest(
        UUID idClient,
        UUID idProfissional,
        UUID idServico,
        @FutureOrPresent(message = "A data não pode ser no passado")
        LocalDate data,
        LocalTime horaInicio
) {
}
