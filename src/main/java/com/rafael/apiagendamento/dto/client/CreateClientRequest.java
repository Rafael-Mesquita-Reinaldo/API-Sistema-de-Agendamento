package com.rafael.apiagendamento.dto.client;

import com.rafael.apiagendamento.dto.users.CreateUserRequest;
import com.rafael.apiagendamento.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateClientRequest(
        @Size(max = 255)
        @NotBlank(message = "Campo nome Obrigatório!")
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{10,11}")
        String telefone,
        @NotNull(message = "Email e Senha é obrigatório")
        @Valid
        CreateUserRequest createUserRequest
) {
}
