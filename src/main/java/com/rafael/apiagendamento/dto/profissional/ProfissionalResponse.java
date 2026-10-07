package com.rafael.apiagendamento.dto.profissional;

import com.rafael.apiagendamento.dto.users.UserResponse;

import java.util.UUID;

public record ProfissionalResponse(
        UUID id,
        String nome,
        String especialidade,
        UserResponse userResponse
) {
}
