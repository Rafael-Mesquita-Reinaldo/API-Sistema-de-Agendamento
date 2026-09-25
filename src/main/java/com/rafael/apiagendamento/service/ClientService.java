package com.rafael.apiagendamento.service;

import com.rafael.apiagendamento.dto.client.ClientResponse;
import com.rafael.apiagendamento.dto.client.CreateClientRequest;
import com.rafael.apiagendamento.mappers.ClientMapper;
import com.rafael.apiagendamento.model.Client;
import com.rafael.apiagendamento.repository.ClientRepository;
import com.rafael.apiagendamento.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final UserRepository userRepository;

    public ClientResponse create(CreateClientRequest createClientRequest){

        Client client = clientMapper.toEntityCreate(createClientRequest);
        client.setUsuario(userRepository.save(client.getUsuario()));
        ClientResponse clientResponse = clientMapper.toDto(clientRepository.save(client));
        return clientResponse;

    }
}
