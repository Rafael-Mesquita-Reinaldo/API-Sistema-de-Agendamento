package com.rafael.apiagendamento.dto.profissional;

import com.rafael.apiagendamento.dto.users.CreateUserRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProfissionalRequest(
        @NotBlank(message = "Campo nome Obrigatório!")
        String nome,
        @NotBlank(message = "Campo especialidade Obrigatório!")
        String especialidade,
        @NotNull(message = "email e senha é obrigatório")
        CreateUserRequest createUserRequest
) {
}
