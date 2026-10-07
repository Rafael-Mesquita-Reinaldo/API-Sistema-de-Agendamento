package com.rafael.apiagendamento.dto.users;

import com.rafael.apiagendamento.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateUserRequest(
        @NotNull(message = "Campo id para atualizar é obrigatório!")
        UUID id,
        @NotBlank(message = "Campo email é obrigatório!")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "Campo email é obrigatório!")
        String senha,
        @NotNull(message = "Campo de tipo de usuário é obrigatório!")
        Role role
) {
}
