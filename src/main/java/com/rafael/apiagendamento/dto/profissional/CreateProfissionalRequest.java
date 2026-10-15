package com.rafael.apiagendamento.dto.profissional;

import com.rafael.apiagendamento.dto.users.CreateUserRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateProfissionalRequest(
        @NotBlank(message = "Campo nome Obrigatório!")
        @Size(max = 255)
        String nome,
        @NotBlank(message = "Campo especialidade Obrigatório!")
        @Size(max = 255)
        String especialidade,
        @NotNull(message = "email e senha é obrigatório")
        @Valid
        CreateUserRequest createUserRequest
) {
}
