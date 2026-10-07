package com.rafael.apiagendamento.dto.profissional;

import com.rafael.apiagendamento.dto.users.CreateUserRequest;
import com.rafael.apiagendamento.dto.users.UpdateUserRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateProfissionalRequest(
        @NotNull(message = "É necessário o id para atualizar." )
        UUID id,
        @NotBlank(message = "Campo nome Obrigatório!")
        String nome,
        @NotBlank(message = "Campo especialidade Obrigatório!")
        String especialidade,
        @NotNull(message = "email e senha é obrigatório.")
        UpdateUserRequest updateUserRequest
) {
}
