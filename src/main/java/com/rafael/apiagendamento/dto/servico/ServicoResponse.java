package com.rafael.apiagendamento.dto.servico;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

public record ServicoResponse(
        UUID id,
        String descricao,
        Integer duracaoMinutos,
        BigDecimal preco
) {
}
