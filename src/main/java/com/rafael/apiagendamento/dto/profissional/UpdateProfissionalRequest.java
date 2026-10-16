package com.rafael.apiagendamento.dto.profissional;

import com.rafael.apiagendamento.dto.users.CreateUserRequest;
import com.rafael.apiagendamento.dto.users.UpdateUserRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UpdateProfissionalRequest(
        @Size(max = 255)
        @Pattern(regexp = ".*\\S.*")
        String nome,
        @Size(max = 255)
        @Pattern(regexp = ".*\\S.*")
        String especialidade
) {
}
