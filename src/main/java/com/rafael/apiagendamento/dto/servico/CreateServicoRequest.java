package com.rafael.apiagendamento.dto.servico;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.Duration;

public record CreateServicoRequest(
        @NotBlank(message = "A descrição do serviço é obrigatória.")
        String descricao,
        @NotNull(message = "O campo de duração do serviço não pode ser nulo.")
        @Positive(message = "Não é aceito número negativo")
        Integer duracaoMinutos,
        @NotNull(message = "O preço do serviço é obrigatório.")
        @DecimalMin(value = "0.00", message = "O preço não pode ser negativo.")
        @Digits(integer = 6, fraction = 2, message = "O preço deve ter até 6 dígitos inteiros e 2 casas decimais.")
        BigDecimal preco
) {
}
