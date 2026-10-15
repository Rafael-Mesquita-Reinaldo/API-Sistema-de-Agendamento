package com.rafael.apiagendamento.dto.servico;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateServicoRequest(
        @Size(max = 255)
        String descricao,
        @Positive(message = "Não é aceito número negativo")
        Integer duracaoMinutos,
        @DecimalMin(value = "0.00", message = "O preço não pode ser negativo.")
        @Digits(integer = 8, fraction = 2, message = "O preço deve ter até 8 dígitos inteiros e 2 casas decimais.")
        BigDecimal preco
) {
}
