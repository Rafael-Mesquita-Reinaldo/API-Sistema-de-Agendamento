package com.rafael.apiagendamento.service;

import com.rafael.apiagendamento.dto.client.ClientResponse;
import com.rafael.apiagendamento.dto.client.CreateClientRequest;
import com.rafael.apiagendamento.dto.users.CreateUserRequest;
import com.rafael.apiagendamento.mappers.ClientMapper;
import com.rafael.apiagendamento.model.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientServiceTest {
    @Autowired
    private ClientService clientService;

    @Test
    public void create(){
        CreateUserRequest createUserRequest = new CreateUserRequest("lucas@gmail.com","12", Role.CLIENTE);
        CreateClientRequest createClientRequest = new CreateClientRequest("lucas","",createUserRequest);
        ClientResponse clientResponse = clientService.create(createClientRequest);
        System.out.println(clientResponse);
    }
}
