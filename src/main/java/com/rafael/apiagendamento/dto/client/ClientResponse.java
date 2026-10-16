package com.rafael.apiagendamento.dto.client;

import com.rafael.apiagendamento.dto.users.UserResponse;
import com.rafael.apiagendamento.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ClientResponse(
        UUID id,
        String nome,
        String telefone,
        UserResponse userResponse
) {

}
