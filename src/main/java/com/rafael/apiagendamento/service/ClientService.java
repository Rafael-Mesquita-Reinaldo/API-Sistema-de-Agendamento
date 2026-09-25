package com.rafael.apiagendamento.service;

import com.rafael.apiagendamento.dto.client.ClientResponse;
import com.rafael.apiagendamento.dto.client.CreateClientRequest;
import com.rafael.apiagendamento.dto.client.UpdateClientRequest;
import com.rafael.apiagendamento.mappers.ClientMapper;
import com.rafael.apiagendamento.model.Client;
import com.rafael.apiagendamento.repository.ClientRepository;
import com.rafael.apiagendamento.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final UserRepository userRepository;

    public ClientResponse create(CreateClientRequest createClientRequest){

        Client client = clientMapper.toEntityCreate(createClientRequest);
        client.setUsuario(userRepository.save(client.getUsuario()));
        ClientResponse clientResponse = clientMapper.toResponse(clientRepository.save(client));
        return clientResponse;
    }

    public ClientResponse searchById(String id){
        UUID uuid = UUID.fromString(id);
        Optional<Client> optionalClient = clientRepository.findById(uuid);
        if (!optionalClient.isPresent()){
            throw new RuntimeException("Cliente não encontrado");
        }
        Client client = optionalClient.get();
        return clientMapper.toResponse(client);
    }

    public ClientResponse update(UpdateClientRequest updateClientRequest){
        if(updateClientRequest.id()==null){
            throw new IllegalArgumentException("Para atualizar, é necessário do ID!");
        }
        if (!clientRepository.existsById(updateClientRequest.id())){
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        Client client = clientMapper.toEntityUpdate(updateClientRequest);
        return clientMapper.toResponse(clientRepository.save(client));

    }
    
}
