package com.rafael.apiagendamento.dto.users;

import com.rafael.apiagendamento.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdminCreateUserRequest(
        @NotBlank(message = "Campo email é obrigatório!")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "Campo email é obrigatório!")
        String senha,
        @NotNull(message = "A role é necessaria para criar usuario")
        Role role

) {
}
