package com.rafael.apiagendamento.dto.client;

import com.rafael.apiagendamento.dto.users.CreateUserRequest;
import com.rafael.apiagendamento.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateClientRequest(
        @NotBlank
        String nome,
        String telefone,
        @NotNull
        CreateUserRequest createUserRequest
) {
}
