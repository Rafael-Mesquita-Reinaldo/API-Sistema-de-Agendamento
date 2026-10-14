package com.rafael.apiagendamento.dto.agendamento;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CreateAgendamentoRequest(
        @NotNull(message = "É necessário do id do cliente")
        UUID idClient,
        @NotNull(message = "É necessário do id do profissional")
        UUID idProfissional,
        @NotNull(message = "É necessário do id do serviço")
        UUID idServico,
        @FutureOrPresent(message = "A data não pode ser no passado")
        LocalDate data,
        @NotNull(message = "É necessário do horário para agendar")
        LocalTime horaInicio

) {
}
