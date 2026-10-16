package com.rafael.apiagendamento.dto.client;

import com.rafael.apiagendamento.dto.users.CreateUserRequest;
import com.rafael.apiagendamento.dto.users.UpdateUserRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record UpdateClientRequest(
        @NotBlank(message = "Campo nome não pode está em branco!")
        String nome,
        @NotBlank(message = "Campo nome não pode está em branco!")
        @Pattern(regexp = "\\\\d{10,11}",message = "Número inválido.")
        String telefone
) {
}
