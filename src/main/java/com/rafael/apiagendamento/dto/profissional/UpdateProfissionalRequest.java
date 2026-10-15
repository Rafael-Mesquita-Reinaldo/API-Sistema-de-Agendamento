package com.rafael.apiagendamento.dto.profissional;

import com.rafael.apiagendamento.dto.users.CreateUserRequest;
import com.rafael.apiagendamento.dto.users.UpdateUserRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UpdateProfissionalRequest(
        @Size(max = 255)
        @NotBlank(message = "Campo nome não pode está em branco")
        String nome,
        @Size(max = 255)
        @NotBlank(message = "Campo especialidade não pode está em branco!")
        String especialidade
) {
}
