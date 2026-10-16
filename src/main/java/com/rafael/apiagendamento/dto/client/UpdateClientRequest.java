package com.rafael.apiagendamento.dto.client;

import com.rafael.apiagendamento.dto.users.CreateUserRequest;
import com.rafael.apiagendamento.dto.users.UpdateUserRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UpdateClientRequest(
        @Pattern(regexp = ".*\\S.*")
        @Size(max = 255)
        String nome,
        @Pattern(regexp = "\\d{10,11}",message = "Número inválido.")
        String telefone
) {
}
