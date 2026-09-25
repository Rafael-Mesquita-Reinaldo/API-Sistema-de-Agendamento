package com.rafael.apiagendamento.dto.client;

import com.rafael.apiagendamento.dto.users.CreateUserRequest;
import com.rafael.apiagendamento.dto.users.UpdateUserRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateClientRequest(
        @NotNull
        UUID id,
        @NotBlank
        String nome,
        String telefone,
        @NotNull
        UpdateUserRequest updateUserRequest
) {
}
